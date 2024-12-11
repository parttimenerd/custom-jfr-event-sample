FROM sapmachine:23-jre-ubuntu
COPY target/sample-server.jar /sample-server.jar
# This is the port that your javalin application will listen on
EXPOSE 7070 9096
ENTRYPOINT ["java", "-Dcom.sun.management.jmxremote.port=9096", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "-XX:+UnlockDiagnosticVMOptions", "-XX:+DebugNonSafepoints" , "-XX:FlightRecorderOptions=stackdepth=512", "-jar", "/sample-server.jar", "--no-tester"]
