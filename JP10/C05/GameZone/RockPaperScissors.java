
import module java.base;

/*
 * Copyright (C) 2026 Sxtormulo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * RockPaperScissors - Play a game of rock paper scissors
 *
 * @author Sxtormulo
 * 2026-03-13T17:40:34-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: user weapon choice
 * Output: the result of play against the computer
 *
 * Get a random choice for the computer
 * Get user choice
 * Compare choices and thell the winner
 */
public class RockPaperScissors
{

    public static final int HIGGER_VALUE = 2;
    public static final int LOWER_VALUE = 0;
    public static final RandomGenerator RANDOM = new Random();

    void main()
    {
        Plays userPlay, computerPlay;
        int userChoice;
        while(true)
        {
            try
            {
                userChoice = Integer.parseInt(IO.readln(
                        "Choose Rock(1), Paper(2), Scissor(3)>>> ")) - 1;
                if(userChoice < LOWER_VALUE || userChoice > HIGGER_VALUE)
                {
                    throw new InputMismatchException("Your choice " + Integer.toString(
                            userChoice + 1));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException e)
            {
                IO.println(e.getMessage() + " needs to be between %d and %d".formatted(
                        LOWER_VALUE + 1,
                        HIGGER_VALUE + 1));
            }
        }

        userPlay = Plays.values()[userChoice];
        computerPlay = Plays.values()[RANDOM.nextInt(LOWER_VALUE, HIGGER_VALUE) + 1];
        IO.println(getPlayResult(userPlay, computerPlay));
    }

    static String getPlayResult(Plays firstPlayer, Plays secondPlayer)
    {
        if(firstPlayer.equals(secondPlayer))
        {
            return "%s vs %s; It is a draw!".formatted(firstPlayer, secondPlayer);
        }
        return (switch(firstPlayer)
        {
            case ROCK ->
            {
                if(secondPlayer.equals(Plays.SCISSORS))
                {
                    yield "%s vs %s; You wins!".formatted(firstPlayer, secondPlayer);
                }
                yield "%s vs %s; You loose".formatted(firstPlayer, secondPlayer);
            }
            case PAPER ->
            {
                if(secondPlayer.equals(Plays.ROCK))
                {
                    yield "%s vs %s; You wins!".formatted(firstPlayer, secondPlayer);
                }
                yield "%s vs %s; You loose".formatted(firstPlayer, secondPlayer);
            }
            case SCISSORS ->
            {
                if(secondPlayer.equals(Plays.PAPER))
                {
                    yield "%s vs %s; You wins!".formatted(firstPlayer, secondPlayer);
                }
                yield "%s vs %s; You loose".formatted(firstPlayer, secondPlayer);
            }

        });
    }
}

enum Plays
{
    ROCK, PAPER, SCISSORS;

    @Override
    public String toString()
    {
        return name().substring(0, 1).toUpperCase() + name().substring(1).
                toLowerCase();
    }

}
