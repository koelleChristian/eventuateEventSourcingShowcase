package de.koelle.christian.es.eventuate.sample.eventstore.eventstore;

import de.koelle.christian.es.eventuate.sample.eventstore.eventstore.backend.EventStoreBackendConfiguration;
import de.koelle.christian.es.eventuate.sample.eventstore.eventstore.web.EventStoreWebConfiguration;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({EventStoreWebConfiguration.class, EventStoreBackendConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class EventStoreApp {

    private static final Logger LOG = LoggerFactory.getLogger(EventStoreApp.class);

    public static void main(String[] args) {
        SpringApplication.run(EventStoreApp.class);
        LOG.info("Started");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
    webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/eventstore");
    }

}