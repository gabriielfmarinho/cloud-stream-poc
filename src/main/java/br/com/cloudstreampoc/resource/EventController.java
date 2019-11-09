package br.com.cloudstreampoc.resource;

import br.com.cloudstreampoc.channel.EventChannel;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/event")
public class EventController {

    @PostMapping("/{event}")
    @InboundChannelAdapter(EventChannel.OUTPUTEVENT)
    public String postEvent(@PathParam("event") String event) {
        return event;
    }
}
