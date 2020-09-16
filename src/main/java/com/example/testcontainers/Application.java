package com.example.testcontainers;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {

    private static final String MAP_NAME = "testcontainers";
    private static final String HELLO_WORLD_KEY= "hello_world";

    private final HazelcastInstance hazelcastInstance;

    public Application(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostConstruct
    public void init() throws UnknownHostException {
        hazelcastInstance.getMap(MAP_NAME).put(HELLO_WORLD_KEY, "Hello World From Hazelcast");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
