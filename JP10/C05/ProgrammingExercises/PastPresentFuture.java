/**
* @author Gabriel Soto
* 2026-02-16T04:43:36+00:00
*
* PastPresentFuture aplication
* Indicates how soon or later is a date
*
* Input: a date entered by the user
* Output: In which month of the year is the date
*
* Get the user date
* Compare with todays month and year
* Return the diference
*/
final static int CURRENT = 0;
void main()
{
    LocalDate today, userDate;
    String inputDate;

    today = LocalDate.now();
    inputDate = IO.readln("Enter a date. Format yyyy-mm-dd>>> ");
    userDate = LocalDate.parse(inputDate);

    IO.println(dateDifference(today, userDate));

}

static String dateDifference(LocalDate firstDate, LocalDate secondDate)
{
    int yearDifference, monthDifference;
    yearDifference = secondDate.getYear() - firstDate.getYear();

    if(yearDifference != CURRENT)
    {
        return "Date %s is not this year".formatted(secondDate);
    }
    else
    {
        monthDifference = secondDate.getMonthValue() - firstDate.getMonthValue();
        if(monthDifference > CURRENT)
        {
            return "Date %s is in a later month in the year.".formatted(secondDate);
        }
        else if(monthDifference < CURRENT)
        {
            return "Date %s is in a early moth in the year.".formatted(secondDate);
        }
        return "Date %s is in this month".formatted(secondDate);
    }

}