Building a Microservices-based Spring Boot project to get into Spring and good development practices.
A Patient Management System with RESTful CRUD, gRPCs to facilitate communication between services, Apache Kafka - a bit of work in progress, I'm putting together a notification service with it.
Modules - 
1) Patient-Service - RESTful, handles CRUD for patients.
2) Billing-Service - Generates a bill once a patient is created using Patient-Service, Billing-Service gets the trigger and necessary information via gRPC from Patient-Service.
3) Notification-Service - Work in Progress, for now the Apache Kafka setup works. Patient-Service produces the topic for Notification-Service to consume. Data pushed to the topic is serialized to ByteArray using Protobuf to minimize load.
   
