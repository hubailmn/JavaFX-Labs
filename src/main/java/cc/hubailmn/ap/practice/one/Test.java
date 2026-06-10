package cc.hubailmn.ap.practice.one;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));


        TriangleMesh triangleMesh = new TriangleMesh();

        triangleMesh.getPoints().addAll(0, 0, 0, 10, 10, 10);




        Scene scene = new Scene(
                new VBox(), 450, 400
        );

        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
