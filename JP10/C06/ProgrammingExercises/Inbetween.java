
import static java.lang.Math.max;
import static java.lang.Math.min;

import module java.base;

/**
 * Inbetween - get two numbers and print the numbers in between
 *
 * @author Sxtormulo
 * 2026-03-20T15:36:25-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Two numbers
 * Output: The numbers between that two numbers
 *
 * Get the two numbers
 * Sort it in ascending order
 * See if the difference is greater than one
 * Print the numbers between them
 *
 */
public class Inbetween
{

    static final int OFF_BY_ONE = 1;
    static final int NO_BETWEEN = 1;

    void main()
    {
        int lowerLimit, upperLimit, tmp;
        IO.println("Enter two numbers to print the numbers between them");
        IO.println("We are going to use as upper limit the greather number entered");

        while(true)
        {
            try
            {
                tmp = getRange("Enter the first number>>> ");
                lowerLimit = getRange("Enter the second number>>> ");
                upperLimit = max(tmp, lowerLimit); //(tmp > lowerLimit) ? tmp : lowerLimit;
                lowerLimit = min(tmp, lowerLimit);//(tmp < lowerLimit) ? tmp : lowerLimit;

                if(upperLimit - lowerLimit <= NO_BETWEEN)
                {
                    throw new InputMismatchException(
                            "No numbers between " + lowerLimit + "-" + upperLimit);
                }
                break;
            }
            catch(InputMismatchException inputInvalid)
            {
                IO.println(inputInvalid.getMessage() + "/nEnter a new number");
            }
        }

        IO.println("The numbers between %d and %d are>>>".formatted(lowerLimit,
                                                                    upperLimit));

        IntStream.range(lowerLimit + OFF_BY_ONE, upperLimit)
                .forEach(IO::println);

    }

    int getRange(String promp)
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(promp));
                break;
            }
            catch(NumberFormatException numberFormatException)
            {
                IO.println(numberFormatException.getMessage() + ". Input invalid!");
            }
        }
        return userInput;
    }

}
