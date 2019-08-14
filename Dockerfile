FROM openjdk:8-jre-alpine
MAINTAINER newscruber
COPY target/tochka-0.0.1-SNAPSHOT.jar /opt/tochka-0.0.1-SNAPSHOT.jar
WORKDIR /opt
CMD ["java","-jar"," /opt/tochka-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080