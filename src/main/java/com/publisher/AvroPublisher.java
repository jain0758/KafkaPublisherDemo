package com.publisher;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.avro.NonGeneratedAvroSteam;

public class AvroPublisher implements KafkaPublisher
{
	private final String TOPIC_NAME = "anshumanTopic";
	
	public void publish() {
		//ByteArrayOutputStream streamedData = new GeneratedAvroStream().generateAvroStream();
		ByteArrayOutputStream streamedData = new NonGeneratedAvroSteam().generateAvroStream();
		KafkaProducer<String, byte[]> messageProducer = new KafkaProducer<String, byte[]>(getProps());
        ProducerRecord<String, byte[]> producerRecord = null;
        producerRecord = new ProducerRecord<String, byte[]>(TOPIC_NAME,streamedData.toByteArray());
        messageProducer.send(producerRecord);
        messageProducer.close();
	}
	
	private Properties getProps() {
		Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        return props;
	}
}

