
// Import-Anweisungen für die benötigten JavaFX- und Java-Klassen
package Quiz;

import java.io.File;

import application.GUIController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Hauptklasse für die Options-Fensteranwendung
public class Options extends Application {

    // Hinzufügen der BackgroundMusicPlayer-Instanz
    private BackgroundMusicPlayer backgroundMusicPlayer;

    // Leerer Konstruktor
    public Options() {
        // Leerer Konstruktor
    }

    // Konstruktor mit BackgroundMusicPlayer-Instanz als Parameter
    public Options(BackgroundMusicPlayer backgroundMusicPlayer) {
        this.backgroundMusicPlayer = backgroundMusicPlayer;
    }

    // Hauptmethode zum Starten der Anwendung
    public static void main(String[] args) {
        launch(args);
    }

    // Hauptmethode zur Erstellung und Anzeige des Options-Fensters
    @Override
    public void start(Stage primaryStage) {
        // Setzen des Fenstertitels
        primaryStage.setTitle("Options");

        // Erstelle ein StackPane für die Optionen
        StackPane root = new StackPane();

//        // Lade das GIF-Bild
//        Image backgroundImageView = new Image(new File("Back.gif").toURI().toString());
//        ImageView imageView = new ImageView(backgroundImageView);
        root.getChildren().add(GUIController.getImageView());

        // Erstelle einen Slider für den Soundregler
        Slider soundSlider = new Slider(0, 1, backgroundMusicPlayer.getVolume()); // Lautstärkeeinstellung im Bereich
                                                                                  // von 0 bis 1
        soundSlider.setShowTickLabels(true);
        soundSlider.setShowTickMarks(true);
        // Schritte in 0,25 Einheiten
        soundSlider.setMajorTickUnit(0.25);
        soundSlider.setMinorTickCount(0);

        // Setze den Anfangswert des Soundreglers
        soundSlider.setValue(backgroundMusicPlayer.getVolume());

        // Erstelle Labels für den Soundregler
        Label soundLabel = new Label("Sound:");

        // Ändern Sie die Textfarbe
        soundLabel.setStyle("-fx-text-fill: white;");
        Label soundValueLabel = new Label((int) (backgroundMusicPlayer.getVolume() * 100) + "%");

        // Verbinde den Slider mit dem Label zur Anzeige des aktuellen Werts und der
        // BackgroundMusicPlayer-Instanz
        soundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            soundValueLabel.setText((int) (volume * 100) + "%");
            backgroundMusicPlayer.setVolume(volume);
        });

        // Erstelle Buttons für die Levelstufen von 1 bis 3
        Button level1Button = new Button("Drück mich!");
        Button level2Button = new Button("Mich auch");
        Button level3Button = new Button("Und mich !!!");
        
        level1Button.setPrefSize(100, 40);
        level2Button.setPrefSize(100, 40);
        level3Button.setPrefSize(100, 40);

        // Erstelle einen Zurück-Button
        Button backButton = new Button("Zurück");
        backButton.setOnAction(event -> {
            // Hier können Sie den Code hinzufügen, um zur Hauptseite zurückzukehren
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });

        // Erstelle eine VBox für die Optionen und füge die Elemente hinzu
        // 20 Pixel Abstand zwischen den Elementen
        VBox optionsLayout = new VBox(20);
        // Zentriere die Elemente
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.getChildren().addAll(soundLabel, soundSlider, soundValueLabel, level1Button, level2Button,
                level3Button, backButton);

        // Füge die Optionen zur StackPane hinzu
        root.getChildren().add(optionsLayout);

        // Erstelle eine Szene und füge die StackPane hinzu
        Scene scene = new Scene(root, 800, 600);

        // Setze die Szene auf das Options-Fenster
        primaryStage.setScene(scene);

        // Zeige das Options-Fenster
        primaryStage.show();

        // Hier können Sie ActionListener für die Level-Buttons hinzufügen
        level1Button.setOnAction(event -> {
            // Aktion für Level 1
            showMotivationalQuote("\"Der einzige Weg, großartige Dinge zu erreichen, ist,\n "
                    + "große Träume zu haben und nicht aufzugeben.\" - Les Brown");
        });

        level2Button.setOnAction(event -> {
            // Aktion für Level 2
            showMotivationalQuote("\"Nichts in der Welt kann an die Stelle von Ausdauer treten. \n Talent nicht, "
                    + "nichts ist häufiger als erfolglose Menschen mit Talent.\"" + " - Calvin Coolidge");
        });

        level3Button.setOnAction(event -> {
            // Aktion für Level 3
            showMotivationalQuote("\"Der Weg zum Erfolg und der Weg zum Misserfolg sind \n"
                    + " oft ein und derselbe Weg. Gib nicht auf!\" - Unbekannt");
        });
    }

    // Methode zum Initialisieren der Anwendung
    @Override
    public void init() throws Exception {
        super.init();
        // Initialisierung der BackgroundMusicPlayer-Instanz, falls nicht vorhanden
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = BackgroundMusicPlayer.getInstance("stranger-things-124008.mp3");
            backgroundMusicPlayer.play();
        }
    }

    // Methode zum Beenden der Anwendung
    @Override
    public void stop() throws Exception {
        super.stop();
        // Stoppen der Hintergrundmusik beim Schließen der Anwendung
        backgroundMusicPlayer.stop();
    }

    // Methode zur Anzeige des Motivationsspruchs
    private void showMotivationalQuote(String quote) {
        // Erstelle ein neues Fenster für den Motivationsspruch
        Stage quoteStage = new Stage();
        quoteStage.setTitle("Motivation");

        // Lade das GIF-Bild für den Hintergrund
//        Image backgroundImageView = new Image(new File("Back.gif").toURI().toString());
//        ImageView backgroundImage = new ImageView(backgroundImageView);

        // Erstelle ein Label für den Motivationsspruch
        Label quoteLabel = new Label(quote);
        quoteLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        // Erstelle eine StackPane für das Motivationsfenster
        StackPane quoteRoot = new StackPane();
        quoteRoot.getChildren().addAll(GUIController.getImageview2(), quoteLabel);

        // Erstelle eine Szene für das Motivationsfenster
        Scene quoteScene = new Scene(quoteRoot, 600, 600);

        // Setze die Szene auf das Motivationsfenster
        quoteStage.setScene(quoteScene);

        // Zeige das Motivationsfenster
        quoteStage.show();
    }
}
