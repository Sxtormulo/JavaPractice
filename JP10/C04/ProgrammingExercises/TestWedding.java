void main()
{
    Person person1, person2;
    Couple weddingCouple;
    Wedding wedding;


    person1 = setPersonData();
    person2 = setPersonData();
    weddingCouple = new Couple(person1, person2);
    wedding = setWeddingData(weddingCouple);

    IO.println(wedding);

}

static Person setPersonData()
{
    String firstName, lastName;
    firstName = IO.readln("Enter person name>>>");
    lastName = IO.readln("Enter person lastname>>> ");

    return new Person(firstName, lastName);
}

static Wedding setWeddingData(Couple couple)
{
    LocalDate weddingDate = LocalDate.parse(
                                IO.readln("Enter the event date in yyyy-mm-dd>>> "));
    String weddingLocation = IO.readln("Set the event location>>> ");

    return new Wedding(couple, weddingDate, weddingLocation);
}
