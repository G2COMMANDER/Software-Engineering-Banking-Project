import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

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

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
