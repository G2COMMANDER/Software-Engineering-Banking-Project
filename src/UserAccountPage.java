import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserAccountPage {

    App m = new App();

    @FXML
    private Label balanceAmount;
    @FXML
    private Label name;

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
