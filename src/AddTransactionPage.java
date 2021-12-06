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
import javafx.scene.control.Label;
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
    @FXML
    private Label balanceAmount;

    private void writeToExcel(int nSheet) {

        int nAmount = Integer.parseInt(Amount.getText());
        //String nAmount = Amount.getText().toString();
        String nTime = Time.getText().toString();
        String nDate = Date.getText().toString();
        String nLocation = Location.getText().toString();

        try{

            FileInputStream file = new FileInputStream(new File("src/Database/UserInformation/banana.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(nSheet);

            Object[][] inputdata = {
                {nAmount, nTime, nDate, nLocation},
            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] anInput : inputdata) {
                Row row = sheet.createRow(++rowCount);
                
                int columnCount = 0;

                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);

                for (Object field : anInput) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }

            file.close();

            FileOutputStream fileOutput = new FileOutputStream("src/Database/UserInformation/banana.xlsx");
            workbook.write(fileOutput);
            workbook.close();
            fileOutput.close();

        } catch (Exception e) {
            System.out.print("Try again lmao");
        }
    }

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
    void Deposit(ActionEvent event) {
        int sheetNum = 2;
        writeToExcel(sheetNum);
    }

    @FXML
    void Withdraw(ActionEvent event) {
        int sheetNum = 1;
        writeToExcel(sheetNum);
    }

    @FXML
    void refreshPage(KeyEvent event) {
        //int sheetNum = 0;
        //getBalanceAmount(sheetNum);
    }

    @FXML
    void returnToHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/UserAccountPage.fxml");
    }

}
