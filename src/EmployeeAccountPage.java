import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EmployeeAccountPage {

    App m = new App();

    // These are all buttons that redirect to the page they are referencing
    @FXML
    void goToAcceptDenyPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/UserListPage.fxml");
    }

    @FXML
    void goToDeleteAccountsPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/employee_pages/ViewDelAccountsPage.fxml");
    }

    @FXML
    void goToUserListPage(ActionEvent event) throws IOException {
        
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/HomePage.fxml");
    }

}
