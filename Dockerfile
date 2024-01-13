FROM docker.io/maven:3.9.5-eclipse-temurin-17-alpine as builder
COPY ./ /
RUN mvn package -DskipTests=true

FROM docker.io/eclipse-temurin:17-jre-alpine
RUN mkdir -p /app
COPY --from=builder target/assets-0.0.1-SNAPSHOT.jar /app/assets.jar
ENTRYPOINT ["java","-jar","/app/assets.jar"]
