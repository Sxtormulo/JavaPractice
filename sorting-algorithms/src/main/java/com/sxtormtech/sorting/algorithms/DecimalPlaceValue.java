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
 * DecimalPlaceValue - the decimal place values with their limit values
 *
 * @author Sxtormulo
 * Copyright 2026
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
/**
 * A {@code DecimalPlaceValue} represents a place value in the positional notation fo
 * the decimal system
 */
enum DecimalPlaceValue
{
    /** First decimal place value */
    ONES(1, 9),
    /** Second decimal place value */
    TENS(2, 99),
    /** Third decimal place value */
    HUNDREDS(3, 999),
    /** Fourth decimal place value */
    THOUSANDS(4, 9_999),
    /** Fifth decimal place value */
    TEN_THOUSANDS(5, 99_999),
    /** Sixth decimal place value */
    HUNDRED_THOUSANDS(6, 999_999),
    /** Seventh decimal place value */
    MILLIONS(7, 9_999_999),
    /** eighth decimal place value */
    TEN_MILLIONS(8, 99_999_999),
    /** ninth decimal place value */
    HUNDRED_MILLIONS(9, 999_999_999),
    /** Tenth decimal place value */
    BILLIONS(10, 9_999_999_999L);
    /** All decimal place values identified by their place */
    private static final Map<Integer, DecimalPlaceValue> VALUES = Arrays
        .stream(values())
        .unordered()
        .parallel()
        .collect(Collectors.toConcurrentMap(d -> d.getPlace(), Function.identity()));
    /** The decimal value limit */
    private final long limit;
    /** The decimal place value */
    private final int place;
    /** the value of the decimal digit */
    private final long value;

    /**
     * Constructs a {@link DecimalPlaceValue} with the current values
     *
     * @param place the decimal value place
     * @param limit the decimal value limit
     */
    private DecimalPlaceValue(int place, long limit)
    {
        this.place = place;
        this.limit = limit;
        value = Math.powExact(10L, place - 1);
    }

    /**
     * Gets the limit of the current decimal place value
     *
     * @return the decimal value limit
     */
    public long getLimit()
    {
        return limit;
    }

    /**
     * Gets the place of the current decimal value
     *
     * @return the place value
     */
    public int getPlace()
    {
        return place;
    }

    /**
     * Returns the value of the enum constant
     *
     * @return the decimal place value
     */
    public long getValue()
    {
        return value;
    }

    /**
     * Returns the enum constant of this class that represent the given place value
     *
     * @param place the positional value
     * @return the enum constant with the specified place
     */
    public static DecimalPlaceValue valueOf(int place)
    {
        return VALUES.get(place);
    }

    /**
     * Returns the enum constant that represents the maximum decimal value place of the
     * argument
     *
     * @param value the value used to determine the decimal place
     * @return the enum constant of the decimal value place
     */
    public static DecimalPlaceValue ofValue(long value)
    {
        return switch(value)
        {
            case long i when i > HUNDRED_MILLIONS.getLimit() ->
                BILLIONS;
            case long i when i > TEN_MILLIONS.getLimit() ->
                HUNDRED_MILLIONS;
            case long i when i > MILLIONS.getLimit() ->
                TEN_MILLIONS;
            case long i when i > HUNDRED_THOUSANDS.getLimit() ->
                MILLIONS;
            case long i when i > TEN_THOUSANDS.getLimit() ->
                HUNDRED_THOUSANDS;
            case long i when i > THOUSANDS.getLimit() ->
                TEN_THOUSANDS;
            case long i when i > HUNDREDS.getLimit() ->
                THOUSANDS;
            case long i when i > TENS.getLimit() ->
                HUNDREDS;
            case long i when i > ONES.getLimit() ->
                TENS;
            case long _ ->
                ONES;
        };
    }

}
