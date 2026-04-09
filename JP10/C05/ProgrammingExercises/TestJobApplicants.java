/**
* TestJobApplicants - Generate three JobApplicant objects and test them
*
* @author Gabriel Soto
* 2026-02-23T23:18-06:00
* SPDX-License-Identifier: GPL-3.0-or-later
*
* Input: The user input to fill the three JobApplicant objects
* Output: If each of the jobs applicants are qualified
*
* Generate the objects, get user data
* test each object,
* if have three of four skill the applicant in qualified
* print result
*/

final static int NUM_OF_APPLICANTS = 3;
final static int MIN_TO_SKILL = 7;

void main()
{
    var applicants = setJobApplicants(NUM_OF_APPLICANTS);

    for(var app: applicants)
    {
        IO.println("Applicant %s is qualified? %b"
                   .formatted(app.getName(), isQualified(app)));
    }
}

static boolean isSkilled(int abilityLevel)
{
    return abilityLevel > MIN_TO_SKILL;
}

static JobApplicant enterApplicantData()
{
    String name, phoneNumber;
    int wordProccessingLevel, spreadsheetLevel, databaseLevel, graphicsLevel;
    String abilityQuestion = "Define from [1-10] your ability level in ";
    name = IO.readln("Enter applicant name>>> ");
    phoneNumber = IO.readln("Enter your phone number>>> ");
    wordProccessingLevel = Integer.parseInt(IO.readln(abilityQuestion +
                                                      "word proccessing>>> "));
    spreadsheetLevel = Integer.parseInt(IO.readln(abilityQuestion +
                                                      "spreadsheets>>> "));
    databaseLevel = Integer.parseInt(IO.readln(abilityQuestion +
                                               "Data bases>>> "));
    graphicsLevel = Integer.parseInt(IO.readln(abilityQuestion +
                                               "Graphics>>> "));

    return new JobApplicant(name,
                            phoneNumber,
                            isSkilled(wordProccessingLevel),
                            isSkilled(spreadsheetLevel),
                            isSkilled(databaseLevel),
                            isSkilled(graphicsLevel));
}


static JobApplicant[] setJobApplicants(int numOfApplicants)
{
    var tmpApplicants = new JobApplicant[numOfApplicants];
    for(int i = 0; i < numOfApplicants; ++i)
    {
        tmpApplicants[i] = enterApplicantData();
    }

    return tmpApplicants;
}

static boolean isQualified(JobApplicant applicant)
{
    boolean wps, sss, dbs, gs;
    wps = applicant.getWordProcessingSkill();
    sss = applicant.getSpreadsheetsSkill();
    dbs = applicant.getDatabasesSkill();
    gs = applicant.getGraphicsSkill();

    //If the user have three of four skills is qualified
    return (wps || sss) && (dbs || gs) && (wps || gs) && (sss || dbs);
}
