
import module java.base;

/**
 * Perfect - Get the perfect numbers for each number until the entered upper limit
 *
 * @author Sxtormulo
 * 2026-03-20T16:49:34-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: The upper limit
 * Output: Perfec numbers from one to the upper limit
 *
 * Ask for the upper limit
 * Validate it
 *
 */
public class Perfect
{

    static final int EVEN_STEP = 1;
    static final int ODD_STEP = 2;

    static final int LOWER_LIMIT = 1;

    void main()
    {
        final int upperLimit;
        upperLimit = getUpperLimit();

        final IntBinaryOperator addOppositeDivisors = (a, b) ->
        {
            return a + (b / a);
        };
        final IntUnaryOperator calculateSumOfDivisors = number ->
        {
            int limit = (int) Math.sqrt(number);
            int step = (number % 2 == 0) ? EVEN_STEP : ODD_STEP;
            //Skip the first, because we do not want sum the number with itself
            //after add the skiped one to the end of the stream
            return IntStream.iterate(LOWER_LIMIT, f -> f <= limit, f -> f += step)
                    .skip(1)
                    .filter(i -> number % i == 0)
                    .map(i -> addOppositeDivisors.applyAsInt(i, number))
                    .sum() + 1;
        };
        IntStream.rangeClosed(LOWER_LIMIT, upperLimit)
                .unordered()
                .parallel()
                .filter(i -> i == calculateSumOfDivisors.applyAsInt(i))
                .forEach(IO::println);
    }

    int getUpperLimit()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln("Enter the upper limit>>> "));
                if(userInput < LOWER_LIMIT)
                {
                    throw new InputMismatchException(
                            "For: " + userInput + " you need to enter a number greather"
                            + " than " + LOWER_LIMIT);
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + ". Input invalid!");
            }
        }
        return userInput;
    }
}
