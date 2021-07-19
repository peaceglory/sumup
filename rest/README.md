# Project Overview
This service provides a RESTful API via which an article object can be written to a Kafka topic. Writing to Kafka happens with Spring's KafkaTemplate class. 
A Kafka topic (named 'all_messages') is created by Spring Kafka based on preconfigured properties. 

# Rationale
For the purposes of this challange all article post requests are written into the 'all_messages' topic thus setting
the stage for further processing by the second service in this project. One of the non-functional requirements is that the pre-processing service should be 
horizontally scalable with at least 3 instances. Since partitions define topic parallelism 'all_messages' was created with 4 partitions. This would
allow 4 instances of the pre-processing service to work in parallel.
