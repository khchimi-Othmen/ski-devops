FROM openjdk:17-alpine

ADD target/gestion-station-ski-*.jar /gestion-station-ski.jar

CMD ["java", "-jar", "/gestion-station-ski.jar"]