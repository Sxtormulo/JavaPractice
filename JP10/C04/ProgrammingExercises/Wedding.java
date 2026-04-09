import module java.base;
public class Wedding
{
    private Couple couple;
    private LocalDate date;
    private String location;


    /**
    Basic constructor for Wedding
    */
    public Wedding(Couple couple,
    LocalDate date,
    String location)
    {
        setCouple(couple);
        setDate(date);
        setLocation(location);
    }


    /**
     * Returns the value of couple.
     */
    public Couple getCouple() {
        return couple;
    }


    /**
     * Sets the value of couple.
     * @param couple The value to assign couple.
     */
    public void setCouple(Couple couple) {
        this.couple = couple;
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


    /**
     * Returns the value of location.
     */
    public String getLocation() {
        return location;
    }


    /**
     * Sets the value of location.
     * @param location The value to assign location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "Wedding for %s in %s in %s"
            .formatted(couple, date, location);
    }

}
