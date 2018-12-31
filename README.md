## Introduction
You are building an audit microservice for a shopping system.

You are building an auditing microservice for an e-commerce platform.

The service receives events from the Kafka topic `users`, which represents user registrations.
The JSON data is structured as follows:

```
{ 
    "login":"someLogin",   
    "creationTime":"2018-06-20T22:51:20.030201" 
}
```

Once the event is received, it is transformed to the Java object `RegisteredUserEvent` (via the Jackson library) and stored in the relational H2 database via Spring Data JPA.
