void main()
{
    final int TWENTY_DOLLARS = 20;
    final int TEN_DOLLARS = 10;
    final int FIVE_DOLLARS = 5;
    final int ONE_DOLLAR = 1;
    int twentiesQuantity, tensQuantity, fiveQuantity, onesQuantity;
    
    int inputDolars = Integer.parseInt(
                                       IO.readln("Enter the number of dolars to convert>>> "));
    
    twentiesQuantity = inputDolars / TWENTY_DOLLARS;
    tensQuantity = ( inputDolars % TWENTY_DOLLARS ) / TEN_DOLLARS;
    fiveQuantity = ( inputDolars % TWENTY_DOLLARS % TEN_DOLLARS ) / FIVE_DOLLARS;
    onesQuantity = inputDolars % TWENTY_DOLLARS % TEN_DOLLARS % FIVE_DOLLARS;
    
    String currencyDenominations = """
        The entered quantity %d can be expressed as:
        * %d 20s,
        * %d 10s,
        * %d 5s,
        * %d 1s""".formatted(
            inputDolars,
            twentiesQuantity,
            tensQuantity,
            fiveQuantity,
            onesQuantity);
    
    IO.println(currencyDenominations);
}
