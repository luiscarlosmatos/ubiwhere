FROM java:8
EXPOSE 8180
COPY ./target/establishment-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch establishment-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","establishment-service-0.0.1-SNAPSHOT.jar"]