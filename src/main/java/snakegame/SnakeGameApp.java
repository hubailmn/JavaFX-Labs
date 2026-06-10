package snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import snakegame.view.controller.SceneController;

public class SnakeGameApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 800, 850);
        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();

        SceneController controller = new SceneController(root);
        controller.showMainMenu();
    }
}