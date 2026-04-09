void main()
{
    BloodData blood1 = new BloodData(), blood2;
    blood2 = getBloodData();
    showValues(blood1);
    showValues(blood2);
    blood1.setBloodType(blood2.getBloodType());
    blood1.setRHFactor(blood2.getRHFactor());
    showValues(blood1);

}

static BloodData getBloodData()
{
    String type, rh;
    type = IO.readln("Enter the blood type>>> ");
    rh = IO.readln("Enter the rh type>>> ");
    return new BloodData(type, rh);
}

static void showValues(BloodData blood)
{
    IO.println("The blood is %s%s"
               .formatted(blood.getBloodType(),blood.getRHFactor()));
}
