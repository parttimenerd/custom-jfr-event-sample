Custom JFR Events Sample Program
--------------------------------
Sample program for my [blog post](https://mostlynerdless.de/blog/2023/11/20/custom-jfr-events-a-short-introduction/)
on Custom JFR Events.

### Build
```
mvn clean package
```

### Run
```
java -XX:StartFlightRecording:filename=recording.jfr -jar target/sample-server.jar
```

### Inspect Recording
```
jfr print --events server.SessionEvent profile.jfr

server.SessionEvent {
  startTime = 15:39:45.918 (2023-11-20)
  duration = 0,231 ms
  sessionId = 683435712
  n = 9
  eventThread = "qtp795321555-46" (javaThreadId = 46)
  stackTrace = [
    ...
  ]
}
...
```

License
-------
MIT, Copyright 2023 SAP SE or an SAP affiliate company, Johannes Bechberger
and custom JFR event sample contributors