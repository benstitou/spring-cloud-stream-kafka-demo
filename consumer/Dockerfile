FROM openjdk:17.0.2-slim-buster as builder
WORKDIR tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar --enable-preview application.jar extract

FROM openjdk:17.0.2-slim-buster
RUN addgroup demo && useradd -g demo -ms /bin/bash app
USER app
WORKDIR /home/app/application
COPY --from=builder tmp/dependencies/ ./
COPY --from=builder tmp/snapshot-dependencies/ ./
COPY --from=builder tmp/spring-boot-loader/ ./
COPY --from=builder tmp/application/ ./
EXPOSE 8080
ARG JAVA_OPTS=""
ENTRYPOINT ["bash", "-c", \
"java -server --enable-preview -XX:+UseContainerSupport -XX:+ShowCodeDetailsInExceptionMessages \
-XX:+AlwaysActAsServerClassMachine -XX:+UseG1GC -XX:+UseStringDeduplication ${JAVA_OPTS} \
org.springframework.boot.loader.JarLauncher ${0} ${@}"]
