
# Project Architecture


![Project Architecture](https://user-images.githubusercontent.com/69304233/218793766-60883e95-f696-46aa-9806-d7696ca8a4ef.png)




# Project Description

A Change data capture (CDC) architecture in the context of weather IoT sensors to collect real-time data on temperature, humidity, air pressure, wind speed, and precipitation. The data will be collected continuously and stored in Cassandra, a highly scalable NoSQL database.

To process the data in real-time, we will be using Apache Kafka and Spark Streaming. Kafka will act as a messaging system to stream the data from the IoT sensors to Spark Streaming. Spark Streaming will process the data in real-time, perform analytics on the data, and output the results to Cassandra.

To visualize and analyze the data, we will be using Power BI. Power Bi will connect to Cassandra and generate interactive visualizations to help us gain insights into the weather patterns and trends.

Technology Stack:

Weather IoT sensors
Apache Kafka
Spark Streaming
Cassandra
Power BI
Project Goals:

Collect real-time weather data using IoT sensors
Process the data in real-time using Kafka and Spark Streaming
Store the processed data in Cassandra for easy retrieval and analysis
Generate interactive visualizations using Tableau to gain insights into the weather patterns and trends
Expected Outcomes:

Real-time weather data collection and processing
Highly scalable and fault-tolerant data storage in Cassandra
Interactive visualizations of weather patterns and trends in Tableau
Improved decision-making for weather-related activities and operations
