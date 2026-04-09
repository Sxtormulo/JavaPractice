
import module java.base;

/**
 * DiagonalNums - Print a diagonal of then numbers with the entered number
 *
 * @author Sxtormulo
 * 2026-03-22T20:33-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: One number
 * Output: a printed diagonal of that number
 *
 * Get the number from the user
 * Print then lines with the number
 * For each line add a new space before the number
 * Until the last line, with nine spaces
 */
public class DiagonalNums
{

    static final int START = 0;
    static final String SPACE = " ";
    static final String NONE = "";
    static final int LINE_LIMIT = 10;

    private static final BiFunction<Integer, Integer, String> createDiagonalPattern =
            (line, number) ->
    {
        return IntStream.rangeClosed(START, line)
                .mapToObj(spaces -> spaces > START ? SPACE : NONE)
                .collect(Collectors.collectingAndThen(Collectors.joining(), pattern ->
                                                      pattern + number));
    };

    void main()
    {
        final int diagonalNumber;
        int userInput = getDiagonalNumber();

        IO.println("We are going to print a diagonal with the numbered entered");
        diagonalNumber = userInput;

        IntStream.rangeClosed(START, LINE_LIMIT)
                .mapToObj(i -> createDiagonalPattern.apply(i, diagonalNumber))
                .forEach(IO::println);

    }

    int getDiagonalNumber()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln("Enter the number>>> "));
                break;
            }
            catch(NumberFormatException numberFormatException)
            {
                IO.println(
                        numberFormatException.getMessage() + ". Your input is invalid!");
            }
        }
        return userInput;
    }
}
