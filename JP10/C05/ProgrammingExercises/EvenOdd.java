void main()
{
    int userNum;
    String response;
    userNum = Integer.parseInt(
                           IO.readln(
                                 "Enter the number to define if is even or odd>>> "));
    response = isEven(userNum) ? "Is even" : "Is odd";
    IO.println(response);
}

public static boolean isEven(int number)
{
    return (number % 2) == 0;
}
