package com.utils;

public class PublisherConstants
{
	public static String BOOTSTRAP = "localhost:9092";

	public static String KEY_SERIALIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";

	public static String VALUE_SERIALIZER = "org.apache.kafka.connect.json.JsonSerializer";
	
	public static String TOPIC = "anshumanTopic";
}
