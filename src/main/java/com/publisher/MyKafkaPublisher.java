package com.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;

import com.dto.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.Utils;

public class MyKafkaPublisher
{
	@Value("${topicName:anshumanTopic}")
	private String TOPIC;

	@Value("${bootstrapServer:localhost:9092}")
	private String BOOTSTRAP;

	@Value("${keySerializer:org.apache.kafka.common.serialization.ByteArraySerializer}")
	private String KEY_SERIALIZER;

	@Value("${valueSerializer:org.apache.kafka.connect.json.JsonSerializer}")
	private String VALUE_SERIALIZER;

	private ObjectMapper objectMapper = new ObjectMapper();

	public void publish()
	{
		Producer<Integer, JsonNode> producer = new KafkaProducer<Integer, JsonNode>(getConfig());
		for (int i = 10; i < 100; i++)
		{
			JsonNode jsonNode = objectMapper.valueToTree(getPlayer(i));
			ProducerRecord<Integer, JsonNode> record = new ProducerRecord<Integer, JsonNode>(TOPIC, jsonNode);
			System.out.println(" ########## Publishing Records ########## ");
			//producer.send(record);
		}
		producer.close();
	}

	private Properties getConfig() {
		return Utils.getConfig(getClass().getName(),BOOTSTRAP, KEY_SERIALIZER, VALUE_SERIALIZER);
	}
	
	private Player getPlayer(int jerseyNumber)
	{
		Player player = new Player();
		player.setAge(34);
		player.setHeight(5.10);
		player.setJerseyNumber(jerseyNumber);
		player.setSportName("Cricket");
		return player;
	}
}
