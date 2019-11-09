package br.com.cloudstreampoc.listener;

import br.com.cloudstreampoc.channel.EventChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
public class EventListener {

    @StreamListener(EventChannel.INPUTEVENT)
    public void receiveEvent(String event) {
        log.info("Recebendo mensagem do RabbitMQ, event={}", event);
    }
}
