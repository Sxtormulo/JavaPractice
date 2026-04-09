package com.sxtormtech.walkingtree;

import module java.base;
import static com.sxtormtech.walkingtree.Chmod.Who.*;
import static com.sxtormtech.walkingtree.Chmod.PosixPermission.*;
import static java.nio.file.attribute.PosixFilePermission.*;
import static java.nio.file.FileVisitResult.*;

/**
 * Chmod - Sample code that changes the permissions of files in a similar manner to the
 * chmod (1) program.l
 *
 * @author Sxtormulo
 */
public class Chmod
{

    private static final String ALL_MASK = "rwx";
    private static final int MINIMUN_ARGUMENTS = 2;

    private static final int NONE = 0;
    private static final String NO_MASK = "";
    private static final String OCTAL = "[0-7]{3}";
    private static final Pattern OCTAL_PATTERN = Pattern.compile(OCTAL);

    private static final String OPERATOR = "[+-=]";
    private static final Pattern OPERATOR_PATTERN = Pattern.compile(OPERATOR);
    private static final String RECURSIVE = "-R";
    private static final String SEPARATOR = ",";
    private static final Pattern MODES_PATTERN = Pattern.compile(SEPARATOR);
    private static final char TO_INT = '0';
    private static final String WHO_ALL = "A";
    /**
     * Supply with an enum set of PosixFilePermission
     */
    private static final Supplier<EnumSet<PosixFilePermission>> newPosixFilePermissionEnumSet =
            () -> EnumSet.noneOf(PosixFilePermission.class);

    void main(String[] args) throws IOException
    {
        if(args.length < MINIMUN_ARGUMENTS)
            usage();
        int index = 0;
        int maxDepth = 0;
        if(args[index].equals(RECURSIVE))
        {
            if(args.length < MINIMUN_ARGUMENTS + 1)
                usage();
            index++;
            maxDepth = Integer.MAX_VALUE;
        }

        var changer = compile(args[index++]);
        var changerVisitor = new TreeVisitor(changer);

        var options = EnumSet.noneOf(FileVisitOption.class);
        while(index < args.length)
        {
            var file = Path.of(args[index]);
            Files.walkFileTree(file, options, maxDepth, changerVisitor);
            ++index;
        }
    }

    /**
     * Compiles a list of one or more <em>symbolic mode expressions</em>, or a
     * expression in octal mode that may be used to change a set of file permissions.
     * This method is intended for use where file permissions are required to be changed
     * in a manner similar to the UNIX * <i>chmod</i> program.
     *
     * <p>
     * The {@code exprs} parameter is a coma separated list of expressions where each
     * takes the form:
     * <blockquote>
     * <i>who operator</i> [<i>permissions</i>]
     * </blockquote>
     * where <i>who</i> is one or more of the characters
     * {@code 'u'}, {@code 'g'}, {@code 'o'}, or {@code 'a'} meaning the owner (user),
     * group, others, or all (owner, group, &amp; others) respectively
     *
     * <p>
     * <i>operator</i> is the character {@code '+'}, {@code '-'}, or {@code '='}
     * signifying how permissions are to be changed. @code '+'} means the permissions
     * are added, {@code '-'} means the permissions are assigned absolutely.
     *
     * <p>
     * <i>permissions</i> is a sequence of zero or more fo the following: {@code 'r'}
     * for read permission, {@code 'w'} for write permission, and {@code 'x'} for
     * execute permission. If <i>permissions</i> is omitted when assigned absolutely,
     * then the permissions are cleared for the owner, group, or others as identified by
     * <i>who</i>. When omitted when adding or removing then the expression is ignored.
     *
     * <table border="">
     * <tr>
     * <td> {@code u=rw} </td>
     * <td> sets the owner permissions to be read and write. <td>
     * </tr>
     * <tr>
     * <td> {@code ug+w} </td>
     * <td> Sets the owner write and group write permissions. </td>
     * </tr>
     * <tr>
     * <td> {@code u+w, o-rwx} </td>
     * <td> Sets the owner write, and removes the others read, others write and others
     * execute permissions. </td>
     * </tr>
     * <tr>
     * <td> {@code o=} </td>
     * <td> Sets the others permission to none (others read, others write and others
     * execute permissions are removed if set) </td>
     * </tr>
     * </table>
     *
     * @param exprs List of one or more <em>mode expressions</em> as <em>symbolic
     * mode</em> or <em>octal mode</em>
     * @return A {@code Changer} that may be used to changer a set of file permissions
     * @throws IllegalArgumentException If the value of the {@code exprs} parameter is
     * invalid
     */
    public static Changer compile(String exprs)
    {
        if(exprs.length() < MINIMUN_ARGUMENTS)
        {
            throw new IllegalArgumentException("Invalid mode");
        }
        if(OCTAL_PATTERN.asMatchPredicate()
                .test(exprs))
        {
            return compileOctalMode(exprs);
        }

        return compileLetterMode(exprs);

    }

    /**
     * Calculate {@link PosixFilePermission} of the {@code symbolic mode} information
     * entered
     *
     * @param toWho the users to give access
     * @param permissions the permissions to give to the users
     * @return the enum set of {@link PosixFilePermission}
     */
    private static EnumSet<PosixFilePermission> calculatePermissions(
            final EnumSet<Who> toWho,
            final EnumSet<PosixPermission> permissions)
    {
        final var filePermissions =
                newPosixFilePermissionEnumSet.get();
        if(toWho.contains(U))
        {
            if(permissions.contains(READ)) filePermissions.add(OWNER_READ);
            if(permissions.contains(WRITE)) filePermissions.add(OWNER_WRITE);
            if(permissions.contains(EXECUTE)) filePermissions.add(OWNER_EXECUTE);
        }
        if(toWho.contains(G))
        {
            if(permissions.contains(READ)) filePermissions.add(GROUP_READ);
            if(permissions.contains(WRITE)) filePermissions.add(GROUP_WRITE);
            if(permissions.contains(EXECUTE)) filePermissions.add(GROUP_EXECUTE);
        }
        if(toWho.contains(O))
        {
            if(permissions.contains(READ)) filePermissions.add(OTHERS_READ);
            if(permissions.contains(WRITE)) filePermissions.add(OTHERS_WRITE);
            if(permissions.contains(EXECUTE)) filePermissions.add(OTHERS_EXECUTE);
        }

        return filePermissions;
    }

    /**
     * Generates a {@link Changer} with the permissions passed in {@code letter mode} as
     * <em>symbolic mode expressions</em>
     *
     * @param exprs List of one or more <em>symbolic mode expressions</em>
     * @return A {@link Changer} that may be used to changer a set of file permissions
     */
    private static Changer compileLetterMode(String exprs)
    {
        record PermissionPair(EnumSet<PosixFilePermission> add,
                              EnumSet<PosixFilePermission> remove)
                {

        }

        Function<String[], PermissionPair> producePermissionPair;
        producePermissionPair =
                (t) ->
        {
            if(t.length < MINIMUN_ARGUMENTS)
            {
                throw new IllegalArgumentException("Invalid mode");
            }
            final var toadd = newPosixFilePermissionEnumSet.get();
            final var toRemove = newPosixFilePermissionEnumSet.get();
            Operator operation;
            String mask = (t.length == MINIMUN_ARGUMENTS) ? NO_MASK : t[2];
            final var modes = t[0];
            final var operator = t[1];

            final EnumSet<Who> toWho;
            try
            {

                toWho = modes.codePoints()
                        .mapToObj(Character::toString)
                        .map(String::toUpperCase)
                        .map((w) ->
                        {
                            if(w.equals(WHO_ALL)) return Who.A;
                            else return EnumSet.of(Who.valueOf(w));
                        })
                        .collect(() -> EnumSet.noneOf(Who.class), (s, w) -> s
                                 .addAll(w), (s1, s2) -> s1.addAll(s2));
            }
            catch(IllegalArgumentException iae)
            {
                throw new IllegalArgumentException("Invalid mode");
            }
            if(toWho.isEmpty())
            {
                throw new IllegalArgumentException("Invalid mode");
            }

            operation = Operator.of(operator.charAt(0));
            if(operation.equals(Operator.ASSIGN) && mask.equals(NO_MASK))
            {
                operation = Operator.REMOVE;
                mask = ALL_MASK;
            }

            final var permissions =
                    mask.chars()
                            .mapToObj(p -> PosixPermission.of((char) p))
                            .collect(Collectors.toCollection(() -> EnumSet.noneOf(
                                    PosixPermission.class)
                            ));
            switch(operation)
            {
                case ADD ->
                {
                    toadd.addAll(calculatePermissions(toWho, permissions));
                }
                case REMOVE ->
                {
                    toRemove.addAll(calculatePermissions(toWho, permissions));
                }
                case ASSIGN ->
                {
                    toadd.addAll(calculatePermissions(toWho, permissions));
                    toRemove.addAll(EnumSet.complementOf(toadd));
                }
            }
            return new PermissionPair(toadd, toRemove);
        };
        //I iterate over each of expression modes
        final var permissionPair =
                MODES_PATTERN.splitAsStream(exprs)
                        .map(e -> OPERATOR_PATTERN
                                .splitWithDelimiters(e, NONE))
                        .map(producePermissionPair)
                        .collect(() -> new PermissionPair(
                                newPosixFilePermissionEnumSet
                                        .get(), newPosixFilePermissionEnumSet.get()),
                                 (s1, ns) ->
                         {
                             s1.add()
                                     .addAll(ns.add());
                             s1.remove()
                                     .addAll(ns.remove());
                         }, (s1, s2) ->
                         {
                             s1.add()
                                     .addAll(s2.add());
                             s1.remove()
                                     .addAll(s2.remove());
                         });
        return (perms) ->
        {
            final EnumSet<PosixFilePermission> permsCopy = EnumSet.copyOf(perms);
            permsCopy.addAll(permissionPair.add());
            permsCopy.removeAll(permissionPair.remove());
            return permsCopy;
        };
    }

    /**
     * Generates a {@link Changer} with the permissions passed in {@code octal mode} as
     * <em>octal mode expressions</em>
     *
     * @param exprs Octal numbers that represent the {@code modes}
     * @return A {@link Changer} that may be used to changer a set of file permissions
     */
    private static Changer compileOctalMode(String exprs)
    {
        final var permissionsToChange =
                newPosixFilePermissionEnumSet.get();
        final var posixPermissions =
                exprs.codePoints()
                        .map(o -> o - TO_INT)
                        .mapToObj(PosixPermission::of)
                        .toList();
        permissionsToChange.addAll(calculatePermissions(EnumSet.of(U),
                                                        posixPermissions.get(0)));
        permissionsToChange.addAll(calculatePermissions(EnumSet.of(G),
                                                        posixPermissions.get(1)));
        permissionsToChange.addAll(calculatePermissions(EnumSet.of(O),
                                                        posixPermissions.get(2)));
        return (perms) ->
        {
            return permissionsToChange;
        };
    }

    /**
     * Changes the permissions of the file using the given Changer
     *
     * @param file the file to change the permissions
     * @param changer the changer with the new permissions
     */
    static void chmod(Path file, Changer changer)
    {
        try
        {
            final Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(
                    file);
            Files.setPosixFilePermissions(file, changer.change(permissions));
        }
        catch(IOException iOException)
        {
            System.err.println(iOException);
        }
    }

    /**
     * Displas the usage of the app in the command line
     */
    static void usage()
    {
        System.err.println(
                """
                                java Chmod [-R] symbolic-mode-list file....
                                or: java Chmod [-R] octal-mode file
                                           """);
    }

    static class TreeVisitor implements FileVisitor<Path>
    {

        private final Changer changer;

        public TreeVisitor(Changer changer)
        {
            this.changer = changer;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path directory,
                                                 BasicFileAttributes attributes)
        {
            chmod(directory, changer);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes)
        {
            chmod(file, changer);
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path directory,
                                                  IOException ioException)
        {
            if(ioException == null)
                System.err.println("WARNING " + ioException);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException iOException)
        {
            System.err.println("WARNING " + iOException);
            return CONTINUE;
        }

    }

    /**
     * A task that <i>changes</i> a set of {@link PosixFilePermission} elements
     */
    @FunctionalInterface
    public interface Changer
    {

        /**
         * Applies the changes to the given set of permissions.
         *
         * @param perms the set of permissions to change
         * @return the {@code perms} parameter
         */
        Set<PosixFilePermission> change(Set<PosixFilePermission> perms);
    }

    /**
     * Defines the characters and octals that represent the modes in the
     * {@link Chmod#compile} method.
     */
    enum PosixPermission
    {
        /// Represent the __execute__ permission in _letter_ and _octal_ mode
        EXECUTE(0, 'x'),
        /// Represents the __write__ permission in _letter_ and _octal_mode
        WRITE(1, 'w'),
        /// Represents the __read__ permission in _letter_ and _octal_ mode
        READ(2, 'r');
        /// Represent a [EnumSet] of all permission modes
        public static final EnumSet<PosixPermission> ALL_MODES = EnumSet.allOf(
                PosixPermission.class);
        private static final int executeOctal = EXECUTE.mode.octal();
        private static final int writeOctal = WRITE.mode.octal();
        private static final int readOctal = READ.mode.octal();
        private static final PosixPermission[] VALUES = PosixPermission.values();
        private static final int ALL_PERMISSIONS = ALL_MODES.stream()
                .mapToInt(p -> p.mode.octal())
                .sum();
        /// Represent the combination of the _letter_ and _octal_ mode
        final Mode mode;

        /// Defines the representation of the _letter_, _octal_ mode pair
        /// @param octal the file permissions in octal
        /// @param letter the file permissions in symbolic mode
        private PosixPermission(int octal, char letter)
        {
            this.mode = new Mode(octal, letter);
        }

        /**
         * Creates an enum set initially containing the specified elements by their
         * <em>octal</em> values
         *
         * @param octalMode the {@code modes} represented <em>octal</em> form
         * @return a enum set initially containing the combination of their octal values
         */
        public static EnumSet<PosixPermission> of(int octalMode)
        {
            if(octalMode > ALL_PERMISSIONS)
            {
                throw new IllegalArgumentException("Invalid mode: " + octalMode);
            }
            final var modes = EnumSet.noneOf(PosixPermission.class);
            if((octalMode & executeOctal) == executeOctal)
            {
                modes.add(EXECUTE);
            }
            if((octalMode & writeOctal) == writeOctal)
            {
                modes.add(WRITE);
            }
            if((octalMode & readOctal) == readOctal)
            {
                modes.add(READ);
            }
            return modes;
        }

        /**
         * Returns the enum constant of this class that represent the specified letter
         * in <em>letter</em> permission mode
         *
         * @param letter the letter representing the {@code mode} to be returned
         * @return the enum constant with the specified {@code mode}
         */
        public static PosixPermission of(char letter)
        {
            return Arrays.stream(VALUES)
                    .filter(p -> p.mode.letter() == letter)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid mode: " + letter));
        }

         /// Defines the representation of the _letter_, _octal_ mode pair
        /// @param octal the file permissions in octal
        /// @param letter the file permissions in symbolic mode
        record Mode(int octal, char letter)
                {

            Mode
            {
                octal = 1 << octal;
            }
        }

    }

    /**
     * Defines the characters that represents the who in the {@link Chmod#compile }
     * method.
     */
    enum Who
    {
        /**
         * Owner (user) character.
         */
        U,
        /**
         * Group character.
         */
        G,
        /**
         * Others character.
         */
        O;
        /**
         * All character - owner (user), group, others.
         */
        public static final EnumSet<Who> A = EnumSet.allOf(Who.class);
    }

    /**
     * Defines the symbol that represent the operation to be executed in
     * {@link Chmod#compile}.
     */
    enum Operator
    {
        /**
         * Add the specified permissions.
         */
        ADD('+'),
        /**
         * Remove the specified permissions
         */
        REMOVE('-'),
        /**
         * Add absolutely (only the specified, remove the rest) the permissions
         */
        ASSIGN('=');
        private final char symbol;
        private static final Operator[] VALUES = values();

        private Operator(char symbol)
        {
            this.symbol = symbol;
        }

        /**
         * Get the value of symbol
         *
         * @return the value of symbol
         */
        public char getSymbol()
        {
            return symbol;
        }

        /**
         * A fabric method to get an operator from the entered symbol
         *
         * @param symbol the symbol of the operator to be matched
         * @return the resulting {@code Operator}
         */
        public static Operator of(char symbol)
        {

            return Arrays.stream(VALUES)
                    .filter(o -> o.getSymbol() == symbol)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid mode: " + symbol));
        }

    }

}
