package com.kafkaexample.publisher;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kafkaexample.avro.AvroStream;

@Component(value="avroPublisher")
public class AvroPublisher implements KafkaPublisher
{
	@Value("${topic.name}")
	private String topicName;
	
	@Value("${bootstrap.servers}")
	private String bootstrapServers;
	
	@Value("${serializer.class}")
	private String serializerClass;
	
	@Value("${key.serializer}")
	private String keySerializer;
	
	@Value("${value.serializer}")
	private String valueSerializer;
	
	@Autowired
	@Qualifier("nonGeneratedAvroSteam")
	private AvroStream avroStream;
	
	public void publish() {
		ByteArrayOutputStream streamedData = avroStream.generateAvroStream();
		KafkaProducer<String, byte[]> messageProducer = new KafkaProducer<String, byte[]>(getProps());
        ProducerRecord<String, byte[]> producerRecord = null;
        producerRecord = new ProducerRecord<String, byte[]>(topicName,streamedData.toByteArray());
        messageProducer.send(producerRecord);
        messageProducer.close();
	}
	
	private Properties getProps() {
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put("serializer.class", serializerClass);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return props;
	}
}

