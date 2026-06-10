package sa.edu.uqu.snakegame.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sa.edu.uqu.snakegame.data.Direction;
import sa.edu.uqu.snakegame.data.GameData;
import sa.edu.uqu.snakegame.data.StatisticModal;
import sa.edu.uqu.snakegame.view.controller.SceneController;

import java.util.Random;

public class GameView extends Pane {

    private Random rand = new Random();
    Label scoreLabel;
    private Direction direction;
    private Timeline timer;
    private int score = 0;

    public GameView(SceneController controller) {
        super();

        Image snakeImage = new Image(getClass().getResourceAsStream("/assets/images/SnakeHead.png"));
        Image appleImage = new Image(getClass().getResourceAsStream("/assets/images/Apple.png"));

        direction = Direction.DOWN;

        ImageView snakeView = new ImageView(snakeImage);
        snakeView.setFitWidth(80);
        snakeView.setFitHeight(50);
        snakeView.setLayoutX(247);
        snakeView.setLayoutY(80);
        snakeView.setPreserveRatio(true);

        ImageView appleButtonImage = new ImageView(appleImage);
        appleButtonImage.setFitWidth(80);
        appleButtonImage.setFitHeight(50);
        appleButtonImage.setLayoutX(rand.nextInt(700));
        appleButtonImage.setLayoutY(rand.nextInt(750));
        appleButtonImage.setPreserveRatio(true);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black;");
        scoreLabel.setPrefWidth(800);
        scoreLabel.setAlignment(javafx.geometry.Pos.CENTER);
        scoreLabel.setLayoutY(20);


        timer = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> {
            if (snakeView.getLayoutY() == 850 || snakeView.getLayoutY() == 0 ||
                    snakeView.getLayoutX() == 800 || snakeView.getLayoutX() == 0) {
                GameData.getInstance().saveModal(new StatisticModal(score, System.currentTimeMillis()));
                timer.stop();
                controller.showMainMenu();
            }

            if (Math.abs(snakeView.getLayoutX() - appleButtonImage.getLayoutX()) <= 15 &&
                    Math.abs(snakeView.getLayoutY() - appleButtonImage.getLayoutY()) <= 15) {
                int randomX = rand.nextInt(700);
                int randomY = rand.nextInt(750);

                appleButtonImage.setLayoutX(randomX);
                appleButtonImage.setLayoutY(randomY);

                score += 10;
                scoreLabel.setText("Score: " + score);
                System.out.println(score);
            }

            switch (direction) {
                case DOWN -> {
                    if (snakeView.getLayoutY() != 850) {
                        snakeView.setLayoutY(snakeView.getLayoutY() + 1);
                    }
                }
                case UP -> {
                    if (snakeView.getLayoutY() != 0) {
                        snakeView.setLayoutY(snakeView.getLayoutY() - 1);
                    }
                }
                case LEFT -> {
                    if (snakeView.getLayoutX() != 0) {
                        snakeView.setLayoutX(snakeView.getLayoutX() - 1);
                    }
                }
                case RIGHT -> {
                    if (snakeView.getLayoutX() != 800) {
                        snakeView.setLayoutX(snakeView.getLayoutX() + 1);
                    }
                }
            }

        }));


        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        getChildren().addAll(scoreLabel, snakeView, appleButtonImage);

        this.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                timer.stop();
                controller.showMainMenu();
                e.consume();
            }

            if (e.getCode() == KeyCode.UP) {
                snakeView.setRotate(180);
                direction = Direction.UP;
                e.consume();
            } else if (e.getCode() == KeyCode.DOWN) {
                snakeView.setRotate(0);
                direction = Direction.DOWN;
                e.consume();
            } else if (e.getCode() == KeyCode.LEFT) {
                snakeView.setRotate(90);
                direction = Direction.LEFT;
                e.consume();
            } else if (e.getCode() == KeyCode.RIGHT) {
                snakeView.setRotate(270);
                direction = Direction.RIGHT;
                e.consume();
            }
        });

        this.setFocusTraversable(true);
        this.requestFocus();

    }
}
