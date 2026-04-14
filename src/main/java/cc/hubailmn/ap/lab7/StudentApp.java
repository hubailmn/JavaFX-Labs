package cc.hubailmn.ap.lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentApp extends Application {
    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Student modal = new Student();
        StudentView studentView = new StudentView();
        new StudentController(modal, studentView);

        Scene scene = new Scene(studentView, 300, 350);
        stage.setTitle("Student Form");
        stage.setScene(scene);
        stage.show();
    }
}
