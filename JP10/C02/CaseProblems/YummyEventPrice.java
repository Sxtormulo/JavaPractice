//Copyright (C) Wed Aug 27 05:31:54 CST 2025 @522 /Internet Time/ Gabriel Soto
void main()
{
    final int LARGE_EVENT = 50, COST_PER_PERSON = 35;
    int numberOfGuests, totalCost;
    boolean isLargeEvent;
    
    String YummyMotto =
        """
        ********************************************
        *Yummy makes the food that makes it a party*
        ********************************************
        
        Yummy catering is $%d dolars for each pearson.
        A Large event is more than %d pearsons.
        """.formatted(COST_PER_PERSON, LARGE_EVENT);
    IO.println(YummyMotto);
    
    numberOfGuests = Integer.parseInt(
                                      IO.readln("Enter the number of guest in the event>>> "));
    
    totalCost = numberOfGuests * COST_PER_PERSON;
    isLargeEvent = numberOfGuests >= LARGE_EVENT ? true : false;
    
    String invoice = """
    Is large event: %b
    Number of guets: %d
    Total cost: $%d""".formatted(isLargeEvent, numberOfGuests, totalCost);    
    
    IO.println(invoice);
}
