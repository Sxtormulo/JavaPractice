
import module java.base;

/*
 * Copyright (C) 2026 Sxtormulo
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * War - Simplified war game
 *
 * @author Sxtormulo
 * 2026-03-12T15:00:34-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: nothing
 * Output: Who win from a war game
 *
 * Generate the cards randomly
 * If are the same generate a new card
 * Compare the values, the bigger value wins
 *
 */
public class War
{

    public static final Random RANDOM = new Random();

    public static final int HIGHER_VALUE = Card.HIGHER_VALUE;
    public static final int LOWER_VALUE = Card.LOWER_VALUE;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        Supplier<Card> newCard = () ->
        {
            int value = RANDOM.nextInt(LOWER_VALUE,
                    HIGHER_VALUE + 1);
            Suits suit = Suits.getRandomSuit(RANDOM);
            return new Card(suit, value);
        };

        Card userCard, computerCard;

        userCard = newCard.get();
        do
        {
            computerCard = newCard.get();
        } while(userCard.equals(computerCard));
        byte greatterCard = (byte) Integer.compare(userCard.value(), computerCard.
                value());
        IO.println(switch(greatterCard)

        {
            case -1 ->
                "Computer won";
            case 0 ->
                "It is a draw";
            case 1 ->
                "User won";
            default ->
                throw new IllegalArgumentException("Unexpected compared value");
        });
    }
}

/**
 * Card - A class that contains a card value
 *
 * @author Sxtormulo
 * 2026-03-11T15:53:28-06:00
 * SPDX-licence-Identifier: GPL-3.0-or-later
 *
 * Input: A suit and a value for a card
 * Output: A card object
 *
 * Save the suit and value in variables
 * Return the suit and value of the card
 */
enum Suits
{
    S("Spades"), H("Hearts"), D("Diamonds"), C("Clubs");

    private static final Suits[] VALUES = values();
    private static final int LENGHT = VALUES.length;
    private final String suit;

    Suits(String suit)
    {
        this.suit = suit;
    }

    @Override
    public String toString()
    {
        return suit;
    }

    public static Suits getRandomSuit(RandomGenerator random)
    {
        return VALUES[random.nextInt(LENGHT)];
    }
}

record Card(Suits suit, int value)
        {

    public static final int HIGHER_VALUE = 13;
    public static final int LOWER_VALUE = 1;

    Card
    {
        if(value < LOWER_VALUE || value > HIGHER_VALUE)
        {
            value = LOWER_VALUE;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.suit);
        hash = 97 * hash + this.value;
        return hash;
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
        final Card other = (Card) obj;
        if(this.value != other.value)
        {
            return false;
        }
        return this.suit == other.suit;
    }

}
