package cc.hubailmn.ap.lab4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SmartLampApp extends Application {

    private boolean isLampOn = true;
    private Circle lamp;
    private Color currentColor;
    private Button dimButton;
    private Button brightenButton;

    public static void runer(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle rectangle = new Rectangle(300, 250);
        rectangle.setFill(Color.DARKGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);

        Line line = new Line(0, 0, 0, 95);
        line.setStrokeWidth(4);
        line.setStroke(Color.BLACK);

        lamp = new Circle(30);
        lamp.setFill(Color.YELLOW);
        currentColor = (Color) lamp.getFill();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, line, lamp);
        StackPane.setAlignment(line, Pos.TOP_CENTER);

        Button toggleButton = new Button("Toggle Power");
        dimButton = new Button("Dim Light");
        brightenButton = new Button("Brighten Light");

        toggleButton.setOnAction(event -> {
            isLampOn = !isLampOn;
            if (isLampOn) {
                lamp.setFill(currentColor);
                dimButton.setDisable(false);
                brightenButton.setDisable(false);
            } else {
                currentColor = (Color) lamp.getFill();
                lamp.setFill(Color.DARKSLATEGRAY);
                dimButton.setDisable(true);
                brightenButton.setDisable(true);
            }
        });

        dimButton.setOnAction(event -> {
            if (isLampOn) {
                lamp.setOpacity(0.1);
            }
        });

        brightenButton.setOnAction(event -> {
            if (isLampOn) {
                lamp.setOpacity(1.0);
            }
        });

        HBox settings = new HBox(10);
        settings.setAlignment(Pos.CENTER);
        settings.getChildren().addAll(toggleButton, dimButton, brightenButton);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        vBox.setStyle("-fx-background-color: #676161;");

        vBox.getChildren().addAll(stackPane, settings);

        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setTitle("Simple Lamp");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}