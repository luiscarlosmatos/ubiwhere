FROM java:8
EXPOSE 8080
COPY ./target/review-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch review-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","review-service-0.0.1-SNAPSHOT.jar"]