package Quiz;

import java.io.File;

import application.AudioManager;
import application.GUIController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 * @author Dario Kasumovic Carballeira
 *Das Spiel ist ein Quiz Game für Kinder von 6 bis 14 jahren 
 *und soll den kindern die Gefahren des Internets spielerisch
 *näher bringen und ihr wissen erweitern
 *es gint 3 schwierigkeitsgrade:Level 16 von 20 um auf level 2 
 *level 2 20 von 20 muss erreicht werden 
 *level 3 ist dann endlos
 *die überraschung ist bei 20 von 20 eine blinkende Motivationsrede
 *zudem gibt es in den options einige motivierende sprüche plus 
 *ein liederboard zum speichern des Fortschritts
 */
public class Main extends Application {
    // Lade das GIF-Bild
//    static Image image = new Image(new File("Back.gif").toURI().toString());
//    static ImageView imageView = new ImageView(image);
//    static Image image2 = new Image(new File("Back.gif").toURI().toString());
//    static ImageView imageview2 = new ImageView(image2);
    // Hinzufügen der BackgroundMusicPlayer-Instanz
    private BackgroundMusicPlayer backgroundMusicPlayer;
    
    // Methode zum Starten der Benutzeroberfläche
    @Override
    public void start(Stage primaryStage) {
        // Setzen des Fenstertitels
        primaryStage.setTitle("Quiz Game");

        
        // Erstelle ein StackPane, um das GIF-Bild und die Buttons zu überlagern
        StackPane root = new StackPane();

        

        
        
        // Erstelle eine Instanz des BackgroundMusicPlayer und starte die Musik
        backgroundMusicPlayer = BackgroundMusicPlayer.getInstance("stranger-things-124008.mp3");
        backgroundMusicPlayer.play();


        
        // Erstelle Buttons in einer VBox
        Button startButton = new Button("Start");
        Button optionsButton = new Button("Options");
        Button leaderboardButton = new Button("Leaderboard");
        Button spielsammlung=new Button("Spielesammlung");

        startButton.setPrefSize(500, 100);
        optionsButton.setPrefSize(500, 100);
        leaderboardButton.setPrefSize(500, 100);
        
        // Verwende CSS, um die Buttons zu vergrößern
        startButton.setStyle("-fx-font-size: 60px;");
        optionsButton.setStyle("-fx-font-size: 60px;");
        leaderboardButton.setStyle("-fx-font-size: 60px;");

        
        // Erstelle eine VBox und füge die Buttons hinzu
        // 20 Pixel Abstand zwischen den Buttons
        VBox buttonLayout = new VBox(20);
        // Zentriere die Buttons
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(startButton, optionsButton, leaderboardButton,spielsammlung);

        // Füge das GIF-Bild und die VBox mit den Buttons zur StackPane hinzu
        root.getChildren().addAll(GUIController.getImageView(), buttonLayout);

        
        // Erstelle eine Szene und füge die StackPane hinzu
        Scene scene = new Scene(root, 800, 600); // Breite: 800, Höhe: 600

         
        // Füge einen ActionListener für den "Start" Button hinzu
        startButton.setOnAction(event -> {
            Questions questionsApp = new Questions();
            // Öffne die Questions-Klasse in einem neuen Fenster
            questionsApp.start(primaryStage);
            // Schließe das aktuelle Hauptfenster
        });
        
        
        // Füge einen ActionListener für den "Option" Button hinzu und übergebe das BackgroundMusicPlayer-Objekt
        optionsButton.setOnAction(event -> {
            Options optionsApp = new Options(backgroundMusicPlayer);
            // Öffne die Options-Klasse in einem neuen Fenster
            optionsApp.start(primaryStage);
            // Schließe das aktuelle Hauptfenster
        });
        
        
        // Füge einen ActionListener für den "Leaderboard" Button hinzu
        leaderboardButton.setOnAction(event -> {
            Leaderboard leaderboardapp = new Leaderboard();
            // Öffne die Leaderboard-Klasse in einem neuen Fenster
            leaderboardapp.start(primaryStage);
            // Schließe das aktuelle Hauptfenster
        });
     // Füge einen ActionListener für den "Start" Button hinzu
        spielsammlung.setOnAction(event -> {
            GUIController gui = GUIController.getInstance();
            gui.ReturnToMainScene();
            BackgroundMusicPlayer bgms = BackgroundMusicPlayer.getInstance("Wolf");
            bgms.stop();
            AudioManager.continueBackGroundAudio();
            // Öffne die Questions-Klasse in einem neuen Fenster
            // Schließe das aktuelle Hauptfenster
        });
        
        
        // Setze die Szene auf das Hauptfenster
        primaryStage.setScene(scene);

        
        // Zeige das Hauptfenster
        primaryStage.show();
        
    }
}
