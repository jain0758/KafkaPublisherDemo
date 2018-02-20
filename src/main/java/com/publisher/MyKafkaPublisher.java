package com.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.dto.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.Utils;

public class MyKafkaPublisher implements Publisher<String, String, String, String>
{
	@Override
	public void publish(String topic, String bootstrap, String key, String value)
	{
		Producer<Integer, Integer> producer = new KafkaProducer<Integer, Integer>(getProperties(bootstrap, key, value));
		//ObjectMapper mapper = new ObjectMapper();
		for (int i = 10; i < 100; i++)
		{
			//JsonNode jsonNode = mapper.valueToTree(getPlayer(i));
			ProducerRecord<Integer, Integer> record = new ProducerRecord<Integer, Integer>(topic, i, i);
			producer.send(record);
		}
		producer.close();
	}

	private Properties getProperties(String bootstrap, String key, String value)
	{
		return Utils.getConnectionProperties(getClass().getName(), bootstrap, key, value);
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
