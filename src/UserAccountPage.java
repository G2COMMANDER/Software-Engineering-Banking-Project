import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class UserAccountPage {

    App m = new App();

    @FXML
    private Label name;

    @FXML
    void goToTransactHistoryPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/customer_pages/TransactionHistoryPage.fxml");
    }

    @FXML
    void goToAddTransactionPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/customer_pages/AddTransactionPage.fxml");
    }

    @FXML
    void goToTransferMoneyPage(ActionEvent event) {
        //m.changeScene("fxml_pages/customer_pages/TransferMoneyPage.fxml");
    }

    @FXML
    void goToUserInfoPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/customer_pages/UserInfoPage.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/HomePage.fxml");
    }

    public void initialize() {
        name.setText(App.efName + " " + App.elName);
    }
}