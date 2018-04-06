FROM java:8-jre

WORKDIR /app

ADD ./target /app

EXPOSE 8282 1521

RUN chmod 755 /app/examxml-1.0-SNAPSHOT-withdependencies.jar

CMD java -jar /app/examxml-1.0-SNAPSHOT-withdependencies.jar
