final static int INCH_PER_FOOT = 12, FOOT_PER_YARD = 3;
final static int INCH_PER_YARD = INCH_PER_FOOT * FOOT_PER_YARD;

void main()
{
    double inputInches = Double.parseDouble(
        IO.readln("Enter the inches that are going to be converted>>> "));

    inchesToFeet(inputInches);
    inchesToYards(inputInches);
}

static void inchesToFeet(double inch)
{

    double feet = inch / INCH_PER_FOOT;

    IO.println("There are %2.1f foot in %2.1f inches".formatted(feet, inch));
}

static void inchesToYards(double inch)
{
    double yard = inch / INCH_PER_YARD;

    IO.println("There are %2.1f yards in %2.1f inches".formatted(yard, inch));
}
