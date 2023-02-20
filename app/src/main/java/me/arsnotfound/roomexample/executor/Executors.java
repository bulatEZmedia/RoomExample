package me.arsnotfound.roomexample.executor;

import java.util.function.Function;

public class Executors {
    private static final java.util.concurrent.Executor IO_THREAD =
            java.util.concurrent.Executors.newSingleThreadExecutor();

    public static void ioThread(Runnable f) {
        IO_THREAD.execute(f);
    }
}
