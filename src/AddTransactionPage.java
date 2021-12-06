import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddTransactionPage {

    App m = new App();

    @FXML
    private TextField Amount;
    @FXML
    private TextArea Comments;
    @FXML
    private TextField Date;
    @FXML
    private TextField Location;
    @FXML
    private TextField Time;
    @FXML
    private Label balanceAmount;

    @FXML
    void Deposit(ActionEvent event) {

    }

    @FXML
    void Withdraw(ActionEvent event) {

    }

    @FXML
    void refreshPage(KeyEvent event) {

    }

    @FXML
    void returnToHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/UserAccountPage.fxml");
    }

}
