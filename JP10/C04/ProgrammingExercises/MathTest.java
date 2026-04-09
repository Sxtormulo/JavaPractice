void main()
{
    RandomGenerator rg = RandomGenerator.getDefault();
    int aInt = Integer.parseInt(
                            IO.readln(
                                  "Enter an Int>>> "));
    double aDouble = Double.parseDouble(
                                IO.readln(
                                  "Enter an double>>> "));

    IO.println("The square root of int %2.2f"
               .formatted(Math.sqrt(aInt)));
    IO.println("The random number between 0 and %d is: %d"
               .formatted(aInt, rg.nextInt(aInt)));
    IO.println("From double the ceil is %.1f, the floor is %.1f and rounded is %d"
               .formatted(
                          Math.ceil(aDouble),
                          Math.floor(aDouble),
                          Math.round(aDouble)));
    IO.println("The larges is %.1f and the smaller is %.1f"
               .formatted(Math.max(aInt, aDouble), Math.min(aInt, aDouble)));
}