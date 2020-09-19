FROM koosiedemoer/netty-tcnative-alpine
VOLUME /tmp
EXPOSE 8082
ADD target/*.jar app.jar
ADD service-account.json service-account.json
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]