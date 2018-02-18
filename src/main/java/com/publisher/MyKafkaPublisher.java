package com.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyKafkaPublisher
{
	Producer<Long, String> producer;
	private final static String TOPIC = "anshumanTopic";
	private final static String BOOTSTRAP_SERVER = "localhost:9092";

	private void publishData()
	{
		producer = new KafkaProducer<Long, String>(getProperties());
		long currentTime = System.currentTimeMillis();
		for (long i = currentTime; i < currentTime + 100; i++) {
			producer.send(new ProducerRecord<Long, String>(TOPIC, i, " #### Dwivedi #### "));
		}
		producer.close();
	}

	private Properties getProperties()
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, getClass().getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return props;
	}
	
	public static void main(String[] args)
	{
		MyKafkaPublisher publisher = new MyKafkaPublisher();
		System.out.println("Publishing starts ...");
		publisher.publishData();
		System.out.println("Published.");
	}
}
