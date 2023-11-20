package server;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscriber;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        startTester();
        Javalin.create(conf -> {
                conf.jetty.server(() ->
                    new Server(new QueuedThreadPool(4))
                );
                })
                .get("/fib/{fib}", ctx -> {
                    handleRequest(ctx, newSessionId());
                })
                .start(7070);
        System.in.read();
    }

    static void handleRequest(Context ctx, int sessionId) {
        int n = Integer.parseInt(ctx.pathParam("fib"));
        System.out.printf("Handle session %d n = %d\n", sessionId, n);
        var event = new SessionEvent(sessionId, n);
        event.begin();
        ctx.result("fibonacci: " + fib(n));
        event.commit();
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    static int newSessionId() {
        return new Random().nextInt();
    }

    static void startTester() {
        new Thread(() -> {
            while (true) {
                int val = new Random().nextInt(40);
                try {
                    var request = HttpRequest.newBuilder(URI.create("http://localhost:7070/fib/" + val)).build();
                    HttpClient.newHttpClient().sendAsync(request, responseInfo -> {
                        return HttpResponse.BodySubscribers.discarding();
                    });
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}