package br.com.cloudstreampoc.integration.config.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.Properties;

public class RabbitMQExtension implements BeforeAllCallback, AfterAllCallback {

    private RabbitMQContainer container;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        container = new RabbitMQContainer()
                .withExposedPorts(5672, 15672);
        container.start();
        container.waitingFor(Wait.forListeningPort());
        String properties = System.getProperty("spring.rabbitmq.port");
        System.clearProperty("spring.rabbitmq.port");
        System.setProperty("spring.rabbitmq.host", container.getContainerIpAddress());
        System.setProperty("spring.rabbitmq.port", String.valueOf(container.getMappedPort(5672)));
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
     //   container.stop();
    }
}
