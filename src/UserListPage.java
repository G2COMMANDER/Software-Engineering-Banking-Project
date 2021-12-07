import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserListPage {

    App m = new App();

    @FXML
    private TableColumn<?, ?> cardInfoCol;
    @FXML
    private TableColumn<?, ?> dobCol;
    @FXML
    private TableColumn<?, ?> empStatCol;
    @FXML
    private TableColumn<?, ?> fNameCol;
    @FXML
    private TableColumn<?, ?> lNameCol;
    @FXML
    private TableColumn<?, ?> lastAccessedCol;
    @FXML
    private TableView<?> userList;
    @FXML
    private TableColumn<?, ?> userNameCol;

    @FXML
    void returnHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/EmployeeAccountPage.fxml");
    }

}
