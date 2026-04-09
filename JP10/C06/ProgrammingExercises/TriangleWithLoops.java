
import module java.base;

/**
 * TriangleWithLoops - Print a triangle with a pattern of the number entered
 *
 * @author Sxtormulo
 * 2026-03-22T21:22-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: One number
 * Output: Print a triangle of seven lines, with a increasing number pattern in each
 * line
 *
 * Get the number from the user
 * Start with seven spaces and one number
 * For each line
 * reduce a space and enter two more numbers
 * until reaching seven lines
 */
public class TriangleWithLoops
{

    static final int NUMBER_OF_LINES = 6;
    static final int START = 0;
    static final int SIDES = 2;
    static final int CENTER = 1;
    static final int UPPER_LIMIT = 9;

    private static final IntFunction<String> getSpacePattern = (line) ->
    {
        return IntStream.iterate(NUMBER_OF_LINES - line, space -> space > START,
                                 space -> --space)
                .mapToObj(space -> " ")
                .collect(Collectors.joining());
    };

    private static final BiFunction<Integer, Integer, String> getNumberPattern =
            (line, numer) ->
    {
        int numberInLine = (line * SIDES) + CENTER;
        return IntStream.range(START, numberInLine)
                .mapToObj(b -> Integer.toString(numer))
                .collect(Collectors.joining());
    };

    static final String NEW_LINE = "%n".formatted();

    void main()
    {
        int numberPattern;
        int userInput;
        String trianglePattern;

        IO.println("Application to display a triangle pattern");
        userInput = getUserNumber();
        numberPattern = userInput;

        trianglePattern = IntStream.rangeClosed(START, NUMBER_OF_LINES)
                .mapToObj(i -> getSpacePattern.apply(i) + getNumberPattern.apply(i,
                                                                                   numberPattern))
                .collect(Collectors.joining(NEW_LINE));

        IO.println("Your pattern in" + NEW_LINE + trianglePattern);
    }

    int getUserNumber()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter the number to get the pattern(0-9)>>> "));
                if(userInput < START || userInput > UPPER_LIMIT)
                {
                    throw new InputMismatchException(
                            "Your number need to be between %d and %d; you entered %d"
                                    .formatted(START, UPPER_LIMIT, userInput));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + ". Invalid input!");
            }
        }
        return userInput;
    }
}
