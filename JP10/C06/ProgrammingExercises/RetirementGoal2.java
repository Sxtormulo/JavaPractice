
import module java.base;

/**
 * RetirementGoal2 - Calculate the retirement savings with an interest rate
 *
 * @author Sxtormulo
 * 2026-03-24T22:07-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Years until retirement and saving amount
 * Output: Total amount saved for retirement
 *
 * Request the user for the years until retirement and amount saved yearly
 * Validate do not get 0 or negative values
 * For each year
 * Add the saved amount to the total
 * Show the total saved
 */
public class RetirementGoal2
{

    void main()
    {
        int yearsUntilRetirement;
        double annualSave;
        double savedAmount = NONE;

        IO.println(
                "To calculate the amount saved for retirement enter the following data");
        yearsUntilRetirement = getYearsUntilRetirement();
        annualSave = getAnnualSave();

        for(int year = 0; year <= yearsUntilRetirement; year++)
        {
            savedAmount += annualSave + (annualSave * INTEREST_RATE);
        }

        IO.println("For the retirement you save $" + "%03.2f".formatted(savedAmount));

    }
    static final double INTEREST_RATE = 0.04;

    double getAnnualSave()
    {
        double userInput;
        while(true)
        {
            try
            {
                userInput = Double.parseDouble(IO.readln(
                        "Enter the number of years remaining "
                        + "remaining until retirement (greater than 0)>>> "));
                if(userInput < NONE)
                {
                    throw new InputMismatchException(
                            "You need to enter a positive number");
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException inputInvalid)
            {
                IO.println(inputInvalid.getMessage() + ". Invalid input!");
            }
        }
        return userInput;
    }

    int getYearsUntilRetirement()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln(
                        "Enter the number of years remaining "
                        + "remaining until retirement (greater than 0)>>> "));
                if(userInput < NONE)
                {
                    throw new InputMismatchException(
                            "You need to enter a positive number");
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException inputInvalid)
            {
                IO.println(inputInvalid.getMessage() + ". Invalid input!");
            }
        }
        return userInput;
    }
    static final int NONE = 0;
}
