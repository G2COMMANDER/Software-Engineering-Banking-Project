import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
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

    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException { // https://www.geeksforgeeks.org/sha-256-hash-in-java/

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) { // https://www.geeksforgeeks.org/sha-256-hash-in-java/
        
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        while (hexString.length() < 32) {

            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private boolean checkCredentials(String uName, String pWord) {

        boolean checkCreds = false;
        String tmp;
        
        try { // https://www.geeksforgeeks.org/reading-writing-data-excel-file-using-apache-poi/?ref=lbp
            
            FileInputStream file = new FileInputStream(new File("src/Database/Database.xlsx"));

            // Create Workbook instance holding reference to Database.xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get UserList sheet from Database.xlsx
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                
                Row row = rowIterator.next();
                
                
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    
                    Cell cell = cellIterator.next();
                    tmp = cell.getStringCellValue().toString();

                    if (uName.equals(tmp)) {
                        checkCreds = true;
                        System.out.println("Found Password: " + cell.getStringCellValue());
                        break;
                        
                    } else {
                        System.out.println("Password Incorrect: " + cell.getStringCellValue());
                        break;
                    }

                }
                

            }

            file.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
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
