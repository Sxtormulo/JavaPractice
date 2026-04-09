
import module java.base;
import module java.net.http;

/**
 * VirtualThreadDemo - A test with virtual threads
 *
 * @author Sxtormulo
 * 2026-04-01T23:23:10-06:00
 */
public class RateLimiting
{

    private static final int PERMITS = 20;
    private static final Semaphore SEMAPHORE = new Semaphore(PERMITS);
    private static final int START = 0;
    private static final int TASKS = 250;
    private static final int WAIT_TIME = 100;

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final Function<Callable<String>, Stream<Callable<String>>> produceCallableStream =
            c -> IntStream.rangeClosed(START, TASKS).mapToObj(_ -> c);

    void main(String... args) throws InterruptedException, ExecutionException
    {

        try(final var serviceExecutor =
                Executors.newVirtualThreadPerTaskExecutor();)
        {
            final List<Callable<String>> callableWords =
                    produceCallableStream
                            .apply(() -> get("https://horstmann.com/random/word"))
                            .toList();
            final var result =
                    serviceExecutor.invokeAll(callableWords).stream().map(f ->
                    {
                        try
                        {
                            return f.get();
                        }
                        catch(InterruptedException | ExecutionException interruptedException)
                        {
                            throw new IllegalStateException(interruptedException);
                        }
                    })
                            .collect(
                                    Collectors.joining(" "));
            IO.println(result);

        }
    }

    public static String get(String url)
    {
        try
        {
            final var request =
                    HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            SEMAPHORE.acquire();
            try
            {
                TimeUnit.MILLISECONDS.sleep(WAIT_TIME);
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            }
            finally
            {
                SEMAPHORE.release();
            }
        }
        catch(IOException | URISyntaxException | InterruptedException requestException)
        {
            throw new RuntimeException(requestException);
        }
    }

}
