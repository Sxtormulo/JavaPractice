
import module java.base;

/**
 * RentalDemo - Test three Rental objects
 *
 * @author Sxtormulo
 * 2026-03-17T20:04:55-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: user data about the objects
 * Output: The three objects compared
 *
 * Declare three Rental objects
 * Get user data
 * Compare one to other to get the greater
 * Print the objects.-
 */
public class RentalDemo
{

    static final int SMALLER = -1;
    static final int EQUALS = 0;
    static final int GREATTER = 1;
    private static final int MINIMUM_TIME = 1;
    //    static <E,R> Gatherer<E,?,R> mapping(Function<E,R> mapper)
//    {
//        Gatherer.Integrator<Void,E,R> integrator = (_, element, downstream) -> downstream.push(mapper.apply(element));
//        return Gatherer.ofSequential(integrator);
//    }
    private static final int RENTALS_AMOUNT = 3;

    void main()
    {
        Rental[] rentals, largerRentals;
        Supplier<Rental> getRental = () -> getRentalData();
        var rentalSuppyingGatherer = supplying(getRental);
        rentals = IntStream.range(0, RENTALS_AMOUNT).parallel().boxed().gather(
                rentalSuppyingGatherer).toArray(Rental[]::new);

        //compare to get the larger
        largerRentals = IntStream.range(0, RENTALS_AMOUNT).mapToObj(
                (index) -> getLargerRental(rentals[index],
                                           rentals[(index + OFF_BY_ONE) % RENTALS_AMOUNT]))
                .toArray(Rental[]::new);

        //Print the larger element
        IntStream.range(0, RENTALS_AMOUNT).forEach(index -> IO.println(
                "From %s and %s -> %s rents for more time".formatted(rentals[index],
                                                                     rentals[(index + OFF_BY_ONE) % RENTALS_AMOUNT],
                                                                     largerRentals[index])));

    }
    private static final int OFF_BY_ONE = 1;

    static Rental getLargerRental(Rental firstRental, Rental secondRental)
    {
        int largerRental = Integer.compare(firstRental.getTotalRentalTime(),
                                           secondRental.getTotalRentalTime());

        return switch(largerRental)
        {
            case SMALLER ->
                secondRental;
            case EQUALS ->
                firstRental;
            case GREATTER ->
                firstRental;
            default -> throw new IllegalArgumentException(
                        "%s and %s an illegal result: %s".formatted(firstRental,
                                                                    secondRental,
                                                                    largerRental));

        };
    }

    static <E, R> Gatherer<E, ?, R> supplying(Supplier<R> supplier)
    {
        Gatherer.Integrator<Void, E, R> integrator = (_, _, downstream) -> downstream
                .push(supplier.get());
        return Gatherer.ofSequential(integrator);
    }

    /**
     *
     * @return Rental Returns the rental object with the data assigned
     */
    static Rental getRentalData()
    {
        String id;
        int rentalTime = 0;

        id = IO.readln("Enter the contract ID (A000 format)>>> ");
        while(true)
        {
            try
            {
                rentalTime = Integer.parseInt(
                        IO.readln(
                                "Enter the total rental time in minutes>>> "));
                if(rentalTime < MINIMUM_TIME)
                {
                    throw new InputMismatchException(
                            "You cannot rent the equipment for less than %d".formatted(
                                    MINIMUM_TIME));

                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + " !Value invalid");
            }
        }
        return (id.isEmpty()) ? new Rental(rentalTime) : new Rental(id, rentalTime);
    }
}

class Rental
{

    public static final int HOURLY_RENTAL_RATE = 40;
    public static final int EXTRA_PER_MINUTE = 1;

    private static final int MINUTES_PER_HOUR = 60;
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

    public Rental(int rentalTime)
    {
        this(DEFAULT_ID, rentalTime);
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
        minutesPrice = (minutes <= HOURLY_RENTAL_RATE) ? minutes * EXTRA_PER_MINUTE
                               : HOURLY_RENTAL_RATE;

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

    public int getTotalRentalTime()
    {
        return (hours * MINUTES_PER_HOUR) + minutes;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.contractID);
        hash = 41 * hash + this.hours;
        hash = 41 * hash + this.minutes;
        hash = 41 * hash + this.price;
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Rental{");
        sb.append("contractID=").append(contractID);
        sb.append(", hours=").append(hours);
        sb.append(", minutes=").append(minutes);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
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
        final Rental other = (Rental) obj;
        if(this.hours != other.hours)
        {
            return false;
        }
        if(this.minutes != other.minutes)
        {
            return false;
        }
        if(this.price != other.price)
        {
            return false;
        }
        return Objects.equals(this.contractID, other.contractID);
    }

}
