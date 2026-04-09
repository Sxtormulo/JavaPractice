public class Event
{
    public static final int PRICE_PER_GUEST = 35;
    public static final int MIN_LARGE_EVENT = 50;

    private String eventID;
    private int numOfGuest;
    private int eventPrice;


    /**
    Basic constructor for Event
    */
    public Event()
    {
        String defaultID = "A000";
        int defaultNumOfGuest = 0;
        this(defaultID, defaultNumOfGuest);
    }


    public Event(String id, int numOfGuest)
    {
        setEventID(id);
        setNumOfGuest(numOfGuest);
    }

    /**
     * Returns the value of eventID.
     */
    public String getEventID() {
        return eventID;
    }


    /*
     * Sets the value of eventID.
     * @param eventID The value to assign eventID.
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }


    /**
     * Returns the value of numOfGuest.
     */
    public int getNumOfGuest() {
        return numOfGuest;
    }


    /**
     * Sets the value of numOfGuest.
     * @param numOfGuest The value to assign numOfGuest.
     */
    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;

        eventPrice = numOfGuest * PRICE_PER_GUEST;
    }


    /**
     * Returns the value of price.
     */
    public int getEventPrice() {
        return eventPrice;
    }

    @Override
    public String toString()
    {
        return "Event=%s;Guests=%d;Price=%d".formatted(eventID, numOfGuest, eventPrice);
    }

}
