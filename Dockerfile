FROM openjdk:11.0
EXPOSE 8089
ENV NEXUS_USERNAME=admin
ENV NEXUS_PASSWORD=admin123
ENV NEXUS_REPO_URL=http://192.168.1.3:8081/repository/maven-releases/tn/esprit/spring/estion-station-ski/1.0/estion-station-ski-1.0.jar

# Download the JAR file from Nexus and copy it to the container
RUN curl -L -o achat.jar -u $NEXUS_USERNAME:$NEXUS_PASSWORD $NEXUS_REPO_URL

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "gestion-station-ski.jar"]
