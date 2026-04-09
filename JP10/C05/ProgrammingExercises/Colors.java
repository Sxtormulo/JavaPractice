public enum Colors
{
    RED, GREEN, WHITE, BLACK;

    @Override
    public String toString()
    {
        String name = this.name();
        return (name.substring(0,1).toUpperCase() +  name.substring(1).toLowerCase());
    }
}
