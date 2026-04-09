
import module java.base;

/**
 * QuizScoreStatistics - Get quiz scores and show the min, max and average
 *
 * @author Sxtormulo
 * 2026-03-24T00:09-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Student grades
 * Output: Calculated min, max and average
 *
 * Get user grades
 * If the user put the sentinel value quit
 * For each grade entered
 * Calculate min
 * Calculate max
 * Calculate total of grades entered
 * Calculate average
 */
public class QuizScoreStatistics
{

    static final int SENTINEL_VALUE = 99;
    static final int MAX = 10;
    static final int MIN = 0;

    void main()
    {
        float scoresAverage;
        int score;
        int scores = MIN;
        int enteredScores = MIN;
        int minGrade = MAX;
        int maxGrade = MIN;

        displayIntro();

        while(true)
        {
            score = getGrade();
            if(score == SENTINEL_VALUE)
            {
                break;
            }

            minGrade = Math.min(minGrade, score);
            maxGrade = Math.max(maxGrade, score);
            scores += score;
            ++enteredScores;
        }

        scoresAverage = (float) scores / (float) enteredScores;

        displayCalculatedGrades(enteredScores, minGrade, maxGrade, scoresAverage);

    }

    void displayCalculatedGrades(int enteredScores, int minGrade, int maxGrade,
                                 float scoresAverage)
    {
        IO.println(
                """
                                For the %d entered scores
                                * The minimun note is\t%d
                                * The maximun note is\t%d
                                * The average is\t%-2.2f"""
                        .formatted(enteredScores, minGrade, maxGrade, scoresAverage));
    }

    void displayIntro()
    {
        IO.println(
                """
                                                   Whe can calculate the grades of your students
                                                   Yo need to enter grades between %d and %d
                                                   If you need to quit enter (%d)

                                                   Whe calculate the minimun grade, maximun grade and average"""
                        .formatted(MIN, MAX, SENTINEL_VALUE));
    }

    int getGrade()
    {
        int userInput;
        while(true)
        {
            try
            {
                userInput = Integer.parseInt(IO.readln("Enter the next grade>>> "));
                if((userInput > MAX && userInput != SENTINEL_VALUE) || userInput < MIN)
                {
                    throw new InputMismatchException(
                            "You entered a value outside the range %d-%d".formatted(MIN,
                                                                                    MAX));
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
