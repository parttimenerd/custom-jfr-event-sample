FROM sapmachine:23-jre-ubuntu
COPY sampler-server.jar /sample-server.jar
# This is the port that your javalin application will listen on
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "/sample-server.jar"]