package br.com.cloudstreampoc.integration.listener;

import br.com.cloudstreampoc.channel.EventChannel;
import br.com.cloudstreampoc.integration.config.annotation.IntegrationTest;
import br.com.cloudstreampoc.listener.EventListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@IntegrationTest
class EventListenerTest {

    private final EventChannel eventChannel;

    @SpyBean
    private EventListener listener;

    public EventListenerTest(final EventChannel eventChannel) {
        this.eventChannel = eventChannel;
    }

    @Test
    void shouldListenEventMessageOne() {
        eventChannel.outputEvent().send(message("event-one"));
        verify(listener, timeout(5000)).receiveEvent("event-one");
    }

    @Test
    void shouldListenEventMessageTwo() {
        eventChannel.outputEvent().send(message("event-two"));
        verify(listener, timeout(5000)).receiveEvent("event-two");
    }

    private static <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
