//Copyright (C)Tue Aug 26 22:36:36 CST 2025 @233 /Internet Time/ Gabriel Soto
final static int RENTAL_COST = 40, EXTRA_PER_MINUTE = 1;
void main()
{
    int rentalTime;

    displaySunshineMotto();

    rentalTime = getRentalTime();

    displayInvoice(rentalTime);
}

static int getRentalTime()
{
    int rentalTime = Integer.parseInt(
                                  IO.readln("Enter the number of minuets you rent the equipment>>> "));

    return rentalTime;
}

static void displaySunshineMotto()
{
    String SUNSHINE_MOTTO =
        """
        SsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSs
        sSunshine Seashore makes it fun in the sun!S
        SsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSs
        We rent kayaks, canoes, beach chairs and umbrellas.
        For the rent of a equipment is %d per hour plus a
        extra of %d per minute over the hour
        """.formatted(RENTAL_COST, EXTRA_PER_MINUTE);

    IO.println(SUNSHINE_MOTTO);
}

static void displayInvoice(int rentalTime)
{
    int normalPrice, extraPrice, totalPrice;

    normalPrice = ( rentalTime / 60 ) * RENTAL_COST;
    extraPrice = ( rentalTime % 60 ) * EXTRA_PER_MINUTE;
    totalPrice = normalPrice + extraPrice;

    String Invoice = """
        You rented the equipment for %d for a price of
        *   %d for each hour
        *   %d for each extra minute
        *   %d total""".formatted(rentalTime, normalPrice, extraPrice, totalPrice);

    IO.println(Invoice);
}
