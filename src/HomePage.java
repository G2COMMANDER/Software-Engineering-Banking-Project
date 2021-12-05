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
    private boolean checkCredentials(String uName, String pWord) {

        boolean checkCreds = false;
        String tmpUser;
        String tmpPass;
        
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
                tmpUser = userCell.getStringCellValue(); // turns it into a string
                tmpPass = passCell.getStringCellValue(); // turns it into a string

                // checks if the username and password matches
                if (uName.equals(tmpUser) && pWord.equals(tmpPass)) {
                    checkCreds = true;
                    System.out.println(userCell.getColumnIndex() + " Found Username: " + userCell.getStringCellValue());
                    System.out.println(passCell.getColumnIndex() + " Found Password: " + passCell.getStringCellValue());
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
    void goToEmployeePage(ActionEvent event) throws IOException {

        String uName = username.getText().toString();
        String pWord = password.getText().toString();

        if (uName.equals("banana") && pWord.equals("test")) {
            m.changeScene("fxml_pages/EmployeeAccountPage.fxml");
        } else {
            System.out.println("Try again lmao");
        }

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

        if (checkCredentials(uName, pWord) == true) {
            m.changeScene("fxml_pages/UserAccountPage.fxml");
        }
        
    }

}
