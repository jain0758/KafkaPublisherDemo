package com.kafkaexample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafkaexample.publisher.PublisherInterface;

@SpringBootApplication(scanBasePackages = {"com.kafkaexample"})
public class KafkaPublisherApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("avroPublisher")
    private PublisherInterface publisher;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KafkaPublisherApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        System.out.println(" ########## Publishing Records ########## ");
        publisher.publish();
        System.out.println(" ########## Published Records ########## ");
    }
}
