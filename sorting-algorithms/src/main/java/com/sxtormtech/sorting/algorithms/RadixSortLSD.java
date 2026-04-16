/*
 * RadixSortLSD - Order an List by their radix; without making comparisons
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
 * A class that provide the implementation of radix sort algorithm. This sort is made
 * from lest significant digit to most.
 */
public class RadixSortLSD
{

    /** Number or buckets to save positive digits */
    private static final int POSITIVE_DIGITS = 9;
    /** Available digits from negative to positive */
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

    /** Provides a container to store all sorted digit, by radix */
    private static final Supplier<List<? extends List<Integer>>> newRadixContainer =
        () -> IntStream.range(START, RADIX_SLOTS)
            .mapToObj(_ -> new ArrayList<Integer>(2))
            .toList();

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

    /** Unifies the elements of the second list in the first list */
    private static final BiConsumer<List<? extends List<Integer>>, List<? extends List<Integer>>> radixContainerCombiner =
        (l1, l2) -> IntStream.range(START, RADIX_SLOTS)
            .forEach(i -> l1.get(i)
                .addAll(l2.get(i)));

    /**
     * Operates a step inside the radix sort algorithm, sorting the numbers as the given
     * digit place value
     *
     * @param unsorted the list to be sorted
     * @param place    the value place for sorting
     * @return the list sorted as ordered in the value place
     */
    private static final BiFunction<List<Integer>, Integer, List<Integer>> radixSortPlaceStep =
        (unsorted, place) ->
    {
        return unsorted.stream()
            .collect(newRadixContainer, (l, i) -> l.get(
                     producePlaceDigit.applyAsInt(i, place) + POSITIVE_DIGITS)
                     .add(i), radixContainerCombiner
            )
            .stream()
            .flatMap(c -> c.stream())
            .toList();
    };

    private RadixSortLSD()
    {
    }

    /**
     * Sort a unordered list using LSD radix sort algorithm. This method can sort lists
     * containing both positive and negative values. The returned list in unmodifiable.
     * Attempting to modify it throws an {@code UnsupportedOperationException}
     *
     * @param unsortedList the list to be sorted
     * @return a sorted list with the passed values
     */
    public static List<Integer> sort(List<Integer> unsortedList)
    {
        // avoid doing cost sort for only one element list
        if(unsortedList.size() < TWO_ELEMENTS) return List.copyOf(unsortedList);
        if(isSorted.test(unsortedList)) return List.copyOf(unsortedList);

        int valueLenght;
        valueLenght = findLongestValueLength(unsortedList);
        return IntStream.range(START, valueLenght)
            .boxed()
            .gather(radixSort((unsortedList)))
            .toList();

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

    /**
     * A Gatherer that performs radix sort over the list passed as parameter.
     * <p>
     * From the stream it gets the decimal place values to produce the sort.
     * It is a sequential gatherer
     */
    private static Gatherer<Integer, ?, Integer> radixSort(List<Integer> unsortedList)
    {
        Supplier<List<Integer>> container = () -> new ArrayList<Integer>(unsortedList);
        Gatherer.Integrator.Greedy<List<Integer>, Integer, Integer> sort =
            (list, r, downstream) ->
        {
            var tempList = radixSortPlaceStep.apply(list, r);
            list.clear();
            list.addAll(tempList);
            return true;
        };
        BiConsumer<List<Integer>, Gatherer.Downstream<? super Integer>> finisher =
            (list, downstream) -> list.stream()
                .allMatch(downstream::push);
        Gatherer<Integer, List<Integer>, Integer> radixSorter = Gatherer
            .ofSequential(container, sort, finisher);
        return radixSorter;
    }

}
