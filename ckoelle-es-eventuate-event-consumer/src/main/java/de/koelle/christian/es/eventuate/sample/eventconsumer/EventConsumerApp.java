package de.koelle.christian.es.eventuate.sample.eventconsumer;

import de.koelle.christian.es.eventuate.sample.eventconsumer.backend.EventConsumerWorkflowConfiguration;
import de.koelle.christian.es.eventuate.sample.eventconsumer.web.EventConsumerWebConfiguration;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({EventConsumerWebConfiguration.class, EventConsumerWorkflowConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class EventConsumerApp {
    private static final Logger LOG = LoggerFactory.getLogger(EventConsumerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(EventConsumerApp.class, args);
        LOG.info("Started.");
    }

    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
    webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/eventconsumer");
    }
}
