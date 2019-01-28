FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/project-0.0.1-SNAPSHOT.war /app/project.war
ENTRYPOINT ["java","-jar","/app/project.war"]