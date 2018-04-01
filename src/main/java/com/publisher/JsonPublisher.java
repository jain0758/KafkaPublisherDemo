package com.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.dto.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPublisher implements KafkaPublisher
{
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private final String TOPIC_NAME = "anshumanTopic";

	public void publish()
	{
		Producer<Integer, JsonNode> producer = new KafkaProducer<Integer, JsonNode>(getConfig());
		for (int i = 10; i < 15; i++)
		{
			JsonNode jsonNode = objectMapper.valueToTree(Player.getPlayer(i));
			ProducerRecord<Integer, JsonNode> record = new ProducerRecord<Integer, JsonNode>(TOPIC_NAME, jsonNode);;
			producer.send(record);
		}
		producer.close();
	}
	
	private Properties getConfig()
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, getClass().getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonSerializer");
		return props;
	}
}
