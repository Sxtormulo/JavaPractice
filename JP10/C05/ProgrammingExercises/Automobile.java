/**
* Automobile - A class with automobile information
*
* @author Gabriel Soto
* 2026-02-24T14:56-06:00
* SPDX-License-Identifier: GPL-3.0-or-later
*
* Input: Automobile data
* Output: An automobile object with id, make, model, color, year and Km/L
*
* The class have id, make, model, color, year and km/L
* Validate that the id is positive and less than 9999
* Validate that the year is between the allowed years
* Validate that the consumed gas is between the allowed
* The value out of range set to 0
*/


public class Automobile
{
    protected static int DEF_ID = 0;
    protected static int DEF_YEAR = 0;
    protected static float DEF_GAS_USAGE = 0f;
    protected static String DEF_MAKE = "default", DEF_MODEL = "liviano";
    protected static Colors DEF_COLOR = Colors.BLACK;
    protected static int MIN_ID = 0;
    protected static int MAX_ID = 9999;
    protected static int MIN_YEAR = 2005;
    protected static int MAX_YEAR = 2027;
    protected static int MIN_KM_L = 4;
    protected static int MAX_KM_L = 25;
    protected static String FORMAT_GAS_USAGE = "%2.2fkm/L";

    private int id;
    private String make;
    private String model;
    private Colors color;
    private int year;
    private float gasUsage;


    public Automobile()
    {
        this(DEF_ID, DEF_MAKE, DEF_MODEL, DEF_COLOR, DEF_YEAR, DEF_GAS_USAGE);
    }


    /**
    Basic constructor for Automobile
    */
    public Automobile(int id,
                      String make,
                      String model,
                      Colors color,
                      int year,
                      float gasUsage)
    {
        setId(id);
        setMake(make);
        setModel(model);
        setColor(color);
        setYear(year);
        setGasUsage(gasUsage);
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
        if(id < MIN_ID || id > MAX_ID)
        {
            this.id = DEF_ID;
        }
        else
        {
            this.id = id;
        }
    }


    /**
     * Returns the value of make.
     */
    public String getMake() {
        return make;
    }


    /**
     * Sets the value of make.
     * @param make The value to assign make.
     */
    public void setMake(String make) {
        this.make = make;
    }


    /**
     * Returns the value of model.
     */
    public String getModel() {
        return model;
    }


    /**
     * Sets the value of model.
     * @param model The value to assign model.
     */
    public void setModel(String model) {
        this.model = model;
    }


    /**
     * Returns the value of color.
     */
    public Colors getColor() {
        return color;
    }


    /**
     * Sets the value of color.
     * @param color The value to assign color.
     */
    public void setColor(Colors color) {
        this.color = color;
    }


    /**
     * Returns the value of year.
     */
    public int getYear() {
        return year;
    }


    /**
     * Sets the value of year.
     * @param year The value to assign year.
     */
    public void setYear(int year) {
        if( year < MIN_YEAR || year > MAX_YEAR)
        {
            this.year = DEF_YEAR;
        }
        else
        {
            this.year = year;
        }
    }


    /**
     * Returns the value of gasUsage.
     */
    public float getGasUsage() {
        return gasUsage;
    }


    /**
     * Sets the value of gasUsage.
     * @param gasUsage The value to assign gasUsage.
     */
    public void setGasUsage(float gasUsage) {
        if(gasUsage < MIN_KM_L || gasUsage > MAX_KM_L)
        {
            this.gasUsage = DEF_GAS_USAGE;
        }
        else
        {
            this.gasUsage = gasUsage;
        }
    }

    @Override
    public String toString()
    {
        return "ID: %d; Make: %s; Model: %s; Color: %s; Year: %d; Gas Usage: %s"
                .formatted(id,
                           make,
                           model,
                           color,
                           year,
                           FORMAT_GAS_USAGE.formatted(gasUsage));
    }


}
