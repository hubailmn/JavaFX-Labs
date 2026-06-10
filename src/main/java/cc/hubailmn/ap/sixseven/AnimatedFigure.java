package cc.hubailmn.ap.sixseven;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class AnimatedFigure extends Application {

    private double time = 0;

    @Override
    public void start(Stage stage) {

        // Head
        Circle head = new Circle(300, 150, 30);
        head.setFill(Color.TRANSPARENT);
        head.setStroke(Color.BLACK);

        // Body
        Line body = new Line(300, 180, 300, 300);

        // Arms
        Line leftArm = new Line(300, 220, 250, 260);
        Line rightArm = new Line(300, 220, 350, 260);

        // Legs
        Line leftLeg = new Line(300, 300, 250, 360);
        Line rightLeg = new Line(300, 300, 350, 360);

        Label label = new Label("Abo Al3z: ");
        label.paddingProperty().set(new javafx.geometry.Insets(10));
        label.setLineSpacing(10.0);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Group
        Group figure = new Group(head, body, leftArm, rightArm, leftLeg, rightLeg);

        // Rotations (cached once, no allocations later)
        Rotate leftArmRotate = new Rotate(0, 300, 220);
        Rotate rightArmRotate = new Rotate(0, 300, 220);
        Rotate headRotate = new Rotate(0, 300, 150);

        leftArm.getTransforms().add(leftArmRotate);
        rightArm.getTransforms().add(rightArmRotate);
        head.getTransforms().add(headRotate);

        // High-performance animation loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Increase the multiplier here to speed up the oscillation
                // 5.0 is quite fast; adjust as needed.
                time += 0.016;
                double fastTime = time * 5.0;

                // Smooth oscillation
                double wave = Math.sin(fastTime);

                // Both arms move together by using the same sign (positive)
                // We add a base offset (e.g., -90) if you want them to start
                // from a specific raised position.
                leftArmRotate.setAngle(wave * 45);
                rightArmRotate.setAngle(wave * 45);

                // Subtle head motion - also sped up to match
                headRotate.setAngle(Math.sin(fastTime * 0.5) * 10);
            }
        };

        timer.start();

        Scene scene = new Scene(new Group(label, figure), 600, 500, Color.WHITE);

        stage.setTitle("Optimized JavaFX Animation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}