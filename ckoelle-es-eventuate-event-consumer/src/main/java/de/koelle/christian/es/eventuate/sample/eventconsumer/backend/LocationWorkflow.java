package de.koelle.christian.es.eventuate.sample.eventconsumer.backend;

import de.koelle.christian.es.eventuate.sample.eventapi.LocationCreatedEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EventSubscriber(id = "LocationWorkflow")
public class LocationWorkflow {
    private static final Logger LOG = LoggerFactory.getLogger(LocationWorkflow.class);

    public LocationWorkflow() {
        LOG.info("construct()");
    }

    @EventHandlerMethod
    public void createLocation(DispatchedEvent<LocationCreatedEvent> de) {
        LOG.info("createLocation() {}.", ToStringBuilder.reflectionToString(de));
    }


}
