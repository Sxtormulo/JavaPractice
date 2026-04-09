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
//formatting



    static final String NUMERIC_FORMAT = "%3d"; //00.00
    static final String PREFIX = "[";
    static final String SUFFIX = "]";
    static final String DELIMITER = ":";
    static final Function<Number[], String> formatLotteryNumbers = i
            -> Arrays.stream(i).
                    map(
                            j -> NUMERIC_FORMAT.formatted(j.intValue())).collect(
                            Collectors.
                                    joining(
                                            DELIMITER,
                                            PREFIX, SUFFIX));

//Prizes
    static final int FIRST_AWARD = 1_000_000;
    static final int SECOND_AWARD = 1_000;
    static final int THIRD_AWARD = 100;
    static final int FORTH_AWARD = 10;
    static final int LAST_PRICE = 0;
//limits
    static final int MIN_RANGE = 0;
    static final int MAX_RANGE = 101; // Max range exclusive
    static final int LOTTERY_NUMBERS = 3;
//helpers
    static final IntSupplier getRandomNumber = () -> RandomGenerator.getDefault().
            nextInt(MIN_RANGE, MAX_RANGE);

    void main()
    {
        //values
        int correctGuesses = 0;
        boolean allMatched = false;
        boolean[] guessedNumbers;
        int[] lotteryNumbers = new int[LOTTERY_NUMBERS], userNumbers = new int[LOTTERY_NUMBERS];
        Integer[] userNumbersAsIntegers, lotteryNumbersAsInteges;
        Function<int[], Integer[]> intToIntegerArray = i -> Arrays.stream(i).mapToObj(
                Integer::valueOf).toArray(Integer[]::new);

        for(int i = 0; i < LOTTERY_NUMBERS; ++i)
        {
            lotteryNumbers[i] = getRandomNumber.getAsInt();
            userNumbers[i] = getRandomNumber.getAsInt();

//            userNumbers[i] = Integer.parseInt(IO.readln("Enter your %d guess>>> ".
//                    formatted(i)));
        }

        userNumbersAsIntegers = intToIntegerArray.apply(userNumbers);
        lotteryNumbersAsInteges = intToIntegerArray.apply(lotteryNumbers);

        guessedNumbers = guessedLotteryNumbers(userNumbersAsIntegers,
                lotteryNumbersAsInteges);

        for(var guessedNumber : guessedNumbers)
        {
            if(guessedNumber)
            {
                ++correctGuesses;
            }
        }

        if(correctGuesses == LOTTERY_NUMBERS)
        {
            allMatched = (userNumbers[0] == lotteryNumbers[0] && userNumbers[1]
                    == lotteryNumbers[1] && userNumbers[2] == lotteryNumbers[2]);
        }

        IO.println(getPriceAnnonce(correctGuesses, userNumbersAsIntegers,
                lotteryNumbersAsInteges,
                allMatched));
    }

    static <T extends Number> boolean[] guessedLotteryNumbers(T[] guesses,
                                                              T[] lotteryNumbers)
    {
        int LOTTERY_LENGTH = lotteryNumbers.length, NUMBER_OF_GUESSES = guesses.length;
        var guessedNumbers = new boolean[LOTTERY_LENGTH];

        for(int guess = 0; guess < NUMBER_OF_GUESSES; guess++)
        {
            for(int lottery = 0; lottery < LOTTERY_LENGTH; lottery++)
            {
                // If the lotter number is alredy guessed continue to the next
                if(guessedNumbers[lottery])
                {
                    continue;
                }
                // If a new number if guessed indicate it in the array an continue
                // to the next guess
                if(guesses[guess].equals(lotteryNumbers[lottery]))
                {
                    guessedNumbers[lottery] = true;
                    break;
                }
            }
        }

        return guessedNumbers;

    }

    static <T extends Number> String getPriceAnnonce(int numberOfMatches, T[] guesses,
                                                     T[] lotteryNumbers,
                                                     boolean allMatched)
    {
        String allGuesses = formatLotteryNumbers.apply(guesses), allLotteryNumbers = formatLotteryNumbers.
                apply(lotteryNumbers);
        return switch(numberOfMatches)
        {
            case 1 ->
                "With %s you matched one number of %s; award is $%,d".
                formatted(allGuesses, allLotteryNumbers, FORTH_AWARD);
            case 2 ->
                "With %s you have two matching numbers of %s; award is $%,d".formatted(
                allGuesses, allLotteryNumbers, THIRD_AWARD);
            case 3 ->
            {
                if(allMatched)
                {
                    yield "With %s you matched all numbers of %s in exact order; award is $%,d".
                    formatted(allGuesses, allLotteryNumbers, FIRST_AWARD);
                }
                yield "With %s you matched all numbers of %s, not in order; award is $%,d".
                formatted(allGuesses, allLotteryNumbers, SECOND_AWARD);
            }
            default ->
                "With %s no matching number for %s; award is $%,d".formatted(allGuesses,
                allLotteryNumbers, LAST_PRICE);
        };
    }
