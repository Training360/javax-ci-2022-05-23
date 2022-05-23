FROM eclipse-temurin:17
WORKDIR app
COPY target/*.jar employees.jar
CMD ["java", "-jar", "employees.jar"]