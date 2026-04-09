
import module java.base;

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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * TwoDice2 - Generate two random dices and print winner
 *
 * @author Sxtormulo
 * 2026-03-12T17:01:32-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: none
 * Output: Dice winner
 *
 * Generate two random dices
 * compare them
 * Output winner.
 */
public class TwoDice2
{

    public static final RandomGenerator RANDOM = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Dice userDice, computerDice;
        int greatterDice;
        userDice = new Dice(RANDOM);
        computerDice = new Dice(RANDOM);
        greatterDice = Integer.compare(userDice.getValue(), computerDice.getValue());

        IO.println(switch(greatterDice)

        {
            case -1 ->
                "Computer won. U:%d C:%d".formatted(userDice.getValue(), computerDice.
                getValue());
            case 0 ->
                "It is a draw. U:%d C:%d".formatted(userDice.getValue(), computerDice.
                getValue());
            case 1 ->
                "User won. U:%d C:%d".formatted(userDice.getValue(), computerDice.
                getValue());
            default ->
                throw new IllegalArgumentException("Unexpected compared value");
        });

    }

}

class Dice
{

    private final int HIGHER_VALUE;
    private final int LOWER_VALUE;
    private final int value;

    public Dice(RandomGenerator random, int lowerLimit, int upperLimit)
    {
        LOWER_VALUE = lowerLimit;
        HIGHER_VALUE = upperLimit;
        value = random.nextInt(LOWER_VALUE, HIGHER_VALUE + 1);
    }

    public Dice(RandomGenerator random, int higherValue)
    {
        this(random, 1, higherValue);
    }

    public Dice(RandomGenerator random)
    {
        this(random, 1, 6);
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + this.HIGHER_VALUE;
        hash = 53 * hash + this.LOWER_VALUE;
        hash = 53 * hash + this.value;
        return hash;
    }

    public int getHigherValue()
    {
        return HIGHER_VALUE;
    }

    public int getLowerValue()
    {
        return LOWER_VALUE;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final Dice other = (Dice) obj;
        if(this.HIGHER_VALUE != other.HIGHER_VALUE)
        {
            return false;
        }
        if(this.LOWER_VALUE != other.LOWER_VALUE)
        {
            return false;
        }
        return this.value == other.value;
    }

    @Override
    public String toString()
    {
        return "Dice{" + "value=" + value + '}';
    }

}
