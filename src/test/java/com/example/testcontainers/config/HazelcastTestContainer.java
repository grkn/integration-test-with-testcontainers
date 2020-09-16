package com.example.testcontainers.config;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategyTarget;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.time.Duration;
import java.util.List;

public class HazelcastTestContainer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        GenericContainer genericContainer = new GenericContainer("hazelcast/hazelcast:3.12.7");
        genericContainer.addExposedPort(34700);

        WaitAllStrategy waitAllStrategy = new WaitAllStrategy();
        waitAllStrategy.withStartupTimeout(Duration.ofMinutes(5));
        waitAllStrategy.waitUntilReady(getWaitStrategyTarget(genericContainer));
        genericContainer.waitingFor(waitAllStrategy);
        genericContainer.start();

        TestPropertyValues.of(
                "hazelcast.config.host=" + genericContainer.getContainerIpAddress(),
                "hazelcast.config.port=" + genericContainer.getMappedPort(34700)
        ).applyTo(configurableApplicationContext.getEnvironment());
    }

    @NotNull
    private static WaitStrategyTarget getWaitStrategyTarget(GenericContainer genericContainer) {
        return new WaitStrategyTarget() {
            @Override
            public List<Integer> getExposedPorts() {
                return ImmutableList.of(34700, 34701);
            }

            @Override
            public InspectContainerResponse getContainerInfo() {
                return genericContainer.getContainerInfo();
            }
        };
    }
}
