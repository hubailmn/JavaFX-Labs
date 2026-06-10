package cc.hubailmn.ap.lab6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Lab 6 - Student Registration");
        Label titleLabel = new Label("Student Registration Form");

        Label nameLabel = new Label("Name:");
        nameLabel.setPrefWidth(100);
        TextField nameInput = new TextField();
        HBox.setHgrow(nameInput, Priority.ALWAYS);
        HBox nameHbox = new HBox(10, nameLabel, nameInput);
        nameHbox.setAlignment(Pos.CENTER_LEFT);

        Label idLabel = new Label("ID:");
        idLabel.setPrefWidth(100);
        TextField idInput = new TextField();
        HBox.setHgrow(idInput, Priority.ALWAYS);
        HBox idHbox = new HBox(10, idLabel, idInput);

        Label studentLevelLabel = new Label("Student Level:");
        studentLevelLabel.setPrefWidth(100);

        RadioButton freshMan = new RadioButton("Freshman");
        RadioButton senior = new RadioButton("Senior");

        ToggleGroup studentLevelGroup = new ToggleGroup();
        freshMan.setToggleGroup(studentLevelGroup);
        senior.setToggleGroup(studentLevelGroup);

        HBox levelHbox = new HBox(10, studentLevelLabel, freshMan, senior);

        Label coursesLabel = new Label("Courses:");
        coursesLabel.setPrefWidth(100);

        CheckBox programmer = new CheckBox("Programming");
        CheckBox database = new CheckBox("Databases");
        CheckBox network = new CheckBox("Networks");

        VBox coursesList = new VBox(5, programmer, database, network);
        HBox coursesHbox = new HBox(10, coursesLabel, coursesList);

        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(150);

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);


        submitButton.setOnAction(_ -> {
            String name = nameInput.getText();
            String id = idInput.getText();

            if (name.isBlank() || id.isBlank()) {
                outputArea.setText("Please fill in all fields.");
                return;
            }

            String studentLevelSelected = "";
            if (senior.isSelected()) {
                studentLevelSelected = "Senior";
            } else if (freshMan.isSelected()) {
                studentLevelSelected = "Freshman";
            } else {
                outputArea.setText("Please select a student level.");
                return;
            }

            List<String> coursesSelected = new ArrayList<>();

            if (programmer.isSelected()) {
                coursesSelected.add(programmer.getText());
            }

            if (database.isSelected()) {
                coursesSelected.add(database.getText());
            }

            if (network.isSelected()) {
                coursesSelected.add(network.getText());
            }

            outputArea.setText(
                    String.format("""
                            Name: %s
                            Student ID: %s
                            Student Level: %s
                            Courses: %s
                            """, name, id, studentLevelSelected, coursesSelected)
            );

        });

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(
                titleLabel,
                nameHbox,
                idHbox,
                levelHbox,
                coursesHbox,
                submitButton,
                outputArea
        );

        Scene scene = new Scene(root, 450, 550);
        stage.setScene(scene);
        stage.show();
    }

    public static void run() {
        launch();
    }
}
