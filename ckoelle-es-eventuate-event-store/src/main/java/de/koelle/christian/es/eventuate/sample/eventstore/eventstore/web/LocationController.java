package de.koelle.christian.es.eventuate.sample.eventstore.eventstore.web;

import de.koelle.christian.es.eventuate.sample.eventstore.eventstore.backend.LocationService;
import de.koelle.christian.es.eventuate.sample.eventstore.eventstore.webapi.CreateLocationRequest;
import de.koelle.christian.es.eventuate.sample.eventstore.eventstore.webapi.CreateLocationResponse;
import de.koelle.christian.es.eventuate.sample.eventapi.Location;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private LocationService locationService;

    /**
     * Konstruktor
     */
    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public ResponseEntity<CreateLocationResponse> createOrder(@RequestBody CreateLocationRequest createOrderRequest) {
        String locationName = createOrderRequest.getName();
        EntityWithIdAndVersion<Location> order = locationService.createLocation(locationName);
        return new ResponseEntity<>(new CreateLocationResponse(order.getEntityId()), HttpStatus.OK);
        // try-catch, when interacting with other microservices
    }
}