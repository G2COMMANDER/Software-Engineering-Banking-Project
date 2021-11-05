import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomePage {

    @FXML
    private Button loginBtn;
    @FXML
    private Button reqActBtn;

    App m = new App();

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException {
        m.changeScene("RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException {
        m.changeScene("UserAccountPage.fxml");
    }

}
