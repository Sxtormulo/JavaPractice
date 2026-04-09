
import module java.base;

/**
 * CountByFives - Count in multiples of five
 *
 * @author Sxtormulo
 * 2026-03-18T20:56:56-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: multiples of five
 * Output: multiples of five in the same line; every multiple of 50 a new line
 *
 * Get the stream of numbers
 * If is divisible by 50 print a new line
 * else print the number in the same line
 */
public class CountByFives
{

    static final int NEXT_LINE = 0;
    static final int LINE_LIMIT = 50;
    static final int INCREMENT = 5;
    static final int STOP = 500;
    static final int START = 5;

    void main()
    {
        IntConsumer printByFives = i ->
        {
            if(i % LINE_LIMIT == NEXT_LINE)
            {
                IO.println(i);
            }
            else
            {
                IO.print(i + "\t");
            }
        };
        IntStream.iterate(START, i -> i <= STOP, i -> i + INCREMENT)
                .forEach(printByFives);
    }
}
