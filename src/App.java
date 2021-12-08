import java.io.IOException;
import com.gembox.spreadsheet.*;
import javafx.application.Application;
import javafx.beans.binding.When.BooleanConditionBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    static {
        //this is necesarry for the Gembox dependencies to work
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    // These are global variables so that the User experience is more interactive
    static String usah; // This is shorthand for username
    static String efName; // This is shorthand for fName (First Name)
    static String elName; // This is shorhand for lName (Last Name)
    static String birth; // This is shorthand for DoB (Date of Birth)
    static String balanceAmountuh; // This is shorthand for balanceAmount (Balance Amount)

    /*
    static boolean checkIfBlank(String... values) {

        for (String s : values) {
            if (s.)
        }
    }
    */
    

    // This sets the stage for the JavaFX program to run on
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("fxml_pages/HomePage.fxml"));
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // This is a global function to allow changing of scenes between the pages
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
