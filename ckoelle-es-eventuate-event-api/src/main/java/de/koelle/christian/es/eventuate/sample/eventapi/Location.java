/*
 * Copyright 2019 IQTIG – Institut für Qualitätssicherung und Transparenz im Gesundheitswesen.
 * Diese Code ist urheberrechtlich geschützt (Copyright). Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet, beim IQTIG.
 * Wer gegen das Urheberrecht verstößt, macht sich gem. § 106 ff Urhebergesetz strafbar. Er wird zudem kostenpflichtig abgemahnt und muss
 * Schadensersatz leisten.
 */
package de.koelle.christian.es.eventuate.sample.eventapi;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;

public class Location extends ReflectiveMutableCommandProcessingAggregate<Location, LocationCommand> {

    private String name;

    public List<Event> process(CreateLocationCommand cmd) {
        return EventUtil.events(new LocationCreatedEvent(cmd.getName()));
    }

    public void apply(LocationCreatedEvent event) {
        this.name = event.getName();
    }

    public static class CreateLocationCommand implements LocationCommand {

        private final String name;

        public CreateLocationCommand(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}