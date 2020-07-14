FROM openjdk:8-jre
RUN apt-get update; apt-get install -y netcat
COPY .//bbdd_ejer3-0.0.1-SNAPSHOT.jar /app/
COPY ./run.sh /app/
WORKDIR /app
RUN mkdir /root/facturas
RUN mkdir /root/facturas/facturasgeneradas
RUN chmod 777 /app/run.sh
CMD ["/app/run.sh"]
