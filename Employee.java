/* Created by: Scott Filipkowski
Submission Date: 1/25/2016
Assignment: Lab1
*/
package Lab1;

public class Employee 
{
    //declaring all variables to be used in this class
    public static final int length = 3;
    public static Employee[] employeeArray = new Employee[length];
    private int employeeID;
    private String fname;
    private String middleIntial;
    private String lName;
    private String streetAddress;
    private String zipCode;
    private String dateOfBirth;
    private String hireDate;
    private String licenseDate;
    private String terminationDate;
    private double annualSalary;
    private String country;
    private String state;
    public Accident[] accidentArray;
    public static int numEmployee = 0;
    public int numAccident;
    
    public Employee(String fname, String middleIntial,
            String lname,String streetAddress, String dateOfBirth, 
            String hireDate, String licenseDate, double annualSalary, 
            String country, String state, String zipCode, String termDate)
    {
        //intializing the variables using the respective mutators
        setEmployeeID();
        setFirstName(fname);
        setMiddleIntial(middleIntial);
        setLastName(lname);
        setStreetAddress(streetAddress);
        setDateOfBirth(dateOfBirth);
        setHireDate(hireDate);
        setLicenseDate(licenseDate);
        setAnnualSalary(annualSalary);
        setCountry(country);
        setState(state);
        setZipCode(zipCode);
        setTerminationDate(termDate);
        this.numAccident = 0;
        this.accidentArray = new Accident[10];
    }
    
    //creating the accessors for this class
    public int getEmployeeID ()
    {
        return this.employeeID;
    }
    public String getFirstName ()
    {
        return this.fname;
    }
    public String getMiddleIntial ()
    {
        return this.middleIntial;
    }
    public String getLastName ()
    {
        return this.lName;
    }
    public String getStreetAddress ()
    {
        return this.streetAddress;
    }
    public String getZipCode ()
    {
        return this.zipCode;
    }
    public String getDateOfBirth ()
    {
        return this.dateOfBirth;
    }
    public String getHireDate ()
    {
        return this.hireDate;
    }
    public String getLicenseDate ()
    {
        return this.licenseDate;
    }
    public String getTerminationDate ()
    {
        return this.terminationDate;
    }
    public double getAnnualSalary ()
    {
        return this.annualSalary;
    }
    public String getState ()
    {
        return this.state;
    }
    public String getCountry ()
    {
        return this.country;
    }
    public int getNumAccidents ()
    {
        return this.numAccident;
    }
    
    //creating the mutators for this class
    public void setEmployeeID ()
    {
        this.employeeID = numEmployee + 1;
    }
    public void setFirstName (String firstName)
    {
        this.fname = firstName;
    }
    public void setMiddleIntial (String middleIntial)
    {
        this.middleIntial = middleIntial;
    }
    public void setLastName (String lastName)
    {
        this.lName = lastName;
    }
    public void setStreetAddress (String streetAddress)
    {
        this.streetAddress = streetAddress;
    }
    public void setZipCode (String zipCode)
    {
        this.zipCode = zipCode;
    }
    public void setDateOfBirth (String birthDate)
    {
        this.dateOfBirth = birthDate;
    }
    public void setHireDate (String hireDate)
    {
        this.hireDate = hireDate;
    }
    public void setTerminationDate (String terminationDate)
    {
        this.terminationDate = terminationDate;
    }
    public void setLicenseDate (String licenseDate)
    {
        this.licenseDate = licenseDate;
    }
    public void setAnnualSalary (double annualSalary)
    {
        this.annualSalary = annualSalary;
    }
    public void setState (String state)
    {
        this.state = state;
    }
    public void setCountry (String country)
    {
        this.country = country;
    }
    
    //for use to create a new employee at the correct location in the array
    public static void createEmployee(Employee newEmployee)
    {
        employeeArray[numEmployee] = newEmployee;
        numEmployee++;
    }
    
    //finding the location in the array if an employee exists
    public static int findEmployee (String firstName, String lastName)
    {
        int i = 0;
        while (i<numEmployee)
        {
            if (lastName.equals(employeeArray[i].getLastName()))
            {
                if (firstName.equals(employeeArray[i].getFirstName()))
                {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
    
    //adding an employee accident to that employees accident array
    public void addEmployeeAccident (String dateOfAccident, String accidentDescription, 
            String accidentLocation, int employeeID)
    {
        employeeArray[employeeID - 1].accidentArray[numAccident] = new Accident(dateOfAccident, 
        accidentDescription, accidentLocation, employeeID);
        
        numAccident++;
    }
}
