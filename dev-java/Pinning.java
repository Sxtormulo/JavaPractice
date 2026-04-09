
import module java.base;
import module java.net.http;

/**
 * VirtualThreadDemo - A test with virtual threads
 *
 * @author Sxtormulo
 * 2026-04-01T23:23:10-06:00
 */
public class Pinning
{

    private static final long NANOS = TimeUnit.SECONDS.toNanos(1);
    private static final int START = 0;

    private static final int TASK = 20;
    private static Lock lock = new ReentrantLock();

    void main(String[] args)
    {

        final Instant start = Instant.now();
        try(var executorService = Executors.newCachedThreadPool())
//        try(var executorService = Executors.newVirtualThreadPerTaskExecutor())
        {
            IntStream.range(START, TASK).
                    //                    forEach(_ -> executorService.submit(() -> block()));
                    forEach(_ -> executorService.submit(() -> rblock()));
            IntStream.range(START, TASK).
                    forEach(_ -> executorService.submit(() -> noblock()));
        }
        final Instant end = Instant.now();
        IO.println(TimeUnit.NANOSECONDS
                .toMillis(Duration.between(start, end).getNano()));
    }

    public static synchronized void block()
    {
        IO.println("Entering block " + Thread.currentThread());
        LockSupport.parkNanos(NANOS);
        IO.println("Exiting block " + Thread.currentThread());
    }

    public static void noblock()
    {
        IO.println("Entering noblock " + Thread.currentThread());
        LockSupport.parkNanos(NANOS);
        IO.println("Exiting noblock " + Thread.currentThread());
    }

    public static void rblock()
    {
        lock.lock();
        try
        {
            IO.println("Entering rblock " + Thread.currentThread());
            LockSupport.parkNanos(NANOS);
            IO.println("Exiting rblock " + Thread.currentThread());
        }
        finally
        {
            lock.unlock();
        }
    }
}
