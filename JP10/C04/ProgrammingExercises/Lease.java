class Lease
{
    private String aptName;
    private int aptNumber;
    private double rentAmount;
    private int leaseMonths;

//{{{

    Lease()
    {
        this("XXX",0,1000,12);
    }


    Lease(String name, int id, double rentCost, int monthsAmount)
    {
        setAptName(name);
        setAptNumber(id);
        setRentAmount(rentCost);
        setLeaseMonths(monthsAmount);
    }


    /**
     * Returns the value of aptName.
     */
    public String getAptName() {
        return aptName;
    }


    /**
     * Sets the value of aptName.
     * @param aptName The value to assign aptName.
     */
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }


    /**
     * Returns the value of aptNumber.
     */
    public int getAptNumber() {
        return aptNumber;
    }


    /**
     * Sets the value of aptNumber.
     * @param aptNumber The value to assign aptNumber.
     */
    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }


    /**
     * Returns the value of rentAmount.
     */
    public double getRentAmount() {
        return rentAmount;
    }


    /**
     * Sets the value of rentAmount.
     * @param rentAmount The value to assign rentAmount.
     */
    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }


    /**
     * Returns the value of leaseMonths.
     */
    public int getLeaseMonths() {
        return leaseMonths;
    }


    /**
     * Sets the value of leaseMonths.
     * @param leaseMonths The value to assign leaseMonths.
     */
    public void setLeaseMonths(int leaseMonths) {
        this.leaseMonths = leaseMonths;
    }

//}}}

    public void addPetFee()
    {
        explainPetPolicy();
        rentAmount += 10;
    }

    public static void explainPetPolicy()
    {
        IO.println("10 are added to rent amount for each pet");
    }

}
