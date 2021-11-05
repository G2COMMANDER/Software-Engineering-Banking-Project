import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomePage {

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
