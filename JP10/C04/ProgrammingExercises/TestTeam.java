public class TestTeam
{
    void main()
    {
        Team team1, team2, team3;

        team1 = setTeamData();
        team2 = setTeamData();
        team3 = setTeamData();

        displayTeam(team1);
        displayTeam(team2);
        displayTeam(team3);
    }

    public static Team setTeamData()
    {
        String highSchool, sport, teamName;
        highSchool = IO.readln("Enter the team High School>>> ");
        sport = IO.readln("Enter the team sport>>> ");
        teamName = IO.readln("Enter the team name>>> ");
        return new Team(highSchool, sport, teamName);
    }

    public static void displayTeam(Team team)
    {
        IO.println("%s; %s".formatted(team, team.MOTTO));
    }
}
