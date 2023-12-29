package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Autor: Henschell Hans Rackebrandt
 * 
 * TimerManager ist eine Klasse, die einen Countdown-Timer für ein Spiel verwaltet.
 * Sie verwendet die Timeline-Klasse von JavaFX, um den Timer zu implementieren.
 * 
 * Die Klasse bietet Methoden zum Starten, Stoppen, Aktualisieren und Pausieren des Timers.
 */

public class TimerManager {
    private Timeline timer;
    private int timeInSeconds;
    private Label timerLabel;
    private GUIController guicontroller;
    private boolean isPaused = false;
    
    /**
     * Konstruktor für die TimerManager-Klasse.
     * Initialisiert den Timer, das Timer-Label, die Zeit in Sekunden und den GUI-Controller.
     * @param timerLabel Das Label, das den Timer anzeigt.
     * @param timeInSeconds Die Zeit in Sekunden, die der Timer zählen soll.
     * @param guiController Der GUI-Controller, der das Spiel steuert.
     */

    public TimerManager(Label timerLabel, int timeInSeconds, GUIController guiController) {
        this.timerLabel = timerLabel;
        this.timeInSeconds = timeInSeconds;
        this.guicontroller = guiController;
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timer.setCycleCount(Timeline.INDEFINITE);
    }
    
    /**
     * Startet den Timer.
     */
    
    public void start() {
        timer.play();
    }
    
    /**
     * Stoppt den Timer.
     */
    
    public void stop() {
        timer.stop();
    }
    /**
     * Aktualisiert den Timer. Wenn die Zeit abgelaufen ist, wird das Spiel beendet.
     */
    
    private void updateTimer() {
        if (timeInSeconds > 0) {
            timeInSeconds--;
            int minutes = timeInSeconds / 60;
            int seconds = timeInSeconds % 60;
            timerLabel.setText("Timer: " + minutes + ":" + String.format("%02d", seconds));
        } else {
            stop();
            guicontroller.showGameOver();
        }
    }
    
    /**
     * Erhöht die Zeit des Timers um eine bestimmte Anzahl von Sekunden.
     * @param seconds Die Anzahl der Sekunden, um die der Timer erhöht werden soll.
     */
    
    public void increaseTimer(int seconds) {
        this.timeInSeconds += seconds;
    }
    
    /**
     * Pausiert den Timer.
     */
    
    public void pauseTimer() {
        isPaused = true;
        timer.pause();
    }
    
    /**
     * Setzt den Timer fort, wenn er pausiert ist.
     */
    
    public void resumeTimer() {
        if (isPaused) {
            isPaused = false;
            start();
        }
    }
}
