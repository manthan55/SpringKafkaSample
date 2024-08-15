package org.manthan.kafkaproducer.controllers;

import org.manthan.kafkaproducer.dtos.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> sendMessage(@RequestBody MessageDTO requestDTO){
        try{
            kafkaTemplate.send(TOPIC, requestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(requestDTO);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }
}
