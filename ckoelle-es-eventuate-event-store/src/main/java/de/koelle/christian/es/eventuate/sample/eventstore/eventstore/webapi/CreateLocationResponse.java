package de.koelle.christian.es.eventuate.sample.eventstore.eventstore.webapi;


public class CreateLocationResponse {
    private String locationId;

    public CreateLocationResponse() {
    }

    public CreateLocationResponse(String locationId) {
        this.locationId = locationId;

    }

    public String getLocationId() {
        return locationId;
    }
}
