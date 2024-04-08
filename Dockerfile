FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
COPY "./build/libs/*.jar" app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
