FROM openjdk:11
EXPOSE 8080
#ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
RUN curl -O http://192.168.1.13:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar","/gestion-station-ski-1.0.jar"]