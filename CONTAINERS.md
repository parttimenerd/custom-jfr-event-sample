Working with Containers
=======================

To publish this application on Docker Hub:

```sh
mvn package
docker build -t custom-jfr-event-sample:$VERSION
docker tag custom-jfr-event-sample:$VERSION $YOUR_DOCKERHUB_USER/custom-jfr-event-sample:$VERSION
docker login  # if you aren't logged in already
docker push $YOUR_DOCKERHUB_USER/custom-jfr-event-sample:$VERSION
```

You can find a published version at [parttimenerd/custom-jfr-event-sample](https://hub.docker.com/r/parttimenerd/custom-jfr-event-sample).
