FROM koosiedemoer/netty-tcnative-alpine
VOLUME /tmp
EXPOSE 8083
ADD target/*.jar app.jar
ADD private-key.json private-key.json
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]