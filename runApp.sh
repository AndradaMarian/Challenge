#!/bin/bash

# Exit script on error
set -e

# Step 1: Build the Maven project
echo "Building the Maven project..."
mvn clean install

# Step 2: Build and start the Docker containers using Docker Compose
echo "Starting Docker containers..."
docker-compose up -d --build

# Step 3: Wait for Kafka to be fully up (give it some time)
echo "Waiting for Kafka to be ready..."
sleep 20  # Adjust sleep time if needed based on your setup

# Step 4: Create Kafka topics
echo "Creating Kafka topics..."
docker exec -it kafka /bin/bash -c "sh /tmp/create-topics.sh"

# Step 5: Print success message
echo "Docker containers started and Kafka topics created successfully!"

# Optional: Show running Docker containers
docker ps