FROM eclipse-temurin:21

COPY ./target/crud_csv-backend-0.0.1-SNAPSHOT.jar /

RUN sh -c 'touch crud_csv-backend-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","/crud_csv-backend-0.0.1-SNAPSHOT.jar"]

RUN chmod +x /crud_csv-backend-0.0.1-SNAPSHOT.jar