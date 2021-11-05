import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UserAccountPage {

    App m = new App();

    @FXML
    void goToDepositPage(ActionEvent event) {

    }

    @FXML
    void goToTransferPage(ActionEvent event) {

    }

    @FXML
    void goToWithdrawPage(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/HomePage.fxml");
    }

}
