package de.koelle.christian.es.eventuate.sample.eventapi;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationCreatedEvent implements LocationEvent {

    private String name;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public LocationCreatedEvent() {
    }

    public LocationCreatedEvent(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
