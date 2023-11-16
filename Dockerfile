FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/gestion-station-ski-0.0.1-SNAPSHOT.jar StationSki.jar
ENTRYPOINT ["java","-jar","StationSki.jar"]
