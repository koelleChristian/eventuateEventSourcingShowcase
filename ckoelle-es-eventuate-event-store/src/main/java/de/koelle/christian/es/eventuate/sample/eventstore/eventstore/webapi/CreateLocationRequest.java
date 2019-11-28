package de.koelle.christian.es.eventuate.sample.eventstore.eventstore.webapi;


public class CreateLocationRequest {
    private String name;

    public CreateLocationRequest() {
    }

    public CreateLocationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
