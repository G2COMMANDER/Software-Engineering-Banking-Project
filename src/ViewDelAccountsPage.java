import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ViewDelAccountsPage {

    App m = new App();

    @FXML
    private TableView table;
    @FXML
    private Label updateStatus;

    @FXML
    void exitPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/EmployeeAccountPage.fxml");
    }

    @FXML
    void loadRequests(ActionEvent event) {
        
    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

}
