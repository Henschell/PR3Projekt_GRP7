package Labyrinth;
	
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Die Hauptklasse für die Anwendung des Labyrinthspiels.
 */

public class Labyrinth {
    
 // Standardeinstellungen
    
    private static String difficulty;
    private static char diff;
    private static boolean musicstat ;
    private static Media media ;
    private static MediaPlayer mediaPlayer ;
    private static Color buttonTextColor ;
    private static Font buttonFont ;
    private static Image backgroundImage;
    private static MazeGenerate gen;
    private static BackgroundImage background ;
    public static void setLabyrinth(String difficultyp, char diffp, boolean musicstatp , Media mediap ,MediaPlayer mediaPlayerp, Color buttonTextColorp, Font buttonFontp, Image backgroundImagep, BackgroundImage backgroundp) {
        difficulty = difficultyp;
        diff = diffp;
        musicstat = musicstatp;
        media = mediap;
        mediaPlayer = mediaPlayerp;
        buttonTextColor = buttonTextColorp;
        buttonFont = buttonFontp;
        backgroundImage = backgroundImagep;
        background = backgroundp;
    }
    /**
     * Gibt die aktuelle Schwierigkeitseinstellung zurück.
     *
     * @return Die aktuelle Schwierigkeitseinstellung.
     */
    
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * Setzt die Schwierigkeitseinstellung.
     *
     * @param difficulty Die neue Schwierigkeitseinstellung.
     */

    public static void setDifficulty(String difficulty) {
        Labyrinth.difficulty = difficulty;
    }

    /**
     * Gibt das aktuelle Schwierigkeitsniveau als Zeichen zurück.
     *
     * @return Das aktuelle Schwierigkeitsniveau.
     */

    public static char getDiff() {
        return diff;
    }

    /**
     * Setzt das Schwierigkeitsniveau als Zeichen.
     *
     * @param diff Das neue Schwierigkeitsniveau.
     */

    public static void setDiff(char diff) {
        Labyrinth.diff = diff;
    }

    /**
     * Gibt das Medienobjekt für die Hintergrundmusik zurück.
     *
     * @return Das Medienobjekt.
     */

    public static Media getMedia() {
        return media;
    }

    /**
     * Setzt das Medienobjekt für die Hintergrundmusik.
     *
     * @param media Das neue Medienobjekt.
     */

    public static void setMedia(Media media) {
        Labyrinth.media = media;
    }

    /**
     * Gibt den Mediaplayer für die Hintergrundmusik zurück.
     *
     * @return Das Mediaplayer-Objekt.
     */

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Setzt den Mediaplayer für die Hintergrundmusik.
     *
     * @param mediaPlayer Das neue Mediaplayer-Objekt.
     */

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        Labyrinth.mediaPlayer = mediaPlayer;
    }

    /**
     * Gibt die Textfarbe der Schaltflächen zurück.
     *
     * @return Die Textfarbe der Schaltflächen.
     */

    public static Color getButtonTextColor() {
        return buttonTextColor;
    }

    /**
     * Setzt die Textfarbe der Schaltflächen.
     *
     * @param buttonTextColor Die neue Textfarbe der Schaltflächen.
     */

    public static void setButtonTextColor(Color buttonTextColor) {
        Labyrinth.buttonTextColor = buttonTextColor;
    }

    /**
     * Gibt die Schriftart der Schaltflächen zurück.
     *
     * @return Die Schriftart der Schaltflächen.
     */

    public static Font getButtonFont() {
        return buttonFont;
    }

    /**
     * Setzt die Schriftart der Schaltflächen.
     *
     * @param buttonFont Die neue Schriftart der Schaltflächen.
     */

    public static void setButtonFont(Font buttonFont) {
        Labyrinth.buttonFont = buttonFont;
    }

    /**
     * Gibt das Hintergrundbild zurück.
     *
     * @return Das Hintergrundbild.
     */

    public static Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Setzt das Hintergrundbild.
     *
     * @param backgroundImage Das neue Hintergrundbild.
     */

    public static void setBackgroundImage(Image backgroundImage) {
        Labyrinth.backgroundImage = backgroundImage;
    }

    /**
     * Gibt das MazeGenerate-Objekt zurück.
     *
     * @return Das MazeGenerate-Objekt.
     */

    public static MazeGenerate getGen() {
        return gen;
    }

    /**
     * Setzt das MazeGenerate-Objekt.
     *
     * @param gen Das neue MazeGenerate-Objekt.
     */

    public static void setGen(MazeGenerate gen) {
        Labyrinth.gen = gen;
    }

    /**
     * Gibt die Konfiguration des Hintergrundbildes zurück.
     *
     * @return Die Konfiguration des Hintergrundbildes.
     */

    public static BackgroundImage getBackground() {
        return background;
    }

    /**
     * Setzt die Konfiguration des Hintergrundbildes.
     *
     * @param background Die neue Konfiguration des Hintergrundbildes.
     */

    public static void setBackground(BackgroundImage background) {
        Labyrinth.background = background;
    }

    /**
     * Erstellt und gibt die Options-Szene zurück.
     *
     * @param primaryStage Der Hauptbühnen-Container der Anwendung.
     * @return Die Options-Szene.
     */

    public static Scene optionsScene(Stage primaryStage) {
        BorderPane optionsmenu = new BorderPane();
        optionsmenu.setBackground(new Background(background));
        ToggleButton musicmute = new ToggleButton("Music: " + (Labyrinth.isMusicstat() ? "ON" : Labyrinth.isMusicstat()==false ? "OFF" : "ON"));
        musicmute.setPrefSize(200, 60);

        // Ereignishandler für die Stummschaltungsschaltfläche
        musicmute.setOnAction(event -> {
            if (musicmute.isSelected() && musicstat == true) {
                mediaPlayer.setMute(true); // Musik stummschalten
                musicmute.setText("Music: OFF");
                Labyrinth.setMusicstat(false);
            } else {
                mediaPlayer.setMute(false); // Musik einschalten
                musicmute.setText("Music: ON");
                Labyrinth.setMusicstat(true);
            }
        });
        musicmute.setTextFill(buttonTextColor);
//        musicmute.setBackground(Background.EMPTY);
        musicmute.setFont(buttonFont);
        
        
        // Schaltfläche zum Ändern der Schwierigkeit erstellen
        Button changedifficultyButton = new Button("Difficulty: " + difficulty);
        changedifficultyButton.setPrefSize(200, 60);

        // Ereignishandler zum Ändern der Schwierigkeit
        changedifficultyButton.setOnAction(event -> {
            // Zwischen "Einfach" und "Schwer" umschalten
            difficulty = difficulty.equals("Easy") ? "Medium" : difficulty.equals("Medium") ? "Hard" : "Easy";
            switch (difficulty) {
                case "Easy":
                    diff = 'E';
                    break;
                case "Medium":
                    diff = 'M';
                    break;
                case "Hard":
                    diff = 'H';
                    break;
                default:
                    diff = 'U'; // 'U' für unbekannt oder einen geeigneten Standardwert
                    break;
            }
            System.out.println("Difficulty changed to: " + difficulty);
            changedifficultyButton.setText("Difficulty: " + difficulty);
        });
        changedifficultyButton.setTextFill(buttonTextColor);
//        changedifficultyButton.setBackground(Background.EMPTY);
        changedifficultyButton.setFont(buttonFont);
        
        Button back = new Button("Back to Menu");
        back.setTextFill(buttonTextColor);
        back.setPrefSize(200, 60);
//        back.setBackground(Background.EMPTY);
        back.setFont(buttonFont);
        
        
        
        VBox optionsBox = new VBox(50);
        optionsBox.getChildren().addAll(musicmute, changedifficultyButton, back);
        optionsBox.setAlignment(Pos.CENTER_LEFT);
        optionsmenu.setCenter(optionsBox);
        
        Scene optionsscene = new Scene(optionsmenu, 800, 600);
        
        
        
        
        back.setOnAction(event -> {
            primaryStage.setScene(menu(primaryStage));
        });
        
        return optionsscene;
    }
    
    /**
     * Erstellt und gibt die Menü-Szene zurück.
     *
     * @param primaryStage Der Hauptbühnen-Container der Anwendung.
     * @return Die Menü-Szene.
     */
    
    public static Scene menu(Stage primaryStage) {
        BorderPane root = new BorderPane();
        // Hintergrundbild mit dem Bild erstellen
        
        
       root.setBackground(new Background(background));
       
       
       Button startgameButton = new Button("Start Game");
       Button optionsButton = new Button("Options");
       Button exitgameButton = new Button("Exit Game");
       
       startgameButton.setPrefSize(200, 60);
       optionsButton.setPrefSize(200, 60);
       exitgameButton.setPrefSize(200, 60);
       


    // Setze die Textfarbe und Schriftart
       

       startgameButton.setTextFill(buttonTextColor);
       startgameButton.setFont(buttonFont);

       optionsButton.setTextFill(buttonTextColor);
       optionsButton.setFont(buttonFont);

       exitgameButton.setTextFill(buttonTextColor);
       exitgameButton.setFont(buttonFont);
       
       exitgameButton.setOnAction(event -> {
           primaryStage.close(); // Anwendung schließen
       });
       
       
       
       VBox mainbuttonBox = new VBox(50);
       mainbuttonBox.getChildren().addAll(startgameButton, optionsButton, exitgameButton);
       mainbuttonBox.setAlignment(Pos.CENTER_LEFT);
       root.setCenter(mainbuttonBox);
       
       optionsButton.setOnAction(event -> {
           primaryStage.setScene(optionsScene(primaryStage));
       });
       
       Scene menu = new Scene(root,800,600);
       
       startgameButton.setOnAction(event -> {
           gen = new MazeGenerate();
           gen.setScene(primaryStage);
       });
       
       return menu;
    }
    
    
    
    
    
	public static void start(Stage primaryStage) {
		try {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Musik unendlich wiederholen
            mediaPlayer.play();
			

			//menu(primaryStage).getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(menu(primaryStage));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
//	public static void main(String[] args) {
//		launch(args);
//	}

	/**
     * Gibt den aktuellen Musikstatus zurück.
     *
     * @return Der aktuelle Musikstatus.
     */

    public static boolean isMusicstat() {
        return musicstat;
    }

    /**
     * Setzt den Musikstatus.
     *
     * @param musicstat Der neue Musikstatus.
     */

    public static void setMusicstat(boolean musicstat) {
        Labyrinth.musicstat = musicstat;
    }
}
