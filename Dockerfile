FROM openjdk:8-jre-alpine
RUN apk --no-cache add curl
COPY target/tochka-0.0.1-SNAPSHOT.jar /opt/tochka-0.0.1-SNAPSHOT.jar
WORKDIR /opt
CMD java ${JAVA_OPTS} -jar /opt/tochka-0.0.1-SNAPSHOT.jar
EXPOSE 8080