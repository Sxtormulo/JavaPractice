/**
* JobApplicant - Class for job applicants
*
* @author Gabriel Soto
* 2026-02-23T22:13-06:00
* SPDX-License-Identifier: GPL-3.0-or-later
*
* Input: Applicant skills, names,and phone number
* Output: JobApplicant object
*
* Build a job applicant object than shows the applicant skills in:
* work  processing
* spreadsheets
* databases
* graphics
*/
public class JobApplicant
{
    private String name;
    private String phoneNumber;
    private boolean wordProcessingSkill;
    private boolean spreadsheetsSkill;
    private boolean databasesSkill;
    private boolean graphicsSkill;


    //Default constructor
    public JobApplicant()
    {
        String defaultName = "Jhon Conor", defaultPhoneNumber = "99999999";
        boolean defaultWPS = false,
                defaultSSS = false,
                defaultDBS = false,
                defaultGS = false;
        this(defaultName,
             defaultPhoneNumber,
             defaultWPS,
             defaultSSS,
             defaultDBS,
             defaultGS);
    }


    /**
    * Basic constructor for JobApplicant
    */
    public JobApplicant(String name,
                        String phoneNumber,
                        boolean wordProcessingSkill,
                        boolean spreadsheetsSkill,
                        boolean databasesSkill,
                        boolean graphicsSkill)
    {
        setName(name);
        setPhoneNumber(phoneNumber);
        setWordProcessingSkill(wordProcessingSkill);
        setSpreadsheetsSkill(spreadsheetsSkill);
        setDatabasesSkill(databasesSkill);
        setGraphicsSkill(graphicsSkill);
    }


    /**
     * Returns the value of name.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the value of name.
     * @param name The value to assign name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the value of phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the value of phoneNumber.
     * @param phoneNumber The value to assign phoneNumber.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Returns the value of wordProcessingSkill.
     */
    public boolean getWordProcessingSkill() {
        return wordProcessingSkill;
    }


    /**
     * Sets the value of wordProcessingSkill.
     * @param wordProcessingSkill The value to assign wordProcessingSkill.
     */
    public void setWordProcessingSkill(boolean wordProcessingSkill) {
        this.wordProcessingSkill = wordProcessingSkill;
    }


    /**
     * Returns the value of spreadsheetsSkill.
     */
    public boolean getSpreadsheetsSkill() {
        return spreadsheetsSkill;
    }


    /**
     * Sets the value of spreadsheetsSkill.
     * @param spreadsheetsSkill The value to assign spreadsheetsSkill.
     */
    public void setSpreadsheetsSkill(boolean spreadsheetsSkill) {
        this.spreadsheetsSkill = spreadsheetsSkill;
    }


    /**
     * Returns the value of databasesSkill.
     */
    public boolean getDatabasesSkill() {
        return databasesSkill;
    }


    /**
     * Sets the value of databasesSkill.
     * @param databasesSkill The value to assign databasesSkill.
     */
    public void setDatabasesSkill(boolean databasesSkill) {
        this.databasesSkill = databasesSkill;
    }


    /**
     * Returns the value of graphicsSkill.
     */
    public boolean getGraphicsSkill() {
        return graphicsSkill;
    }


    /**
     * Sets the value of graphicsSkill.
     * @param graphicsSkill The value to assign graphicsSkill.
     */
    public void setGraphicsSkill(boolean graphicsSkill) {
        this.graphicsSkill = graphicsSkill;
    }


    @Override
    public String toString()
    {
        return """
            JobApplicant: %s - Phone number: %s
            Skills:
            * Word Processing: %b
            * Spreadsheets Skill: %b
            * Database Skill: %b
            * Graphics Skill: %b
            """.formatted(name,
                        phoneNumber,
                        wordProcessingSkill,
                        spreadsheetsSkill,
                        databasesSkill,
                        graphicsSkill);
    }
}
