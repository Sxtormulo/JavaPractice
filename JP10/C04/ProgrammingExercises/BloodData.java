class BloodData
{
    private String bloodType, rhFactor;


    /**
    Basic constructor for BloodData
    */
    public BloodData()
    {
        String defaultType = "O", defaultRH = "+";
        this(defaultType, defaultRH);
    }


    BloodData(String bloodType, String rhFactor)
    {
        setBloodType(bloodType);
        setRHFactor(rhFactor);
    }


    /**
     * Returns the value of bloodType.
     */
    public String getBloodType() {
        return bloodType;
    }


    /**
     * Sets the value of bloodType.
     * @param bloodType The value to assign bloodType.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Returns the value of rHFactor.
     */
    public String getRHFactor() {
        return rhFactor;
    }


    /**
     * Sets the value of rHFactor.
     * @param rHFactor The value to assign rHFactor.
     */
    public void setRHFactor(String rHFactor) {
        this.rhFactor = rHFactor;
    }

    public String toString()
    {
        return "%s%s".formatted(getBloodType(), getRHFactor());
    }

}
