class Couple
{
    private Person groom;
    private Person bride;


    /**
    Basic constructor for Couple
    */
    public Couple(Person groom,
    Person bride)
    {
        setGroom(groom);
        setBride(bride);
    }


    /**
     * Returns the value of groom.
     */
    public Person getGroom() {
        return groom;
    }


    /**
     * Sets the value of groom.
     * @param groom The value to assign groom.
     */
    public void setGroom(Person groom) {
        this.groom = groom;
    }


    /**
     * Returns the value of bride.
     */
    public Person getBride() {
        return bride;
    }


    /**
     * Sets the value of bride.
     * @param bride The value to assign bride.
     */
    public void setBride(Person bride) {
        this.bride = bride;
    }

    @Override
    public String toString()
    {
        return "Groom %s & Bride %s".formatted(groom, bride);
    }

}
