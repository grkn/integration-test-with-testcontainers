package com.example.testcontainers.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("hazelcast.config")
public class HazelcastConfig {

    private String instanceName;
    private String host;
    private Integer port;

    @Bean
    public Config clientConfig() {
        Config config = new Config();
        config.setInstanceName(instanceName);
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPublicAddress(host + ":" + port);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config conf) {
        return HazelcastInstanceFactory.getOrCreateHazelcastInstance(conf);
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
