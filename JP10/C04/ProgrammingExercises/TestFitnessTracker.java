import module java.base;
void main()
{
    String Activity = IO.readln("Enter the activity name>>> ");
    int minutes = Integer.parseInt(
                            IO.readln("Enter the number of minutes>>> "));
    LocalDate date = LocalDate.parse(
                            IO.readln("Enter the date in year-month-day>>> "));

    FitnessTracker act1 = new FitnessTracker(Activity, minutes, date), act2 = new FitnessTracker();
    showValues(act1);
    showValues(act2);

}

static void showValues(FitnessTracker activity)
{
    String activityValues = """
    Spent %d minutes in activity %s in %s"""
        .formatted(
                   activity.getMinutes(),
                   activity.getActivity(),
                   activity.getDate());

    IO.println(activityValues);
}
