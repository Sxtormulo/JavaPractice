void main()
{
    LocalDate localDate;

    localDate = LocalDate.parse(
                                 IO.readln("Enter a date to be caluclated in yyyy-mm-dd format>>> "));

    displayDaysTilNextMonth(localDate);
}

static void displayDaysTilNextMonth(LocalDate date)
{
    int monthLenght, monthDay, untilNextMonth;
    monthLenght = date.lengthOfMonth();
    monthDay = date.getDayOfMonth();
    untilNextMonth = monthLenght - monthDay;
    IO.println("Days until next month %d"
               .formatted(untilNextMonth));
}
