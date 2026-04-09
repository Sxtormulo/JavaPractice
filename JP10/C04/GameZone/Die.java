import module java.base;
public class Die
{
    private static final int HIGHEST_DIE_VALUE = 6;
    private static final int LOWEST_DIE_VALUE = 1;
    private static final int TOP_RANDOM_LIMIT = HIGHEST_DIE_VALUE+1;
    private static final RandomGenerator DIE_THROW = RandomGenerator.getDefault();
    private int value;

    public Die()
    {
        setValue();
    }

    /**
     * Returns the value of value.
     */
    public int getValue() {
        return value;
    }


    /**
     * Sets the value of value.
     * @param value The value to assign value.
     */
     public void setValue()
     {
         this.value = DIE_THROW.nextInt(LOWEST_DIE_VALUE, TOP_RANDOM_LIMIT);
     }


    public Die nextThrow()
    {
        return new Die();
    }

    @Override
    public String toString()
    {
        return Integer.toString(value);
    }

}

