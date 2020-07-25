package com.kafkaexample.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component(value="generatedAvroStream")
public class GeneratedAvroStream implements AvroStream
{
	public ByteArrayOutputStream generateAvroStream()
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Schema schema = getSchema();
		DataFileWriter<GenericRecord> dataFileWriter = getDataFileWriter(schema);
		Player_Avro player = getPlayer();
		try
		{
			dataFileWriter.create(schema, outputStream);
			dataFileWriter.append(player);
			dataFileWriter.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return outputStream;
	}

	private Player_Avro getPlayer()
	{
		Player_Avro player = new Player_Avro();
		player.setName("Virat");
		player.setAge(34);
		player.setHeight(5.10);
		player.setJerseyNumber(18);
		player.setSportName("Cricket");
		return player;
	}

	private DataFileWriter<GenericRecord> getDataFileWriter(Schema schema)
	{
		// Encode the data using JSON schema and embed the schema as metadata along with the data.
		DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(writer);
		return dataFileWriter;
	}

	@SuppressWarnings("deprecation")
	private Schema getSchema()
	{
		/*final String USER_SCHEMA = "{"
				+ "\"namespace\": \"com.avro\","
				+ "\"type\": \"record\","						
				+ "\"name\": \"Player_Avro\","
				+ "fields\": ["
				+ "{\"name\": \"sportName\", \"type\": \"string\"},"
				+ "{\"name\": \"jerseyNumber\", \"type\": \"int\"},"
				+ "{\"name\": \"name\", \"type\": \"string\"},"
				+ "{\"name\": \"age\", \"type\": \"int\"},"
				+ "{\"name\": \"height\",  \"type\": \"double\"}"
				+ "]}";*/
		// Creating schema
		String schemaDescription = Player_Avro.getClassSchema().toString();
		Schema schema = Schema.parse(schemaDescription);
		return schema;
	}
}
