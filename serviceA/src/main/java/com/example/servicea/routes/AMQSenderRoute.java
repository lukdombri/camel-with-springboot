package com.example.servicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AMQSenderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://D:\\projekty_git\\DFG_microservices\\camel-with-springboot\\serviceA\\src\\main\\resources\\xml")
                .log("${body}")
                .to("activemq:serviceABQueue");
    }
}