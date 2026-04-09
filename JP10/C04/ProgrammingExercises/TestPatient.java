void main()
{
    Patient patient1 = new Patient(), patient2, patient3;
    BloodData blood1, blood2 = new BloodData();
    int id1, id2, age1, age2;

    id1 = Integer.parseInt(
                   IO.readln("Enter the patient ID>>> "));
    age1 = Integer.parseInt(
                   IO.readln("Enter the patient age>>> "));
    blood1 = getBloodData();

    patient2 = new Patient(id1, age1, blood1);

    id2 = Integer.parseInt(
                   IO.readln("Enter the patient ID>>> "));
    age2 = Integer.parseInt(
                   IO.readln("Enter the patient age>>> "));
    patient3 = new Patient(id2, age2, blood2);

    IO.println(patient1);
    IO.println(patient2);
    IO.println(patient3);

}

static BloodData getBloodData()
{
    String type, rh;
    type = IO.readln("Enter the blood type>>> ");
    rh = IO.readln("Enter the rh type>>> ");
    return new BloodData(type, rh);
}
