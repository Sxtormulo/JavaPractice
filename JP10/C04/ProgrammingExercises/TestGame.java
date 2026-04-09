void main()
{
    Game playingNow;
    Team homeTeam, guestTeam;
    LocalTime gameTime;

    homeTeam = TestTeam.setTeamData();
    guestTeam = TestTeam.setTeamData();
    gameTime = LocalTime.parse(IO.readln("Enter game time (HH:MM) >>> "));

    playingNow = new Game(homeTeam, guestTeam, gameTime);

    IO.println(playingNow);
}
