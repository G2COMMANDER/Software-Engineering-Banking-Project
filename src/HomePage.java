import java.io.IOException;

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

    @FXML
    void goToEmployeePage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/EmployeeAccountPage.fxml");
    }

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException {
        System.out.println(username.getText().toString() + password.getText().toString());
        m.changeScene("fxml_pages/UserAccountPage.fxml");
    }

}
