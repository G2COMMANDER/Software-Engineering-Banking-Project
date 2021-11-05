import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RequestAccountPage {

    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private PasswordField pass;
    @FXML
    private Button reqActBtn;
    @FXML
    private TextField uName;

    App m = new App();
    
    @FXML
    void sendAndReturn(ActionEvent event) throws IOException {
        m.changeScene("HomePage.fxml");
    }

}
