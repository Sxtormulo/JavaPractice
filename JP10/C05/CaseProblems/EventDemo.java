
import module java.base;

/**
 * EventDemo - Try creating event object to test how it works
 *
 * @author Sxtormulo
 * Copyright 2026-03-17T15:36:44-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
public class EventDemo
{

    static final int SMALLER = -1;
    static final int EQUALS = 0;
    static final int GREATTER = 1;

    static final int NEXT_EVENT = 1;
    static final int EVENTS_AMOUNT = 3;
    private static final int MINIMUM_GUESTS = 1;

    void main()
    {
        Event[] events, largerEvents;
        //load event data
        events = IntStream.range(0, EVENTS_AMOUNT).mapToObj(_ -> getEventData())
                .toArray(Event[]::new);

        //compare events to get the bigger
        largerEvents = IntStream.range(0, EVENTS_AMOUNT).mapToObj(
                index -> getLargerEvent(events[index],
                                        events[(index + NEXT_EVENT) % EVENTS_AMOUNT]))
                .toArray(Event[]::new);

        //Printe the result of comparing the events
        IntStream.range(0, EVENTS_AMOUNT).forEach(index -> IO.println(
                "From %s and %s -> %s is the larger".formatted(events[index],
                                                               events[(index + NEXT_EVENT) % EVENTS_AMOUNT],
                                                               largerEvents[index])));

    }

    static Event getLargerEvent(Event firstEvent, Event secondEvent)
    {
        int largerEvent = Integer.compare(firstEvent.getNumOfGuest(), secondEvent
                                          .getNumOfGuest());

        return switch(largerEvent)
        {
            case SMALLER ->
                secondEvent;
            case EQUALS ->
                firstEvent;
            case GREATTER ->
                firstEvent;
            default -> throw new IllegalArgumentException(
                        "%s and %s an illegal result: %s".formatted(firstEvent,
                                                                    secondEvent,
                                                                    largerEvent));

        };
    }

    static Event getEventData()
    {
        String eventID;
        int numOfGuests;

        eventID = IO.readln("Enter the event ID>>> ");

        while(true)
        {
            try
            {
                numOfGuests = Integer.parseInt(
                        IO.readln("Enter the numOfGuests>>> "));
                if(numOfGuests < MINIMUM_GUESTS)
                {
                    throw new InputMismatchException(
                            "You can not have less than %d guests".formatted(
                                    MINIMUM_GUESTS));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + " !Value invalid");
            }
        }

        return new Event(eventID, numOfGuests);
    }

}

class Event
{

    public static final int DEFAULT_EVENT_GUEST_PRICE = 35;
    public static final int LARGE_EVENT_GUEST_PRICE = 32;
    public static final int LARGE_EVENT = 50;
    private static final int DEFAULT_GUESTS_AMOUNT = 0;
    private static final String DEFAULT_ID = "A000";

    private String eventID;
    private int numOfGuest;
    private int eventPrice;

    /**
     * Basic constructor for Event
     */
    public Event()
    {
        this(DEFAULT_ID, DEFAULT_GUESTS_AMOUNT);
    }

    public Event(String id, int numOfGuest)
    {
        setEventID(id);
        setNumOfGuest(numOfGuest);
    }

    /**
     * The implementation of a copy constructors.
     * Creates a new constructor with the values of the original.
     *
     * @param original An event object to be copyed
     */
    public Event(Event original)
    {
        this(original.getEventID(), original.getNumOfGuest());
    }

    /**
     * Returns the value of eventID.
     */
    public String getEventID()
    {
        return eventID;
    }


    /*
     * Sets the value of eventID.
     * @param eventID The value to assign eventID.
     */
    public final void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    /**
     * Returns the value of numOfGuest.
     */
    public int getNumOfGuest()
    {
        return numOfGuest;
    }

    /**
     * Sets the value of numOfGuest.
     *
     * @param numOfGuest The value to assign numOfGuest.
     */
    public final void setNumOfGuest(int numOfGuest)
    {
        this.numOfGuest = numOfGuest;
        if(isLargeEvent(numOfGuest))
        {
            eventPrice = numOfGuest * LARGE_EVENT_GUEST_PRICE;
        }
        else
        {
            eventPrice = numOfGuest * DEFAULT_EVENT_GUEST_PRICE;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{");
        sb.append("eventID=").append(eventID);
        sb.append(", numOfGuest=").append(numOfGuest);
        sb.append(", eventPrice=").append(eventPrice);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.eventID);
        hash = 13 * hash + this.numOfGuest;
        hash = 13 * hash + this.eventPrice;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final Event other = (Event) obj;
        if(this.numOfGuest != other.numOfGuest)
        {
            return false;
        }
        if(this.eventPrice != other.eventPrice)
        {
            return false;
        }
        return Objects.equals(this.eventID, other.eventID);
    }

    /**
     * Returns the value of price.
     */
    public int getEventPrice()
    {
        return eventPrice;
    }

    public boolean isLargeEvent(int numOfGuest)
    {
        return numOfGuest >= LARGE_EVENT;
    }

}
