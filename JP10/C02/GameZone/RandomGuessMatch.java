//Copyright (C) Wed Aug 27 05:19:32 CST 2025 @513 /Internet Time/ Gabriel Soto
import javax.swing.JOptionPane;

void main()
{
    final int MIN  = 1, MAX = 5;
    int randomValue, guessedValue, diffValue;
    boolean guessed;
    
    randomValue = RandomGenerator.getDefault().nextInt(MIN, MAX);
    
    guessedValue = Integer.parseInt(JOptionPane.showInputDialog(null, "Guess the number between %s and %s".formatted(MIN, MAX)));
    diffValue = Math.abs(randomValue - guessedValue);
    guessed = diffValue == 0 ? true : false;
    
    JOptionPane.showMessageDialog(null, "You are %d far from the number".formatted(diffValue));
    
    JOptionPane.showMessageDialog(null, "The number was %s. Asserted %b".formatted(randomValue, guessed));
}
