# eventuateEventSourcingShowcase
A simplified Eventuate (EventSourcing-Framework) showcase



## Starting the showcase

#### 1 Boot the infrastructure

```
export DOCKER_HOST_IP=...
docker-compose -f docker-compose-eventuate-local-mysql.yml up -d
```

... this will launch Kafka, Zookeeper and MySQL.

#### 2 Launch the Showcase

1. Import the application in your IDE of choice.

2. Launch the Spring Boot applications with the following JVM Parameters

   ```
   -Dserver.port=<Port of your choice>
   -Dspring.datasource.url=jdbc:mysql://<DOCKER_HOST_IP>:3406/eventuate
   -Dspring.datasource.username=mysqluser
   -Dspring.datasource.password=mysqlpw
   -Dspring.datasource.driver-class-name=com.mysql.jdbc.Driver
   -Deventuatelocal.kafka.bootstrap.servers=<DOCKER_HOST_IP>:9092
   -Dcompose.http.timeout=240
   -Deventuatelocal.zookeeper.connection.string=<DOCKER_HOST_IP>:2181
   ```

   



