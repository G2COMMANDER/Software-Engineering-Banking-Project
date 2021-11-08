import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.message.Message;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomePage {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    App m = new App();

    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        while (hexString.length() < 32) {

            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    @FXML
    void goToEmployeePage(ActionEvent event) throws IOException {

        m.changeScene("fxml_pages/EmployeeAccountPage.fxml");
    }

    @FXML
    void goToRequestPage(ActionEvent event) throws IOException {

        m.changeScene("fxml_pages/RequestAccountPage.fxml");
    }

    @FXML
    void goToUserAccountPage(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        String uName = username.getText().toString();
        String pWord = toHexString(getSHA256(password.getText().toString()));

        System.out.println(uName + "\n" + pWord);

        m.changeScene("fxml_pages/UserAccountPage.fxml");
    }

}
