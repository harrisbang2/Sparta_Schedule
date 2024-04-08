# open jdk 17 버전의 환경을 구성
FROM openjdk:17-alpine
CMD ["./mvnw", "clean", "package"]
COPY "./build/libs/*.jar" app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
