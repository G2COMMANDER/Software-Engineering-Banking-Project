import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.poi.ss.usermodel.Cell;
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

    private String objUName;
    private String objFName;
    private String objLName;

    /*
    HomePage() {
        this.objUName = "x";
        this.objFName = "y";
        this.objLName = "z";
    }

    public String getUName() {
        return this.objUName;
    }

    public void setUName(String u) {
        this.objUName = u;
    }

    public String getFName() {
        return this.objFName;
    }

    public void setFName(String f) {
        this.objFName = f;
    }

    public String getLName() {
        return this.objLName;
    }

    public void setLName(String l) {
        this.objLName = l;
    }

    */
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
                
                Cell userCell = row.getCell(0); // Get the Cell at the Index / Column you want.
                Cell passCell = row.getCell(7); // Get the Cell at the Index / Column you want.
                Cell employeeCell = row.getCell(6); // Get the Cell at the Index / Column you want.
                tmpUser = userCell.getStringCellValue(); // turns it into a string
                tmpPass = passCell.getStringCellValue(); // turns it into a string
                tmpEmployee = employeeCell.getStringCellValue(); // turns it into a string

                // checks if the username and password matches. Then it checks if the user is an employee
                if (uName.equals(tmpUser) && pWord.equals(tmpPass) && tmpEmployee.equals("f")) {
                    checkCreds += 1;
                    System.out.println(userCell.getColumnIndex() + " Found Username: " + userCell.getStringCellValue());
                    System.out.println(passCell.getColumnIndex() + " Found Password: " + passCell.getStringCellValue());
                    System.out.println(employeeCell.getColumnIndex() + " Employee Status: " + employeeCell.getStringCellValue());
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
    void goToRequestPage(ActionEvent event) throws IOException {

        m.changeScene("fxml_pages/RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        String uName = username.getText().toString();
        String pWord = toHexString(getSHA256(password.getText().toString()));
        String pWordTest = password.getText().toString();

        System.out.println(uName + "\n" + pWordTest + "\n" + pWord);

        Integer login = checkCredentials(uName, pWord);
        System.out.println("This is the login number " + login);

        switch (login) {
            case 1: m.changeScene("fxml_pages/UserAccountPage.fxml");
                    App.usah = uName;
                    break;
            case 3: m.changeScene("fxml_pages/EmployeeAccountPage.fxml");
                    break;
            default: System.out.println("Login Erorr, please try again.");
                    break;
        }
    }
}
