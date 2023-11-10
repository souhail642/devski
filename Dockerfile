FROM openjdk:11
ADD target/ggestion-station-ski-1.0.jar ggestion-station-ski.jar
ENTRYPOINT ["java", "-jar", "/ggestion-station-ski-1.0.jar"]
EXPOSE 9097
