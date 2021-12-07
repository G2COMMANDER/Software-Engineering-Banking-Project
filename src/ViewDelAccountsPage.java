import com.gembox.spreadsheet.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewDelAccountsPage {

    App m = new App();

    @FXML
    private TableView table;
    @FXML
    private Label updateStatus;

    private void fillTable(String[][] dataSource) {
        table.getColumns().clear();

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource)
            data.add(FXCollections.observableArrayList(row));
        table.setItems(data);

        for (int i = 0; i < dataSource[0].length; i++) {
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(ExcelColumnCollection.columnIndexToName(currentColumn));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            column.setEditable(true);
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    });
            table.getColumns().add(column);
        }
        updateStatus.setText("User List Loaded");
    }

    @FXML
    void exitPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/EmployeeAccountPage.fxml");
    }

    @FXML
    void loadRequests(ActionEvent event) throws IOException {

        FileInputStream file = new FileInputStream(new File("src/Database/Database.xlsx"));
        ExcelFile workbook = ExcelFile.load(file);
        ExcelWorksheet worksheet = workbook.getWorksheet(0);
        
        String[][] sourceData = new String[50][6];
        for (int row = 0; row < sourceData.length; row++) {
            for (int column = 0; column < sourceData[row].length; column++) {
                ExcelCell cell = worksheet.getCell(row, column);
                if (cell.getValueType() != CellValueType.NULL)
                    sourceData[row][column] = cell.getValue().toString();
            }
        }
        fillTable(sourceData);
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {

        /*
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        for (int row = 0; row < table.getItems().size(); row++) {
            ObservableList cells = (ObservableList) table.getItems().get(row);
            for (int column = 0; column < cells.size(); column++) {
                if (cells.get(column) != null)
                    worksheet.getCell(row, column).setValue(cells.get(column).toString());
            }
        }

        FileOutputStream fileOutput = new FileOutputStream("src/Database/Database.xlsx");
        file.save("src/Database/Database.xlsx");

        fileOutput.close();

        updateStatus.setText("File Successfully updated");
        */
    }
}
