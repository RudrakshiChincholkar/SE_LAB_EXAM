package control;

import java.util.concurrent.TimeUnit;

public final class SessionMonitor {
    private static final long MAX_DURATION_MS = TimeUnit.MINUTES.toMillis(120);
    private static Long startTimeMs = null;
    private static boolean locked = false;

    private SessionMonitor() {}

    public static void startSession() {
        startTimeMs = System.currentTimeMillis();
        locked = false;
    }

    public static boolean isSessionActive() {
        if (locked) {
            return false;
        }
        if (startTimeMs == null) {
            return false;
        }
        long elapsed = System.currentTimeMillis() - startTimeMs;
        return elapsed < MAX_DURATION_MS;
    }

    public static void lockSession() {
        locked = true;
    }

    static void resetForTests() {
        startTimeMs = null;
        locked = false;
    }
}

