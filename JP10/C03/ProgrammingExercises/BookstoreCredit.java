void main()
{
    IO.println(
"""
Hermosa High School gives an award to as an bookstore credit.
This award correspond to a multiplied sum of the grade average.
""");

    double gradeAverage = Double.parseDouble(IO.readln(
        "Enter the grade average getted by the student>>> "));
    String studentName = IO.readln("Enter the student name>>> ");

    displayCreditWinned(gradeAverage, studentName);
}

static void displayCreditWinned(double gradeAverage, String studentName)
{
    final int CREDIT_MULTIPLIER = 10;

    double studentCredit = gradeAverage * CREDIT_MULTIPLIER;

    IO.println("The student %s gains %2.1f credits for a %2.1f score"
               .formatted(studentName, studentCredit, gradeAverage));
}
