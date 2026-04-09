
import module java.base;

/**
 * Factorias - Display the factorial for each number to the entered
 *
 * @author Sxtormulo
 * 2026-03-20T12:11:34-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: A limit numer
 * Output: The factorial of each number until the entered
 *
 * Get the user input
 * Use it as a limit
 * For each number, calculate the factorial
 * And print each until the limit
 */
public class Factorials
{

    void main()
    {
        final int userLimit;
        int userInput;

        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter a positive number, between %d and %d ".formatted(
                                MINIMUN_RANGE, MAXIMUN_RANGE)
                        + "that indicates the limit to which calculate the factorials>>> "
                ));
                if(userInput < MINIMUN_RANGE || userInput > MAXIMUN_RANGE)
                {
                    throw new InputMismatchException(
                            "Number" + userInput + " it is outside of range");
                }
                break;
            }
            catch(NumberFormatException inputException)
            {
                IO.println(
                        inputException.getMessage() + ". Input it is outside of range (%d-%d)"
                        .formatted(MINIMUN_RANGE, MAXIMUN_RANGE));
            }

        }
        userLimit = userInput;
        IntStream.rangeClosed(MINIMUN_RANGE, userLimit)
                .mapToLong(i -> LongStream.rangeClosed(MINIMUN_RANGE, i)
                        .reduce(MINIMUN_RANGE, (accumulated, newFactor) ->
                                accumulated * newFactor))
                .mapToObj(Long::toUnsignedString)
                .forEach(IO::println);

    }
    static final int MAXIMUN_RANGE = 22;
    static final int MINIMUN_RANGE = 1;

}
