package cc.hubailmn.ap.lab7;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StudentView extends VBox {

    private Label nameLabel;
    private Label idLabel;

    private TextField nameInput;
    private TextField idInput;

    private Button submitButton;

    private TextArea outputArea;

    public StudentView() {
        setSpacing(10);
        setPadding(new Insets(10));

        nameLabel = new Label("Name:");
        idLabel = new Label("ID:");

        nameInput = new TextField();
        idInput = new TextField();

        submitButton = new Button("Submit");

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(120);

        getChildren().addAll(nameLabel, nameInput, idLabel, idInput, submitButton, outputArea);
    }


    public String getNameInput() {
        return nameInput.getText();
    }

    public String getIdInput() {
        return idInput.getText();
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void setOutputArea(String text) {
        this.outputArea.setText(text);
    }
}
