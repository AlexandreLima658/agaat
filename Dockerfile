FROM gradle:8.5-jdk21-alpine AS builder

WORKDIR /usr/src/app

COPY . .

RUN gradle bootJar

# Build runtime image
FROM eclipse-temurin:21-jre-alpine

COPY --from=builder /usr/src/app/build/libs/*.jar /opt/app/app.jar

RUN addgroup -S java && adduser -S java -G java
USER java:java

CMD java -jar /opt/app/app.jar