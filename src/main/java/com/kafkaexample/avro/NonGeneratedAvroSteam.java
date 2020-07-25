package com.kafkaexample.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component(value = "nonGeneratedAvroSteam")
public class NonGeneratedAvroSteam implements AvroStream {
    public ByteArrayOutputStream generateAvroStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Schema schema = null;
        try {
            File file = ResourceUtils.getFile("classpath:player.avsc");
            schema = new Schema.Parser().parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GenericRecord user = getGenericRecord(schema);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        try {
            dataFileWriter.create(schema, outputStream);
            dataFileWriter.append(user);
            dataFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }

    private GenericRecord getGenericRecord(Schema schema) {
        GenericRecord user = new GenericData.Record(schema);
        user.put("sportName", "Cricket");
        user.put("jerseyNumber", 18);
        user.put("name", "Virat");
        user.put("age", 29);
        user.put("height", 5.9);
        return user;
    }
}
