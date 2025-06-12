package org.manthan.kafkaconsumer.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.manthan.kafkaconsumer.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, MessageDTO> consumerFactory()
    {
        JsonDeserializer<MessageDTO> deserializer = new JsonDeserializer<>(MessageDTO.class);
//        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("org.manthan.kafkaproducer.dtos.MessageDTO");
//        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        // although we have mentioned Key and value deserializers in config,
        // they are over-ridden by the new StringDeserializer() and deserializer we passed to the DefaultKafkaConsumerFactory in return statement
        // Passing deserializers direclty in DefaultKafkaConsumerFactory allows us to configure them like we did for JsonDeserializer above
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // only for AWS
        if(activeProfile.equals("aws")){
            config.put("security.protocol", "SASL_SSL");
            config.put(SaslConfigs.SASL_MECHANISM, "AWS_MSK_IAM");
            config.put(SaslConfigs.SASL_JAAS_CONFIG, "software.amazon.msk.auth.iam.IAMLoginModule required awsDebugCreds=true;");
            config.put(SaslConfigs.SASL_CLIENT_CALLBACK_HANDLER_CLASS, "software.amazon.msk.auth.iam.IAMClientCallbackHandler");
        }

//        return new DefaultKafkaConsumerFactory<>(config); // will use deserializers from config

//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(MessageDTO.class));
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDTO> messageListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, MessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
