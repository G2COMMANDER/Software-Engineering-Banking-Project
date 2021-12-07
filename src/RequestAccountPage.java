import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class RequestAccountPage {

    @FXML
    private TextField userName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField checkPassword;

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

    private int checkRequestedAccount(String uNameC, String fNameC, String lNameC, String passC, String checkPassC) {
        
        int checkReqAcc = 0;
        String tmpUName;
        String tmpFName;
        String tmpLName;
        String tmpDOB = "test";

        try {

            FileInputStream file = new FileInputStream(new File("src/Database/Database.xlsx"));

            // Create Workbook instance holding reference to Database.xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);



            for (int i = 0; i < 2; i++) {

                // Get UserList sheet from Database.xlsx
                XSSFSheet sheet = workbook.getSheetAt(i);
                
            

                // this checks the username, firstname, and lastname
                for (Row row : sheet) { // For each Row.
                    
                    Cell uNameCell = row.getCell(0); // this is the username cell
                    Cell fNameCell = row.getCell(1); // this is the first name cell
                    Cell lNameCell = row.getCell(2); // this is the last name cell

                    tmpUName = uNameCell.getStringCellValue();
                    tmpFName = fNameCell.getStringCellValue();
                    tmpLName = lNameCell.getStringCellValue();

                    if (fNameC.equals(tmpFName) && lNameC.equals(tmpLName)) { // checks is first and last name exist
                        checkReqAcc = 1;
                        System.out.println("The user \"" + fNameCell.getStringCellValue() + " " + lNameCell.getStringCellValue() + "\" already exists");
                        break;
                    } else if (uNameC.equals(tmpUName)) { // checks if the username already exists
                        checkReqAcc = 2;
                        System.out.println("The username \"" + uNameCell.getStringCellValue() + "\" already exists");
                        break;
                    } else if (!passC.equals(checkPassC)) { // checks if the passwords match
                        checkReqAcc = 3;
                        System.out.println("Passwords do not match, please try again.");
                        break;
                    } else {
                        continue;
                    }
                }
            }

            if (checkReqAcc == 0) {

                passC = toHexString(getSHA256(passC));

                XSSFSheet sheet = workbook.getSheetAt(1);

                Object[][] inputdata = {
                    {uNameC, fNameC, lNameC, tmpDOB},
                };
    
                int rowCount = sheet.getLastRowNum();
    
                for (Object[] anInput : inputdata) {
                    Row row = sheet.createRow(++rowCount);
                    
                    int columnCount = 0;
    
                    Cell cell = row.createCell(columnCount);
                    cell.setCellValue(rowCount);
    
                    for (Object field : anInput) {
                        cell = row.createCell(columnCount++);
                        if (field instanceof String) {
                            cell.setCellValue((String) field);
                        } else if (field instanceof Integer) {
                            cell.setCellValue((Integer) field);
                        }
                    }
                }

                FileOutputStream fileOutput = new FileOutputStream("src/Database/Database.xlsx");
                workbook.write(fileOutput);
                fileOutput.close();
            }

            file.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("Something broke lol?");
        }
        
        return checkReqAcc;
    }
    
    @FXML
    void requestAccount(ActionEvent event) throws IOException {

        String uName = userName.getText().toString();
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String pass = password.getText().toString();
        String checkPass = checkPassword.getText().toString();

        int test = checkRequestedAccount(uName, fName, lName, pass, checkPass);

        switch (test) {
            case 0: System.out.println("Account created successfully!");
                    break;
            case 1: System.out.println("Add the first/last name scenebuilder thing here");
                    break;
            case 2: System.out.println("Add the username scenebuilder thing here");
                    break;
            case 3: System.out.println("Add scenebuilder password thing here");
                    break;
            default: break;
        }
    }

    @FXML
    void returnHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/HomePage.fxml");
    }

}
