# Project Description

![1](https://user-images.githubusercontent.com/69304233/221230867-09d076fd-3aae-403b-b899-aa96b7631143.png)



# Project Description

A Change data capture (CDC) architecture in the context of weather IoT sensors to collect real-time data on temperature, humidity, air pressure, wind speed, and precipitation. The data will be collected continuously and stored in Cassandra, a highly scalable NoSQL database.

To process the data in real-time, we will be using Apache Kafka and Spark Streaming. Kafka will act as a messaging system to stream the data from the IoT sensors to Spark Streaming. Spark Streaming will process the data in real-time, perform analytics on the data, and output the results to Cassandra.

To visualize and analyze the data, we will be using Tableau. Tableau will connect to Cassandra and generate interactive visualizations to help us gain insights into the weather patterns and trends.

## Technology Stack:
1. Weather IoT sensors
2. Apache Kafka
3. Spark Streaming
4. Cassandra
5. Tableau


## Project Goals:
1. Collect real-time weather data using IoT sensors
2. Process the data in real-time using Kafka and Spark Streaming
3. Store the processed data in Cassandra for easy retrieval and analysis
4. Generate interactive visualizations using Power Bi to gain insights into the weather patterns and trends

## Expected Outcomes:,
1. Real-time weather data collection and processing
2. Highly scalable and fault-tolerant data storage in Cassandra
3. Interactive visualizations of weather patterns and trends in Tableau
4. Improved decision-making for weather-related activities and operations

# Quick Start

1. Start a Kafka server
    * create a topic called ```weather```
  
2. Start a Cassandra database
  *create a keyspace called ```weatherSensors``` (SimpleStrategy, replication=1)
 ```
 CREATE KEYSPACE weatherSensors
 WITH replication = {'class': 'SimpleStrategy, 'replication_factor' : 1};
 ```

3. create a table called weather with the following schema
 ```
CREATE TABLE weatherSensors (
  date text,
  locality_name text,
  temperature_max int,
  temperature_min int,
  humidity int,
  wind int,
  wind_direction text,
  PRIMARY KEY (date, locality_name)
);

 ```
4. package up everything in a scala the file using Java/Scala the build tool:
```
sbt package
```

5. Run you spark, Kafka and cassandra using: 
```
spark-submit --class StreamHandler \
--master local[*] \
--packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.5,\
	com.datastax.cassandra:cassandra-driver-core:4.0.0,\
	com.datastax.spark:spark-cassandra-connector_2.11:2.4.3 \
target/scala-2.11/stream-handler_2.11-1.0.jar
```
Then start running your "IoT devices" script:
```
./IoT_Weather_sens0rs.py
```
6. To view data in cassandra DB, run CQL shell ```./bin/cqlsh``` and ```select * from weather``` to see if the data is being processed saved correctly!

# Visualization
