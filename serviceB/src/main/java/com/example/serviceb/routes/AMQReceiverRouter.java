package com.example.serviceb.routes;

import com.example.serviceb.dto.UserDTO;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AMQReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:serviceABQueue")
                .unmarshal().jacksonXml(UserDTO.class)
                .to("log:received-message-from-active-mq");
    }
}
