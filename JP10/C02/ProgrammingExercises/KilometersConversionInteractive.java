//Copyright (C) Wed Aug 27 18:54:53 CST 2025 @79 /Internet Time/ Gabriel Soto
import java.util.Scanner;
public class KilometersConversionInteractive
{
    public static void main(String[] args)
    {
        final short METRO = 1000, HECTOMETRO = 100, DECAMETRO = 10;
        int metros;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Inserte los kilometros totales >> ");
        metros = input.nextInt();
        System.out.println(metros + " metro(s) es/son " + (DECAMETRO * metros) + " decamentros, " + (HECTOMETRO * metros) + " hectometros y " + (METRO * metros) + " metros.");        
    }
}