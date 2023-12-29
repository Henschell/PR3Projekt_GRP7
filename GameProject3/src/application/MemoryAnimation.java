package application;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.animation.Animation;

/**
 * Die MemoryAnimation-Klasse steuert die Animationen für das Austauschen von Memory-Karten in einem GridPane.
 * Sie verwendet JavaFX-Animationen, um zufällig ausgewählte Karten im GridPane zu tauschen und die Bewegungen zu animieren.
 * Autor: Henschell Hans Rackebrandt
 */

public class MemoryAnimation {
    //private Memory memory;
    private GridPane memoryGrid;
    private Timeline timeline;
    private int nRows;
    private int nColumns;
    private int row1;
    private int col1;
    private int row2;
    private int col2;
    private int timer;
    
    /**
     * Erstellt eine neue Instanz von MemoryAnimation für die Verwaltung von Animationen für Memory-Karten.
     * @param memory Das Memory-Objekt, auf das sich die Animation bezieht.
     * @param nRows Die Anzahl der Zeilen im GridPane, in dem die Memory-Karten platziert sind.
     * @param nColumns Die Anzahl der Spalten im GridPane, in dem die Memory-Karten platziert sind.
     * @param timer Die Zeit in Sekunden, nach der die Animation für den Austausch von Karten ausgelöst wird.
     */

    public MemoryAnimation(Memory memory, int nRows, int nColumns,int timer) {
        //this.memory = memory;
        this.memoryGrid = memory.getGrid();
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.timeline = new Timeline();
        this.timer = timer;
    }
    
    /**
     * Startet die Animation, um Memory-Karten in zufälligen Positionen im GridPane zu tauschen.
     * Die Animation wird kontinuierlich wiederholt, basierend auf dem definierten Timer.
     */

    public void start() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(timer), event -> swapRandomCards());
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    /**
     * Tauscht zufällig ausgewählte Memory-Karten im GridPane.
     * Die Bewegungen werden animiert, um den Kartenwechsel sichtbar zu machen.
     */

    private void swapRandomCards() {
        row1 = new Random().nextInt(nRows);
        row2 = row1;  // Stellen Sie sicher, dass row1 und row2 immer gleich sind
        col1 = new Random().nextInt(nColumns);
        col2 = new Random().nextInt(nColumns);

        // Sicherstellen, dass die ausgewählten Positionen nicht gleich sind
        while (col1 == col2) {
            col2 = new Random().nextInt(nColumns);
        }

        // Die Karten an den ausgewählten Positionen abrufen
        MemoryCard card1 = (MemoryCard) getNodeFromGridPane(memoryGrid, col1, row1);
        MemoryCard card2 = (MemoryCard) getNodeFromGridPane(memoryGrid, col2, row2);
        
        // Speichern der Layout-Positionen der Karten
        double card1LayoutX = card1.getLayoutX();
        double card1LayoutY = card1.getLayoutY();
        double card2LayoutX = card2.getLayoutX();
        double card2LayoutY = card2.getLayoutY();

        // Animationen für den Karten-Tausch
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), card1);
        tt1.setToX(card2LayoutX - card1LayoutX);
        tt1.setToY(card2LayoutY - card1LayoutY);

        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), card2);
        tt2.setToX(card1LayoutX - card2LayoutX);
        tt2.setToY(card1LayoutY - card2LayoutY);

        tt1.setOnFinished(event -> {
            memoryGrid.getChildren().remove(card1);
            memoryGrid.add(card1, col2, row2);
            card1.setTranslateX(0);
            card1.setTranslateY(0);
            System.out.println("Nach dem Tausch:");
            //System.out.println("Karte 1 Position: " + GridPane.getColumnIndex(card1) + ", " + GridPane.getRowIndex(card1))
            Bounds card1BoundsAfter = card1.localToScene(card1.getBoundsInLocal());
            double card1SceneXAfter = card1BoundsAfter.getMinX();
            double card1SceneYAfter = card1BoundsAfter.getMinY();
            System.out.println("Karte 1 absolute Position nach dem Tausch: " + card1SceneXAfter + ", " + card1SceneYAfter);;
        });

        tt2.setOnFinished(event -> {
            memoryGrid.getChildren().remove(card2);
            memoryGrid.add(card2, col1, row1);
            card2.setTranslateX(0);
            card2.setTranslateY(0);
            System.out.println("Nach dem Tausch:");
            //System.out.println("Karte 2 Position: " + GridPane.getColumnIndex(card2) + ", " + GridPane.getRowIndex(card2));
            Bounds card2BoundsAfter = card2.localToScene(card2.getBoundsInLocal());
            double card2SceneXAfter = card2BoundsAfter.getMinX();
            double card2SceneYAfter = card2BoundsAfter.getMinY();
            System.out.println("Karte 2 absolute Position nach dem Tausch: " + card2SceneXAfter + ", " + card2SceneYAfter);
        });

        tt1.play();
        tt2.play();
    }
    
    /**
     * Stoppt die laufende Animation der Memory-Karten.
     */

    public void stop() {
        timeline.stop();
    }
    
    /**
     * Setzt die angehaltene Timeline der Animation fort, falls sie pausiert oder gestoppt wurde.
     */
    
    public void continueTimeline() {
        if (timeline.getStatus() == Animation.Status.PAUSED || 
            timeline.getStatus() == Animation.Status.STOPPED) {
            timeline.play();
        }
    }
    
    /**
     * Hilfsmethode, um ein Node-Objekt basierend auf der Position im GridPane zu erhalten.
     * @param gridPane Das GridPane, in dem die Node-Objekte enthalten sind.
     * @param col Die Spaltenposition der gesuchten Node.
     * @param row Die Zeilenposition der gesuchten Node.
     * @return Das Node-Objekt an der angegebenen Position im GridPane oder null, falls nicht gefunden.
     */
    
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
