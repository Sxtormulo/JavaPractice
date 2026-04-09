
/**
 * TwelveDays - Print twelve days for Christmas song
 *
 * @author Gabriel Soto
 * 2026-02-27T00:19-06:00
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * Input: Days until Christmas
 * Output: The songs with the gift until Christmas
 *
 * Get the user input
 * pass the input to a switch
 * use the falltrought
 *
 */



    final static int DAYS_UNTIL_CHRISTMAS = 12;

    void main()
    {
        int currentDay = 0;
        String initialVerse = "On the %s first day of Christmas my true love sent to me";
        while (currentDay < DAYS_UNTIL_CHRISTMAS)
        {
            var currentDayOrdinal = ChristmasDays.values()[currentDay];
            StringBuilder christmasSong = new StringBuilder(200);
            IO.print(initialVerse.
                    formatted(currentDayOrdinal.toString().toLowerCase()));
            switch (currentDay)
            {
                case 11:
                    christmasSong.append("\nTwelve drummers drumming");
                case 10:
                    christmasSong.append("\nEleven pipers piping");
                case 9:
                    christmasSong.append("\nTen lords a-leaping");
                case 8:
                    christmasSong.append("\nNine ladies dancing");
                case 7:
                    christmasSong.append("\nEight maids a-milking0");
                case 6:
                    christmasSong.append("\nSeven swans a-swinmming");
                case 5:
                    christmasSong.append("\nSix geese a-laying");
                case 4:
                    christmasSong.append("\nFive gold rings");
                case 3:
                    christmasSong.append("\nFour calling birds");
                case 2:
                    christmasSong.append("\nThree French hens");
                case 1:
                    christmasSong.append("\nTwo turtle doves");
                case 0:
                    christmasSong.append("\nA partridge in a pear tree");
                    break;
                default:
                    break;
            }
            IO.println(christmasSong);
            IO.println();
            ++currentDay;
        }
    }

    enum ChristmasDays
    {
        FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, EIGHTH, NINTH, TENTH,
        ELEVENTH, TWELFTH
    }
