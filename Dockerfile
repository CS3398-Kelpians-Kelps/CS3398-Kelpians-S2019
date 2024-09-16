FROM openjdk:13
COPY SendIt.jar .
CMD ["java", "-jar", "SendIt.jar"]
