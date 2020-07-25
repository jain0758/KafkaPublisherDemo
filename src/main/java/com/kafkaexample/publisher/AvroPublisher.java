package com.kafkaexample.publisher;

import com.kafkaexample.avro.AvroStream;
import com.kafkaexample.config.MyKafkaAvroConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Component(value = "avroPublisher")
public class AvroPublisher implements PublisherInterface {

    @Autowired
    private MyKafkaAvroConfig myKafkaConfig;

    @Autowired
    @Qualifier("nonGeneratedAvroSteam")
    private AvroStream avroStream;

    public void publish() {
        ByteArrayOutputStream streamedData = avroStream.generateAvroStream();
        KafkaProducer<String, byte[]> messageProducer = new KafkaProducer<>(getProps());
        ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<>(myKafkaConfig.getTopicName(),
                streamedData.toByteArray());
        messageProducer.send(producerRecord);
        messageProducer.close();
    }

    private Properties getProps() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, myKafkaConfig.getBootstrapServers());
        props.put("serializer.class", myKafkaConfig.getSerializerClass());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, myKafkaConfig.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, myKafkaConfig.getValueSerializer());
        return props;
    }
}

