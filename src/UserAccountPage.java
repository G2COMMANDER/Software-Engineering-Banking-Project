import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserAccountPage {
    
    @FXML
    private Button depositMoneyBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button transferMoneyBtn;
    @FXML
    private Button withdrawMoneyBtn;

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
        App m = new App();
        m.changeScene("HomePage.fxml");
    }

}
