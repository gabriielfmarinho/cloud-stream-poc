package br.com.cloudstreampoc.config;

import br.com.cloudstreampoc.channel.EventChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(EventChannel.class)
public class CloudStreamConfig {
}
