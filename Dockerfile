FROM openjdk:11

COPY target/IT-support-0.0.1-SNAPSHOT.jar IT-support-0.0.1-SNAPSHOT.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","IT-support-0.0.1-SNAPSHOT.jar"]