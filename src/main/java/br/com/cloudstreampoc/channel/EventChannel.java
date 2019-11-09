package br.com.cloudstreampoc.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventChannel {

    String INPUTEVENT = "input-event";

    String OUTPUTEVENT = "output-event";

    @Input(INPUTEVENT)
    MessageChannel inputEvent();

    @Output(OUTPUTEVENT)
    MessageChannel outputEvent();
}
