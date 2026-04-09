void main() throws InterruptedException
{
    TimeUnit.SECONDS.sleep(10);
    try(final var executorService = Executors.newVirtualThreadPerTaskExecutor())
    {
        final var callables = IntStream.rangeClosed(0,10_000_000).<Runnable>mapToObj(_ ->(()->
        {
            final var id = Thread.currentThread().threadId();
            IO.println(id);
        })).map(Executors::callable).toList();
       executorService.invokeAll(callables);
    }
}
