package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "6410451059", 800, 600);
        configRoute();
        FXRouter.goTo("home");

    }

    private static void configRoute() {
        String packageStr = "ku/cs/";

        FXRouter.when("home", packageStr + "home.fxml");
        FXRouter.when("word_add",packageStr + "word_add.fxml");
        FXRouter.when("word_list",packageStr + "word_list.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}