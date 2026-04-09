/**
* @author Gabriel Soto
* 2026-02-16T22:12+00:00
*
* PastPresentFuture2 aplication
* Indicates when in the time the date occurs
*
* Input: a date entered by the user
* Output: If the date is in the past, present or futhure
*
* Get the user date
* Compare with with LocalDate methods
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
    if(secondDate.isAfter(firstDate))
    {
        return "Date %s is in a later from %s.".formatted(secondDate, firstDate);
    }
    else if(secondDate.isBefore(firstDate))
    {
        return "Date %s is early than %s.".formatted(secondDate, firstDate);
    }
    return "Date %s is the same to %s".formatted(secondDate, firstDate);


}