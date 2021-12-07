import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddTransactionPage {

    App m = new App();

    @FXML
    private TextField Amount;
    @FXML
    private TextArea Comments;
    @FXML
    private TextField Date;
    @FXML
    private TextField Location;
    @FXML
    private TextField Time;

    // This function retrieves the inputed data from the user and writes it into
    // their respective page (Withdrawal or Deposit depending on which button was pressed)
    private void writeToExcel(int nSheet) {

        // This takes the data entered into the TextFields from the user
        // and turns it into a String type
        String nAmount = Amount.getText().toString();
        String nTime = Time.getText().toString();
        String nDate = Date.getText().toString();
        String nLocation = Location.getText().toString();
        String nComment = Comments.getText().toString();

        // This is basically concactinating the pathname using the current user's username that
        // was obtained from HomePage
        String userDatabaseFile = ("src/Database/UserInformation/" + App.usah + ".xlsx");

        try{

            // This loads and sets up the Excel file using the Apache POU library
            // so that it can be manipulated with code
            FileInputStream file = new FileInputStream(new File(userDatabaseFile));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(nSheet);

            // This assigns the inputted data into an array so that it can then
            // be appended to the Excel document
            Object[][] inputdata = {
                {nAmount, nTime, nDate, nLocation, nComment},
            };

            int rowCount = sheet.getLastRowNum();

            // This loop is used to make a new row in the excel sheet and then
            // appends the data to each of their respective cells
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

            file.close();

            // This saves the changes onto the excel sheet
            FileOutputStream fileOutput = new FileOutputStream(userDatabaseFile);
            workbook.write(fileOutput);
            workbook.close();
            fileOutput.close();

        } catch (Exception e) {
            System.out.print("Try again lmao");
        }
        // This is a placeholder for a feature that would've outputted this text onto a label
        // in the application, but is instead outputting it to the console for now
        switch (nSheet){
            case 1: System.out.println("Withdraw data successfully written.");
                    break;
            case 2: System.out.println("Deposit data successfully written.");
                    break;
            default: break;
        }
    }

    // This is placeholder for the code to implement getting the Balance Amount
    /*
    private void getBalanceAmount(int nSheet) {

        String tmpBalanceAmount;

        try {

            FileInputStream file = new FileInputStream("src/Database/UserInformation/banana.xlsx");

            // Create Workbook instance holding reference to Database.xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(nSheet);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            tmpBalanceAmount = cell.getStringCellValue();

            balanceAmount.setText(tmpBalanceAmount);

            file.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("test");
        }
    }
    */

    @FXML
    void Deposit(ActionEvent event) { // This is the action for the Deposit button
        int sheetNum = 2;
        writeToExcel(sheetNum);
    }

    @FXML
    void Withdraw(ActionEvent event) { // This is the action for the Withdraw button
        int sheetNum = 1;
        writeToExcel(sheetNum);
    }

    @FXML
    void refreshPage(KeyEvent event) { // This is a placeholder for a refresh button
        //int sheetNum = 0;
        //getBalanceAmount(sheetNum);
    }

    @FXML
    void returnToHome(ActionEvent event) throws IOException { // This is the action for the Exit button
        m.changeScene("fxml_pages/customer_pages/UserAccountPage.fxml");
    }

}
