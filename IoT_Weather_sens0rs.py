#!/usr/bin/python3
#main


# imports
from kafka import KafkaProducer # pip install kafka-python
import requests            # pip install requests
from sys import argv, exit
from time import time, sleep
import json

# Define the API endpoint
API_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}"

# Define the Kafka Producer configuration
KAFKA_BOOTSTRAP_SERVERS = ['localhost:9092'] # Change this if your Kafka Broker is running in a different server
KAFKA_TOPIC_NAME = 'weather' 
KAFKA_PRODUCER_CONFIG = {
    'bootstrap_servers': KAFKA_BOOTSTRAP_SERVERS
}

# Define the function to fetch the weather data from the API
def get_weather_data(latitude, longitude, api_key):
    # Construct the API endpoint URL
    api_url = API_ENDPOINT.format(lat=latitude, lon=longitude, API_key=api_key)
    
    # Call the API
    response = requests.get(api_url)
    
    # Check if the API call was successful
    if response.status_code != 200:
        raise ValueError('API returned unexpected response code')
    
    # Extract the weather data from the API response
    weather_data = response.json()
    
    # Return the weather data
    return weather_data

# Define the main function
def main(latitude, longitude, api_key):
    # Create the Kafka Producer
    producer = KafkaProducer(**KAFKA_PRODUCER_CONFIG)
    
    # Loop forever
    while True:
        try:
            # Get the weather data from the API
            weather_data = get_weather_data(latitude, longitude, api_key)
            
            # Convert the weather data to JSON format
            json_data = json.dumps(weather_data)
            
            # Send the JSON data to Kafka
            producer.send(KAFKA_TOPIC_NAME, json_data.encode())
            
            # Sleep for 24 hours
            sleep(24*60*60)
        
        except Exception as e:
            # Log the error and continue
            print(f'Error: {e}')
            sleep(60)

# Check if the script was called with the correct number of arguments
if len(argv) != 4:
    print('Usage: python3 weather_kafka_producer.py <latitude> <longitude> <api_key>')
    exit(1)

# Parse the command line arguments
latitude = float(argv[1])
longitude = float(argv[2])
api_key = argv[3]

# Call the main function
main(latitude, longitude, api_key)

