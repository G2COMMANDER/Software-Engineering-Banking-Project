import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeAccountPage {

    @FXML
    private Label name;

    App m = new App();

    @FXML
    void goToAcceptDenyPage(ActionEvent event) {

    }

    @FXML
    void goToDeleteAccountsPage(ActionEvent event) {

    }

    @FXML
    void goToUserListPage(ActionEvent event) throws IOException {
        m.changeScene("src/fxml_pages/employee_pages/UserListPage.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/HomePage.fxml");
    }

}
