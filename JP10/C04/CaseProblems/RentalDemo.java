
public class RentalDemo
{

    void main()
    {
        Rental defRent;
        Rental rent1;
        defRent = new Rental();

        rent1 = getRentalData();

        IO.println(defRent + " " + rent1);

    }

    /**
     *
     * @return Rental Returns the rental object with the data assigned
     */
    static Rental getRentalData()
    {
        String id = IO.readln("Enter the contract ID (A000 format)>>> ");
        int rentalTime = Integer.parseInt(
                IO.readln(
                        "Enter the total rental time in minutes>>> "));
        return new Rental(id, rentalTime);
    }
}

class Rental
{

    public static final int MINUTES_PER_HOUR = 60;
    public static final int HOURLY_RENTAL_RATE = 40;
    public static final int EXTRA_PER_MINUTE = 1;

    private static final String DEFAULT_ID = "A000";
    private static final int DEFAULT_TIME = 0;

    private String contractID;
    private int hours;
    private int minutes;
    private int price;

    /**
     * Basic constructor for Rental
     */
    public Rental()
    {
        this(DEFAULT_ID, DEFAULT_TIME);
    }

    /**
     * Basic constructor for Rental
     */
    public Rental(String contractID,
                  int totalTime)
    {
        setContractID(contractID);
        setHoursAndMinutes(totalTime);
    }

    /**
     * Returns the value of contractID.
     */
    public String getContractID()
    {
        return contractID;
    }

    /**
     * Sets the value of contractID.
     *
     * @param contractID The value to assign contractID.
     */
    public final void setContractID(String contractID)
    {
        this.contractID = contractID;
    }

    public final void setHoursAndMinutes(int totalTime)
    {
        int hoursPrice;
        int minutesPrice;

        hours = totalTime / MINUTES_PER_HOUR;
        minutes = totalTime % MINUTES_PER_HOUR;

        hoursPrice = hours * HOURLY_RENTAL_RATE;
        minutesPrice = minutes * EXTRA_PER_MINUTE;

        price = hoursPrice + minutesPrice;
    }

    /**
     * Returns the value of hours.
     */
    public int getHours()
    {
        return hours;
    }

    /**
     * Returns the value of minutes.
     */
    public int getMinutes()
    {
        return minutes;
    }

    /**
     * Returns the value of price.
     */
    public int getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "ID %s, hours %d, minutes %d, price %d".formatted(
                contractID,
                hours,
                minutes,
                price);
    }

}
