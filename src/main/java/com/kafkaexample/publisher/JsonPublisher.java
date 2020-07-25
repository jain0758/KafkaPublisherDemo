package com.kafkaexample.publisher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaexample.config.MyKafkaJsonConfig;
import com.kafkaexample.dto.Player;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component(value = "jsonPublisher")
public class JsonPublisher implements PublisherInterface {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MyKafkaJsonConfig myKafkaConfig;

    public void publish() {
        Producer<Integer, JsonNode> producer = new KafkaProducer<>(getConfig());
        for (int i = 10; i < 15; i++) {
            JsonNode jsonNode = objectMapper.valueToTree(Player.getPlayer(i));
            ProducerRecord<Integer, JsonNode> record = new ProducerRecord<>(myKafkaConfig.getTopicName(), jsonNode);
            producer.send(record);
        }
        producer.close();
    }

    private Properties getConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, myKafkaConfig.getBootstrapServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, getClass().getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, myKafkaConfig.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, myKafkaConfig.getValueSerializer());
        return props;
    }
}
