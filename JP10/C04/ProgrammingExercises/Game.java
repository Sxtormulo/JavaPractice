import module java.base;
public class Game
{
    private Team homeTeam;
    private Team guestTeam;
    private LocalTime gameTime;


    Game(Team homeTeam, Team guestTeam, LocalTime gameTime)
    {
        setHomeTeam(homeTeam);
        setGuestTeam(guestTeam);
        setGameTime(gameTime);
    }


    /**
     * Returns the value of homeTeam.
     */
    public Team getHomeTeam() {
        return homeTeam;
    }


    /**
     * Sets the value of homeTeam.
     * @param homeTeam The value to assign homeTeam.
     */
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }


    /**
     * Returns the value of guestTeam.
     */
    public Team getGuestTeam() {
        return guestTeam;
    }


    /**
     * Sets the value of guestTeam.
     * @param guestTeam The value to assign guestTeam.
     */
    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }


    /**
     * Returns the value of gameTime.
     */
    public LocalTime getGameTime() {
        return gameTime;
    }


    /**
     * Sets the value of gameTime.
     * @param gameTime The value to assign gameTime.
     */
    public void setGameTime(LocalTime gameTime) {
        this.gameTime = gameTime;
    }

    public String toString()
    {
        return "%s vs %s at %s".formatted(homeTeam, guestTeam, gameTime);
    }

}
