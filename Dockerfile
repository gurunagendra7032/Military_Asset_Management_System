FROM eclipse-temurin:21-jdk

 WORKDIR /app

 COPY . .

 RUN chmod +x mvnw

 RUN echo "===== CHECK PROD PROPERTIES ====="
 RUN cat src/main/resources/application-prod.properties

 RUN ./mvnw clean package -DskipTests

 RUN echo "===== CHECK JAR CONTENT ====="
 RUN jar tf target/*.jar | grep application-prod.properties

 CMD ["sh", "-c", "java -jar target/*.jar"]