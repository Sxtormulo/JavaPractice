//Price
static final int PLAN_A_COST = 49;
static final int PLAN_B_COST = 55;
static final int PLAN_C_COST = 61;
static final int PLAN_D_COST = 70;
static final int PLAN_E_COST = 79;
static final int PLAN_F_COST = 87;

//Plans thresholds
static final int MINUTES_THRESHOLD = 500;
static final int MESSAGES_THRESHOLD = 100;
static final int DATA_THRESHOLD = 3;
static final int  NO_NEEDED = 0;

void main()
{
    int minutesNeeded, messagesNeeded, dataNeeded;

    IO.println("Hello. Welcome to your mobile plan assessor");

    minutesNeeded = Integer.parseInt(
                                     IO.readln("Enter how many minutes you need>>> "));
    messagesNeeded = Integer.parseInt(
                                      IO.readln("Enter how many messages you need>>> "));
    dataNeeded = Integer.parseInt(
                                  IO.readln("Enter how many data you need>>> "));

    if(dataNeeded > NO_NEEDED)
    {
        if(dataNeeded < DATA_THRESHOLD)
        {
            IO.println(
                   "We recomend buy plan E. With more than %d minutes and %d and messages; and up to %d gigabytes of data. For $%d"
                       .formatted(MINUTES_THRESHOLD, MESSAGES_THRESHOLD, DATA_THRESHOLD, PLAN_E_COST));
        }
        else
        {
            IO.println(
                       "We recomend buy plan F. With more than %d minutes, %d messages and %d gigabytes of data. For $%d"
                       .formatted(MINUTES_THRESHOLD, MESSAGES_THRESHOLD, DATA_THRESHOLD, PLAN_F_COST));
        }
    }
    else if(messagesNeeded  > NO_NEEDED)
    {
        if(messagesNeeded > MESSAGES_THRESHOLD)
        {
            IO.println(
                       "We recomend buy plan D. With more than %d minutes and %d messages. For $%d"
                       .formatted(MINUTES_THRESHOLD, MESSAGES_THRESHOLD, PLAN_D_COST));
        }
        else if(minutesNeeded > MINUTES_THRESHOLD)
        {
            IO.println(
                       "We recomend buy plan C. With more than %d minutes and up to %d messages. For $%d"
                       .formatted(MINUTES_THRESHOLD, MESSAGES_THRESHOLD, PLAN_C_COST));
        }
        else
        {
            IO.println(
                       "We recomend buy plan B. With up to %d minutes and %d messages. For $%d"
                       .formatted(MINUTES_THRESHOLD, MESSAGES_THRESHOLD, PLAN_B_COST));
        }
    }
    else
    {
        IO.println(
                   "We recomend buy plan A. With up to %d minutes. For $%d"
                   .formatted(MINUTES_THRESHOLD, PLAN_A_COST));
    }
}
