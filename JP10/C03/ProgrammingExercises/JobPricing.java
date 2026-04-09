final static int COST_PER_HOUR = 35, COST_PER_TRAVEL_HOUR = 12;
void main()
{
    double materialsCost, jobCost;
    int numOfHours, numOfTravelHours;
    String jobName;

    IO.print("""
             With Renew Your Home Company we get your project done
             Enter the next data to make te cotization for your job""");
    jobName = IO.readln("How are your project named>>> ");
    materialsCost = Double.parseDouble(IO.readln("Which are the cost of the materials>>> "));
    numOfHours = Integer.parseInt(IO.readln("How many hours the proyect require>>> "));
    numOfTravelHours = Integer.parseInt(IO.readln("How many hours of travel are need>>> "));

    jobCost = projectCostCalculator( materialsCost, numOfHours, numOfTravelHours);

    String cotization = """
        For the %s job the estimated cost are %2.2f""".formatted(jobName, jobCost);

    IO.println(cotization);
}

static double projectCostCalculator(double materialsCost, int numOfHours, int numOfTravelHours)
{
    return materialsCost + (numOfHours * COST_PER_HOUR) + (numOfTravelHours * COST_PER_TRAVEL_HOUR);
}
