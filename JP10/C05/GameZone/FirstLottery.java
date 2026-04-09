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
 * Lottery - Game of lottery, enter three digits and get the winner prize
 *
 * @author Sxtormulo
 * 2026-03-02T22:38-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: three numbers for the lottery
 * Output: The amount of money that the users wins
 *
 * Generate three random numbers
 * Get three numbers from the user
 * Get the random numbers that are equals to the user input
 * Return the winning price depending on the numbers matched
 */



    static final int ONE_GUESS = 1;
    static final int FIRST_AWARD = 1_000_000;
    static final int SECOND_AWARD = 1_000;
    static final int THIRD_AWARD = 100;
    static final int FORTH_AWARD = 10;
    static final int FIRST_NUMBER = 0;
    static final int SECOND_NUMBER = 1;
    static final int THIRD_NUMBER = 2;

    void main()
    {
        final int MIN_RANGE = 0;
        final int MAX_RANGE = 101; // Max range exclusive
        final int LOTTERY_NUMBERS = 3;
        int[] lotteryNumbers = new int[LOTTERY_NUMBERS], userNumbers = new int[LOTTERY_NUMBERS];

        for(int i = 0; i < LOTTERY_NUMBERS; ++i)
        {
            lotteryNumbers[i] = getRandomNumber(MIN_RANGE, MAX_RANGE);
            userNumbers[i] = Integer.parseInt(IO.readln("Enter your %d guess>>> ".
                    formatted(i)));
        }

    }

    static <T extends Number & Comparable> String validateLottery(T[] userGuess, T[] lotteryNumbers)
    {
        if(userGuess[FIRST_NUMBER].compareTo(lotteryNumbers[FIRST_NUMBER]) == 0 && userGuess[SECOND_NUMBER].
                compareTo(lotteryNumbers[SECOND_NUMBER]) == 0 && userGuess[THIRD_NUMBER].
                compareTo(lotteryNumbers[THIRD_NUMBER]) == 0)
        {
            return """
                   With the numbers %s:%s:%s you guess correctly all numbers, in exact order.
                   You win the first prize of %d!""".
                    formatted(userGuess[FIRST_NUMBER], userGuess[SECOND_NUMBER], userGuess[THIRD_NUMBER], FIRST_AWARD);
        }
        else if(guessedThreeNumbersWithoutOrder(lotteryNumbers, userGuess))
        {
            return """

                   """;
        }
        else
        {
            return "";
        }
    }

    static <T extends Number & Comparable> boolean guessedThreeNumbersWithoutOrder(T[] lotteryNumbers, T[] userGuess)
    {
        int lotteryLenght = lotteryNumbers.length;
        for(int i = 0; i < lotteryLenght; ++i)
        {
            T firstGuess = userGuess[FIRST_NUMBER], secondGuess = userGuess[SECOND_NUMBER], thirdGuess = userGuess[THIRD_NUMBER];
            T firstLotteryNumber = lotteryNumbers[i % lotteryLenght], secondLotteryNumber = lotteryNumbers[(i % lotteryLenght) + 1], thirdLotteryNumber = lotteryNumbers[(i % lotteryLenght) + 2];
            return (firstGuess.compareTo(firstLotteryNumber) == 0 && secondGuess.
                    compareTo(secondLotteryNumber) == 0 && thirdGuess.
                    compareTo(thirdLotteryNumber) == 0);
        }
        return false;
    }

    static int validateTwoLotNum(int firstUserInput, int secondUserInput, int firstRandomNumber, int secondRandomNumber)
    {
        if(firstUserInput == firstRandomNumber)
        {
            return ONE_GUESS + validateOneLotNum(secondUserInput, secondRandomNumber);
        }
        else if(secondUserInput == firstRandomNumber)
        {
            return ONE_GUESS + validateOneLotNum(firstUserInput, secondRandomNumber);
        }
        else if(firstUserInput == secondRandomNumber)
        {
            return ONE_GUESS + validateOneLotNum(secondUserInput, firstRandomNumber);
        }
        else if(secondUserInput == secondRandomNumber)
        {
            return ONE_GUESS + validateOneLotNum(firstUserInput, firstRandomNumber);
        }
        else
        {
            return 0;
        }
    }

    static int validateOneLotNum(int userInput, int randomNumber)
    {
        return userInput == randomNumber ? 1 : 0;
    }

    static int getRandomNumber(int initialRange, int endRange)
    {
        return RandomGenerator.getDefault().nextInt(initialRange, endRange);
    }
