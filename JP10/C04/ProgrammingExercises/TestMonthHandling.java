void main()
{
    LocalDate localDate1, localDate2;

    localDate1 = LocalDate.parse(
                                 IO.readln("Enter a date in yyyy-mm-dd format>>> "));
    localDate2 = LocalDate.parse(
                                 IO.readln("Enter a date in yyyy-mm-dd format>>> "));

    displayAddedMonths(localDate1);
    displayAddedMonths(localDate2);
}

static void displayAddedMonths(LocalDate date)
{
    IO.println(date.plusMonths(1));
    IO.println(date.plusMonths(2));
    IO.println(date.plusMonths(3));
}
