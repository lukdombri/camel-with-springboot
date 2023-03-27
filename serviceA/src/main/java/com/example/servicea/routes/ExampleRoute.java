package com.example.servicea.routes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
//@Component
public class ExampleRoute extends RouteBuilder {

    private final TransformService transformService;
    private final ProcessService processService;
    private final ProcessorService processorService;

    @Override
    public void configure() throws Exception {
        from("timer:example-timer?period=10000")
                .log("${body}")
                //Transformation
                .transform().constant("Body transformed for constant by transform()")
                .log("${body}")
                .bean(transformService)
                .log("${body}")
                //Processing
                .bean(processService)
                .log("${body}")
                .process(processorService)
                .log("${body}")
                .to("log:timer");
    }


}

@Component
class TransformService {

    public String transformMessage(String message) {
        return "Body transformed for constant by service method";
    }
}

@Component
@Slf4j
class ProcessService {

    public void processMessage(String message) {
        log.info("Example task without changing body");
    }
}

@Component
@Slf4j
class ProcessorService implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Example runs by processor... ");
        log.info("In method there is access to {$body}: " + exchange.getMessage().getBody());
    }
}
