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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Sxtormulo
 */
//@DisplayName("Testing DecimalPlaceValue enum")
class DecimalPlaceValueTest
{

    /** The last higher digit of each decimal place value */
    private static final String LIMIT = "9";
    private static final int LONG_MAX_DIGITS = 19;
    private static final int PLACE_VALUES_TO_SUPPORT = 20;
    private static final long RADIX = 10L;

    public DecimalPlaceValueTest()
    {
    }

    /**
     * Test of getLimit method, of class DecimalPlaceValue. By getting the place of the
     * limit, and calculating the corresponding limit. Therefore, comparing the
     * calculated limit with the enum constant limit
     * <p>
     * <p>
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
     *
     * @param placeValue the current enum constant
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = " {0} place it is a valid decimal place")
    @EnumSource
    public void testGetPlace(DecimalPlaceValue placeValue)
    {
        final int place = placeValue.getPlace();
        final int valuePlace = Long.toString(placeValue.getValue())
            .length();
        final int limitPlace = Long.toString(placeValue.getLimit())
            .length();
        assertAll("place values", () -> assertTrue(place == valuePlace), () ->
                  assertTrue(place == limitPlace), () -> assertTrue(place > 0));
    }

    /**
     * Test of getValue method, of class DecimalPlaceValue. Verifying that the value
     * returned follows the formula {@code radix^n-1}.
     *
     * @param placeValue the current enum constant
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
     * Test of ofValue method, of class DecimalPlaceValue. By testing if the
     * number entered is inside the range of the returned DecimalPlaceValue, and have
     * the same length.
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = "returned by a number of length {index} is equals to {0}")
    @EnumSource
    public void testOfValue(DecimalPlaceValue placeValue)
    {
        final int exponent = placeValue.getPlace() - 1;
        final long minPlaceValue = Math.powExact(RADIX, exponent);
        final long maxPlaceValue;
        long maxValue;

        try
        {
            maxValue =
                Long.parseLong(LIMIT
                    .repeat(placeValue.getPlace()));
        }
        catch(NumberFormatException numberFormatException)
        {
            if(placeValue.getPlace() != LONG_MAX_DIGITS) throw numberFormatException;
            maxValue = Long.MAX_VALUE;
        }

        maxPlaceValue = maxValue;
        assertAll("Cover decimal place value range",
                  () -> assertEquals(placeValue, DecimalPlaceValue.ofValue(
                                       minPlaceValue)),
                  () -> assertEquals(placeValue, DecimalPlaceValue.ofValue(
                                       maxPlaceValue))
        );

    }

    /**
     * Test of valueOf method, of class DecimalPlaceValue. Asserting that returns the
     * correct constant
     *
     * @param placeValue the current enum constant
     */
    @DisplayName("Test that the DecimalPlaceValue")
    @ParameterizedTest(name = " {0} place value is returned for the given place")
    @EnumSource
    public void testValueOf_int(DecimalPlaceValue placeValue)
    {
        assertEquals(placeValue, DecimalPlaceValue.valueOf(placeValue.getPlace()));
    }

    /**
     * Test that the method valueOf(int place) throws an
     * {@code IllegalArgumentException} when given an invalid value
     */
    @DisplayName("Test that the DecimalPlaceValue.valueOf(int place) throws "
        + "IllegalArgumentExeption with a invalid value")
    @Test
    public void testValueOf_int_throwException_withInvalidValue()
    {
        final int unsupportedPlaceValue = PLACE_VALUES_TO_SUPPORT + 1;
        final var illegalArgument =
            assertThrows(IllegalArgumentException.class, () -> DecimalPlaceValue
                         .valueOf(unsupportedPlaceValue));

        assertEquals("No enum constant %s with place value of %d".formatted(
            DecimalPlaceValue.class.getCanonicalName(), unsupportedPlaceValue),
                     illegalArgument.getMessage());
    }
}
