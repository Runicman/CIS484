/* Created by: Scott Filipkowski
Submission Date: 1/25/2016
Assignment: Lab1
*/
package Lab1;

public class Accident 
{
    //declaring all variables to be used in this class
    private int accidentID;
    private String dateOfAccident;
    private String accidentDescription;
    private String accidentLocation;
    private int employeeID;
    private static int numAccident = 0;
    
    public Accident (String dateOfAccident, String accidentDescription, 
            String accidentLocation, int employeeID)
    {
        //intializing the variables using the respective mutators
        setAccidentID();
        setDateOfAccident(dateOfAccident);
        setAccidentDescription(accidentDescription);
        setAccidentLocation(accidentLocation);
        setEmployeeID(employeeID);
        numAccident++;
    }
    
    //creating the accessors for this class
    public int getAccidentID ()
    {
        return this.accidentID;
    }
    public String getDateOfAccident ()
    {
        return this.dateOfAccident;
    }
    public String getAccidentDescription ()
    {
        return this.accidentDescription;
    }
    public String getAccidentLocation ()
    {
        return this.accidentLocation;
    }
    public int getEmployeeID ()
    {
        return this.employeeID;
    }
    
    //creating the mutators for this class
    public void setAccidentID ()
    {
        this.accidentID = numAccident + 1;
    }
    public void setDateOfAccident (String dateOfAccident)
    {
        this.dateOfAccident = dateOfAccident;
    }
    public void setAccidentDescription (String accidentDescription)
    {
        this.accidentDescription = accidentDescription;
    }
    public void setAccidentLocation (String accidentLocation)
    {
        this.accidentLocation = accidentLocation;
    }
    public void setEmployeeID (int employeeID)
    {
        this.employeeID = employeeID;
    }
}
