import module java.base;
class FitnessTracker
{
    private String Activity;
    private int minutes;
    private LocalDate date;

    FitnessTracker()
    {
        String defaultActivity = "running";
        int defaultMinutes = 0;
        LocalDate defaultDate = LocalDate.ofYearDay(LocalDate.now().getYear(), 1);

        this(defaultActivity, defaultMinutes, defaultDate);
    }

    FitnessTracker(String name, int spentTime, LocalDate date)
    {
        setActivity(name);
        setMinutes(spentTime);
        setDate(date);
    }


    /**
     * Returns the value of Activity.
     */
    public String getActivity() {
        return Activity;
    }


    /**
     * Sets the value of Activity.
     * @param Activity The value to assign Activity.
     */
    public void setActivity(String Activity) {
        this.Activity = Activity;
    }


    /**
     * Returns the value of minutes.
     */
    public int getMinutes() {
        return minutes;
    }


    /**
     * Sets the value of minutes.
     * @param minutes The value to assign minutes.
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


    /**
     * Returns the value of date.
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Sets the value of date.
     * @param date The value to assign date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }




}