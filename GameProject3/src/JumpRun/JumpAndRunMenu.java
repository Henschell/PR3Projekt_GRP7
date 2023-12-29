package JumpRun;

import application.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JumpAndRunMenu extends Application {

    private BackgroundMusic backgroundMusic;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Die Musik Methode wird benutzt
        backgroundMusic = BackgroundMusic.getInstance("Audio/JumpRun/home.m4a");
        backgroundMusic.play();

        // Erzeugt die Buttons und setzt ihre Positionen und Größen
        Button button1 = new Button("Starten");
        button1.setLayoutX(450);
        button1.setLayoutY(100);
        button1.setPrefWidth(200);
        button1.setPrefHeight(100);

        Button button2 = new Button("Optionen");
        button2.setLayoutX(450);
        button2.setLayoutY(210);
        button2.setPrefWidth(200);
        button2.setPrefHeight(100);
        button2.setOnAction(e -> {
            // Stoppen Sie die Musik
            backgroundMusic.stop();

            // Fügen Sie UI-Elemente zur optionsLayout-Szene hinzu, z.B. ein "Musik stoppen"-Button
        });

        Button button3 = new Button("Spielsammlung");
        button3.setLayoutX(450);
        button3.setLayoutY(320);
        button3.setPrefWidth(200);
        button3.setPrefHeight(100);

        Button exitButton = new Button("Beenden");
        exitButton.setLayoutX(450);
        exitButton.setLayoutY(430);
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(100);

        // Fügt Aktionen zu den Buttons hinzu
        button1.setOnAction(e -> {
            // Wechseln Sie zur Spielszene oder einem anderen Projekt
            Levels gameController = new Levels();
            gameController.start(primaryStage);
        });

        button2.setOnAction(e -> {
            // Wechseln Sie zur Spielszene oder einem anderen Projekt
            Option gameController = new Option(backgroundMusic);
            gameController.start(primaryStage);

        });

        button3.setOnAction(e -> {GUIController.getInstance().backToMenu(); backgroundMusic.stop();});
        exitButton.setOnAction(e -> {
            // Stoppen Sie die Musik, bevor das Fenster geschlossen wird
            backgroundMusic.stop();
            primaryStage.close();
        });

        // Lädt das Bild für den Hintergrund
        Image backgroundImage = new Image("file:images/JumpRun/image.jpg");

        // Erzeugt ein Hintergrundbild für das Layout
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        // Erzeugt ein Pane-Layout und setzt den Hintergrund
        Pane pane = new Pane();
        pane.setBackground(new Background(background));

        // Fügt die Buttons zum Pane-Layout hinzu
        pane.getChildren().addAll(button1, button2, button3, exitButton);

        // Erzeugt eine Szene mit dem Pane-Layout
        Scene scene = new Scene(pane, 800, 600); // Setzt die Größe auf 800x600

        // Setzt die Szene für die Bühne (Stage) und zeigt sie an
        primaryStage.setScene(scene);
        primaryStage.setTitle("JumpRun");
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Stoppen Sie die Musik, wenn die Anwendung beendet wird
        backgroundMusic.stop();
    }
}