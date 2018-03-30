package com.utils;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;

public class Utils
{	
	public static Properties getConfig(String clientId)
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, PublisherConstants.BOOTSTRAP);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, PublisherConstants.KEY_SERIALIZER);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PublisherConstants.VALUE_SERIALIZER);
		return props;
	}
}
