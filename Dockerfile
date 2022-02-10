FROM openjdk:11
ADD /target/WebServiceSchedule-0.0.1-SNAPSHOT.war webservice.war
COPY src/main/resources src/main/resources
EXPOSE 8076
ENTRYPOINT ["java", "-jar", "webservice.war"]