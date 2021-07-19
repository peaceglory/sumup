# Overview
This service acts as the pre-processing functionality of the system. For fun and out of love of exploring it was created with Kafka Streams library.


# Rationale
In order for this service to do its job it has to opearte on two topics - one input and one output. The input topic is the one created by the 'rest' service 
(called 'all_messages'). The output topic should be created manually with the following script:

kafka-topics.sh --create --bootstrap-server <kafka-server> --topic filtered_messages --partitions 3 --replication-factor 1

Replication factor is chosen to be 1 for simplicity since this solution was developed and tested on a non-cluster environment.

Functional requirement for this solution was the pre-processing logic to filter articles which don't contain any of the words from a given list.
This is at the core of the stream transformation from 'all_messages' to 'filtered_messages'.
  
The service utilizes spring profiles and the template method design pattern in order to make it more general and reusable.
For this particular scenario the service should be started with the 'article-preprocessor' profile.
