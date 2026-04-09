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
 * Apartment - Data class to store Apartment information
 *
 * @author Sxtormulo
 * 2026-02-25T23:09-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Obect data: Apartment number, number of bedrooms, number of baths, and rent
 * amount.
 * Output: An Apartment obect instantiated.
 *
 */
public class Apartment
{

    public static int DEF_APT_NUMBER = 0;
    public static int DEF_NUM_OF_BEDROOMS = 0;
    public static int DEF_NUM_OF_BATHS = 0;
    public static float DEF_RENT_AMOUNT = 0f;

    private int aptNumber;
    private int numOfBedrooms;
    private int numOfBaths;
    private float rentAmount;

    public Apartment()
    {
        this(DEF_APT_NUMBER, DEF_NUM_OF_BEDROOMS, DEF_NUM_OF_BATHS, DEF_RENT_AMOUNT);
    }

    public Apartment(int aptNumber, int numOfBedrooms, int numOfBaths, float rentAmount)
    {
        setAptNumber(aptNumber);
        setNumOfBedrooms(numOfBedrooms);
        setNumOfBaths(numOfBaths);
        setRentAmount(rentAmount);
    }

    public int getAptNumber()
    {
        return aptNumber;
    }

    /**
     * Sets the apartment number
     *
     * @param aptNumber
     */
    public final void setAptNumber(int aptNumber)
    {
        this.aptNumber = aptNumber;
    }

    public int getNumOfBedrooms()
    {
        return numOfBedrooms;
    }

    public final void setNumOfBedrooms(int numOfBedrooms)
    {
        this.numOfBedrooms = numOfBedrooms;
    }

    public int getNumOfBaths()
    {
        return numOfBaths;
    }

    public final void setNumOfBaths(int numOfBaths)
    {
        this.numOfBaths = numOfBaths;
    }

    public float getRentAmount()
    {
        return rentAmount;
    }

    public final void setRentAmount(float rentAmount)
    {
        this.rentAmount = rentAmount;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Apartment{");
        sb.append("aptNumber=").append(aptNumber);
        sb.append(", numOfBedrooms=").append(numOfBedrooms);
        sb.append(", numOfBaths=").append(numOfBaths);
        sb.append(", rentAmount=").append(rentAmount);
        sb.append('}');
        return sb.toString();
    }

}
