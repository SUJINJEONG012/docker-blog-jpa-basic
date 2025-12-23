# -------------------------
# 1️⃣ Build Stage
# -------------------------
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# -------------------------
# 2️⃣ Run Stage
# -------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# WAR 복사
#COPY --from=build /app/target/*.war app.war
# JAR 복사
COPY --from=build /app/target/*.jar app.jar

# 포트 노출
EXPOSE 8080
# Render 환경변수 PORT 대응
ENV PORT 8080

# 실행 명령어
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.war"]
