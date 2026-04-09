void main()
{
    Card card1, card2;
    card1 = new Card('s');
    card2 = new Card('h');


    IO.println(chooseCard(card1));
    IO.println(chooseCard(card2));
}

public static Card chooseCard(Card card)
{
    final int CARDS_IN_SUIT = 13;
    RandomGenerator rSelector = RandomGenerator.getDefault();

    card.setValue(rSelector.nextInt(CARDS_IN_SUIT));

    return card;
}
