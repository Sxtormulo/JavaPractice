void main()
{
    String firstWord, secondWord, thirdWord, fourthWord;
    
    firstWord = IO.readln("Enter a word for the madlib>>> ");
    secondWord = IO.readln("Enter the next word for the madlib>>> ");
    thirdWord = IO.readln("Enter the next word for the madlib>>> ");
    fourthWord = IO.readln("Enter the next word for the madlib>>> ");
    
    String madlib = """
    Mary had a little %1$s
    Its %2$s was %3$s as snow
    And everywhere that Mary %4$s
    The %1$s was sure to go""".formatted(firstWord, secondWord, thirdWord, fourthWord);
    
    IO.println(madlib);
}
