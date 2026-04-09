
import module java.base;

/**
 * CountByAnything - Count in multiples of the number entered by the user
 *
 * @author Sxtormulo
 * 2026-03-18T20:56:56-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: multiples of the numbered entered by the user
 * Output: Print then numbers in the same line
 *
 * Get the stream of numbers
 * If is divisible by 10 print a new line
 * else print the number in the same line
 */
/*
 * IntStream.iterate(5,i->i<500,i->i+12).boxed().collect(Collectors.toList()).stream().gather(Gatherers.windowFixed(10)).forEach(line
 * ->{ line.forEach(i -> IO.print(i+"\t"));IO.println();})
 */
public class CountByAnything
{

    static final int NEXT_LINE = 0;
    static final int LINE_LIMIT = 10;
    static final int STOP = 500;
    static final int START = 5;

    void main()
    {
        final int INCREMENT = getIncrement();

        for(int count = START, element = 1; count < STOP;
            count += INCREMENT, element++)
        {

            if(element % LINE_LIMIT == NEXT_LINE)
            {
                IO.println(count);
            }
            else
            {
                IO.print(count + "\t");
            }
        }
        IO.println();
    }

    int getIncrement()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter the number to count in multiples>>> "));
                if(userInput < MINIMUN_INCREMENT)
                {
                    throw new InputMismatchException(
                            "Your number need to be more than" + MINIMUN_INCREMENT);
                }
                break;
            }
            catch(InputMismatchException | NumberFormatException invalidInput)
            {
                IO.println(invalidInput.getMessage() + " Invalid Input!");
            }
        }
        return userInput;
    }
    static final int MINIMUN_INCREMENT = 1;
}
