FROM openjdk:17-alpine

ADD target/station_ski-*.jar /station_ski.jar

CMD ["java", "-jar", "/station_ski.jar"]