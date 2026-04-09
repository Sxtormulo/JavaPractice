
import module java.base;

/**
 * Copy - Copy the contents of a directory recursively
 *
 * @author Sxtormulo
 *
 * @code java Copy . new -depth 4
 */
public class Copy
{

    /**
     * A {@code FileVisitor} that finds all files
     * that match the specified pattern
     */
    public static class Replicator extends SimpleFileVisitor<Path>
    {

        Path source;
        Path destination;
        long count = 0L;

        /**
         * Copy recursively from {@code source} to {@code destination}
         *
         * @param source the place to start copying
         * @param destination the place to copy the files
         */
        public Replicator(Path source, Path destination)
        {
            this.source = source;
            this.destination = destination;
        }

        /**
         * Prints the total number of files copied to standard out
         */
        void done() throws IOException
        {
            IO.println("Number of files copied: " + count);
        }

        /**
         * Copy a file in destination
         *
         * @param file the file to be copied
         * @param attrs the file's attributes
         * @return the visits result
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
            IO.println("Copy file: " + file);
            Path newFile = destination.resolve(source.relativize(file));
            try
            {
                Files.copy(file, newFile);
                count++;
            }
            catch(IOException ioException)
            {
                System.err.println("Error: " + ioException.getMessage());
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoke copy of a directory
         *
         * @param dir the directory to be copied
         * @param attrs the directory's attributes
         * @return the visit result
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        {
            IO.println("Copy directory: " + dir);
            Path targetDir = destination.resolve(source.relativize(dir));
            try
            {
                Files.copy(dir, targetDir, StandardCopyOption.REPLACE_EXISTING,
                           StandardCopyOption.COPY_ATTRIBUTES);
            }
            catch(IOException iOException)
            {
                System.err.println(
                        "Unable to create " + targetDir + " [" + iOException + "] ");
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Set the last modified time of the destination directory to that of the source
         * directory
         *
         * @param dir the directory copied
         * @param exc the exception, if any, of the previous methods
         * @return the visit result
         * @throws IOException the exception caused by the previous methods
         */
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws
                IOException
        {
            if(exc == null)
            {
                Path destinantion = this.destination.resolve(source.relativize(dir));
                try
                {
                    FileTime time = Files.getLastModifiedTime(dir);
                    Files.setLastModifiedTime(destinantion, time);
                }
                catch(IOException iOException)
                {
                    System.err.println(
                            "Unable to copy all attributes to: " + destinantion + " [" + iOException + "] ");
                }
            }
            else
            {
                throw exc;
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoked for a file that could not be visited.
         *
         * @param file a reference to the file
         * @param exc the I/O exception that prevented the file from being visited
         * @return the visit result
         */
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc)
        {
            if(exc instanceof FileSystemLoopException)
            {
                System.err.println("cycle detected: " + file);
            }
            else
            {
                System.err
                        .println("Unable to copy:" + " %s: %s%n%".formatted(file, exc));
            }
            return FileVisitResult.CONTINUE;
        }

    }

    static void usage()
    {
        System.err.println("java Copy <source> <destination> -depth \"max_level_dir\"");
        System.exit(-1);
    }

    void main(String... args) throws IOException
    {
        if(args.length < REQUIRED_PARAMS || !args[FLAG].equals("-depth"))
        {
            usage();
        }
        Path source = Path.of(args[SOURCE]);
        Path destination = Path.of(args[Copy.DESTINATION]);
        int depth = Integer.parseInt(args[DEPTH]);

        var walkReplicating = new Replicator(source, destination);
        var visitOptions = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(source, visitOptions, depth, walkReplicating);
        walkReplicating.done();
    }
    private static final int DEPTH = 3;
    private static final int DESTINATION = 1;
    private static final int SOURCE = 0;
    private static final int FLAG = 2;
    private static final int REQUIRED_PARAMS = 4;

}
