package com.utils;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;

public class Utils
{
	public static Properties getConnectionProperties(String clientId, String bootstrap, String key, String value)
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value);
		return props;
	}
}
