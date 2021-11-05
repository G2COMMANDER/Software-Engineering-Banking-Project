import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserListPage {

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

}
