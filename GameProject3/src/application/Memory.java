package application;

import java.util.ArrayList;
import java.util.Collections;

import application.SettingsManager.Difficulty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;

/**Author: Henschell Hans Rackebrandt
 * 
 * Die Memory-Klasse verwaltet das Memory-Spiel, einschließlich des Spielablaufs, der Szene und der Interaktionen.
 * Sie erstellt das Spielraster, steuert den Spielablauf basierend auf der Schwierigkeit und hält den Punktestand fest.
 */

public class Memory {
    
    private static Memory instance = null;
    private Scene memoryScene;
    private GridPane memoryGrid;
    private MemoryGameLogic gameLogic;
    private Label scoreLabel;
    private TimerManager timerManager;
    private ArrayList<Image> storedImages;
    private ArrayList<Image> selectedImages;
    private GUIController guiController;
    private VBox root;
    private MemoryAnimation memoryAnimation;
    private Difficulty currentDifficulty;
    int nRows = 6; 
    int nColumns = 8; 
    
    /**
     * Konstruktor für das Memory-Spiel.
     * @param allImages Eine Liste aller verfügbaren Bilder für das Spiel.
     * @param defaultBackGround Das Standardhintergrundbild für das Memory-Spiel.
     * @param heigh Die Höhe der Memory-Szene.
     * @param width Die Breite der Memory-Szene.
     * @param guiController Der GUI-Controller für die Benutzeroberfläche.
     * @param difficulty Die gewählte Schwierigkeitsstufe des Spiels.
     */

    private Memory(ArrayList<Image> allImages,Background defaultBackGround, int heigh, int width, GUIController guiController,Difficulty difficulty) {
        this.guiController = guiController;
        storedImages = allImages;
        selectedImages = ImageManager.create24Paair(storedImages);
        gameLogic = new MemoryGameLogic(this,nRows*nColumns);
        root = new VBox();
        currentDifficulty = difficulty;
        memoryGrid = createMemoryGrid();
        root.getChildren().addAll(scoreAndTimer(),memoryGrid);
        root.setBackground(defaultBackGround);
        if (currentDifficulty.equals(Difficulty.HARD)) {
            memoryAnimation = new MemoryAnimation(this, nRows, nColumns,1);
            memoryAnimation.start();
        } else if (currentDifficulty.equals(Difficulty.MEDIUM)) {
            memoryAnimation = new MemoryAnimation(this, nRows, nColumns,5);
            memoryAnimation.start();
        }
        memoryScene = new Scene(root, heigh, width);
    }
    
    /**
     * Erstellt das Raster für das Memory-Spiel basierend auf den ausgewählten Bildern.
     * @return Das erstellte GridPane für das Memory-Spiel.
     */
    
    private GridPane createMemoryGrid() {
        GridPane memoryfield = new GridPane();
        memoryfield.setHgap(15);
        memoryfield.setVgap(10);
        memoryfield.setPadding(new Insets(30));
        int imageIndex = 0;
        Image back = new Image("file:images/Cardback1.jpg");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                Image front = selectedImages.get(imageIndex);
                MemoryCard card = new MemoryCard(front,back);
                imageIndex++;                    
                memoryfield.add(card, j, i);
                card.setOnMouseClicked(x -> {
                    gameLogic.setCards(card);
                    updateScore();
                });
                if (i == 0) { // Wenn es sich um die erste Zeile handelt
                    GridPane.setMargin(card, new Insets(20, 0, 0, 0)); // Fügen Sie oben einen Rand von 20 Pixeln hinzu
                }
            }
        }
        memoryfield.setTranslateY(-50);
        return memoryfield;
    }
    
    /**
     * Gibt die Szene des Memory-Spiels zurück.
     * @return Die Szene des Memory-Spiels.
     */
    
    public Scene getScene() {
        return memoryScene; 
    }
    
    /**
     * Erstellt die Anzeige für den Punktestand und den Timer des Spiels.
     * @return Ein HBox-Container mit den Anzeigen für Score und Timer.
     */
    
    private HBox scoreAndTimer() {
        int timer = 300;
//        if (currentDifficulty.equals(Difficulty.EASY)) {
//            timer = 500;
//        }
        scoreLabel = new Label("Score: " + gameLogic.getScore());
        Label timerLabel = new Label();
        timerManager = new TimerManager(timerLabel, timer,guiController);
        scoreLabel.setFont(new Font(40));
        scoreLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(new Font(40));
        timerLabel.setTextFill(Color.WHITE);
        timerManager.start();
        HBox labelBox = new HBox();
        labelBox.getChildren().addAll(timerLabel, scoreLabel);
        HBox.setHgrow(timerLabel, Priority.ALWAYS);
        labelBox.setMouseTransparent(true);
        timerLabel.setMaxWidth(Double.MAX_VALUE);
        scoreLabel.setMaxWidth(Double.MAX_VALUE);
        timerLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER_RIGHT);
        return labelBox;
    }
    
    /**
     * Aktualisiert den angezeigten Punktestand des Spiels.
     */
    
    public void updateScore() {
        scoreLabel.setText("Score: " + gameLogic.getScore());
    }
    
    /**
     * Fügt eine neue Runde zum Spiel hinzu, aktualisiert das Raster und den Timer entsprechend.
     */
    
    public void addNewRound() {
        if (currentDifficulty.equals(Difficulty.HARD) || currentDifficulty.equals(Difficulty.MEDIUM)) {
            memoryAnimation.stop();            
        }
        Collections.shuffle(selectedImages);
        memoryGrid.getChildren().clear();
        memoryGrid = createMemoryGrid();
        root.getChildren().set(1, memoryGrid);
        timerManager.increaseTimer(100);
        if (currentDifficulty.equals(Difficulty.HARD)) {
            memoryAnimation = new MemoryAnimation(this, nRows, nColumns,1);
            memoryAnimation.start();            
        }
        else if(currentDifficulty.equals(Difficulty.MEDIUM)) {
            memoryAnimation = new MemoryAnimation(this, nRows, nColumns,5);
            memoryAnimation.start(); 
        }
    }
    /**
     * Pausiert den Spiel-Timer.
     */
    
    public void pauseTimer() {
        timerManager.pauseTimer();
    }
    /**
     * Setzt den Spiel-Timer fort.
     */
    public void resumeTimer() {
        timerManager.resumeTimer();
    }
    
    /**
     * Gibt das GridPane des Memory-Spiels zurück.
     * @return Das GridPane des Memory-Spiels.
     */
    
    public GridPane getGrid() {
        return memoryGrid;
    }
    
    /**
     * Stoppt die Spielanimation basierend auf der Schwierigkeit.
     */
    
    public void stopAnimation() {
        if (currentDifficulty.equals(Difficulty.HARD) || currentDifficulty.equals(Difficulty.MEDIUM)) {
            memoryAnimation.stop();
        }
    }
    
    /**
     * Setzt die Spielanimation fort basierend auf der Schwierigkeit.
     */
    
    public void continueAnimation() {
        if (currentDifficulty.equals(Difficulty.HARD) ||  currentDifficulty.equals(Difficulty.MEDIUM)) {
            memoryAnimation.continueTimeline();
        }
    }
    
    /**
     * Setzt die Instanz des Memory-Spiels zurück.
     */
    
    public static void resetInstance() {
        instance = null;
    }
    
    /**
     * Gibt die Singleton-Instanz des Memory-Spiels zurück.
     * Falls keine Instanz vorhanden ist, wird eine neue erstellt.
     * @param allImages Eine Liste aller verfügbaren Bilder für das Spiel.
     * @param defaultBackGround Das Standardhintergrundbild für das Memory-Spiel.
     * @param heigh Die Höhe der Memory-Szene.
     * @param width Die Breite der Memory-Szene.
     * @param guiController Der GUI-Controller für die Benutzeroberfläche.
     * @param difficulty Die gewählte Schwierigkeitsstufe des Spiels.
     * @return Die Singleton-Instanz des Memory-Spiels.
     */
    public static Memory getInstance(ArrayList<Image> allImages, Background defaultBackGround, int heigh, int width, GUIController guiController, Difficulty difficulty) {
        if (instance == null) {
            instance = new Memory(allImages, defaultBackGround, heigh, width, guiController, difficulty);
        }
        return instance;
    }
}
