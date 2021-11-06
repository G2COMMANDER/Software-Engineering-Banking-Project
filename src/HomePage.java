import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomePage {

    App m = new App();

    @FXML
    void goToEmployeePage(ActionEvent event) {
        m.changeScene("fxml_pages/EmployeeAccountPage.fxml");
    }

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/UserAccountPage.fxml");
    }

}
