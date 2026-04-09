/**
* TestAutomobiles - Declare and test automobile class objects
*
* @author Gabriel Soto
* 2026-02-25T00:14-06:00
* SPDX-License-Identifier: GPL-3.0-or-later
*
* Input: data for automobile objects
* Output: The automobile object instances
*
* Declare the objects
* Get user data for each object
* Validate data
* Print the generated objects
*/


static String DATA_REQUEST = "Please enter info of ";
static int NUM_OF_CARS = 2;

void main()
{
    var automobiles = getAutomobilesData(NUM_OF_CARS);

    IO.println(Arrays.toString(automobiles));
}

static Automobile getAutomobileData()
{
    int id, year;
    String make, model, userColor;
    Colors color;
    float gasUsage;

    id = Integer.parseInt(IO.readln(DATA_REQUEST + "car id (0-9999): "));
    make = IO.readln(DATA_REQUEST + "car make: ");
    model = IO.readln(DATA_REQUEST + "car model: ");

    IO.println(DATA_REQUEST + " car color.\nSelect from:");
    for(var col : Colors.values())
    {
        IO.println("* "+col);
    }
    userColor = IO.readln(">>> ").toUpperCase();
    color = Colors.valueOf(userColor);

    year = Integer.parseInt(IO.readln(DATA_REQUEST + "car year: "));
    gasUsage = Float.parseFloat(IO.readln(DATA_REQUEST + "gas usage: "));

    return new Automobile(id, make, model, color, year, gasUsage);
}

static Automobile[] getAutomobilesData(int numOfCars)
{
    var automobiles = new Automobile[numOfCars];
    for(int i = 0; i < numOfCars; ++i)
    {
        automobiles[i] = getAutomobileData();
    }

    return automobiles;
}

