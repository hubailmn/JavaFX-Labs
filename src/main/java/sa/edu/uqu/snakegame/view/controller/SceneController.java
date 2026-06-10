package sa.edu.uqu.snakegame.view.controller;


import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sa.edu.uqu.snakegame.view.GameView;
import sa.edu.uqu.snakegame.view.MainView;
import sa.edu.uqu.snakegame.view.StatisticView;

public class SceneController {
    private final StackPane root;

    public SceneController(StackPane root) {
        this.root = root;
        showMainMenu();
    }

    public void showMainMenu() {
        MainView view = new MainView(this);
        switchTo(view);
    }

    public void showStatisticMenu() {
        StatisticView view = new StatisticView(this);
        switchTo(view);
    }

    public void showGameMenu() {
        GameView view = new GameView(this);
        switchTo(view);
    }

    private void switchTo(Region view) {
        if (root.getChildren().isEmpty()) {
            root.getChildren().add(view);
            return;
        }

        Node currentView = root.getChildren().getLast();
        root.getChildren().add(view);

        FadeTransition out = new FadeTransition(Duration.millis(250), currentView);
        out.setToValue(0);
        out.setOnFinished(e -> root.getChildren().remove(currentView));
        out.play();
    }


}