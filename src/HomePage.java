import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomePage {

    @FXML
    private Button loginBtn;

    @FXML
    private Button reqActBtn;

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("UserAccountPage.fxml");
    }

}
