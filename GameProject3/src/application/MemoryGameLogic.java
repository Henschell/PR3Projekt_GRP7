package application;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Autor: Henschell Hans Rackebrandt
 * 
 * MemoryGameLogic ist eine Klasse, die die Spiellogik für ein Memory-Spiel implementiert.
 * Sie verwaltet die aktuell ausgewählten Karten, den Spielstand und das Spielfeld.
 * 
 * Die Klasse bietet Methoden zum Setzen von Karten, Überprüfen von Übereinstimmungen, Erhöhen des Spielstands und Pausieren des Spiels.
 */

public class MemoryGameLogic {
    private MemoryCard firstCard;
    private MemoryCard secondCard;
    private int score = 0;
    private Memory memoryfield;
    private int paairs;
    private int ogpairs;
    
    /**
     * Konstruktor für die MemoryGameLogic-Klasse.
     * Initialisiert das Spielfeld und die Anzahl der Paare.
     * @param mfield Das Spielfeld, auf dem das Spiel stattfindet.
     * @param paairs Die Anzahl der Paare im Spiel.
     */
    
    public MemoryGameLogic(Memory mfield,int paairs) {
        memoryfield = mfield;
        this.paairs = paairs;
        this.ogpairs = paairs;
    }
    
    /**
     * Setzt die ausgewählten Karten. Wenn bereits zwei Karten ausgewählt sind, wird überprüft, ob sie übereinstimmen.
     * @param card Die ausgewählte Karte.
     */
    
    public void setCards(MemoryCard card) {
        if (firstCard == null) {
            firstCard = card;
            firstCard.flipImage();
        } else if (secondCard == null && firstCard != card) {
            secondCard = card;
            secondCard.flipImage();
            pauseFornSeconds(()-> checkMatch(),0.5);
        }
    }
    
    /**
     * Überprüft, ob die beiden ausgewählten Karten übereinstimmen. Wenn sie übereinstimmen, wird der Spielstand erhöht und die Karten werden gelöscht.
     * Wenn sie nicht übereinstimmen, werden die Karten zurückgedreht.
     */
    
    public void checkMatch(){
        if (firstCard.matches(secondCard)) {
            increaseScore(100);
            paairs --;
            paairs --;
            System.out.println("Paairs left = " + paairs);
            memoryfield.updateScore();
            firstCard.deleteCard();
            secondCard.deleteCard();
            if (paairs == 0) {
                memoryfield.addNewRound();
                paairs = ogpairs;
            }
        }
        else {
            firstCard.flipImage();
            secondCard.flipImage();
        }
        firstCard = null;
        secondCard = null;
    }
    
    /**
     * Erhöht den Spielstand um eine bestimmte Anzahl von Punkten.
     * @param points Die Anzahl der Punkte, um die der Spielstand erhöht werden soll.
     */
    
    public void increaseScore(int points) {
        score += points;
    }
    
    /**
     * Gibt den aktuellen Spielstand zurück.
     * @return Der aktuelle Spielstand.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Pausiert das Spiel für eine bestimmte Anzahl von Sekunden und führt danach eine Aktion aus.
     * @param action Die Aktion, die nach der Pause ausgeführt werden soll.
     * @param time Die Dauer der Pause in Sekunden.
     */

    public void pauseFornSeconds(Runnable action,double time) {
        PauseTransition pause = new PauseTransition(Duration.seconds(time));
        pause.setOnFinished(event -> action.run());
        pause.play();
    }
}
