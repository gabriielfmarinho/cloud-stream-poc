package br.com.cloudstreampoc.resource;

import br.com.cloudstreampoc.channel.EventChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventChannel eventChannel;

    @PostMapping("/{event}")
    @ResponseStatus(HttpStatus.OK)
    public void postEvent(@PathVariable("event") String event) {
        eventChannel.outputEvent().send(message(event));
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
