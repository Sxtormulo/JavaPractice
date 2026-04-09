void main()
{
    String firstName, lastName;
    firstName = IO.readln("Enter your first name>>> ");
    lastName = IO.readln("Enter your last name>>> ");

    displaySalutation(firstName);
    displaySalutation(firstName, lastName);
    IO.println("Thank you for your recent order");

}

static void displaySalutation(String firstName)
{
    String Saludation = """
    Dear %s""".formatted(firstName);
    IO.println(Saludation);
}

static void displaySalutation(String firstName, String lastName)
{
    String Saludation = """
    Dear %s %s""".formatted(firstName, lastName);
    IO.println(Saludation);
}
