FROM maven:3.8.4 as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve

COPY src/ src/
RUN mvn clean install

FROM openjdk:11.0
WORKDIR /app
COPY --from=build /app/target/SKI.jar /app/
EXPOSE 8089
CMD ["java","-jar","SKI.jar"]
