//Copyright (C) Wed Aug 27 05:31:54 CST 2025 @522 /Internet Time/ Gabriel Soto
final static int LARGE_EVENT = 50, COST_PER_PERSON = 35;
void main()
{
    int numberOfGuests, totalCost;
    boolean isLargeEvent;

    numberOfGuests = getNumberOfGuests();
    displayYummyMotto();
    displayInvoice(numberOfGuests);
}

static int getNumberOfGuests()
{
    String yummyPrice = """
    Yummy catering is $%d dolars for each pearson.
        A Large event is more than %d pearsons.
        """.formatted(COST_PER_PERSON, LARGE_EVENT);
    int numberOfGuests = Integer.parseInt(
                                      IO.readln("Enter the number of guest in the event>>> "));

    return numberOfGuests;
}

static void displayYummyMotto()
{
    final String YUMMY_MOTTO =
        """
        ********************************************
        *Yummy makes the food that makes it a party*
        ********************************************
        """;
    IO.println(YUMMY_MOTTO);
}

static void displayInvoice(int numberOfGuests)
{
    int totalCost = numberOfGuests * COST_PER_PERSON;
    boolean isLargeEvent = numberOfGuests >= LARGE_EVENT;

    String invoice = """
    Is large event: %b
    Number of guets: %d
    Total cost: $%d""".formatted(isLargeEvent, numberOfGuests, totalCost);

    IO.println(invoice);
}
