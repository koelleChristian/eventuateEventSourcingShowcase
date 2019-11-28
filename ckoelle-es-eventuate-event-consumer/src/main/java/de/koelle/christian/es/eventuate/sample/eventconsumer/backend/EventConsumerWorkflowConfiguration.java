package de.koelle.christian.es.eventuate.sample.eventconsumer.backend;

import de.koelle.christian.es.eventuate.sample.eventapi.Location;
import de.koelle.christian.es.eventuate.sample.eventapi.LocationCommand;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEventHandlers
public class EventConsumerWorkflowConfiguration {

    @Bean
    public LocationWorkflow orderHistoryViewWorkflow() {
        return new LocationWorkflow();
    }

    @Bean
    public AggregateRepository<Location, LocationCommand> locationRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(Location.class, eventStore);
    }

}
