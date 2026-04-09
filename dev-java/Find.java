
import module java.base;

/**
 * Find - Finds the files matching the passed wildcard
 *
 * @author Sxtormulo
 * @code java Find . -name "*.java"
 */
public class Find
{

    static void usage()
    {
        System.err.println("java Find <Path>"
                + " -name \"<glob_pattern>\"");
        System.exit(-1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException
    {
        if(args.length < ARGS_NEEDED || !args[FLAG].equals("-name"))
        {
            usage();
        }

        Path startingDir = Path.of(args[PATH]);
        String pattern = args[PATTERN];
        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, EnumSet.of(FileVisitOption.FOLLOW_LINKS),
                           Integer.MAX_VALUE, finder);
        finder.done();

    }
    private static final int PATTERN = 2;
    private static final int PATH = 0;
    private static final int FLAG = 1;
    private static final int ARGS_NEEDED = 3;

    public static class Finder extends SimpleFileVisitor<Path>
    {

        private int matchesCount = 0;

        Finder(String pattern)
        {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
        }
        private final PathMatcher matcher;

        /**
         * Compares the glob pattern against the file or directory name.
         *
         * @param file - The file that are going to be matched
         */
        void find(Path file)
        {
            Path name = file.getFileName();
            if(name != null && matcher.matches(name))
            {
                ++matchesCount;
                IO.println(file);
            }
        }

        /**
         * Prints the total number of matches to standard out.
         *
         * @
         */
        void done()
        {
            IO.println("Matched: " + matchesCount);
        }

        /**
         * Invoke the pattern matching method on each file
         *
         * @param file - the file to be matched
         * @param attrs - the file's basic attributes
         * @return the visit result
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
            find(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc)
        {
            System.err.println(exc);
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoke the pattern matching method on each directory
         *
         * @param dir - the directory to be visited
         * @param attrs - the directory's basic attributes
         * @return
         * @throws IOException
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        {
            find(dir);
            return FileVisitResult.CONTINUE;
        }

    }
}
