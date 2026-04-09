
import module java.base;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;

/**
 * WatchDir - Example to watch a directory (or tree) for changes to files.
 *
 * @author Sxtormulo
 * Copyright 2026-04-05T22:41-06:00
 */
public class WatchDir
{

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean recursive;
    private boolean trace = false;

    public WatchDir(Path directory, boolean recursive) throws IOException
    {
        watcher = FileSystems.getDefault().newWatchService();
        keys = new HashMap<>();
        this.recursive = recursive;
        if(recursive)
        {
            IO.println("Scanning %s ...".formatted(directory));
            registerAll(directory);
            IO.println("Done.");
        }
        else
        {
            register(directory);
        }
        // enable trace after initial registration
        this.trace = true;
    }

    /**
     * Try to cast the current event to {@link WatchEvent} type
     *
     * @param <T>
     * @param event
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event)
    {
        return (WatchEvent< T>) event;
    }

    /**
     * Register the given directory with the {@link WatchService}
     *
     * @param directory the directory to track for changes
     * @throws IOException if an {@link IOException} occurs
     */
    private void register(Path directory) throws IOException
    {
        final var key = directory.
                register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if(trace)
        {
            final var prev = keys.get(key);
            if(prev == null)
            {
                IO.println("register: %s".formatted(directory));
            }
            else if(!directory.equals(prev))
            {
                IO.println("update: %s -> %s".formatted(prev, directory));
            }
        }
        keys.put(key, directory);
    }

    /**
     * Register the given directory, and all its sub-directories with the
     * {@link WatchService}
     *
     * @param start the directory from when start recursion
     * @throws IOException if an {@link IOException} occurs
     */
    private void registerAll(final Path start) throws IOException
    {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>()
                   {
                       @Override
                       public FileVisitResult preVisitDirectory(Path dir,
                                                                BasicFileAttributes attrs)
                               throws IOException
                       {
                           register(dir);
                           return FileVisitResult.CONTINUE;
                       }

                   });
    }

//    @SuppressWarnings("unchecked")
    void processEvents()
    {
        record eventChild(WatchEvent.Kind kind, Path child)
                {

        }
        while(true)
        {
            // wait for key to be signalled
            WatchKey key;
            try
            {
                key = watcher.take();
            }
            catch(InterruptedException e)
            {
                return;
            }
            final var directory = keys.get(key);
            if(directory == null)
            {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
            // TBD - provide example of how OVERFLOW event is handled
            final var noOverflowEvents =
                    key.pollEvents().stream().filter(e -> e.kind() != OVERFLOW)
                            .toList();
            final var eventChilds =
                    noOverflowEvents.stream().map(e ->
                    {
                        WatchEvent<Path> event = cast(e);
                        var pathName = event.context();
                        return new eventChild(e.kind(), directory
                                              .resolve(pathName));
                    }).toList();
            /*
             * print out event and if directory is created, and watching
             * recursively, then register it and its sub-directories
             */
            eventChilds.forEach(ec ->
            {
                IO.println("%s: %s".formatted(ec.kind().name(), ec.child()));
                if(recursive && (ec.kind() == ENTRY_CREATE))
                {
                    try
                    {
                        if(Files.isDirectory(ec.child(),
                                             NOFOLLOW_LINKS))
                        {
                            registerAll(ec.child());
                        }
                    }
                    catch(IOException iOException)
                    {
                        System.err.println(iOException.getMessage());
                    }
                }
            });
            // reset key and remove from set if directory no longer accessible
            final var valid = key.reset();
            if(!valid)
            {
                keys.remove(key);
                // all directories are inaccesible
                if(keys.isEmpty())
                {
                    break;
                }
            }
        }
    }

    static void usage()
    {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException
    {
        if(args.length == NONE || args.length > MAX_ARGS)
        {
            usage();
        }

        boolean recursive = false;
        int index = INITIAL;
        if(args[INITIAL].equals(RECURSIVE))
        {
            if(args.length < MAX_ARGS)
            {
                usage();
            }
            recursive = true;
            ++index;
        }

        final var directory = Paths.get(args[index]);
        new WatchDir(directory, recursive).processEvents();
    }
    private static final int INITIAL = 0;
    private static final String RECURSIVE = "-r";
    private static final int MAX_ARGS = 2;
    private static final int NONE = 0;
}
