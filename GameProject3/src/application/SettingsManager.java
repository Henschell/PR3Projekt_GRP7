package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Author: Henschell Hans Rackebrandt
 * 
 * Die SettingsManager-Klasse verwaltet die Einstellungen für das Spiel, einschließlich Audio- und Schwierigkeitsoptionen.
 * Sie erstellt eine Szene, auf der Benutzer die Lautstärke für Hintergrundmusik und Soundeffekte einstellen sowie die
 * Schwierigkeitsstufe des Spiels auswählen können.
 */

public class SettingsManager {
    
    private static SettingsManager instance = null;
    private Slider volumeSlider;
    private Slider soundEffectSlider;
    private HBox sliderContainer;
    private Scene settingsScene;
    private static Text currentDifficultyText;
    
    private Stage primaryStage;
    private Scene previosScene;
    private Background defaultBackGround;
    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private static Difficulty selectedDifficulty = Difficulty.MEDIUM;
    
    public enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }
    
    /**
     * Konstruktor für den SettingsManager.
     * @param primaryStage Die Hauptbühne der Anwendung.
     * @param previosScene Die vorherige Szene, zu der zurückgekehrt werden soll.
     * @param background Das Hintergrundbild für die Einstellungen.
     * @param width Die Breite der Einstellungsszene.
     * @param height Die Höhe der Einstellungsszene.
     */
    
    private SettingsManager(Stage primaryStage,Scene previosScene,Background background, int width, int height) {
        this.primaryStage = primaryStage;
        this.previosScene = previosScene;
        this.defaultBackGround = background;
        this.WINDOW_WIDTH = width;
        this.WINDOW_HEIGHT = height;
        createScene();

    }
    
    /**
     * Erstellt die Einstellungsszene mit Schiebereglern für Lautstärke, Schwierigkeitsbuttons und Zurück-Taste.
     */
    
    public void createScene() {
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(100);
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.setBackgroundAudioVolume(newVal.doubleValue() / 100);
        });
        soundEffectSlider = new Slider();
        soundEffectSlider.setPrefWidth(100);
        soundEffectSlider.setMin(0);
        soundEffectSlider.setMax(100);
        soundEffectSlider.setValue(50);
        soundEffectSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.setSoundEffectVolume(newVal.doubleValue() / 100);
        });
        sliderContainer = new HBox();
        sliderContainer.getChildren().addAll(volumeSlider);
        sliderContainer.setAlignment(Pos.CENTER);
        Text soundText = new Text("SFX");
        soundText.setFont(new Font(30));
        soundText.setFill(Color.RED);
        HBox soundsliderContainer = new HBox();
        soundsliderContainer.getChildren().addAll(soundEffectSlider);
        soundsliderContainer.setAlignment(Pos.CENTER);
        Text title = new Text("Einstellungen");
        title.setFont(new Font(100));
        title.setFill(Color.WHITE);
        Text audio = new Text("Music");
        audio.setFont(new Font(30));
        audio.setFill(Color.WHITE);
        VBox titleholder = new VBox();
        titleholder.setAlignment(Pos.TOP_CENTER);
        titleholder.getChildren().addAll(title);
        Button backbutton = new Button("Zurück");
        backbutton.setOnAction(x -> primaryStage.setScene(previosScene));
        VBox difficultyContainer = createDifficultyButtons();
        VBox buttomholder = new VBox(10);
        buttomholder.setAlignment(Pos.CENTER);
        buttomholder.getChildren().addAll(audio, sliderContainer,soundText,soundsliderContainer,difficultyContainer, backbutton);
        StackPane settingsstackpane = new StackPane();
        settingsstackpane.setBackground(defaultBackGround);
        settingsstackpane.getChildren().addAll(titleholder, buttomholder);
        settingsScene = new Scene(settingsstackpane, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    /**
     * Erstellt eine VBox mit Schwierigkeitsauswahlbuttons.
     * @return Die VBox mit den Schwierigkeitsauswahlbuttons.
     */
    
    public VBox createDifficultyButtons() {
        int buttonx = 100;
        int buttony = 50;
        VBox difficultyContainer = new VBox();
        Button easyButton = new Button("Einfach");
        easyButton.setPrefSize(buttonx, buttony);
        easyButton.setOnAction(x -> setDifficulty(Difficulty.EASY));
        Button mediumButton = new Button("Mittel");
        mediumButton.setPrefSize(buttonx, buttony);
        mediumButton.setOnAction(x -> setDifficulty(Difficulty.MEDIUM));
        Button schwerButton = new Button("Schwer");
        schwerButton.setPrefSize(buttonx, buttony);
        schwerButton.setOnAction(x -> setDifficulty(Difficulty.HARD));
        currentDifficultyText = new Text("Aktuelle Schwierigkeit: " + selectedDifficulty.toString());
        currentDifficultyText.setFont(new Font(30));
        currentDifficultyText.setTranslateX(-30);
        currentDifficultyText.setFill(Color.WHITE);
        HBox currentDifficultyBox = new HBox();
        currentDifficultyBox.getChildren().addAll(new Text("Aktuelle Schwierigkeit: "), currentDifficultyText);
        currentDifficultyBox.setAlignment(Pos.CENTER);
        HBox buttonContainer = new HBox(20);
        buttonContainer.getChildren().addAll(easyButton,mediumButton,schwerButton);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        difficultyContainer.getChildren().addAll(buttonContainer,currentDifficultyBox);
        return difficultyContainer;
    }
    
    /**
     * Legt die ausgewählte Schwierigkeitsstufe fest.
     * @param difficulty Die ausgewählte Schwierigkeitsstufe.
     */
    public static void setDifficulty(Difficulty difficulty) {
        selectedDifficulty = difficulty;
        System.out.println("current difficulty = " + selectedDifficulty);
        currentDifficultyText.setText("Aktuelle Schwierigkeit: " + selectedDifficulty.toString());
    }
    
    /**
     * Gibt die Singleton-Instanz des SettingsManagers zurück.
     * Falls keine Instanz vorhanden ist, wird eine neue erstellt.
     * @param primaryStage Die Hauptbühne der Anwendung.
     * @param previosScene Die vorherige Szene, zu der zurückgekehrt werden soll.
     * @param background Das Hintergrundbild für die Einstellungen.
     * @param width Die Breite der Einstellungsszene.
     * @param height Die Höhe der Einstellungsszene.
     * @return Die Singleton-Instanz des SettingsManagers.
     */
    
    public static SettingsManager getInstance(Stage primaryStage,Scene previosScene,Background background, int width, int height) {
        if (instance == null) {
            instance = new SettingsManager(primaryStage,previosScene,background,width,height);
        }
        return instance;
    }
    
    /**
     * Gibt die aktuell ausgewählte Schwierigkeitsstufe zurück.
     * @return Die aktuell ausgewählte Schwierigkeitsstufe.
     */
    
    public static Difficulty getDifficulty() {
        return selectedDifficulty;
    }
    
    /**
     * Legt die vorherige Szene fest, zu der zurückgekehrt werden soll.
     * @param previos Die vorherige Szene.
     */
    
    public void setPreviosScene(Scene previos) {
        this.previosScene = previos;
    }
    
    /**
     * Gibt die Einstellungsszene zurück.
     * @return Die Szene mit den Einstellungen.
     */
    
    public Scene getSettingsScene() {
        return settingsScene;
    }

}
