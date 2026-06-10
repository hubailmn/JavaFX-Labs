package cc.hubailmn.ap.media;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FolderViewer extends Application {

    private List<File> playlist = new ArrayList<>();
    private int currentIndex = 0;
    private ImageView imageView = new ImageView();
    private MediaView mediaView = new MediaView();
    private MediaPlayer mediaPlayer;
    private StackPane root = new StackPane();

    @Override
    public void start(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Folder to View");
        File selectedDir = chooser.showDialog(stage);

        if (selectedDir != null) {
            loadFilesFromFolder(selectedDir);
        }

        // Setup UI scaling
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.fitHeightProperty().bind(stage.heightProperty());

        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(stage.widthProperty());
        mediaView.fitHeightProperty().bind(stage.heightProperty());

        // Add to root (StackPane layers items)
        root.setStyle("-fx-background-color: black;"); // Black background looks better for media
        root.getChildren().addAll(imageView, mediaView);

        Scene scene = new Scene(root, 1000, 700);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> showNext();
                case LEFT -> showPrevious();
                case D -> deleteCurrentFile();
            }
        });

        stage.setScene(scene);
        stage.setTitle("JavaFX Media Culler");
        stage.show();

        // Start displaying after the stage is shown
        displayFile();
    }

    private void loadFilesFromFolder(File folder) {
        File[] files = folder.listFiles((dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".png") ||
                    lowercaseName.endsWith(".jpeg") || lowercaseName.endsWith(".gif") ||
                    lowercaseName.endsWith(".mp4");
        });

        if (files != null) {
            playlist.clear();
            playlist.addAll(List.of(files));
            System.out.println("DEBUG: Found " + playlist.size() + " files.");
        }
    }

    private void displayFile() {
        if (playlist.isEmpty()) {
            System.out.println("DEBUG: Playlist is empty.");
            return;
        }

        // Clean up video player
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }

        File file = playlist.get(currentIndex);
        String path = file.toURI().toString();
        System.out.println("DEBUG: Displaying [" + currentIndex + "]: " + file.getName());

        if (path.toLowerCase().endsWith(".mp4")) {
            imageView.setVisible(false);
            mediaView.setVisible(true);

            try {
                Media media = new Media(path);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            } catch (Exception e) {
                System.err.println("Failed to play video: " + e.getMessage());
            }
        } else {
            mediaView.setVisible(false);
            imageView.setVisible(true);

            // Background loading to prevent UI freeze
            Image img = new Image(path, true);
            imageView.setImage(img);
        }
    }

    private void showNext() {
        if (currentIndex < playlist.size() - 1) {
            currentIndex++;
            displayFile();
        }
    }

    private void showPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            displayFile();
        }
    }

    private void deleteCurrentFile() {
        if (playlist.isEmpty()) return;

        File fileToDelete = playlist.get(currentIndex);
        try {
            Path trashPath = Paths.get(fileToDelete.getParent(), "to_delete");
            if (Files.notExists(trashPath)) Files.createDirectories(trashPath);

            Files.move(fileToDelete.toPath(), trashPath.resolve(fileToDelete.getName()), StandardCopyOption.REPLACE_EXISTING);

            playlist.remove(currentIndex);
            if (currentIndex >= playlist.size() && !playlist.isEmpty()) currentIndex = playlist.size() - 1;

            displayFile();
            System.out.println("Moved to trash: " + fileToDelete.getName());
        } catch (Exception e) {
            System.err.println("Delete failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}