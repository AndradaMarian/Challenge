#!/bin/bash
# Wait for Kafka to be ready
sleep 10

# Create Kafka topics
kafka-topics.sh --create --topic user-topic --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1

# Print confirmation
echo "Kafka topics created!"