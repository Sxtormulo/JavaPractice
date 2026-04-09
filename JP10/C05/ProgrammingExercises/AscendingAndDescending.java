void main()
{
    int lower, medium, bigger, tmp;

    lower = Integer.parseInt(
                         IO.readln(
                               "Enter a number>>> "));

    //Get new number and sort it
    tmp = Integer.parseInt(
                         IO.readln(
                               "Enter a number>>> "));
    medium = getBigger(lower, tmp);
    lower = getSmaller(lower, tmp);

    //Get new number and sort it
    tmp = Integer.parseInt(
                         IO.readln(
                               "Enter a number>>> "));
    bigger = getBigger(medium, tmp);
    medium = getSmaller(medium, tmp);

    //Verify sort
    tmp = medium;
    medium = getBigger(lower, tmp);
    lower = getSmaller(lower, tmp);

    IO.println("Asc: %d %d %d".formatted(lower,
                                         medium,
                                         bigger));
    IO.println("Desc: %d %d %d".formatted(bigger,
                                          medium,
                                          lower));
}

public static int getBigger(int a, int b)
{
    return (a > b) ? a : b;
}

public static int getSmaller(int a, int b)
{
    return (a < b) ? a : b;
}

