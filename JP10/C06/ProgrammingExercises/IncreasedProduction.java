
import module java.base;

/**
 * IncreasedProduction - Calculate the increase in production of a worker per month
 *
 * @author Sxtormulo
 * 2026-03-24T18:35-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Worker parth production per month
 * Output: The increase in production per month until 24 months
 *
 * Get user production
 * Iterate 24 times
 * Increase the month output per 6% monthly
 * Show the month in with it exceeds the 10 000 parts production
 * Show the increase in production in 24 moths and if it exceeds the 10 000 parts
 */
public class IncreasedProduction
{

    static final int INITIAL_MONTH = 0;
    static final int NO_PRODUCTION = 0;
    static final int PRODUCTION_THRESHOLD = 10_000;
    static final double MONTHLY_INCREASE = 0.06;
    static final int MONTH_THRESHOLD = 24;

    static final int NOT_REACHED = -1;

    void main()
    {
        int initialProduction;
        int incresedProduction;
        int monthToReachdProductionThreshold = NOT_REACHED;
        initialProduction = getWorkerProduction();
        incresedProduction = initialProduction;
        for(int month = INITIAL_MONTH; month < MONTH_THRESHOLD; month++)
        {
            incresedProduction += incresedProduction * MONTHLY_INCREASE;
            IO.println("In month %d\tworkerd produced\t%d\tparts".formatted(
                    month, incresedProduction));
            if(incresedProduction > PRODUCTION_THRESHOLD && monthToReachdProductionThreshold != NOT_REACHED)
            {
                monthToReachdProductionThreshold = month;
            }
        }
        displayProductionIncrement(incresedProduction, monthToReachdProductionThreshold);

    }

    void displayProductionIncrement(int incresedProduction,
                                    int monthToReachdProductionThreshold)
    {
        IO.println("At the end of the %d months the user produced\t%d parts".formatted(
                MONTH_THRESHOLD, incresedProduction));
        if(incresedProduction >= PRODUCTION_THRESHOLD)
        {
            if(monthToReachdProductionThreshold > INITIAL_MONTH)
            {
                IO.println(
                        "In month %d the worked reached the production threshold of\t%d parts"
                                .formatted(monthToReachdProductionThreshold,
                                           incresedProduction));
            }
            else
            {
                IO.println(
                        "The worked already reached the production threshold of\t%d parts"
                                .formatted(PRODUCTION_THRESHOLD));
            }
        }
        else
        {
            IO.println("Worker does not reach  the %d threshold at the end of %d months"
                    .formatted(PRODUCTION_THRESHOLD, MONTH_THRESHOLD));
        }
    }

    int getWorkerProduction()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter user monthly production>> "));
                if(userInput <= NO_PRODUCTION)
                {
                    throw new InputMismatchException("The user cannot produce nothing "
                            + "or less than nothing");
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + ". Invalid Input!");
            }
        }
        return userInput;
    }
}
