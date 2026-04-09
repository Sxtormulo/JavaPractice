
import java.util.InputMismatchException;

/**
 * EvenEntryLoop - Ask for even numbers until the user stop
 *
 * @author Sxtormulo
 * 2026-03-18T23:20:49-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: user numbers
 * Output: Indicate if is even
 *
 * Get user input
 * validate if is even
 * if is odd throw an error
 * quit if the user enter the sentinel value
 */
public class EvenEntryLoop
{
//LongStream.range(1,17+1).map(i -> LongStream.range(1,i+1).reduce(1L, (a,b)->a*b)).forEach(IO::println);

    static final int ODD = 1;
    static final int EVEN = 2;
    static final int SENTINEL_VALUE = 999;

    void main()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter an even number or %d to quit>>> ".formatted(
                                SENTINEL_VALUE)));
                if(userInput == SENTINEL_VALUE)
                {
                    break;
                }
                if(userInput % EVEN == ODD)
                {
                    throw new InputMismatchException(
                            "Number entered is odd: " + userInput);
                }

                IO.println("Good job!");
            }
            catch(InputMismatchException | NumberFormatException invalidInput)
            {
                IO.println(invalidInput.getMessage() + " Invalid Input!");
            }
        }
    }

}
