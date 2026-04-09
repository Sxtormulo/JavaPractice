class Patient
{
    private int id, age;
    private BloodData patientBlood;

    /**
    Basic constructor for Patient
    */
    public Patient()
    {
        int defaultID = 0, defaultAge = 0;
        BloodData defaultBlood = new BloodData();
        this(defaultID, defaultAge, defaultBlood);
    }


    Patient(int id, int age, BloodData blood)
    {
        setId(id);
        setAge(age);
        setPatientBlood(blood);
    }


    /**
     * Returns the value of id.
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the value of id.
     * @param id The value to assign id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the value of age.
     */
    public int getAge() {
        return age;
    }


    /**
     * Sets the value of age.
     * @param age The value to assign age.
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Returns the value of patientBlood.
     */
    public BloodData getPatientBlood() {
        return patientBlood;
    }


    /**
     * Sets the value of patientBlood.
     * @param patientBlood The value to assign patientBlood.
     */
    public void setPatientBlood(BloodData patientBlood) {
        this.patientBlood = patientBlood;
    }

    public String toString()
    {
        return "Patient %d, of age %d. Blood type %s".formatted(getId(), getAge(), getPatientBlood());
    }

}
