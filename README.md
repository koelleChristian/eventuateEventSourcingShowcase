# eventuateEventSourcingShowcase
A 'simplified' [Eventuate](https://eventuate.io/) showcase - i. e. an event-sourcing framework -  for personal eventuate framework evaluation purposes.



## Motivation

I looked into the concepts behind [event sourcing](https://martinfowler.com/eaaDev/EventSourcing.html). 

After reading chapter 6 of Chris Richardson's excellent book [Microservices Patterns](https://www.manning.com/books/microservices-patterns) on Safaribooks online, I wanted to evaluated the Eventuate framework, as an example of an event-sourcing framework, without having a support contract. My ideas where:

1. To adapt the provided [showcase](https://github.com/eventuate-examples/eventuate-examples-java-customers-and-orders) to maven (instead of gradle) and use my own domain objects.
2. To check the event-sourcing API:
   1. How does it feel?
   2. Does it allow to query the event state at certain point in time?
   3. How is event schema migration done?
   4. How does it deal with event snapshots?
3. How is the event propagation done? How is the consistency eventually guaranteed?

## Starting the showcase

#### 1 Boot the infrastructure

```bash
export DOCKER_HOST_IP=<Your machine's IP>
export MYSQL_PORT=<Your mysql port of choice>
docker-compose -f docker-compose-eventuate-local-mysql.yml up -d
```

... this will launch Kafka, Zookeeper, MySQL and the CDC-Service.

#### 2 Launch the Showcase

1. Import the application in your IDE of choice. There will be 2 Spring Boot-Server-Applications

   1. `EventStoreApp`
      - ... which represents the event store, i.e. the data mutating, i.e. the the writing, command executing part of the infrastructure 
   2. `EventConsumerApp`
      - ... which just gets notified about events persisted in the eventstore.

2. Launch both Spring Boot applications with the following JVM Parameters

   ```shell
   -Dserver.port=<Port of your choice per App>
   -Dspring.datasource.url=jdbc:mysql://<DOCKER_HOST_IP>:3406/eventuate
   -Dspring.datasource.username=mysqluser
   -Dspring.datasource.password=mysqlpw
   -Dspring.datasource.driver-class-name=com.mysql.jdbc.Driver
   -Deventuatelocal.kafka.bootstrap.servers=<DOCKER_HOST_IP>:9092
   -Dcompose.http.timeout=240
   -Deventuatelocal.zookeeper.connection.string=<DOCKER_HOST_IP>:2181
   ```

3. Send a post request to  http://localhost:<CHOOSEN_PORT_OF_EVENTSTOR_APP>/eventstore/locations

   ```json
   {
     "name":"WhateverLocationName" 
   }
   ```

#### 3 Check the outcome

1. Check the `eventstore` database in your running mysql instance:
   - You should find the event behind the submitted command to be persisted. 
2. Check the console of `EventConsumerApp`
   - The console should reveal that the persisted event has been distributed to the EventConsumerApp.

## Further background information

- The actual event propagation post persisting is executed by the CDC service.

## Evaluation outcome 

02.01.2020

Please keep in mind: The picture I draw here, is a picture from an outsider, i. e. from someone without Eventuate support contract and with limited private resources to investigate everything.

First of all: If you have to get involved with either CQRS or event sourcing, I highly recommend to read Chris Richardson's book, as he gives detailed insight into the topics. The information he gives might actually enable you to write your own framework(s) without forgetting to think about important key aspects.

The [customers-and-orders example](https://github.com/eventuate-examples/eventuate-examples-java-customers-and-orders) provided by Eventuate is actually a fully fledged CQRS example with multiple flavors on the CQRS Q(ery)-side as well as for your choice of database.

- The official example focuses more on the big CQRS interaction picture and less solely on Eventuate's event sourcing features. 
- Its primary goal is *not* to illustrate important event sourcing aspects, like event schema migration and  snapshots handling, by example usage. Both aspects are not facilitated by the  original example. However: I could spot the API parts which are responsible for dealing with those aspects. I subjectively think there is a lack of documentation and therefore I would require more time to dig into it, before I would consider myself being in control of the framework. 
  - After interaction with the helpful and responsive user mailing list, Eventuate opened up a change request to allow to query an event state at a historic point in time, see: https://github.com/eventuate-clients/eventuate-client-java/issues/21, which is actually my primary use case for a event sourcing framework.
- I could not find the code of everything involved in the official example publicly, so I expect that not everything is open source.
- At first glance various aspects seem to be tighter coupled than expected. My thoughts where: Why are the command declarations visible on the query side? Why do I need CDC for event propagation, even if I might not yet see much advantages of CDC?  However: Consider how you would implement such an infrastructure? It appears to be a proper trade-off to obtain more maintainability.

To summarize my outcome in short: I would recommend to consider getting a support contract with Eventuate, if you want to use it on a larger scale. For my tiny potential use case, I would fear not to have everything under control by simply using it, without expecting much more interaction with the user group.

## 

