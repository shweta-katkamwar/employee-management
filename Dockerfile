FROM openjdk:23-ea-17-jdk-bookworm
EXPOSE 8080
ADD target/docker-practice-0.0.1-SNAPSHOT.jar docker-practice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/docker-practice-0.0.1-SNAPSHOT.jar"]