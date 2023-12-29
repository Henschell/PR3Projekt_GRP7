package JumpRun;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class BackgroundMusic {
    
    // Statische Instanz, um sicherzustellen, dass nur eine Instanz der Klasse existiert
    private static BackgroundMusic instance;
    
    
    // MediaPlayer für die Musikwiedergabe
    private MediaPlayer mediaPlayer;

    
    // Konstruktor, initialisiert den MediaPlayer mit der angegebenen Musikdatei
    public BackgroundMusic(String musicFilePath) {
        // Erzeugen eines Media-Objekts aus der Musikdatei
        Media media = new Media(new File(musicFilePath).toURI().toString());
        
        // Initialisieren des MediaPlayer mit dem Media-Objekt
        mediaPlayer = new MediaPlayer(media);
        
        // Festlegen, dass die Musik in einer Schleife (unendlich) abgespielt wird
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    // Statische Methode zur Rückgabe der einzigen Instanz der Klasse
    public static BackgroundMusic getInstance(String musicFilePath) {
        // Erzeugen der Instanz, wenn noch keine existiert
        if (instance == null) {
            instance = new BackgroundMusic(musicFilePath);
        }
        
        return instance;
    }

    // Methode zum Starten der Musikwiedergabe
    public void play() {
        // Überprüfen, ob die Musik nicht bereits abgespielt wird
        if (mediaPlayer.getStatus() != Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    
    // Methode zum Pausieren der Musikwiedergabe
    public void pause() {
        // Überprüfen, ob die Musik abgespielt wird, bevor sie pausiert wird
        if (mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    
    // Methode zum Stoppen der Musikwiedergabe
    public void stop() {
        mediaPlayer.stop();
    }

    
    // Methode zum Einstellen der Lautstärke
    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    
    // Methode zum Abrufen der aktuellen Lautstärke
    public double getVolume() {
        return mediaPlayer.getVolume();
    }
}