package sa.edu.uqu.snakegame.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sa.edu.uqu.snakegame.view.controller.SceneController;

public class MainView extends VBox {

    public MainView(SceneController controller) {
        super(20);

        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #2A3D2A, #1A241A);");

        Label gameTitle = new Label("Snake Game");
        gameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameTitle.setTextFill(Color.web("#C8E6C9"));
        gameTitle.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        Button startGameButton = createStyledButton("Start Game");
        startGameButton.setOnAction(e -> controller.showGameMenu());

        Button statisticButton = createStyledButton("Statistic");
        statisticButton.setOnAction(e -> controller.showStatisticMenu());

        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        getChildren().addAll(gameTitle, startGameButton, statisticButton, exitButton);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);

        String baseStyle = "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 30; " +
                "-fx-padding: 10 30 10 30; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #66BB6A; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 30; " +
                "-fx-padding: 10 30 10 30; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 8, 0, 0, 6);";

        button.setStyle(baseStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(baseStyle));

        button.setMinWidth(200);
        return button;
    }
}