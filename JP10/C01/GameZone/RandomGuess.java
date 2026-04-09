//Copyright (C) Wed Aug 27 05:19:32 CST 2025 @513 /Internet Time/ Gabriel Soto
import javax.swing.JOptionPane;

public class RandomGuess
{
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null, "Guess the number");
        JOptionPane.showMessageDialog(null, "The number is "+
                                      (1 + (int)(Math.random() * 10)));
        System.exit(0);
    }
}