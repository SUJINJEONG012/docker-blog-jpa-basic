# -------------------------
# 1️⃣ Build Stage
# -------------------------
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /docker-blog-jpa-basic
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# -------------------------
# 2️⃣ Run Stage
# -------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /docker-blog-jpa-basic

#COPY --from=build /docker-blog-jpa-basic/target/*.jar docker-blog-jpa-basic.jar
#COPY --from=build /docker-blog-jpa-basic/target/blog-0.0.1-SNAPSHOT-spring-boot.jar
COPY --from=build /docker-blog-jpa-basic/target/*-spring-boot.jar blog-0.0.1-SNAPSHOT-spring-boot.jar


# 포트 노출
EXPOSE 8080

# Render 환경변수 PORT 대응
ENV PORT 8080

# 실행 명령어
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "blog-0.0.1-SNAPSHOT-spring-boot.jar"]
