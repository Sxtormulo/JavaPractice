public class Card
{
    private char suit;
    private int value;

    /**
    Basic constructor for Card
    */
    public Card(char suit)
    {
        this(suit,0);
    }

    /**
    Basic constructor for Card
    */
    public Card(char suit, int value)
    {
        setSuit(suit);
        setValue(value);
    }

    /**
     * Returns the value of suit.
     */
    public char getSuit() {
        return suit;
    }


    /**
     * Sets the value of suit.
     * @param suit The value to assign suit.
     */
    public void setSuit(char suit) {
        this.suit = suit;
    }


    /**
     * Returns the value of value.
     */
    public int getValue() {
        return value;
    }


    /**
     * Sets the value of value.
     * @param value The value to assign value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "%c%d".formatted(suit, value);
    }

}
