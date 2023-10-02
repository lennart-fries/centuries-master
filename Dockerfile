FROM primetoninc/jdk:1.8

WORKDIR /app
ADD target/universal/play-java-starter-example-1.0-SNAPSHOT.zip /app/app.zip
RUN unzip app.zip

EXPOSE 9000

CMD ["./play-java-starter-example-1.0-SNAPSHOT/bin/play-java-starter-example", "-Dplay.http.context=/ws17/2/"]
