FROM amazoncorretto:21-alpine

WORKDIR /app

COPY /src ./src
COPY pom.xml .

RUN apk add --no-cache maven
RUN mvn clean package spring-boot:repackage

CMD ["java", "-jar", "target/my-hotel-0.0.1-SNAPSHOT.jar"]