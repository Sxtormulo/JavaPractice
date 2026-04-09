
import module java.base;

/**
 * Population - Calculate the year in which the Mexican population exceeds of US
 *
 * @author Sxtormulo
 * 2026-03-25T17:45-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Mexican population increment percent and US population decrement percent
 * Output: Every year of the populations change until they meet
 *
 * Get and validate Mexican population increment
 * Get and validate US population decrement
 * While US population is greater than the Mexican population
 * Decrement US population and increment Mexican population
 * Print each change with their year
 * Print how many years it needs to reach the same population
 */
public class Population
{

    static final int NONE = 0;

    static final double TOTAL_PERCENT = 1.0;
    static final int UNITED_STATE_POPULATION = 323_000_000;
    static final int MEXICAN_POPULATION = 128_000_000;

    record CountryPopulation(String name, int population)
            {

        CountryPopulation
        {
            population = (population < NONE) ? NONE : population;
        }

        CountryPopulation decrement(double decrement)
        {
            int newPopulation = (int) (population * decrement);
            return new CountryPopulation(name, newPopulation);
        }

        CountryPopulation increment(double increment)
        {
            return new CountryPopulation(name, (int) (population * increment));
        }
    }

    void main()
    {
        CountryPopulation incrementedMexicanPopulation = new CountryPopulation("Mexico",
                                                                               MEXICAN_POPULATION);
        CountryPopulation decrementedUSPopulation = new CountryPopulation(
                "United States", UNITED_STATE_POPULATION);
        int yearsUntilExceeds = NONE;
        double incrementPercent;
        double decrementPercent;

        incrementPercent = TOTAL_PERCENT + getPercentage(
                "Enter the increment percentage>>> ");
        decrementPercent = TOTAL_PERCENT - getPercentage(
                "Enter the decrement percentage>>> ");

        while(incrementedMexicanPopulation.population() < decrementedUSPopulation
                .population())
        {
            incrementedMexicanPopulation = incrementedMexicanPopulation.increment(
                    incrementPercent);
            decrementedUSPopulation = decrementedUSPopulation
                    .decrement(decrementPercent);
            ++yearsUntilExceeds;

            displayPopulationChange(yearsUntilExceeds, incrementedMexicanPopulation,
                                    decrementedUSPopulation);
        }

        displayFinalPopulation(yearsUntilExceeds, incrementedMexicanPopulation,
                               decrementedUSPopulation);
    }

    void displayPopulationChange(int yearsUntilExceeds,
                                 CountryPopulation incrementedPopulation,
                                 CountryPopulation decrementedPopulation)
    {
        IO.println(
                "In year %d the Population of %s is %d and the population of %s is %d"
                        .formatted(yearsUntilExceeds,
                                   incrementedPopulation.name(),
                                   incrementedPopulation.population(),
                                   decrementedPopulation.name(),
                                   decrementedPopulation.population()));
    }

    void displayFinalPopulation(int yearsUntilExceeds,
                                CountryPopulation incrementedMexicanPopulation,
                                CountryPopulation decrementedUSPopulation)
    {
        IO.println(
                "In year %d the population of %s reached the population of %s to %d-%d"
                        .formatted(yearsUntilExceeds,
                                   incrementedMexicanPopulation.name(),
                                   decrementedUSPopulation.name(),
                                   incrementedMexicanPopulation.population(),
                                   decrementedUSPopulation.population()));
    }

    double getPercentage(String userRequestPromp)
    {
        double userInput;
        while(true)
        {
            try
            {
                userInput = Double.parseDouble(IO.readln(userRequestPromp));
                if(userInput <= NONE)
                {
                    throw new InputMismatchException(
                            "You can not have a negative precent. "
                            + "You entered %d".formatted(userInput));
                }
                break;
            }
            catch(NumberFormatException | InputMismatchException invalidInput)
            {
                IO.println(invalidInput.getMessage() + ". Invalid input!");
            }
        }
        return userInput;
    }
}
