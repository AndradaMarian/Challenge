FROM openjdk:17
COPY target/Challenge-0.0.1-SNAPSHOT.jar Challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Challenge-0.0.1-SNAPSHOT.jar"]
