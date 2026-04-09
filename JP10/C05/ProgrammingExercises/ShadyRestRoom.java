public static int QUEEN_SUITE = 125;
public static int KING_SUITE = 139;
public static int KING_WITH_POUCH_SUITE = 165;
public static int LAKE_VIEW_EXTRA = 15;
public static int SUITE_TYPES = 3;
public static int VIEW_TYES = 2;

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
