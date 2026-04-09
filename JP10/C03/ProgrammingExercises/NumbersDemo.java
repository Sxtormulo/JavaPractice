void main()
{
    int firstNumber, secondNumber;

    firstNumber = Integer.parseInt(IO.readln("Enter the first variable number>>> "));
    displayTwiceTheNumber(firstNumber);
    displayNumberPlusFive(firstNumber);
    displayNumberSquared(firstNumber);
    secondNumber = Integer.parseInt(IO.readln("Enter the second variable number>>> "));
    displayTwiceTheNumber(secondNumber);
    displayNumberPlusFive(secondNumber);
    displayNumberSquared(secondNumber);
}

public static void displayTwiceTheNumber(int number)
{
    IO.println(number);
    IO.println(number);
}

static void displayNumberPlusFive(int number)
{
    IO.println(number+5);
}

static void displayNumberSquared(int number)
{
    IO.println(Math.pow(2, number));
}
