FROM adoptopenjdk/maven-openjdk11 as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests
EXPOSE 8080
FROM openjdk:11
WORKDIR /agro-api
COPY --from=maven target/agro-api-*.jar ./agro-api/AgroApiApplication.jar
CMD ["java", "-jar", "./agro-api/AgroApiApplication.jar"]
