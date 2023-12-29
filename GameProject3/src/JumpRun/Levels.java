package JumpRun;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;


public class Levels extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Button button1 = new Button("Level 1");
            button1.setLayoutX(170);
            button1.setLayoutY(460);
            button1.setPrefWidth(150); // Breite: 150
            button1.setPrefHeight(50); // Höhe: 50
            
            Button button2 = new Button("Level 2");
            button2.setLayoutX(330);
            button2.setLayoutY(460);
            button2.setPrefWidth(150);
            button2.setPrefHeight(50);
            
            Button button3 = new Button("Level 3");
            button3.setLayoutX(490);
            button3.setLayoutY(460);
            button3.setPrefWidth(150);
            button3.setPrefHeight(50);
            
            button1.setOnAction(e -> {
                // Wechseln Sie zur Spielszene oder einem anderen Projekt
                Level1 gameController = new Level1();
                gameController.start(primaryStage);
            });
            
            button2.setOnAction(e -> {
                // Wechseln Sie zur Spielszene oder einem anderen Projekt
                Level2 gameController = new Level2();
                gameController.start(primaryStage);
            });
            button3.setOnAction(e -> {
                // Wechseln Sie zur Spielszene oder einem anderen Projekt
                Level3 gameController = new Level3();
                gameController.start(primaryStage);
            });

            // Layout erstellen und Komponenten hinzufügen
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);

            // Hintergrundbild hinzufügen
            Image backgroundImage = new Image("file:images/JumpRun/Levels.jpg");
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImg);
            layout.setBackground(background);

            // Erstelle eine Pane für die Buttons
            Pane buttonPane = new Pane();

            // Buttons für Rückkehr zum Menü und Beenden hinzufügen
            Button optionsButton = createButtonWithImage("file:images/JumpRun/exit.png", e -> handleBeenden(primaryStage));
            optionsButton.setLayoutX(740); // Ändern Sie die X-Koordinate entsprechend
            optionsButton.setLayoutY(-25); // Ändern Sie die Y-Koordinate entsprechend

            Button backButton = createButtonWithImage("file:images/JumpRun/back.png", e -> handleBackToMenu(primaryStage));
            backButton.setLayoutX(690); // Ändern Sie die X-Koordinate entsprechend
            backButton.setLayoutY(-25); // Ändern Sie die Y-Koordinate entsprechend

            // Füge die Buttons zur Button-Pane hinzu
            buttonPane.getChildren().addAll(optionsButton, backButton, button1, button2, button3);

            // Füge die Button-Pane zum Layout hinzu
            layout.getChildren().add(buttonPane);

            // Szene erstellen
            Scene scene = new Scene(layout, 800, 600);

            // Szene zur Bühne hinzufügen
            primaryStage.setScene(scene);
            primaryStage.setTitle("Levels");
            // Bühne anzeigen
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methode zum Behandeln der Optionen
    private void handleBeenden(Stage primaryStage) {
        // Hier können Sie Aktionen für den "Optionen"-Button definieren
        primaryStage.close();
    }

    // Methode zum Zurückkehren zum Startmenü
    private void handleBackToMenu(Stage primaryStage) {
        JumpAndRunMenu menu = new JumpAndRunMenu();
        menu.start(primaryStage);
    }

    // Hilfsmethode zum Erstellen eines Buttons mit einem Bild
    private Button createButtonWithImage(String imagePath, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30); // Passen Sie die Breite nach Bedarf an
        imageView.setFitHeight(30); // Passen Sie die Höhe nach Bedarf an

        Button button = new Button();
        button.setGraphic(imageView);
        button.setOnAction(handler);

        return button;
    }
}