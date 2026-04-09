
void main()
{
    final int PRICE_PER_LITER = 32, SQUARE_LITER_COVERTURE = 16;
    double length, width, height, totalCost;

    IO.println("""
               This aplication calculates how much paint you need for a room
               We need to know the dimensions of your room""");

    length = Double.parseDouble(IO.readln(
        "Enter the length of your room >>> "));
    width = Double.parseDouble(IO.readln(
        "Enter the width of your room >>> "));
    height = Double.parseDouble(IO.readln(
        "Enter the height of your room >>> "));

    totalCost = calculatePaintingCost(length, width, height, SQUARE_LITER_COVERTURE, PRICE_PER_LITER);

    IO.println("The cost to paint a %2.1f-by-%2.1f-meters room with %2.1f-meters ceiling is $%2.2f".formatted(length, width, height, totalCost));
}

static double calculatePaintingCost(double length, double width, double height, int paintCoverture, double paintPrice)
{
    double roomArea, litersNeeded;
    roomArea  = getRoomWallArea(length, width, height);
    litersNeeded = getLitersPerArea(roomArea, paintCoverture);
    IO.println("For painting the room you need %2.2fl of paint".formatted(litersNeeded));

    return litersNeeded * paintPrice;
}

static double getRoomWallArea(double length, double width, double height)
{
    final int WALLS = 2;
    double lengthArea, widthArea;
    lengthArea = length * height * WALLS;
    widthArea = width * height * WALLS;

    return lengthArea + widthArea;
}

static double getLitersPerArea(double roomArea, int paintCoverture )
{
    return roomArea / paintCoverture;
}

