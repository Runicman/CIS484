/* Created by: Scott Filipkowski
Submission Date: 1/25/2016
Assignment: Lab1
*/

package Lab1;

import oracle.jdbc.pool.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class EmployeeInputWindow extends Application {
    
    //declaration of gridpanes
    GridPane infoPane = new GridPane();
    GridPane tablePane = new GridPane();
    GridPane overallPane = new GridPane();
    GridPane btnPane = new GridPane();
    GridPane topPane = new GridPane();
       
    //creation of the calendar items
    DatePicker birthDate = new DatePicker();
    DatePicker hireDate = new DatePicker();
    DatePicker terminationDate = new DatePicker();
    DatePicker licenseDate = new DatePicker();
    DatePicker accidentDate = new DatePicker();
    
    //creation of all radiobuttons to be used
    RadioButton rdoEmployee = new RadioButton("Employee");
    RadioButton rdoAccident = new RadioButton("Accident");
    RadioButton rdoUnitedStates = new RadioButton("United States");
    RadioButton rdoSweden = new RadioButton("Sweden");
    RadioButton rdoSwitzerland = new RadioButton("Switzerland");
    RadioButton rdoGermany = new RadioButton("Germany");
    RadioButton rdoFrance = new RadioButton("France");
    RadioButton rdoVirginia = new RadioButton("Virginia");
    RadioButton rdoNorthCarolina = new RadioButton("North Carolina");
    RadioButton rdoPennsylvania = new RadioButton("Pennsylvania");
    RadioButton rdoOhio = new RadioButton("Ohio");
    RadioButton rdoMaryland = new RadioButton("Maryland");
    
    //creation of toggleGroups to later add radiobuttons to
    ToggleGroup grpChoice = new ToggleGroup();
    ToggleGroup grpCountry = new ToggleGroup();
    ToggleGroup grpState = new ToggleGroup();
    
    //creation of textFields for users to input data
    TextField txtFirstName = new TextField();
    TextField txtLastName = new TextField();
    TextField txtMiddleIntial = new TextField();
    TextField txtStreetAddress = new TextField();
    TextField txtZipCode = new TextField();
    TextField txtSalary = new TextField();
    TextField txtAccidentDescription = new TextField();
    TextField txtAccidentLocation = new TextField();
       
    //creation of labels for the textboxes
    Label lblFirstName = new Label("First Name: ");
    Label lblLastName = new Label("Last Name: ");
    Label lblMiddleIntial = new Label("Middle Intial: ");
    Label lblStreetAddress = new Label("Street Address: ");
    Label lblZipCode = new Label("Zip Code: ");
    Label lblSalary = new Label("Annual Salary: ");
    Label lblDateOfBirth = new Label("Date of Birth: ");
    Label lblDateFormat = new Label("Please type desired bithday as "
            + "\n" + "(MM/DD/YYYY) then press Enter");
    Label lblHireDate = new Label("Hire Date: ");
    Label lblLicenseDate = new Label("License Date: ");
    Label lblTerminationDate = new Label("Termination Date: ");
    Label lblState = new Label("State: ");
    Label lblCountry = new Label("Country: ");
    Label lblDateOfAccident = new Label("Date of Accident: ");
    Label lblAccidentDescription = new Label("Description of Accident: ");
    Label lblAccidentLocation = new Label("Location of Accident: ");
    Label lblEmployeeAge = new Label("Age: ");
    Label lblAge = new Label("");
    
    //creation of area for all outputs    
    TextArea txtaOutput = new TextArea();
    
    //creation of button
    Button btnInsert = new Button("Insert");
    Button btnCommit = new Button("Commit");
    Button btnDisplayInfo = new Button("Display Employees");
    Button btnDisplayTrucks = new Button("Display Trucks");
    Button btnDisplaySalary = new Button("Find Total Salary");
    
    //creation of database items
    public static Connection dbConn;
    public static Statement commStmt;
    public static ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        //importing the correct countries, states, and trucks
        //importCountryStates();
        //importTruck();
                
        //preparing panes for items to be added
        infoPane.setAlignment(Pos.CENTER_LEFT);
        tablePane.setAlignment(Pos.CENTER);
        overallPane.setAlignment(Pos.CENTER);
        btnPane.setAlignment(Pos.CENTER);
        topPane.setAlignment(Pos.CENTER);
        
        //adding items to the first column of the pane
        infoPane.add(lblFirstName,0,0);
        infoPane.add(lblMiddleIntial,0,1);
        infoPane.add(lblLastName,0,2);
        infoPane.add(lblSalary,0,3);
        infoPane.add(lblStreetAddress,0,4);
        infoPane.add(lblCountry,0,5);
        infoPane.add(lblState,0,6);
        infoPane.add(lblZipCode,0,7);
        infoPane.add(lblDateOfBirth,0,8);
        infoPane.add(lblDateFormat,1,9);
        infoPane.add(lblHireDate,0,10);
        infoPane.add(lblLicenseDate,0,11);
        infoPane.add(lblTerminationDate,0,12);
        infoPane.add(lblDateOfAccident,0,13);
        infoPane.add(lblAccidentDescription,0,14);
        infoPane.add(lblAccidentLocation,0,15);
        infoPane.add(lblEmployeeAge,0,16);
        
        //adding items to the second column of the pane
        infoPane.add(txtFirstName,1,0);
        infoPane.add(txtMiddleIntial,1,1);
        infoPane.add(txtLastName,1,2);
        infoPane.add(txtSalary,1,3);
        infoPane.add(txtStreetAddress,1,4);
        infoPane.add(rdoUnitedStates,1,5);
        infoPane.add(rdoSweden,2,5);
        infoPane.add(rdoSwitzerland,3,5);
        infoPane.add(rdoGermany,4,5);
        infoPane.add(rdoFrance,5,5);
        infoPane.add(rdoVirginia,1,6);
        infoPane.add(rdoNorthCarolina,2,6);
        infoPane.add(rdoMaryland,3,6);
        infoPane.add(rdoOhio,4,6);
        infoPane.add(rdoPennsylvania,5,6);
        infoPane.add(txtZipCode,1,7);
        infoPane.add(birthDate,1,8);
        infoPane.add(hireDate,1,10);
        infoPane.add(licenseDate,1,11);
        infoPane.add(terminationDate,1,12);
        infoPane.add(accidentDate,1,13);
        infoPane.add(txtAccidentDescription,1,14);
        infoPane.add(txtAccidentLocation,1,15);
        infoPane.add(lblAge,1,16);
        
        //adding primary choices
        topPane.add(rdoEmployee,0,0);
        topPane.add(rdoAccident,1,0);
        
        //adding output field
        tablePane.add(txtaOutput,0,0);
        
        //adding buttons
        btnPane.add(btnInsert,0,0);
        btnPane.add(btnCommit,1,0);
        btnPane.add(btnDisplayInfo,2,0);
        btnPane.add(btnDisplayTrucks,3,0);
        btnPane.add(btnDisplaySalary,4,0);
        
        //adding smaller panes to the overall pane
        overallPane.add(topPane,0,0);
        overallPane.add(infoPane, 0, 1);
        overallPane.add(tablePane, 0, 2);
        overallPane.add(btnPane, 0,3);
        
        //displaying the completed form
        Scene primaryScene = new Scene(overallPane,900,700);
        primaryStage.setTitle("Patient Application");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        
        txtaOutput.setMinSize(520, 150);
                
        //adding radiobuttons to limit choices to 1 per category
        rdoEmployee.setToggleGroup(grpChoice);
        rdoAccident.setToggleGroup(grpChoice);
        rdoUnitedStates.setToggleGroup(grpCountry);
        rdoSweden.setToggleGroup(grpCountry);
        rdoSwitzerland.setToggleGroup(grpCountry);
        rdoGermany.setToggleGroup(grpCountry);
        rdoFrance.setToggleGroup(grpCountry);
        rdoVirginia.setToggleGroup(grpState);
        rdoNorthCarolina.setToggleGroup(grpState);
        rdoOhio.setToggleGroup(grpState);
        rdoMaryland.setToggleGroup(grpState);
        rdoPennsylvania.setToggleGroup(grpState);
        
        //detailing the form
        topPane.setHgap(10);
        btnPane.setHgap(10);
        infoPane.setHgap(10);
        topPane.setPadding(new Insets(10,0,10,0));
        btnPane.setPadding(new Insets(10,0,10,0));
        infoPane.setPadding(new Insets(0,0,10,0));
        
        //formatting the birthdate comment
        lblDateFormat.setTextFill(Color.GRAY);
        lblDateFormat.setScaleX(1);
        lblDateFormat.setScaleY(.78);
        
        //disabling editable fields for users
        txtaOutput.setEditable(false);
        hireDate.setEditable(false);
        terminationDate.setEditable(false);
        licenseDate.setEditable(false);
        accidentDate.setEditable(false);
        
        //ensuring the form is always reset
        clearForm();
        
        rdoEmployee.setOnAction(e -> {
            
            if (rdoEmployee.isSelected())
                {
                    //preparing form for additional employee
                    lblAge.setText("");
                    txtFirstName.setVisible(true);
                    txtMiddleIntial.setVisible(true);
                    txtLastName.setVisible(true);
                    txtStreetAddress.setVisible(true);
                    birthDate.setVisible(true);
                    hireDate.setVisible(true);
                    terminationDate.setVisible(true);
                    licenseDate.setVisible(true);
                    txtSalary.setVisible(true);
                    rdoUnitedStates.setVisible(true);
                    rdoSweden.setVisible(true);
                    rdoSwitzerland.setVisible(true);
                    rdoGermany.setVisible(true);
                    rdoFrance.setVisible(true);
                    lblDateFormat.setVisible(true);
                    
                    accidentDate.setVisible(false);
                    txtAccidentDescription.setVisible(false);
                    txtAccidentLocation.setVisible(false);
                }
        });
        
        rdoAccident.setOnAction(e -> {
            
                if (rdoAccident.isSelected())
                {
                    //preparing form for additional accident
                    lblAge.setText("");
                    txtFirstName.setVisible(true);
                    txtMiddleIntial.setVisible(true);
                    txtLastName.setVisible(true);
                    accidentDate.setVisible(true);
                    txtAccidentDescription.setVisible(true);
                    txtAccidentLocation.setVisible(true);
                    
                    rdoVirginia.setVisible(false);
                    rdoNorthCarolina.setVisible(false);
                    rdoOhio.setVisible(false);
                    rdoMaryland.setVisible(false);
                    rdoPennsylvania.setVisible(false);
                    rdoUnitedStates.setVisible(false);
                    rdoSweden.setVisible(false);
                    rdoSwitzerland.setVisible(false);
                    rdoGermany.setVisible(false);
                    rdoFrance.setVisible(false);
                    txtStreetAddress.setVisible(false);
                    txtZipCode.setVisible(false);
                    birthDate.setVisible(false);
                    hireDate.setVisible(false);
                    terminationDate.setVisible(false);
                    licenseDate.setVisible(false);
                    txtSalary.setVisible(false);
                    lblDateFormat.setVisible(false);
                }
        });
        
        rdoUnitedStates.setOnAction(e -> {
            
                    //forcing activation of state and zip only if in US
                    rdoVirginia.setVisible(true);
                    rdoNorthCarolina.setVisible(true);
                    rdoOhio.setVisible(true);
                    rdoMaryland.setVisible(true);
                    rdoPennsylvania.setVisible(true);
                    txtZipCode.setVisible(true);
           
        });
        
        btnInsert.setOnAction(e -> {
            //running validation then inserting data
            if (rdoEmployee.isSelected())
            {
                if (fullNameValidation() == true)
                {
                    if (!"".equals(txtSalary.getText()))
                    {
                        if (txtSalary.getText().matches(".*\\d+.*"))
                        {
                            if (txtZipCode.getText().length() == 5)
                            {
                                insertEmployee();
                            }
                            else
                            {
                                txtaOutput.setText("Please enter a valid 5 digit zip code");
                            }
                        }
                        else
                        {
                            txtaOutput.setText("Please enter Valid Salary");
                        }
                    }
                    else
                    {
                        txtaOutput.setText("Please enter Valid Salary");
                    }
                }
                else
                {
                    txtaOutput.setText("Please enter a Valid Name");
                }
            }
            else if (rdoAccident.isSelected())
            {
                if (fullNameValidation() == true)
                {
                    if (accidentValidation() == true)
                    {
                        insertAccident();
                    }
                }
                else
                {
                    txtaOutput.setText("Please enter a Valid Name");
                }
            }
            clearForm();
        });
        
        btnCommit.setOnAction(e -> {
            //sending array to the database
            commitToDB();
            clearForm();
        });
        
        btnDisplayInfo.setOnAction(e -> {
            //displaying the stored information regarding employees and accidents
            clearForm();
            displayData();
        });
        
        btnDisplayTrucks.setOnAction(e -> {
            // showing all data on trucks
            clearForm();
            displayTrucks();
        });
        
        btnDisplaySalary.setOnAction(e ->{
            //totals up salaries in database and displays
            clearForm();
            salaryTotal();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public void insertEmployee()
    {
        String country = "";
        String state = "";
        String zipCode = "";
        String termDate = "";
        
        if (rdoUnitedStates.isSelected())
        {
            //only grabbing values if in US otherwise null is returned
            //also changes names to abbreviations
            zipCode = txtZipCode.getText();
            if (rdoVirginia.isSelected())
                state = "VA";
            if (rdoNorthCarolina.isSelected())
                state = "NC";
            if (rdoOhio.isSelected())
                state = "OH";
            if (rdoMaryland.isSelected())
                state = "MD";
            if (rdoPennsylvania.isSelected())
                state = "PN";
        }
        if (terminationDate.getValue() != null)
        {
            termDate = terminationDate.getValue().toString();
        }
        if (rdoUnitedStates.isSelected())
            country = "US";
        if (rdoSweden.isSelected())
            country = "SE";
        if (rdoSwitzerland.isSelected())
            country = "CH";
        if (rdoGermany.isSelected())
            country = "DE";
        if (rdoFrance.isSelected())
            country = "FR";
        
        //gets location of employee in array
        int test = Employee.findEmployee(txtFirstName.getText(),txtLastName.getText());
        
        if (test == -1)
        {
            if (Employee.numEmployee < Employee.length)
            {
                //adds employees to array
                Employee.createEmployee(new Employee(txtFirstName.getText(), 
                        txtMiddleIntial.getText(), txtLastName.getText(), 
                        txtStreetAddress.getText(), birthDate.getValue().toString(), 
                        hireDate.getValue().toString(), licenseDate.getValue().toString(), 
                        (Double.parseDouble(txtSalary.getText())), country, 
                        state, zipCode, termDate));
                //if all else has run properly then the age will be displayed
                employeeAge();
            }
        }
        else
        {
            txtaOutput.setText("An Employee with that name already exists.");
        }
    }
    
    public void insertAccident ()
    {
        int ID;
        //find employee location in array
        int position = Employee.findEmployee(txtFirstName.getText(), 
                txtLastName.getText());
        if (position >= 0)
        {
            //inserts accident information into the employees accident array
            ID = Employee.employeeArray[position].getEmployeeID();
            Employee.employeeArray[position].addEmployeeAccident(
                    accidentDate.getValue().toString(), txtAccidentDescription.getText(), 
                    txtAccidentLocation.getText(), ID);
        }
        else
        {
            txtaOutput.setText("Employee does not exist.");
        }
    }
    
    public static void sendDBCommand(String sqlQuery)
    {
        //establishing variables for connection
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        OracleDataSource ds;
        
        try
        {
            //connecting to the specified database
            ds = new OracleDataSource();
            ds.setURL(URL);
            dbConn = ds.getConnection(userID, userPASS);
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void commitToDB ()
    {
        //establishing variables to be used and clearing database of existing data
        int i = 0;
        int j = 0;
        String sqlQuery2 = "DELETE FROM ACCIDENT";
        sendDBCommand(sqlQuery2);
        String sqlQuery = "DELETE FROM EMPLOYEE";
        sendDBCommand(sqlQuery);
        
        while (i<Employee.numEmployee)
        {
            //inserts the entire employee array and any accidents into the database
            sqlQuery = "INSERT INTO EMPLOYEE VALUES (";
            sqlQuery += (Integer.toString(Employee.employeeArray[i].getEmployeeID())) + ",'" 
                    + Employee.employeeArray[i].getFirstName() + "','" 
                    + Employee.employeeArray[i].getLastName() + "','" 
                    + Employee.employeeArray[i].getMiddleIntial() + "','" 
                    + Employee.employeeArray[i].getStreetAddress() + "','" 
                    + Employee.employeeArray[i].getZipCode() + "'," 
                    + "(TO_Date(" + formatDate(Employee.employeeArray[i].getDateOfBirth()) + ",'YYYYMMDD'))," 
                    + "(To_Date(" + formatDate(Employee.employeeArray[i].getHireDate()) + ",'YYYYMMDD'))," 
                    + "(To_Date(" + formatDate(Employee.employeeArray[i].getTerminationDate()) + ",'YYYYMMDD'))," 
                    + Employee.employeeArray[i].getAnnualSalary() + "," 
                    + "(To_Date(" + formatDate(Employee.employeeArray[i].getLicenseDate()) + ",'YYYYMMDD')),'" 
                    + Employee.employeeArray[i].getState() + "','" 
                    + Employee.employeeArray[i].getCountry() + "')";
            sendDBCommand(sqlQuery);
            
            while (j < Employee.employeeArray[i].getNumAccidents())
            {
                sqlQuery2 = "INSERT INTO ACCIDENT VALUES (";
                sqlQuery2 += Employee.employeeArray[i].accidentArray[j].getAccidentID() 
                        + "," + "(To_Date(" + formatDate(Employee.employeeArray[i].accidentArray[j].getDateOfAccident()) + ",'YYYYMMDD'))" 
                        + ",'" + Employee.employeeArray[i].accidentArray[j].getAccidentDescription()
                        + "','" + Employee.employeeArray[i].accidentArray[j].getAccidentLocation() 
                        + "'," + Employee.employeeArray[i].accidentArray[j].getEmployeeID() 
                        + ")";
                sendDBCommand(sqlQuery2);
                j++;
            }
            i++;
        }
    }
    
    public void importTruck ()
    {
        //clears existing data for database
        String sqlQuery = "Delete FROM TRUCK";
        sendDBCommand(sqlQuery);
        
        //inserting preloaded truck data to the database
        sqlQuery = "INSERT INTO TRUCK VALUES (11111111111111111,'Mack',"
                + "'Pinnacle',100000,'AAAAAA')";
        sendDBCommand(sqlQuery);
        sqlQuery = "INSERT INTO TRUCK VALUES (22222222222222222,'Mack',"
                + "'Pinnacle',150000,'BBBBBB')";
        sendDBCommand(sqlQuery);
        sqlQuery = "INSERT INTO TRUCK VALUES (33333333333333333,'Mack',"
                + "'Pinnacle',90000,'CCCCCC')";
        sendDBCommand(sqlQuery);
        sqlQuery = "INSERT INTO TRUCK VALUES (44444444444444444,'Mack',"
                + "'Pinnacle',75000,'DDDDDD')";
        sendDBCommand(sqlQuery);
        sqlQuery = "INSERT INTO TRUCK VALUES (55555555555555555,'Mack',"
                + "'Pinnacle',120000,'EEEEEE')";
        sendDBCommand(sqlQuery);
    }
    
    public void importCountryStates ()
    {
        //deleting data from the database
        try
        {
        String sqlQuery = "DELETE FROM EMPLOYEE";
        sendDBCommand(sqlQuery);
        sqlQuery = "DELETE FROM COUNTRY";
        sendDBCommand(sqlQuery);
        sqlQuery = "DELETE FROM HOMESTATE";
        sendDBCommand(sqlQuery);
        
        //inserting preloaded data into the database
            sqlQuery = "INSERT INTO HOMESTATE VALUES ('VA','Virgina')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO HOMESTATE VALUES ('NC','North Carolina')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO HOMESTATE VALUES ('PN','Pennsylvania')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO HOMESTATE VALUES ('OH','Ohio')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO HOMESTATE VALUES ('MD','Maryland')";
            sendDBCommand(sqlQuery);
            
            sqlQuery = "INSERT INTO COUNTRY VALUES ('US','United States')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO COUNTRY VALUES ('SE','Sweden')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO COUNTRY VALUES ('CH','Switzerland')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO COUNTRY VALUES ('DE','Germany')";
            sendDBCommand(sqlQuery);
            sqlQuery = "INSERT INTO COUNTRY VALUES ('FR','France')";
            sendDBCommand(sqlQuery);
        }
        catch (Exception e)
        {
        }
    }
    
    public void employeeAge ()
    {
        try
        {
            //inistiating variables and seperating birthdate into parts
        String fullDate = birthDate.getValue().toString();
        String year = fullDate.substring(0,4);
        String month = fullDate.substring(5,7);
        String day = fullDate.substring(8);
        Period p = null;
        
        //establishing month and figuring out age
        switch (month)
        {
            case "01":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.JANUARY,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "02":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.FEBRUARY,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "03":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.MARCH,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "04":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.APRIL,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "05":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.MAY,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "06":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.JUNE,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "07":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.JULY,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "08":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.AUGUST,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "09":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.SEPTEMBER,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "10":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.OCTOBER,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "11":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.NOVEMBER,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
            case "12":
            {
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(Integer.valueOf(year),Month.DECEMBER,
                Integer.valueOf(day));
                p = Period.between(birthday, today);
                break;
            }
        }
        lblAge.setText(String.valueOf(p.getYears()));
        }
        catch (Exception e)
        {  
        }
    }
    
    public void displayTrucks()
    {
        try
        {
            String sqlQuery = "";
            String output = "";
            int i = 1;
            
            //querying the database
            sqlQuery += "SELECT * FROM TRUCK";
            sendDBCommand(sqlQuery);
            
            //displaying all results for trucks regardless of quantity
            while (dbResults.next())
            {
                output += dbResults.getString(i) + "\t";
                i++;
                output += dbResults.getString(i) + "\t";
                i++;
                output += dbResults.getString(i) + "\t";
                i++;
                output += dbResults.getString(i) + "\t";
                i++;
                output += dbResults.getString(i) + "\n";
                i = 1;
            }
            txtaOutput.setText(output);
        }
        catch (SQLException e)
        {}
    }
    
    public void salaryTotal ()
    {
        String output = "";
        int sum = 0;
        int i = 1;
        try
        {
            //querying the database
        String sqlQuery = "";
        sqlQuery += "SELECT ANNUALSALARY FROM EMPLOYEE";
        sendDBCommand(sqlQuery);
        
        while (dbResults.next())
            //adding salaries to the total for as many as exist
        {
            sum += dbResults.getInt(i);
        }
        
        //displaying the total
        output = "Total Salaries = $" + Integer.toString(sum);
        
        txtaOutput.setText(output);
        }
        catch (SQLException e)
        {}
    }
    
    public void displayData ()
    {
        try
        {
            String output = "";
            String sqlQuery;
        //querying the database with a left join sql command
            sqlQuery = "Select e.EMPLOYEEID, e.LASTNAME, e.FIRSTNAME, a.DATEOFACCIDENT, "
                + "a.ACCIDENTDESCRIPTION, a.ACCIDENTLOCATION FROM EMPLOYEE e "
                + "LEFT JOIN ACCIDENT a ON e.EMPLOYEEID = a.EMPLOYEEID";
            sendDBCommand(sqlQuery);
        
            while (dbResults.next())
            {
                //dynamically displaying all data from database query
                int i = 1;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + " - ";
                }
                i++;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + ", ";
                }
                i++;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + "\n";
                }
                i++;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + "\t";
                }
                i++;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + "\n";
                }
                i++;
                if (dbResults.getString(i) != null)
                {
                    output += dbResults.getString(i) + "\n";
                }
                i++;
            }
            txtaOutput.setText(output);
        }
        catch (SQLException e)
        {}
    }
    
    public boolean fullNameValidation ()
    {
        //verifying the entire name has been entered correctly
        boolean valid = false;
        if (!"".equals(txtFirstName.getText()))
        {
            if (!"".equals(txtMiddleIntial.getText()))
            {
                if (!"".equals(txtLastName.getText()))
                {
                    if (txtFirstName.getText().matches(".*\\d+.*"))
                    {}
                    else
                    {
                        if (txtMiddleIntial.getText().matches(".*\\d+.*"))
                        {}
                        else
                        {
                            if (txtLastName.getText().matches(".*\\d+.*"))
                            {}
                            else
                            {
                                valid = true;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }
    
    public boolean accidentValidation ()
    {
        //verifying accident data is entered correctly
        boolean valid = false;
        
        if (!"".equals(txtAccidentDescription.getText()))
        {
            if (!"".equals(txtAccidentLocation.getText()))
            {
                valid = true;
            }
        }
        
        return valid;
    }
    
    public String formatDate(String date)
    {
        //changing the date to a format for database entry
        String finalForm = null;
        try
        {
            if (date != null)
            {
                String year = date.substring(0,4);
                String month = date.substring(5,7);
                String day = date.substring(8);
        
                finalForm = year + month + day;
            }
            else
            {
                finalForm = null;
            }
        }
        catch (Exception e)
        {
        }
        return finalForm;
    }
    
    public void clearForm()
    {
        //clears the form returning it to the intial point
        txtFirstName.clear();
        txtLastName.clear();
        txtMiddleIntial.clear();
        txtStreetAddress.clear();
        txtZipCode.clear();
        txtSalary.clear();
        birthDate.setValue(null);
        hireDate.setValue(null);
        licenseDate.setValue(null);
        terminationDate.setValue(null);
        accidentDate.setValue(null);
        txtAccidentDescription.clear();
        txtAccidentLocation.clear();
        txtaOutput.setText("");
        rdoVirginia.setSelected(false);
        rdoNorthCarolina.setSelected(false);
        rdoOhio.setSelected(false);
        rdoMaryland.setSelected(false);
        rdoPennsylvania.setSelected(false);
        rdoUnitedStates.setSelected(false);
        rdoSweden.setSelected(false);
        rdoSwitzerland.setSelected(false);
        rdoGermany.setSelected(false);
        rdoFrance.setSelected(false);
        
        rdoEmployee.setSelected(false);
        rdoAccident.setSelected(false);
        txtFirstName.setVisible(false);
        txtLastName.setVisible(false);
        txtMiddleIntial.setVisible(false);
        txtStreetAddress.setVisible(false);
        txtZipCode.setVisible(false);
        txtSalary.setVisible(false);
        birthDate.setVisible(false);
        hireDate.setVisible(false);
        licenseDate.setVisible(false);
        terminationDate.setVisible(false);
        accidentDate.setVisible(false);
        txtAccidentDescription.setVisible(false);
        txtAccidentLocation.setVisible(false);
        rdoVirginia.setVisible(false);
        rdoNorthCarolina.setVisible(false);
        rdoOhio.setVisible(false);
        rdoMaryland.setVisible(false);
        rdoPennsylvania.setVisible(false);
        rdoUnitedStates.setVisible(false);
        rdoSweden.setVisible(false);
        rdoSwitzerland.setVisible(false);
        rdoGermany.setVisible(false);
        rdoFrance.setVisible(false);
        lblDateFormat.setVisible(false);
    }
}
