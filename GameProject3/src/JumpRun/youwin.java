package JumpRun;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;


public class youwin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
     
            // Layout erstellen und Komponenten hinzufügen
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);

            // Hintergrundbild hinzufügen
            Image backgroundImage = new Image("file:images/JumpRun/youwin.gif");
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImg);
            layout.setBackground(background);

            // Erstelle eine Pane für die Buttons
            Pane buttonPane = new Pane();
            
            // Buttons für Rückkehr zum Menü und Beenden hinzufügen
            Button backButton = createButtonWithImage("file:images/JumpRun/back.png", e -> handleBackToMenu(primaryStage));
            backButton.setLayoutX(690); // Ändern Sie die X-Koordinate entsprechend
            backButton.setLayoutY(-190); // Ändern Sie die Y-Koordinate entsprechend

            Button optionsButton = createButtonWithImage("file:images/JumpRun/exit.png", e -> handleBeenden(primaryStage));
            optionsButton.setLayoutX(740); // Ändern Sie die X-Koordinate entsprechend
            optionsButton.setLayoutY(-190); // Ändern Sie die Y-Koordinate entsprechend

          

            // Füge die Buttons zur Button-Pane hinzu
            buttonPane.getChildren().addAll(optionsButton, backButton);

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
        Stage menuStage = new Stage();
        menu.start(menuStage);
        primaryStage.close();
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