package br.com.cloudstreampoc.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventChannel {

    String INPUTEVENT = "input-event";

    String OUTPUTEVENT = "output-event";

    @Input(INPUTEVENT)
    SubscribableChannel inputEvent();

    @Output(OUTPUTEVENT)
    MessageChannel outputEvent();
}
