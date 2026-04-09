public static int QUEEN_SUITE = 125;
public static int KING_SUITE = 139;
public static int KING_WITH_POUCH_SUITE = 165;
public static int PARK_VIEW_EXTRA = 0;
public static int LAKE_VIEW_EXTRA = 15;

void main()
{
    int roomPrice, userInput;

    userInput = getRoom();

    roomPrice = switch (userInput)
    {
        case 1 -> QUEEN_SUITE;
        case 2 -> KING_SUITE;
        case 3 -> KING_WITH_POUCH_SUITE;
        default -> 0;
    };

    if(roomPrice > 0)
    {
        IO.println("You are paying %d for your selection".formatted(roomPrice));
        userInput = getView();

        roomPrice += switch(userInput)
        {
            case 1 -> LAKE_VIEW_EXTRA;
            case 2 -> PARK_VIEW_EXTRA;
            default ->
            {
                IO.println("You enter an invalid number\nLake view is assumed");
                yield LAKE_VIEW_EXTRA;
            }
        };

        IO.println("Your final price is "+roomPrice);
    }
    else
    {
        IO.println("The selected suite does not exists");
    }
}

public static int getRoom()
{
    String selections = """
        Welcome to Shady Rest Hotel!
        Select room number:
        1 for a queen bed
        2 for a king bed
        3 for a king bed & a pullout couch
        Enter one of this>>>\s""";
    int selection = Integer.parseInt(IO.readln(selections));

    return selection;
}

public  static int getView()
{
    String views = """
    You need to select a view for your room:
    1 for lake view at $%d
    2 for park view at $%d
    If you enter a invalid number the lake view is selected
    >>>\s""".formatted(LAKE_VIEW_EXTRA, PARK_VIEW_EXTRA);;
    int view = Integer.parseInt(IO.readln(views));

    return view;
}
