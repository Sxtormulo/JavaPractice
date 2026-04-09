void main()
{
    Die dice = new Die();

    IO.println("Person: %s %s %s %s %s".formatted(
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow()));
    IO.println("Computer: %s %s %s %s %s".formatted(
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow(),
                                   dice.nextThrow()));
}
