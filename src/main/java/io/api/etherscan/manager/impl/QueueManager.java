package io.api.etherscan.manager.impl;

import io.api.etherscan.manager.IQueueManager;

import java.util.concurrent.*;

/**
 * Queue Semaphore implementation with size and reset time as params
 * 
 * @see IQueueManager
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class QueueManager implements IQueueManager, AutoCloseable {

    public static final QueueManager DEFAULT_KEY_QUEUE = new QueueManager(1, 7);
    public static final QueueManager PERSONAL_KEY_QUEUE = new QueueManager(5, 1100L, 1100L, 5);

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Semaphore semaphore;

    public QueueManager(int size, int resetInSec) {
        this(size, resetInSec, resetInSec);
    }

    public QueueManager(int size, int queueResetTimeInSec, int delayInSec) {
        this(size, queueResetTimeInSec, delayInSec, size);
    }

    public QueueManager(int size,
                        int queueResetTimeInSec,
                        int delayInSec,
                        int initialSize) {
        this(size,
                (long) queueResetTimeInSec * 1000,
                (long) delayInSec * 1000,
                initialSize);
    }

    public QueueManager(int size,
                        long queueResetTimeInMillis,
                        long delayInMillis,
                        int initialSize) {
        this.semaphore = new Semaphore(initialSize);
        this.executorService.scheduleAtFixedRate(releaseLocks(size), delayInMillis, queueResetTimeInMillis,
                TimeUnit.MILLISECONDS);
    }

    @Override
    public void takeTurn() {
        semaphore.acquireUninterruptibly();
    }

    private Runnable releaseLocks(int toRelease) {
        return () -> semaphore.release(toRelease);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }
}
