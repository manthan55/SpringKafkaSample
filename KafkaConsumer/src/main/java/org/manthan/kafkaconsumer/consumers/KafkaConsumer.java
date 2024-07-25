package org.manthan.kafkaconsumer.consumers;

import org.manthan.kafkaconsumer.dtos.MessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final String TOPIC = "mytopic";
    private final String GROUPID = "mygroup";

    @KafkaListener(topics = TOPIC, groupId = GROUPID, containerFactory = "messageListener")
    public void handleNotification(MessageDTO message) {
        System.out.println("message: "+message.toString());
    }
}
