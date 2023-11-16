FROM openjdk:11
EXPOSE 8089
#ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
RUN curl -O http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar","/gestion-station-ski-1.0.jar"]
#FROM openjdk:11-jre-slim
#WORKDIR /app
#EXPOSE 8086
#RUN apt-get update && apt-get install -y curl
#RUN curl -o achat-1.0.jar -L "http://192.168.137.135:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
#ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]