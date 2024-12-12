FROM sapmachine:21-jre-ubuntu
COPY target/sample-server.jar /sample-server.jar
ADD https://github.com/pyroscope-io/pyroscope-java/releases/download/v0.13.1/pyroscope.jar /app/pyroscope.jar
# This is the port that your javalin application will listen on
EXPOSE 7070 9096
ENTRYPOINT ["java", "-Dcom.sun.management.jmxremote.port=9096", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "-XX:+UnlockDiagnosticVMOptions", "-XX:+DebugNonSafepoints" , "-XX:FlightRecorderOptions=stackdepth=512", "-jar", "/sample-server.jar", "--no-tester"]
