#Project Overview

IoT devives -> Kafka -> Spark Streaming -> Cassandra/Cosmos DB Database

A Change data capture (CDC) architecture in the context of IoT weather devices implemented using a combination of Apache Kafka, Apache Spark, and Apache Cassandra.

1. Apache Kafka: Changes made to the data from IoT weather devices are captured and published as events to a Kafka topic. Kafka provides a scalable and fault-tolerant platform for real-time data streaming and integration.

2. Apache Spark: The events from the Kafka topic are consumed using Spark, which  then process and transform the data in real-time. Spark is used to perform various data processing tasks, such as filtering, aggregating, and transforming the data.

3. Apache Cassandra/Cosmos DB: The processed data from Spark is stored in Cassandra and/or Cosmos DB, which is a NoSQL database optimized for high-performance and scalability. Cassandra can store large amounts of data, and can handle high-volume write and read operations, making it a suitable choice for storing IoT weather data.

In this setup, changes made to the data from the IoT weather devices are captured and propagated to the target systems in real-time or near real-time, ensuring that the target systems have an up-to-date and accurate representation of the data. This setup also provides a high level of scalability, fault tolerance, and decoupling between the source and target systems, ensuring that the system can handle large amounts of data and handle failures without affecting the overall system.

                      
