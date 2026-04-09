public class MyCharacter
{
    private String name;
    private int strength;
    private int speed;


    public MyCharacter(String name, int strength, int speed)
    {
        setName(name);
        setStrength(strength);
        setSpeed(speed);
    }


    /**
     * Returns the value of name.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the value of name.
     * @param name The value to assign name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the value of Strength.
     */
    public int getStrength() {
        return strength;
    }


    /**
     * Sets the value of Strength.
     * @param strength The value to assign Strength.
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }


    /**
     * Returns the value of Speed.
     */
    public int getSpeed() {
        return speed;
    }


    /**
     * Sets the value of Speed.
     * @param speed The value to assign Speed.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString()
    {
        return "%s: Strength=%d , Speed=%d".formatted(name, strength, speed);
    }
}
