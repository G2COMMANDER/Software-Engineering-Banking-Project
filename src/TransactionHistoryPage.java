import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TransactionHistoryPage {

    App m = new App();

    @FXML
    private TableView<?> table;

    @FXML
    void displayDeposits(ActionEvent event) {

    }

    @FXML
    void displayWithdrawls(ActionEvent event) {

    }

    @FXML
    void exitPage(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/customer_pages/UserAccountPage.fxml");
    }

}
