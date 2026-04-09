final static int TEN_THOUSAND_DAYS = 10_000;
void main()
{
    LocalDate localDate;

    localDate = LocalDate.parse(
                                 IO.readln("Enter your birthday in yyyy-mm-dd format>>> "));

    displayTenThousandDaysOld(localDate);
}

static void displayTenThousandDaysOld(LocalDate date)
{
    IO.println("In %s you are going to be %d days old"
               .formatted(date.plusDays(TEN_THOUSAND_DAYS), TEN_THOUSAND_DAYS));
}
