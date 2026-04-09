void main()
{
    final int INSURANSE_BASE = 15, INSURANSE_MULTIPLIER = 20;
    int birthYear, currentYear, insuranceAmount;

    IO.println("Welcome to Harrison Group Life Insurance calculator");
    birthYear = Integer.parseInt(
        IO.readln("Enter your birth year>>> "));
    currentYear = Integer.parseInt(
        IO.readln("Enter the current year>>> "));

    insuranceAmount = premiumAmountCalculator(birthYear, currentYear, INSURANSE_BASE, INSURANSE_MULTIPLIER);

    IO.println("You need to pay as an premium %d".formatted(insuranceAmount));
}

static int premiumAmountCalculator(int birthYear, int currentYear, final int INSURANSE_BASE, final int INSURANSE_MULTIPLIER)
{
    final int DECADE = 10;
    int userAge, decades;
    userAge = currentYear - birthYear;
    decades = userAge / DECADE;

    return (decades + INSURANSE_BASE) * INSURANSE_MULTIPLIER;
}
