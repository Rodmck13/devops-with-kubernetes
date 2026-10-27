FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY TodoApp.java .

RUN javac TodoApp.java

ENV PORT=3000

CMD ["java", "TodoApp"]