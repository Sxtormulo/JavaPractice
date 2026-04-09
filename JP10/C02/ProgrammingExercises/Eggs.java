//Copyright (C) Wed Aug 27 19:55:58 CST 2025 @122 /Internet Time/ Gabriel Soto 
import java.util.Scanner;
public class Eggs
{
    public static void main(String[] args)
    {
        final float DOZEN_PRICE = 3.25f, UNIT_PRICE = 0.45f;
        final byte DOZEN = 12, UNIT_CENT_PRICE = 45;
        byte eggDozen, eggUnit, eggTotal;
        float priceDozen, priceUnit, priceTotal;
        Scanner input = new Scanner(System.in);
        
        System.out.print("How manny eggs to buy >> ");
        
        eggTotal = input.nextByte();
        eggDozen = (byte) ((byte) eggTotal / (byte) DOZEN);
        eggUnit = (byte) ((byte) eggTotal % (byte) DOZEN);
        priceDozen = DOZEN_PRICE * eggDozen;
        priceUnit = UNIT_PRICE * eggUnit;
        priceTotal = priceDozen + priceUnit;
        
        System.out.println("You ordered " + eggTotal + " eggs. That's " + eggDozen + " dozen at $" + DOZEN_PRICE + " per dozen and " + eggUnit +" loose eggs at " + UNIT_CENT_PRICE + " cents each for a total of: $" + priceDozen + " the dozens + $" + priceUnit + " the units = $" + priceTotal + " total");
    }
}