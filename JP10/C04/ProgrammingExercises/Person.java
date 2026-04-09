class Person
{
    private String firstName;
    private String lastName;


    /**
    Basic constructor for Person
    */
    public Person(String firstName,
    String lastName)
    {
        setFirstName(firstName);
        setLastName(lastName);
    }


    /**
     * Returns the value of firstName.
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Sets the value of firstName.
     * @param firstName The value to assign firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Returns the value of lastName.
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Sets the value of lastName.
     * @param lastName The value to assign lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "%s %s".formatted(firstName, lastName);
    }


}
