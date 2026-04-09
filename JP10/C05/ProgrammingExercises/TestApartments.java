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
 * TestApartment - Return to the user the apartment that best suits to them
 *
 * @author Sxtormulo
 * 2026-02-26T23:13-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: User preferences for the apartment
 * Output: A list os apartments that meets the user criteria
 *
 * Get the user preferences: minimum number of bathrooms and bedrooms; and maximum
 * amount of rent to pay.
 * Compare with the list of apartments
 * Print the apartments that meets the user requests
 *
 */



    void main()
    {
        int minOfBedrooms, minOfBaths;
        float maxRentAmount;
        Apartment[] apartments =
        {
            new Apartment(1, 1, 1, 111.1f), new Apartment(2, 2, 2, 222.2f),
            new Apartment(3, 3, 3, 333.3f), new Apartment(4, 4, 4, 444.4f),
            new Apartment(5, 5, 5, 555.5f)
        };

        minOfBedrooms = Integer.parseInt(IO.readln("Enter the necesary number of bedrooms>>> "));
        minOfBaths = Integer.parseInt(IO.readln("Enter the necessary amount of bathrooms>>>>> "));
        maxRentAmount = Float.parseFloat(IO.readln("Enter the maximum amount of rent to pay>>> "));

        IO.println("For %d requested baths, %d beds and a max rent of %.2f".formatted(minOfBaths, minOfBedrooms, maxRentAmount));
        IO.println("the suitable aparatment for you are:");
        for (var apartment : apartments)
        {
            int numOfBedrooms, numOfBathrooms;
            float rentAmount;
            numOfBathrooms = apartment.getNumOfBaths();
            numOfBedrooms = apartment.getNumOfBedrooms();
            rentAmount = apartment.getRentAmount();

            if (numOfBathrooms >= minOfBaths && numOfBedrooms >= minOfBedrooms
                    && maxRentAmount >= rentAmount)
            {
                IO.println("""
                           
                           *%s""".formatted(apartment));

            }
        }

    }
