package application;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Autor: Henschell Hans Rackebrandt
 * 
 * AudioManager ist eine Klasse, die für die Verwaltung von Audio in einer Anwendung verantwortlich ist.
 * Sie bietet Methoden zum Abspielen von Soundeffekten und Hintergrundmusik, sowie zum Abrufen und Einstellen der Lautstärke.
 * 
 * Die Klasse verwendet die JavaFX-Bibliothek für die Audiowiedergabe und erfordert daher die Einbindung der entsprechenden Bibliothek in das Projekt.
 * 
 * Die Pfade zu den Audiodateien sind fest codiert und müssen daher bei einer Änderung der Dateistruktur oder des Dateinamens angepasst werden.
 * 
 * Die Klasse ist statisch und erfordert daher nicht das Erstellen einer Instanz zum Aufrufen ihrer Methoden.
 */

public class AudioManager {
    
    private static AudioClip audioClip;
    private static double soundEffectVolume = 0.5;
    private static double backgroundVolume = 0.5;
    private static MediaPlayer mediaPlayer;
    
    /**
     * Spielt einen Soundeffekt ab. Der Pfad zum Soundeffekt ist fest codiert.
     * Die Lautstärke des Soundeffekts wird auf den aktuellen Wert von soundEffectVolume gesetzt.
     */
    
    public static void playSoundEffect() {
        try {
            System.out.println("Soundeffect1 FUNKTIONIERT");
            String soundEffectFile = "Audio/Memory/SE1.wav";
            audioClip = new AudioClip(new File(soundEffectFile).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Error soundEffectFile not found" + e.getMessage());
        }
    }
    
    /**
     * Spielt eine Hintergrundmusik ab. Der Pfad zur Hintergrundmusik ist fest codiert.
     * Die Lautstärke der Hintergrundmusik wird auf den aktuellen Wert von backgroundVolume gesetzt.
     * Die Hintergrundmusik wird in einer Endlosschleife abgespielt.
     */
    
    public static void playBackgroundAudio() {
        try {
            String ost13 = "Audio/Memory/Undertale_93.mp3";
            Media sound = new Media(new File(ost13).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(backgroundVolume);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error backgroundFile not found" + e.getMessage());
        }
    }
    public static void stopBackGroundAudio() {
        mediaPlayer.pause();
    }
    public static void continueBackGroundAudio() {
        mediaPlayer.play();
    }
    
    /**
     * Gibt den aktuellen AudioClip zurück, der den zuletzt abgespielten Soundeffekt repräsentiert.
     * @return Der aktuelle AudioClip.
     */
    
    public static AudioClip getAudioClip() {
        return audioClip;
    }
    
    /**
     * Gibt den aktuellen MediaPlayer zurück, der die zuletzt abgespielte Hintergrundmusik repräsentiert.
     * @return Der aktuelle MediaPlayer.
     */
    
    public static MediaPlayer getMediaPlayer() {
        return getMediaPlayer();
    }
    
    /**
     * Setzt die Lautstärke der Hintergrundmusik auf einen neuen Wert.
     * @param newBackgroundVolume Der neue Lautstärkewert für die Hintergrundmusik.
     * @throws IllegalStateException Wenn der MediaPlayer noch nicht initialisiert wurde.
     */
    
    public static void setBackgroundAudioVolume(double newBackgroundVolume) {
        if (mediaPlayer != null ) {
            backgroundVolume = newBackgroundVolume;
            mediaPlayer.setVolume(backgroundVolume);
        } else {
            throw new IllegalStateException("MediaPlayer not Initialized");
        }
    }
    
    /**
     * Setzt die Lautstärke des Soundeffekts auf einen neuen Wert.
     * @param newSoundeffectVolume Der neue Lautstärkewert für den Soundeffekt.
     * @throws IllegalStateException Wenn der AudioClip noch nicht initialisiert wurde.
     */
    
    public static void setSoundEffectVolume(double newSoundeffectVolume) {
        if (audioClip != null) {
            soundEffectVolume = newSoundeffectVolume;
            audioClip.setVolume(soundEffectVolume);
            if (soundEffectVolume == 0) {
                audioClip.stop();
            } else {
                if (audioClip.isPlaying()) {
                    audioClip.stop();
                }
                audioClip.play();
            }
        } else {
            throw new IllegalStateException("AudioClip not Initialized");
        }
    }
    
    /**
     * VIRUSSWEEPER
     * VIRUSSWEEPER
     * VIRUSSWEEPER
     * VIRUSSWEEPER
     * VIRUSSWEEPER
     */
    
    /**
     * Spielt, einen selbst erstellten Sound ab, für das Anklicken einer
     * VirusSweeperFestplatte.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperPlayClickSound() {
        try {
            String soundEffectDatei = "Audio/VirusSweeper/VirusSweeperklick.mp3";
            audioClip = new AudioClip(new File(soundEffectDatei).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Fehler SoundDatei nicht gefunden" + e.getMessage());
        }
    }

    /**
     * Spielt, einen selbst erstellten Sound ab, für das Verlieren im Spiel
     * VirusSweeper.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperPlayVerlorenSound() {
        try {
            String soundEffectDatei = "Audio/VirusSweeper/VirusSweeperVerloren.mp3";
            audioClip = new AudioClip(new File(soundEffectDatei).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Fehler SoundDatei nicht gefunden" + e.getMessage());
        }
    }

    /**
     * Spielt, einen selbst erstellten Sound ab, für das Gewinnen im Spiel
     * VirusSweeper.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperPlayGewonnenSound() {
        try {
            String soundEffectDatei = "Audio/VirusSweeper/VirusSweeperGewonnen.mp3";
            audioClip = new AudioClip(new File(soundEffectDatei).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Fehler SoundDatei nicht gefunden" + e.getMessage());
        }
    }

    /**
     * Spielt, einen selbst erstellten Sound ab, für das entfernen einer Markierung
     * von einer VirusSweeperFestplatte.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperPlayMarkierungentfernen() {
        try {
            String soundEffectDatei = "Audio/VirusSweeper/VirusSweeperMarkierungentfernen.mp3";
            audioClip = new AudioClip(new File(soundEffectDatei).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Fehler SoundDatei nicht gefunden" + e.getMessage());
        }
    }

    /**
     * Spielt, einen selbst erstellten Sound ab, für das markieren von einer
     * VirusSweeperFestplatte.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperPlayMarkieren() {
        try {
            String soundEffectDatei = "Audio/VirusSweeper/VirusSweeperMarkieren.mp3";
            audioClip = new AudioClip(new File(soundEffectDatei).toURI().toString());
            audioClip.setVolume(soundEffectVolume);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Fehler SoundDatei nicht gefunden" + e.getMessage());
        }
    }
}
