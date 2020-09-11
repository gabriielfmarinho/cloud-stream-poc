package br.com.cloudstreampoc.integration.config.initializer;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class RabbitMQInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(final @NotNull ConfigurableApplicationContext configurableApplicationContext) {
        final RabbitMQContainer container = createRabbitMQContainer();
        applyProperties(configurableApplicationContext, container);
    }

    private void applyProperties(final ConfigurableApplicationContext configurableApplicationContext,
                                 final RabbitMQContainer container) {
        TestPropertyValues values = TestPropertyValues.of(
                "spring.rabbitmq.host=" + container.getContainerIpAddress(),
                "spring.rabbitmq.port=" + container.getMappedPort(5672)
        );

        values.applyTo(configurableApplicationContext);
    }

    private RabbitMQContainer createRabbitMQContainer() {
        try {
            final RabbitMQContainer container = new RabbitMQContainer()
                    .withExposedPorts(5672, 15672);
            container.start();
            container.waitingFor(Wait.forListeningPort());
            return container;
        } catch (Exception e) {
            throw new RuntimeException("Failed to up container");
        }
    }
}

