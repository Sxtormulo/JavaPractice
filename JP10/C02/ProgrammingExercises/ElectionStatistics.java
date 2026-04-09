void main()
{
    String firstElector, secondElector, thirdElector;
    int firstVotes, secondVotes, thirdVotes, totalVotes;
    double firstPercentage, secondPercentage, thirdPercentage, percentRatio;
    
    firstElector = IO.readln("Enter the first elector >>> ");
    secondElector = IO.readln("Enter the second elector >>> ");
    thirdElector = IO.readln("Enter the third elector >>> ");
                     
    firstVotes = Integer.parseInt(IO.readln("Enter the votes for the first elector >>> "));
    secondVotes = Integer.parseInt(IO.readln("Enter the votes for the second elector >>> "));
    thirdVotes = Integer.parseInt(IO.readln("Enter the votes for the third elector >>> "));                
    
    totalVotes = firstVotes  + secondVotes + thirdVotes;
    percentRatio = (double) 100 / totalVotes;
    
    firstPercentage = firstVotes * percentRatio;
    secondPercentage = secondVotes * percentRatio;
    thirdPercentage = thirdVotes * percentRatio;
    
    String electionResults = """
        The results for the election was a total of:
        *   %d totalVotes
        *   %s get %2.2f%%
        *   %s get %2.2f%%
        *   %s get %2.2f%%
        """.formatted(
            totalVotes,
            firstElector,
            firstPercentage,
            secondElector,
            secondPercentage,
            thirdElector,
            thirdPercentage);
            
    IO.println(electionResults);
}
