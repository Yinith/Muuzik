FROM openjdk:8-jre
RUN apt-get update; apt-get install -y netcat
COPY muuzik/target/bbdd_ejer3-0.0.1-SNAPSHOT.jar /app/
COPY ./run.sh /app/
WORKDIR /app
RUN chmod 777 /app/run.sh
CMD ["/app/run.sh"]
