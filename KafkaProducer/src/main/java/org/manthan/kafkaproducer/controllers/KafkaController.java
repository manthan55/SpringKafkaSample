package org.manthan.kafkaproducer.controllers;

import org.manthan.kafkaproducer.dtos.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final String TOPIC = "mytopic";
    private KafkaTemplate<String, MessageDTO> kafkaTemplate;


    public KafkaController(KafkaTemplate<String, MessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody MessageDTO requestDTO){
        kafkaTemplate.send(TOPIC, requestDTO);
        return "Message sent";
    }
}
