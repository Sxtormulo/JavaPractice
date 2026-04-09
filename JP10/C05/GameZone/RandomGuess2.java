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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * RandomGuess2 - Guess a random number
 *
 * @author Sxtormulo
 * 2026-03-02T21:55-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: The user guessed number
 * Output: If the user wins or no.
 *
 * Produce the random number
 * Gets the user input
 * Compare the user input with the random number
 * Print the result
 */



    void main()
    {
        final int MIN_VALUE = 0;
        final int MAX_VALUE = 11; //Range is end exclusive
        int randomNumber, userNumber;
        randomNumber = getRandomNumber(MIN_VALUE, MAX_VALUE);
        userNumber = Integer.parseInt(IO.readln("Guess an number from %d to %d>>> ".
                formatted(MIN_VALUE, MAX_VALUE - 1)));

        evaluateGame(userNumber, randomNumber);

    }

    static int getRandomNumber(int initialRange, int endRange)
    {
        return RandomGenerator.getDefault().nextInt(initialRange, endRange);
    }

    static void evaluateGame(int userInput, int randomNumber)
    {
        switch(userInput)
        {
            case Integer i when i > randomNumber ->
                IO.println("Your guess of %d was too high".formatted(i));
            case Integer i when i < randomNumber ->
                IO.println("Your guess of % d was to low".formatted(i));
            default ->
                IO.println("Your guess of was correct");
        }

    }
