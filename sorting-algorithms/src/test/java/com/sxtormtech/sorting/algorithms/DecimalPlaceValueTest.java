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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sxtormulo
 */
@DisplayName("Testing DecimalPlaceValue enum")
class DecimalPlaceValueTest
{

    private static final String LIMIT = "9";
    private static final long RADIX = 10L;
    private static final int TOTAL_PLACE_VALUES = DecimalPlaceValue.values().length;

    public DecimalPlaceValueTest()
    {
    }

    /**
     * Test of getLimit method, of class DecimalPlaceValue. By getting the place of the
     * limit, and calculating the corresponding limit. Therefore, comparing the
     * calculated limit with the enum constant limit
     *
     * @param placeValue the current enum constant
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = " {0} limit corresponds to the place value limit")
    @EnumSource
    public void testGetLimit(DecimalPlaceValue placeValue)
    {
        int place = placeValue.getPlace();
        long limit = Long.parseLong(LIMIT.repeat(place));
        assertEquals(limit, placeValue.getLimit());
    }

    /**
     * Test of getPlace method, of class DecimalPlaceValue. By getting the place of the
     * enum constant and verifying that is a value within the constants
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = " {0} place it is a valid decimal place")
    @EnumSource
    public void testGetPlace(DecimalPlaceValue placeValue)
    {
        final long place = placeValue.getPlace();
        assertTrue(() -> place <= TOTAL_PLACE_VALUES && place > 0);
    }

    /**
     * Test of getValue method, of class DecimalPlaceValue.
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = " {0} place value correspond to its position")
    @EnumSource
    public void testGetValue(DecimalPlaceValue placeValue)
    {
        // Formula radix^n-1
        final int exponent = placeValue.getPlace() - 1;
        final long expectedValue = Math.powExact(RADIX, exponent);
        assertEquals(expectedValue, placeValue.getValue());
    }

    /**
     * Test of ofValue method, of class DecimalPlaceValue.
     */
    @Test
    @Disabled("Until completion")
    public void testOfValue()
    {
        System.out.println("ofValue");
        long value = 0L;
        DecimalPlaceValue expResult = null;
        DecimalPlaceValue result = DecimalPlaceValue.ofValue(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class DecimalPlaceValue.
     */
    @Test
    @Disabled("Until completion")
    public void testValueOf_int()
    {
        System.out.println("valueOf");
        int place = 0;
        DecimalPlaceValue expResult = null;
        DecimalPlaceValue result = DecimalPlaceValue.valueOf(place);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
