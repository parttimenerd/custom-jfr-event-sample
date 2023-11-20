package server;

import jdk.jfr.Event;

public class SessionEvent extends Event {
    int sessionId;
    int n;

    public SessionEvent(int sessionId, int n) {
        this.sessionId = sessionId;
        this.n = n;
    }
}
