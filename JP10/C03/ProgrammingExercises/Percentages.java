void main()
{
    double firstPercent, secondPercent;

    firstPercent = Double.valueOf(
                         IO.readln("Enter a double value>>> "));
    secondPercent = Double.valueOf(
                         IO.readln("Enter a double value>>> "));

    computePercent(firstPercent, secondPercent);
    computePercent(secondPercent, firstPercent);
}

static void computePercent(double total, double  percentage)
{
    double percent = (percentage/total) * 100;
    IO.println("%2.1f is %2.1f of %2.1f".formatted(percentage, percent, total));
}
