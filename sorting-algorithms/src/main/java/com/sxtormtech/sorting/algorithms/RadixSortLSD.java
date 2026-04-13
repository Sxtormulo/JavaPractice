/*
 * Copyright (C) 2026 Sxtormulo
 * SPDX-License-Identifier: GPL-3.0-or-later
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
package com.sxtormtech.sorting.algorithms;

import module java.base;

/**
 * RadixSortLSD - Order an List by their radix; without making comparisons
 *
 * @author Sxtormulo
 * Copyright 2026
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
/**
 * A class that provide the implementation of radix sort algorithm. This sort is made
 * from lest significant digit to most.
 */
public class RadixSortLSD
{

    private static final int RADIX_SLOTS = 19;
    private static final int START = 0;
    private static final int TWO_ELEMENTS = 2;
    /**
     * {@link Predicate} to verify if the provided list is sorted
     *
     * @param l the provide list
     * @return if the given list is sorted
     */
    private static final Predicate<List<Integer>> isSorted = l ->
    {
        return l.stream()
            .gather(Gatherers.windowSliding(TWO_ELEMENTS))
            .allMatch(
                e -> e.getFirst() <= e.getLast());
    };

    /**
     * Produces the value of the radix digit in the current value place
     *
     * @param value   The value from which to derive the radix digit
     * @param exponte The current value place
     * @return the radix digit value
     */
    private static final IntBinaryOperator producePlaceDigit = (value, exponent) ->
    {
        final int digits = 10;
        return (value / Math.powExact(digits, exponent)) % digits;
    };
    /**
     * Operates a step inside the radix sort algorithm, sorting the numbers as the given
     * digit place value
     *
     * @param unsorted the list to be sorted
     * @param place    the value place for sorting
     * @return the list sorted as ordered in the value place
     */
    private static final BiFunction<List<Integer>, Integer, List<Integer>> radixSortPlaceStep
        = (unsorted, place) ->
    {
        return unsorted.stream()
            .collect(Collectors.collectingAndThen(Collectors.groupingBy(i ->
                producePlaceDigit.applyAsInt(i, place)), s -> s.values()
                                                  .stream()
                                                  .flatMap(l -> l.stream())
                                                  .collect(Collectors.toList())));
    };

    public static List<Integer> sort(List<Integer> unsortedList)
    {
        // avoid doing cost sort for only one element list
        if(unsortedList.size() < TWO_ELEMENTS) return List.copyOf(unsortedList);
        if(isSorted.test(unsortedList)) return List.copyOf(unsortedList);

        int valueLenght;
        valueLenght = findLongestValueLength(unsortedList);
        //        IntStream
        //            .range(START, valueLenght)

    }

    /**
     * Find the length of the longest value in the list
     *
     * @param unsortedList the list where the longest value will be found
     * @return the length of the longest value
     */
    private static int findLongestValueLength(List<Integer> unsortedList)
    {
        int maxValue, minValue, valueLength;
        final IntSummaryStatistics minMaxValues = unsortedList.stream()
            .mapToInt(
                Integer::intValue)
            .summaryStatistics();

        maxValue = minMaxValues.getMax();
        minValue = minMaxValues.getMin();

        if(minValue < 0)
        {
            minValue = (minValue == Integer.MIN_VALUE) ? Integer.MAX_VALUE
                           : Math.abs(minValue);
            maxValue = Math.max(minValue, maxValue);
        }

        valueLength = DecimalPlaceValue.ofValue(maxValue)
            .getPlace();

        return valueLength;
    }

}
