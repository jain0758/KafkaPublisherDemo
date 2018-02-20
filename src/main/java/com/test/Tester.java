package com.test;

import org.apache.kafka.common.serialization.IntegerSerializer;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.publisher.MyKafkaPublisher;

public class Tester
{
	private final static String TOPIC = "anshumanTopic";
	private final static String BOOTSTRAP = "localhost:9092";
	private final static String KEY_SERIALIZER = IntegerSerializer.class.getName(); 
	private final static String VALUE_SERIALIZER = IntegerSerializer.class.getName();;//JsonSerializer.class.getName();
	
	
	public static void main(String[] args)
	{
		MyKafkaPublisher publisher = new MyKafkaPublisher(); 
		System.out.println("Publishing starts ...");
		publisher.publish(TOPIC, BOOTSTRAP, KEY_SERIALIZER, VALUE_SERIALIZER);
		System.out.println("Published.");
	}
}
