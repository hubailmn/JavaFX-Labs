package sa.edu.uqu.snakegame.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import sa.edu.uqu.snakegame.data.GameData;
import sa.edu.uqu.snakegame.data.StatisticModal;
import sa.edu.uqu.snakegame.view.controller.SceneController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticView extends VBox {

    Button backButton;

    public StatisticView(SceneController controller) {
        super();
        List<StatisticModal> statisticModalList = GameData.getInstance().loadModals();

        List<StatisticModal> sortedScores = statisticModalList.stream()
                .sorted(Comparator.comparingLong(StatisticModal::getScore).reversed())
                .collect(Collectors.toList());


        VBox topScoreBox = new VBox(5);
        topScoreBox.setAlignment(Pos.CENTER);
        topScoreBox.setMaxWidth(300);
        topScoreBox.setStyle("-fx-background-color: #FFD700; -fx-background-radius: 15; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 5);");

        Label topScoreTitle = new Label("TOP SCORE");
        topScoreTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #8B6508;");

        Label topScoreLabel = new Label(sortedScores.isEmpty() ? "N/A" : String.valueOf(sortedScores.get(0).getScore()));
        topScoreLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #B8860B;");

        topScoreBox.getChildren().addAll(topScoreTitle, topScoreLabel);


        VBox otherScoresBox = new VBox(10);
        otherScoresBox.setAlignment(Pos.TOP_CENTER);
        otherScoresBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 15; -fx-padding: 15;");

        List<StatisticModal> restOfScores = sortedScores.stream().skip(1).collect(Collectors.toList());
        if (restOfScores.isEmpty()) {
            Label noOtherScoresLabel = new Label("No other scores to display.");
            noOtherScoresLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            otherScoresBox.getChildren().add(noOtherScoresLabel);
        } else {
            for (int i = 0; i < restOfScores.size(); i++) {
                Label scoreLabel = new Label("#" + (i + 2) + " - Score: " + restOfScores.get(i).getScore());
                scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
                otherScoresBox.getChildren().add(scoreLabel);
            }
        }

        ScrollPane scrollPane = new ScrollPane(otherScoresBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setMaxWidth(300);
        scrollPane.setPrefHeight(250);

        setSpacing(30);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #2A3D2A, #1A241A);");

        backButton = createStyledButton("Back to Menu");
        backButton.setOnAction(e -> controller.showMainMenu());

        getChildren().addAll(topScoreBox, scrollPane, backButton);
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
