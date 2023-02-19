
# Project Architecture


![Project Architecture](https://user-images.githubusercontent.com/69304233/218793766-60883e95-f696-46aa-9806-d7696ca8a4ef.png)




# Project Description

A Change data capture (CDC) architecture in the context of weather IoT sensors to collect real-time data on temperature, humidity, air pressure, wind speed, and precipitation. The data will be collected continuously and stored in Cassandra, a highly scalable NoSQL database.

To process the data in real-time, we will be using Apache Kafka and Spark Streaming. Kafka will act as a messaging system to stream the data from the IoT sensors to Spark Streaming. Spark Streaming will process the data in real-time, perform analytics on the data, and output the results to Cassandra.

To visualize and analyze the data, we will be using Power BI. Power Bi will connect to Cassandra and generate interactive visualizations to help us gain insights into the weather patterns and trends.

## Technology Stack:
1. Weather IoT sensors
2. Apache Kafka
3. Spark Streaming
4. Cassandra
5. Power BI
6. Docker

## Project Goals:
1. Collect real-time weather data using IoT sensors
2. Process the data in real-time using Kafka and Spark Streaming
3. Store the processed data in Cassandra for easy retrieval and analysis
4. Generate interactive visualizations using Power Bi to gain insights into the weather patterns and trends

## Expected Outcomes:
1. Real-time weather data collection and processing
2. Highly scalable and fault-tolerant data storage in Cassandra
3. Interactive visualizations of weather patterns and trends in Power Bi
4. Improved decision-making for weather-related activities and operations
