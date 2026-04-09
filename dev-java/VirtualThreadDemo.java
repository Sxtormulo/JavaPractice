
import module java.base;
import module java.net.http;

/**
 * VirtualThreadDemo - A test with virtual threads
 *
 * @author Sxtormulo
 * 2026-04-01T22:58:40-06:00
 */
public class VirtualThreadDemo
{

    private static final HttpClient client = HttpClient.newHttpClient();

    void main(String... args) throws InterruptedException, ExecutionException
    {
        try(final var serviceExecutor =
                Executors.newVirtualThreadPerTaskExecutor();)
        {
            final var futureGet1 =
                    serviceExecutor.submit(() -> get(
                            "https://horstmann.com/random/adjective"));
            final var futureGet2 =
                    serviceExecutor.submit(() -> get(
                            "https://horstmann.com/random/noun"));
            final var result = futureGet1.get() + " " + futureGet2.get();
            IO.println(result);
        }
    }

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
