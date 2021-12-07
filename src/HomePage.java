import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomePage {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    App m = new App();

    // this function is what encrypts the password to be stored in the database
    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException { // https://www.geeksforgeeks.org/sha-256-hash-in-java/

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    // this function turns the encrypted password value into a string type
    public static String toHexString(byte[] hash) { // https://www.geeksforgeeks.org/sha-256-hash-in-java/
        
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        while (hexString.length() < 32) {

            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // this function checks the username and password. The heart of the login system
    private int checkCredentials(String uName, String pWord) {

        int checkCreds = 0;
        String tmpUser;
        String tmpFName;
        String tmpLName;
        String tmpDateOfBirth = "oops";
        //String tmpCardNumber;
        String tmpPass;
        String tmpEmployee;
        
        try { // https://www.geeksforgeeks.org/reading-writing-data-excel-file-using-apache-poi/?ref=lbp
            
            FileInputStream file = new FileInputStream(new File("src/Database/Database.xlsx"));

            // Create Workbook instance holding reference to Database.xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get UserList sheet from Database.xlsx
            XSSFSheet sheet = workbook.getSheetAt(0);

            // this checks the username and password
            for (Row row : sheet) { // For each Row.
                
                // This gets the information from each column in a row and assigns them to a cell named after the column header
                Cell userCell = row.getCell(0); // This gets the cell at the Username column
                Cell fNameCell = row.getCell(1); // This gets the cell at the First Name column
                Cell lNameCell = row.getCell(2); // This gets the cell at the Last Name column
                Cell dateOfBirth = row.getCell(3); // This gets the cell at the Date of Birth column
                //Cell cardNumberCell = row.getCell(4); // This gets the cell at the Card Number column. The card features were never implemented
                Cell passCell = row.getCell(5); // This 
                Cell employeeCell = row.getCell(4); // Get the Cell at the Index / Column you want.

                // This turns all the data in the cells into a String type (using .getStringCellValue)
                // so that it can be easily worked with in the .xlsx documents
                tmpUser = userCell.getStringCellValue();
                tmpFName = fNameCell.getStringCellValue(); 
                tmpLName = lNameCell.getStringCellValue(); 
                //tmpCardNumber = cardNumberCell.getStringCellValue(); // The card features were never implemented
                tmpPass = passCell.getStringCellValue(); 
                tmpEmployee = employeeCell.getStringCellValue(); 

                // This reads the users Date of Birth cell and converts it from a Date format to a String format
                // like the cells above
                if (dateOfBirth.getCellType() == CellType.STRING) {
                    tmpDateOfBirth = dateOfBirth.toString();
                } else if (dateOfBirth.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(dateOfBirth)) {
                        tmpDateOfBirth = dateOfBirth.getDateCellValue().toString();
                    } else {
                        System.out.println("welp all that went to waste");
                    }
                }

                // checks if the username and password matches. Then it checks if the user is an employee
                if (uName.equals(tmpUser) && pWord.equals(tmpPass) && tmpEmployee.equals("f")) {
                    checkCreds += 1;
                    System.out.println(userCell.getColumnIndex() + " Found Username: " + userCell.getStringCellValue());
                    System.out.println(passCell.getColumnIndex() + " Found Password: " + passCell.getStringCellValue());
                    System.out.println(employeeCell.getColumnIndex() + " Employee Status: " + employeeCell.getStringCellValue());
                    // This sets the global variables so that it can be used in other pages
                    App.usah = tmpUser;
                    App.efName = tmpFName;
                    App.elName = tmpLName;
                    App.birth = tmpDateOfBirth;
                    //App.cardNumbah = tmpCardNumber;
                    break;
                } else if (uName.equals(tmpUser) && pWord.equals(tmpPass) && tmpEmployee.equals("t")){
                    checkCreds += 3;
                    System.out.println(userCell.getColumnIndex() + " Found Username: " + userCell.getStringCellValue());
                    System.out.println(passCell.getColumnIndex() + " Found Password: " + passCell.getStringCellValue());
                    System.out.println(employeeCell.getColumnIndex() + " Employee Status: " + employeeCell.getStringCellValue());
                    break;
                } else {
                    continue;
                }
            }

            file.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("Username or Password incorrect. Please try again.");
            //e.printStackTrace();
        }

        return checkCreds;
    }

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException { // This is the action for the Request Account button

        m.changeScene("fxml_pages/RequestAccountPage.fxml");
    }

    @FXML // This action passes the username and password to "validate" functions to ensure proper login
    void goToUserAccountPage(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        String uName = username.getText().toString();
        String pWord = toHexString(getSHA256(password.getText().toString()));
        String pWordTest = password.getText().toString();

        System.out.println(uName + "\n" + pWordTest + "\n" + pWord);

        Integer login = checkCredentials(uName, pWord);
        System.out.println("This is the login number " + login);

        switch (login) {
            case 1: m.changeScene("fxml_pages/customer_pages/UserAccountPage.fxml");
                    break;
            case 3: m.changeScene("fxml_pages/employee_pages/EmployeeAccountPage.fxml");
                    break;
            default: System.out.println("Login Erorr, please try again.");
                    break;
        }
    }
}
