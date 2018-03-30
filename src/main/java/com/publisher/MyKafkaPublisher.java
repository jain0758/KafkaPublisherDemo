package com.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.dto.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.PublisherConstants;
import com.utils.Utils;

public class MyKafkaPublisher
{
	private ObjectMapper objectMapper = new ObjectMapper();

	public void publish()
	{
		Producer<Integer, JsonNode> producer = new KafkaProducer<Integer, JsonNode>(getConfig());
		for (int i = 10; i < 15; i++)
		{
			JsonNode jsonNode = objectMapper.valueToTree(Player.getPlayer(i));
			ProducerRecord<Integer, JsonNode> record = getRecord(jsonNode);
			producer.send(record);
		}
		producer.close();
	}

	private ProducerRecord<Integer, JsonNode> getRecord(JsonNode jsonNode)
	{
		return new ProducerRecord<Integer, JsonNode>(PublisherConstants.TOPIC, jsonNode);
	}

	private Properties getConfig()
	{
		return Utils.getConfig(getClass().getName());
	}
}
