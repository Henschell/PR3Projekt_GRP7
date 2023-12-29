package JumpRun;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Option extends Application {
    
    private BackgroundMusic backgroundMusic;


//    public static void main(String[] args) {
//        launch(args);
//    }
    public Option() {
        
    }
    
    public Option(BackgroundMusic backgroundMusic) {
        this.backgroundMusic= backgroundMusic;
    }
    

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Lautstärke-Änderung");


            // Erstelle einen Slider für den Soundregler
            backgroundMusic.setVolume(100);
            Slider soundSlider = new Slider(0, 100, backgroundMusic.getVolume()); // Lautstärkeeinstellung im Bereich von 0 bis 1
            soundSlider.setShowTickLabels(true);
            soundSlider.setShowTickMarks(true);
            // Schritte in 25 Einheiten
            soundSlider.setMajorTickUnit(25);
            soundSlider.setMinorTickCount(100);
            
            // Setze den Anfangswert des Soundreglers
            soundSlider.setValue(backgroundMusic.getVolume());
            
            // Erstelle Labels für den Soundregler
            Label soundLabel = new Label("");
            
            // Ändern Sie die Textfarbe
            soundLabel.setStyle("-fx-text-fill: white;");
            Label soundValueLabel = new Label((int) (backgroundMusic.getVolume() * 100) + "%");

            
            // Verbinde den Slider mit dem Label zur Anzeige des aktuellen Werts und der BackgroundMusicPlayer-Instanz
            soundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.doubleValue();
                soundValueLabel.setText((int) (volume * 100) + "%");
                backgroundMusic.setVolume(volume);
            });

            // Layout erstellen und Komponenten hinzufügen
            VBox layout = new VBox(20);
            layout.setAlignment(Pos.CENTER);

            // Hintergrundbild hinzufügen
            Image backgroundImage = new Image("file:images/JumpRun/image2.jpg");
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImg);
            layout.setBackground(background);

            // Audiosteuerung manuell positionieren
            HBox audioControls = new HBox(93, soundLabel, soundSlider);
            audioControls.setAlignment(Pos.CENTER);



            // Genaue Position der Audiosteuerung setzen
            audioControls.setTranslateX(-50); // X-Koordinate
            audioControls.setTranslateY(350); // Y-Koordinate

            // Audiosteuerung zum Layout hinzufügen
            layout.getChildren().add(audioControls);

            // Erstelle eine Pane für die Buttons
            Pane buttonPane = new Pane();

            // Buttons für Rückkehr zum Menü und Beenden hinzufügen
            Button optionsButton = createButtonWithImage("file:images/JumpRun/exit.png", e -> handleBeenden(primaryStage));
            optionsButton.setLayoutX(740); // Ändern Sie die X-Koordinate entsprechend
            optionsButton.setLayoutY(-200); // Ändern Sie die Y-Koordinate entsprechend

            Button backButton = createButtonWithImage("file:images/JumpRun/back.png", e -> handleBackToMenu(primaryStage));
            backButton.setLayoutX(690); // Ändern Sie die X-Koordinate entsprechend
            backButton.setLayoutY(-200); // Ändern Sie die Y-Koordinate entsprechend

            // Füge die Buttons zur Button-Pane hinzu
            buttonPane.getChildren().addAll(optionsButton, backButton);

            // Füge die Button-Pane zum Layout hinzu
            layout.getChildren().add(buttonPane);

            // Szene erstellen
            Scene scene = new Scene(layout, 800, 600);

            // Szene zur Bühne hinzufügen
            primaryStage.setScene(scene);

            // Bühne anzeigen
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Methode zum Initialisieren der Anwendung
    @Override
    public void init() throws Exception {
        super.init();
        // Initialisierung der BackgroundMusicPlayer-Instanz, falls nicht vorhanden
        if (backgroundMusic == null) {
            backgroundMusic = BackgroundMusic.getInstance("home.m4a");
            backgroundMusic.play();
        }
    }
    
    
    // Methode zum Beenden der Anwendung
    @Override
    public void stop() throws Exception {
        super.stop();
        // Stoppen der Hintergrundmusik beim Schließen der Anwendung
        backgroundMusic.stop();
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