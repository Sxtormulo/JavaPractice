//Copyright (C) Wed Aug 27 18:33:54 CST 2025 @65 /Internet Time/ Gabriel Soto
import java.util.Scanner;

public class QuartsToGallonsInteractive
{
    public static void main(String[] args)
    {
        final byte QUARTS_IN_A_GALLON = 4;
        byte quartsNeeded, gallonsUsed, quartsUsed;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Insert the number of quarts needed for the paint job >> ");
        quartsNeeded = input.nextByte();
        gallonsUsed = (byte) (quartsNeeded / QUARTS_IN_A_GALLON);
        quartsUsed = (byte) (quartsNeeded % QUARTS_IN_A_GALLON);
        System.out.println("A job that needs "+quartsNeeded+" quarts requires "+gallonsUsed+" gallons plus "+quartsUsed+" quarts.");
        
    }
}