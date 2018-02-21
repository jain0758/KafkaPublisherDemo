package com.test;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.publisher.MyKafkaPublisher;

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
		MyKafkaPublisher publisher = new MyKafkaPublisher();
		System.out.println("Publishing starts ...");
		publisher.publish();
		System.out.println("Published.");
	}
}
