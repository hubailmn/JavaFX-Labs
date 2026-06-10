package cc.hubailmn.ap.FinalProjectV2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class MemoryGameApp extends Application {

    private Direction direction;
    private Timeline timer;
    private Random rand = new Random();
    private int score = 0;

    public static void main(String[] arg0) {
        launch(arg0);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Image snakeImage = new Image(getClass().getResourceAsStream("/assets/images/snake.png"));
        Image image_1 = new Image(getClass().getResourceAsStream("/assets/images/d.png"));
        Image image_2 = new Image(getClass().getResourceAsStream("/assets/images/d.png"));
        Image image_3 = new Image(getClass().getResourceAsStream("/assets/images/d.png"));
        Image image_4 = new Image(getClass().getResourceAsStream("/assets/images/d.png"));

        direction = Direction.DOWN;

        ImageView imageView = new ImageView(snakeImage);
        imageView.setFitWidth(80);
        imageView.setFitHeight(50);
        imageView.setLayoutX(247);
        imageView.setLayoutY(80);
        imageView.setPreserveRatio(true);

        ImageView appleView = new ImageView(snakeImage);
        appleView.setFitWidth(80);
        appleView.setFitHeight(50);
        appleView.setLayoutX(rand.nextInt(500));
        appleView.setLayoutY(rand.nextInt(400));
        appleView.setPreserveRatio(true);

        ImageView imageView1 = new ImageView(image_1);
        imageView1.setFitWidth(80);
        imageView1.setFitHeight(50);
        imageView1.setPreserveRatio(true);

        ImageView imageView2 = new ImageView(image_2);
        imageView2.setFitWidth(80);
        imageView2.setFitHeight(50);
        imageView2.setPreserveRatio(true);
        imageView2.setRotate(180);

        ImageView imageView3 = new ImageView(image_3);
        imageView3.setFitWidth(80);
        imageView3.setFitHeight(50);
        imageView3.setPreserveRatio(true);
        imageView3.setRotate(270);

        ImageView imageView4 = new ImageView(image_4);
        imageView4.setFitWidth(80);
        imageView4.setFitHeight(50);
        imageView4.setPreserveRatio(true);
        imageView4.setRotate(90);

        Button down = new Button("", imageView1);
        down.setLayoutX(410);
        down.setLayoutY(350);
        down.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        Button up = new Button("", imageView2);
        up.setLayoutX(410);
        up.setLayoutY(280);
        up.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        Button right = new Button("", imageView3);
        right.setLayoutX(450);
        right.setLayoutY(315);
        right.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        Button left = new Button("", imageView4);
        left.setLayoutX(370);
        left.setLayoutY(315);
        left.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        down.setOnAction(e -> {
            imageView.setRotate(0);
            direction = Direction.DOWN;
        });

        up.setOnAction(e -> {
            imageView.setRotate(180);
            direction = Direction.UP;
        });

        right.setOnAction(e -> {
            imageView.setRotate(270);
            direction = Direction.RIGHT;
        });

        left.setOnAction(e -> {
            imageView.setRotate(90);
            direction = Direction.LEFT;
        });


        timer = new Timeline(new KeyFrame(Duration.seconds(0.01), e -> {
            if (imageView.getLayoutY() == 400 || imageView.getLayoutY() == 0 ||
                    imageView.getLayoutX() == 500 || imageView.getLayoutX() == 0) {
                //todo: خسرت
                // احفظ السكور في ملف او قاعدة بينات
                System.exit(0);
            }

            if (Math.abs(imageView.getLayoutX() - appleView.getLayoutX()) <= 15 &&
                    Math.abs(imageView.getLayoutY() - appleView.getLayoutY()) <= 15) {
                int randomX = rand.nextInt(500);
                int randomY = rand.nextInt(400);

                appleView.setLayoutX(randomX);
                appleView.setLayoutY(randomY);

                score += 10;
                System.out.println(score);
            }

            switch (direction) {
                case DOWN -> {
                    if (imageView.getLayoutY() != 400) {
                        imageView.setLayoutY(imageView.getLayoutY() + 1);
                    }
                }
                case UP -> {
                    if (imageView.getLayoutY() != 0) {
                        imageView.setLayoutY(imageView.getLayoutY() - 1);
                    }
                }
                case LEFT -> {
                    if (imageView.getLayoutX() != 0) {
                        imageView.setLayoutX(imageView.getLayoutX() - 1);
                    }
                }
                case RIGHT -> {
                    if (imageView.getLayoutX() != 500) {
                        imageView.setLayoutX(imageView.getLayoutX() + 1);
                    }
                }
            }

        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        Group btn = new Group(down, up, right, left);

        Group all = new Group(imageView, appleView, btn);
        Scene scene = new Scene(all, 500, 400, Color.WHITE);
        arg0.setScene(scene);
        arg0.show();
    }

    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

}
