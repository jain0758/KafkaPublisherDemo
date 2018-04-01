package com.test;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.publisher.KafkaPublisher;
import com.publisher.JsonPublisher;

@SpringBootApplication
public class Tester implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(Tester.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		KafkaPublisher publisher = new JsonPublisher();
		//KafkaPublisher publisher = new AvroPublisher();
		System.out.println(" ########## Publishing Records ########## ");
		publisher.publish();
		System.out.println(" ########## Published Records ########## ");
	}
}
