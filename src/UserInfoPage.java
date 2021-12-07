import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserInfoPage {

    App m = new App();

    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label uNameLabel;
    @FXML
    private Label doBLabel;

    @FXML
    void refreshPage(ActionEvent event) {
        fNameLabel.setText(App.efName);
        lNameLabel.setText(App.elName);
        uNameLabel.setText(App.usah);
        doBLabel.setText(App.birth);
    }
    
    @FXML
    void returnHome(ActionEvent event) throws IOException {
        m.changeScene("fxml_pages/customer_pages/UserAccountPage.fxml");
    }

}
