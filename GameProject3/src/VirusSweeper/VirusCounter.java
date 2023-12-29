package VirusSweeper;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Diese Klasse VirusCounter hat ein Label vererbt bekommen, um die anzahl der
 * Markierungen in Form "Viren: anzahl" anzeigen zu können.
 * 
 * @author Marc Pauluhn
 */
public class VirusCounter extends Label {
    private final int VIRUS_ANZAHL[] = { 10, 40, 100 };
    private int markierungen;

    /**
     * Konstruktor der es mit der Anzahl der zu findenden viren für die
     * schwierigkeitsstufe initialisiert
     * 
     * @param schwierigkeit
     */
    VirusCounter(int schwierigkeit) {
        setMarkierungen(VIRUS_ANZAHL[schwierigkeit]);
        this.setFont(new Font(20));
        this.setMaxWidth(Double.MAX_VALUE);
        this.setText("Viren: " + this.getMarkierungen());
    }

    /**
     * Erhöht die Anzahl der markierten VirusSweeperFestplatten (Flagge(Markierung)
     * gesetzt)
     */
    public void plusFlag() {
        markierungen--;
    }

    /**
     * verringert die Anzahl der markierten VirusSweeperFestplatten
     * (Flagge(Markierung) entfernt)
     */
    public void minusFlag() {
        markierungen++;
    }

    /**
     * @return gibt anzahl zurück, wie viele Markierungen gesetzt werden müssen.
     */
    public int getMarkierungen() {
        return markierungen;
    }

    /**
     * Setter für anzahl der markierungen, die gesezt werden müssen.
     * 
     * @param markierungen
     */
    public void setMarkierungen(int markierungen) {
        this.markierungen = markierungen;
    }

}