package VirusSweeper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Diese Zeitanzeige, wird dazu verwendet um einen Zeitanzeige in Form
 * minuten:Sekunden, darzustellen. Dies wird gemacht durch die vererbung von
 * einem Label. Und wird nachdem Start jede Sekunde durhc einen Thread erhöht.
 * 
 * @author Marc Pauluhn
 */
public class ZeitAnzeige extends Label {
    private int minuten;
    private int sekunden;
    private static Timeline timeline;
    private boolean isRunning;

    /**
     * Konstruktor, der die Anzeige auf "Time: 00:00" setzt.
     */
    public ZeitAnzeige() {
        this.setFont(new Font(20));
        this.setText("Time: 00:00");
        this.setMaxWidth(Double.MAX_VALUE);
        this.sekunden = 0;
        this.minuten = 0;
        this.isRunning = false;
        // erstellt eine Timeline, die jede sekunde die Anzeige Aktuallisiert.
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTimer();
        }));
        // Dies wird ausgeführt, bis es gestopt wird, sonst unendlich lang.
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Diese Methode Startet, die Timeline.
     */
    public void startTimer() {
        isRunning = true;
        timeline.play();
    }

    /**
     * Diese Methode stoppt die Timeline.
     */
    public void stopTimer() {
        isRunning = false;
        timeline.stop();
    }

    /**
     * Diese Methode setzt die Zeitanzeige zurück, sodass wieder von 0 angefangen
     * wird.
     */
    public void resetTimer() {
        timeline.stop();
        minuten = 0;
        sekunden = 0;
        isRunning = false;
        updateTimer();
    }

    /**
     * Diese Methode ist für die erhöhung der Zeit zuständig.
     */
    private void updateTimer() {
        sekunden++;
        if (sekunden == 60) { // wenn es 60 Sekunden sind
            minuten++; // wird minuten um 1 erhöht
            sekunden = 0; // und sekunden auf 0 gesetzt
        }
        // Zeigt es in Form : "Time: minuten:sekunden" an
        setText(String.format("Time: %02d:%02d", minuten, sekunden));
    }

    /**
     * @return gibt zurück, ob die Zeit schon gestartet ist oder nicht
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @return gibt string mit Zeit in Form "minuten:sekunden" zurück
     */
    public String getZeit() {
        return (minuten + ":" + sekunden);
    }
}