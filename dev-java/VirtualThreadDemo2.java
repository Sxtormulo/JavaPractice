
import module java.base;
import module java.net.http;

/**
 * VirtualThreadDemo - A test with virtual threads
 *
 * @author Sxtormulo
 * 2026-04-01T23:23:10-06:00
 */
public class VirtualThreadDemo2
{

    private static final int ADJETIVES = 3;
    private static final int START = 0;

    private static final HttpClient client = HttpClient.newHttpClient();

    void main(String... args) throws InterruptedException, ExecutionException
    {

        try(final var serviceExecutor =
                Executors.newVirtualThreadPerTaskExecutor();)
        {
            final var callables = Stream.concat(
                    produceCallableStream.apply(() -> get(
                            "https://horstmann.com/random/adjective")),
                    produceCallableStream.apply(() -> get(
                            "https://horstmann.com/random/noun")))
                    .toList();
            final var result =
                    serviceExecutor.invokeAll(callables).stream().map(f ->
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
    private static final Function<Callable<String>, Stream<Callable<String>>> produceCallableStream =
            c -> IntStream.rangeClosed(START, ADJETIVES).mapToObj(_ -> c);

    public static String get(String url)
    {
        try
        {
            final HttpRequest request =
                    HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        }
        catch(IOException | URISyntaxException | InterruptedException requestException)
        {
            throw new RuntimeException(requestException);
        }
    }

}
