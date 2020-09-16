package com.example.testcontainers;

import com.example.testcontainers.config.HazelcastTestContainer;
import com.hazelcast.core.HazelcastInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = Application.class)
@ContextConfiguration(initializers = HazelcastTestContainer.class)
public class TestContainerIT {

    private static final String MAP_NAME = "testcontainers";
    private static final String HELLO_WORLD_KEY = "hello_world";
    private static final String RESULT_TXT = "Hello World From Hazelcast";


    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Test
    public void contextUp() {
        // Given

        // When
        String result = (String) hazelcastInstance.getMap(MAP_NAME).get(HELLO_WORLD_KEY);

        // Then
        Assertions.assertEquals(RESULT_TXT, result);
    }
}
