void main()
{
    Lease lease1, lease2, lease3, lease4;
    lease1 = getData();
    lease2 = getData();
    lease3 = getData();
    lease4 = new Lease();

    showValues(lease1);
    lease1.addPetFee();
    showValues(lease1);
    showValues(lease2);
    showValues(lease3);
    showValues(lease4);

}

static Lease getData()
{
    String aptName;
    int aptNumber, months;
    double leaseCost;

    aptName = IO.readln("Enter the apartment name>>> ");
    aptNumber = Integer.parseInt(
                     IO.readln("Enter the apartment number>>> "));
    leaseCost = Double.parseDouble(
                    IO.readln("Enter the rental cost>>> "));
    months = Integer.parseInt(
                    IO.readln("Enter the months of lease>>> "));

    return new Lease(aptName, aptNumber, leaseCost, months);
}

static void showValues(Lease lease)
{
    String leaseInfo = """
        For the apartment %s with number %d
        the rental cost is %2.2f for %d months"""
        .formatted(
                   lease.getAptName(),
                   lease.getAptNumber(),
                   lease.getRentAmount(),
                   lease.getLeaseMonths());
    IO.println(leaseInfo);
}
