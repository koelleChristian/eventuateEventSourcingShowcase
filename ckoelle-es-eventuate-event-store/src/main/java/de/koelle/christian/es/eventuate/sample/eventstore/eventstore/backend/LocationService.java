/*
 * Copyright 2019 IQTIG – Institut für Qualitätssicherung und Transparenz im Gesundheitswesen.
 * Diese Code ist urheberrechtlich geschützt (Copyright). Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet, beim IQTIG.
 * Wer gegen das Urheberrecht verstößt, macht sich gem. § 106 ff Urhebergesetz strafbar. Er wird zudem kostenpflichtig abgemahnt und muss
 * Schadensersatz leisten.
 */
package de.koelle.christian.es.eventuate.sample.eventstore.eventstore.backend;

import de.koelle.christian.es.eventuate.sample.eventapi.Location;
import io.eventuate.EntityWithIdAndVersion;

public interface LocationService {

    EntityWithIdAndVersion<Location> createLocation(String customerId);
}
