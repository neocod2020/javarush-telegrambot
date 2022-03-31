FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test.jrush_community_bot
ENV BOT_TOKEN=5265304518:AAGavXSFBLu5DQmlXE9TPGSMkak835MB0v4_uso
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}",  "-jar", "/app.jar"]