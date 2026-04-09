class Team
{
    public final static String MOTTO = "Sportmanship!";
    private String highSchool;
    private String sport;
    private String teamName;


    /**
     * Basic constructor for Team
     */
    public Team()
    {
    }


    /**
     * Basic constructor for Team
     * @param highSchool The highSchool name
     * @param sport The sport name
     * @param teamName The sport team name
     */
    public Team(String highSchool,
    String sport,
    String teamName)
    {
        setHighSchool(highSchool);
        setSport(sport);
        setTeamName(teamName);
    }


    /**
     * Returns the value of highSchool.
     */
    public String getHighSchool() {
        return highSchool;
    }


    /**
     * Sets the value of highSchool.
     * @param highSchool The value to assign highSchool.
     */
    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }


    /**
     * Returns the value of sport.
     */
    public String getSport() {
        return sport;
    }


    /**
     * Sets the value of sport.
     * @param sport The value to assign sport.
     */
    public void setSport(String sport) {
        this.sport = sport;
    }


    /**
     * Returns the value of teamName.
     */
    public String getTeamName() {
        return teamName;
    }


    /**
     * Sets the value of teamName.
     * @param teamName The value to assign teamName.
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public String toString()
    {
        return "%s. %s team from %s"
            .formatted(getTeamName(), getSport(), getHighSchool());
    }


}
