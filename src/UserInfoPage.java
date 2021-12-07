import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class UserInfoPage {

    App m = new App();

    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label uNameLabel;

    @FXML
    void refreshPage(KeyEvent event) {
        cardNumberLabel.setText(App.cardNumbah);
        fNameLabel.setText(App.efName);
        lNameLabel.setText(App.elName);
        uNameLabel.setText(App.usah);
    }
    
    @FXML
    void returnHome(ActionEvent event) throws IOException {
        m.changeScene("src/fxml_pages/customer_pages/UserAccountPage.fxml");
    }

}
