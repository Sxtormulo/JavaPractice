//Copyright (C) Wed Aug 27 05:19:32 CST 2025 @513 /Internet Time/ Gabriel Soto
import javax.swing.JOptionPane;

void main()
{
    final int MIN  = 1, MAX = 5;
    int randomValue, guessedValue;
    boolean isGuessed;

    randomValue = RandomGenerator.getDefault().nextInt(MIN, MAX);

    guessedValue = getGuessedValue(MIN, MAX);
    isGuessed = randomValue == guessedValue;

    displayGameResult(randomValue, guessedValue, isGuessed);
}

static int getGuessedValue(final int MIN, final int MAX)
{
    return Integer.parseInt(JOptionPane.showInputDialog(null, "Guess the number between %s and %s".formatted(MIN, MAX)));
}

static void displayGameResult(int randomValue, int guessedValue, boolean isCorrect)
{
    int diffValue = Math.abs(randomValue - guessedValue);

    JOptionPane.showMessageDialog(null, "You are %d far from the number".formatted(diffValue));

    JOptionPane.showMessageDialog(null, "The number was %s. Asserted %b".formatted(randomValue, isCorrect));
}
