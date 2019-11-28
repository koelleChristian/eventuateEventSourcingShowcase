/*
 * Copyright 2019 IQTIG – Institut für Qualitätssicherung und Transparenz im Gesundheitswesen.
 * Diese Code ist urheberrechtlich geschützt (Copyright). Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet, beim IQTIG.
 * Wer gegen das Urheberrecht verstößt, macht sich gem. § 106 ff Urhebergesetz strafbar. Er wird zudem kostenpflichtig abgemahnt und muss
 * Schadensersatz leisten.
 */
package de.koelle.christian.es.eventuate.sample.eventstore.eventstore.backend;

import de.koelle.christian.es.eventuate.sample.eventapi.Location;
import de.koelle.christian.es.eventuate.sample.eventapi.LocationCommand;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EventStoreBackendConfiguration {

    @Bean
    public LocationService locationService(AggregateRepository<Location, LocationCommand> locationRepository) {
        return new LocationServiceImpl(locationRepository);
    }

    @Bean
    public AggregateRepository<Location, LocationCommand> locationRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(Location.class, eventStore);
    }

}