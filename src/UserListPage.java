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
import java.io.IOException;

public class UserListPage {

    App m = new App();

    @FXML
    private TableView table;

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
    }

    @FXML
    void loadUserList(ActionEvent event) throws IOException {

        FileInputStream file = new FileInputStream(new File("src/Database/Database.xlsx"));

        ExcelFile workbook = ExcelFile.load(file);

        ExcelWorksheet worksheet = workbook.getWorksheet(1);
        String[][] sourceData = new String[50][5];
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
    void returnHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/EmployeeAccountPage.fxml");
    }

}
