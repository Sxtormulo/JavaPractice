//Copyright (C) Wed Aug 27 18:54:53 CST 2025 @79 /Internet Time/ Gabriel Soto
import java.util.Scanner;
public class MetersConversionInteractive
{
    public static void main(String[] args)
    {
        final short MILIMETRO = 1000, CENTIMETRO = 100, DECIMETRO = 10;
        int metros;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Inserte los metros totales >> ");
        metros = input.nextInt();
        System.out.println(metros + " metro(s) es/son " + (DECIMETRO * metros) + " decimentros, " + (CENTIMETRO * metros) + " centimetros y " + (MILIMETRO * metros) + " milimetros.");        
    }
}