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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import module java.base;

/** Testing that the radix sort of least significant digit works correctly */
@DisplayName("Testing radix sort lsd")
public class RadixSortLSDTest
{

    public RadixSortLSDTest()
    {
    }

    /** Test if sort works with a list of positive numbers */
    @Test
    public void testSort_IfSortWorksWithPositive()
    {
        // [194, 231, 105, 182, 245, 104, 38, 191, 5, 107, 137, 254, 117, 191, 145]
        final var unsortedList = new Random(2003L).ints(0, 300)
            .limit(20)
            .boxed()
            .toList();
        final var sortedList = unsortedList.stream()
            .sorted()
            .toList();
        assertTrue(() -> sortedList
            .equals(RadixSortLSD.sort(unsortedList)));
    }

    /** Testing if sort works with a list of positive and negative numbers */
    @Test
    public void testSort_ifWorksWithMixedNumbers()
    {

        //  [-106, 231, 105, -118, -55, -196, 38, -109, 5, -193, -163, 254, -183, -109, -155]
        final var unsortedList = new Random(2003L).ints(-300, 300)
            .limit(15)
            .boxed()
            .toList();
        final var sortedList = unsortedList.stream()
            .sorted()
            .toList();

        assertTrue(() -> sortedList
            .equals(RadixSortLSD.sort(unsortedList)));
    }

    /** Test if sort works with a list of only negative numbers */
    @Test
    public void testSort_ifWorksWithNegativeNumbers()
    {

        // [-106, -69, -195, -118, -55, -196, -262, -109, -295, -193, -163, -46, -183, -109, -155]
        final var unsortedList = new Random(2003L).ints(-300, 300)
            .limit(15)
            .boxed()
            .toList();
        final var sortedList = unsortedList.stream()
            .sorted()
            .toList();

        assertTrue(() -> sortedList
            .equals(RadixSortLSD.sort(unsortedList)));
    }

    /** Test if the returned list is unmodifiable */
    @Test
    public void testThat_AttemptingToModifyTheReturnedList_ThrowsUnsupportedOperationException()
    {
        //  [-106, 231, 105, -118, -55, -196, 38, -109, 5, -193, -163, 254, -183, -109, -155]
        final var unsortedList = new Random(2003L).ints(-300, 300)
            .limit(15)
            .boxed()
            .toList();
        final var sortedList = RadixSortLSD.sort(unsortedList);
        assertThrows(UnsupportedOperationException.class, () ->
                     sortedList.add(5)
        );
    }
}
