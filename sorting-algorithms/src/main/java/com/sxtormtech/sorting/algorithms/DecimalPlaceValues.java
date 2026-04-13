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

/**
 * DecimalPlaceValues - the decimal place values with their limit values
 *
 * @author Sxtormulo
 * Copyright 2026
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
enum DecimalPlaceValues
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
    /** The decimal place value */
    private final int place;
    /** The decimal value limit */
    private final long limit;

    /**
     * Constructs a {@link DecimalPlaceValue} with the current values
     *
     * @param place the decimal value place
     * @param limit the decimal value limit
     */
    private DecimalPlaceValues(int place, long limit)
    {
        this.place = place;
        this.limit = limit;
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
     * Gets the limit of the current decimal place value
     *
     * @return the decimal value limit
     */
    public long getLimit()
    {
        return limit;
    }

}
