
import module java.base;

/**
 * BarChart - Display the points scored for each basketball player
 *
 * @author Sxtormulo
 * Copyright 2026-03-25T23:06-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: The scored points for each basketball player
 * Output: A bar chart that display the points scored of each basketball player
 *
 * For each basketball player get and validate the scored points
 * Then for each basketball player
 * For each point that their scored
 * Display a asterisk in the same line
 */
public class BarChart
{

    static final int NO_POINTS = 0;
    static final String POINT = "*";
    static final String[] PLAYERS_NAME =
    {
        "Ali", "Bob", "Cai", "Dan", "Eli"
    };

    void main()
    {
        var players = Arrays.stream(PLAYERS_NAME)
                .map(n ->
                {
                    try
                    {
                        return new BasketballPlayer(n, getPoints(n));
                    }
                    catch(IllegalArgumentException illegalArgument)
                    {
                        IO.println(
                                illegalArgument.getMessage() + "Return default player");
                        return new BasketballPlayer(n, getPoints(n));
                    }
                }
                )
                .toList();

        players.forEach(p -> p.dispayPoints());
    }

    int getPoints(String name)
    {
        int points;
        while(true)
        {
            try
            {
                points = Integer.parseInt(IO.readln("Enter points earned by %s>>> "
                        .formatted(
                                name)));
                if(points < NO_POINTS)
                {
                    throw new InputMismatchException("You entered negative points");
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + ". Invalid input!");
            }
        }
        return points;
    }

    record BasketballPlayer(String name, int points)
            {

        BasketballPlayer
        {
            if(points < NO_POINTS)
            {
                throw new IllegalArgumentException("Negative points are invalid");
            }
        }

        void dispayPoints()
        {
            String pointBar = IntStream.range(NO_POINTS, points)
                    .mapToObj(i -> POINT)
                    .collect(Collectors.joining());
            IO.println(name + "\t" + pointBar);
        }
    }
}
