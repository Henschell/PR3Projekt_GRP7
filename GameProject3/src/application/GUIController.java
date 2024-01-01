package application;

import java.io.File;
import java.util.ArrayList;

import JumpRun.JumpAndRunMenu;
import Labyrinth.Labyrinth;
import Labyrinth.MazeGenerate;
import Quiz.Main;
import Snake.MainController;
import VirusSweeper.VirusSweeper;
import application.SettingsManager.Difficulty;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Author: Henschell Hans Rackebrandt
 * 
 * Die GUIController-Klasse verwaltet die Benutzeroberfläche des Spiels, einschließlich Menüs, Spielstart, Pausenbildschirm und Spielende.
 * Sie handhabt die Steuerung der Szenen und Interaktionen zwischen dem Benutzer und dem Spiel.
 */

public class GUIController extends Application {
    private static GUIController instance = null;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private StackPane stackpane = new StackPane();
    private Scene mainscene = new Scene(stackpane, WINDOW_WIDTH, WINDOW_HEIGHT);
    private Stage primaryStage;
    private Difficulty selectedDifficulty;
    private ArrayList<Image> storedImages;
    private ImageView preview1 = ImageManager.getGamePreview1();
    private ImageView preview2 = ImageManager.getGamePreview2();
    private ImageView preview3 = ImageManager.getGamePreview3();
    private ImageView preview4 = ImageManager.getGamePreview4();
    private ImageView preview5 = ImageManager.getGamePreview5();
    private ImageView preview6 = ImageManager.getGamePreview6();
    private VirusSweeper newVirusSweeperGame;
    private Background VirusSweeperVerlorenHintergrund = VirusSweeperGetSpielVerlorenHintergrund();
    private Background VirusSweeperGewonnenHintergrund = VirusSweeperSpielGewonnenHintergrund();
    private Memory newMemoryGame;
    private Background gameOverBackGround = ImageManager.getGameOverBackground();
    private Background defaultBackGround = ImageManager.getDefaultBackground();
    static Image image = new Image(new File("Back.gif").toURI().toString());
    static ImageView imageView = new ImageView(image);
    static Image image2 = new Image(new File("Back.gif").toURI().toString());
    static ImageView imageview2 = new ImageView(image2);
    
    private static String difficulty = "Easy";// Default difficulty
    private static char diff = 'E';
    private static boolean musicstat = true;
    private static Media media = new Media(new File("background-music.mp3").toURI().toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);
    private static Color buttonTextColor = Color.BLACK;
    private static Font buttonFont = Font.font(25);
    private static Image backgroundImage = new Image("file:BGImage3.gif");
   //private static MazeGenerate gen;
    private static BackgroundImage background = new BackgroundImage(
            backgroundImage, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            BackgroundSize.DEFAULT
        );
    
    /**
     * Die Methode start() wird beim Starten der JavaFX-Anwendung aufgerufen.
     * Sie initialisiert die Hauptmenü-Szene und startet die Hintergrundmusik.
     * @param primaryStage Die Hauptbühne der Anwendung.
     */
    
    @Override
    public void start(Stage primaryStage) {
        try {
            instance = this;
            if (instance != null) {
                System.out.println("Instanz für GUIController erstellt");
            }
            startThreadLoadImages();
            this.primaryStage = primaryStage;
            primaryStage.setResizable(false); // Die Stage ist jetzt nicht mehr vergrößerbar
            AudioManager.playBackgroundAudio();
            createMenu();
            mainscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(mainscene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public GUIController() {
        if (instance != null) {
            throw new RuntimeException("Verwenden Sie getInstance(), um diese Klasse zu verwenden.");
        }
    }
    
    public void initializeMainScene() {
        stackpane.getChildren().clear(); // Falls vorhanden, lösche vorherige Inhalte im StackPane
        // Erstelle die Hauptmenüszene mit verschiedenen Spieloptionen
        createMenu();
        // Weise die Szene dem primaryStage zu
        primaryStage.setScene(mainscene);
    }
    
    /**
     * Startet einen Thread, um Bilder asynchron zu laden.
     */
    
    public void startThreadLoadImages() {
        MemoryImageLoaderTask task = new MemoryImageLoaderTask();
        task.setOnSucceeded( x -> {
            Platform.runLater(()-> storedImages = task.getValue());
        });
        new Thread(task).start();
    }
    
    /**
     * Die Hauptmethode, die die JavaFX-Anwendung startet.
     * @param args Die Eingabeparameter für die Anwendung.
     */

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Erlaubt dem Benutzer, ein Spiel auszuwählen und startet das ausgewählte Spiel.
     */

    public void selectGame() {
        int imageWidth = 150;
        Text txt = new Text("Spiel auswählen");
        txt.setFont(new Font(100));
        txt.setFill(Color.WHITE);
        VBox titleHolder = new VBox();
        titleHolder.setAlignment(Pos.TOP_CENTER);
        titleHolder.getChildren().addAll(txt);
        ImageView game1PreviewView = preview1;
        Button game1Button = new Button("Memory");
        game1Button.setMinSize(100, 30);
        //game1Button.setStyle("-fx-background-color: #ff0000;");
        game1Button.setOnAction(x -> startMemory());
        game1PreviewView.setFitWidth(imageWidth);
        game1PreviewView.setPreserveRatio(true);
        VBox game1Container = new VBox(10);
        game1Container.setAlignment(Pos.CENTER);
        game1Container.getChildren().addAll(game1PreviewView,game1Button);
        ImageView game2PreviewView = preview2;
        Button game2Button = new Button("VirusSweeper");
        game2Button.setMinSize(100, 30);
        game2Button.setOnAction(x -> {AudioManager.playSoundEffect();
            startVirusSweeper();AudioManager.stopBackGroundAudio();
            });
        game2PreviewView.setFitWidth(imageWidth);
        game2PreviewView.setPreserveRatio(true);
        VBox game2Container = new VBox(10);
        game2Container.setAlignment(Pos.CENTER);
        game2Container.getChildren().addAll(game2PreviewView,game2Button);
        ImageView game3PreviewView = preview3;
        Button game3Button = new Button("Snake");
        game3Button.setMinSize(100, 30);
        game3PreviewView.setFitWidth(imageWidth);
        game3PreviewView.setPreserveRatio(true);
        game3Button.setOnAction(x-> startSnake());
        VBox game3Container = new VBox(10);
        game3Container.setAlignment(Pos.CENTER);
        game3Container.getChildren().addAll(game3PreviewView,game3Button);
        ImageView game4PreviewView = preview4;
        Button game4Button = new Button("Labyrinth");
        game4Button.setMinSize(100, 30);
        game4PreviewView.setFitWidth(imageWidth);
        game4PreviewView.setPreserveRatio(true);
        game4Button.setOnAction(x -> startLabyrinth());
        VBox game4Container = new VBox(10);
        game4Container.setAlignment(Pos.CENTER);
        game4Container.getChildren().addAll(game4PreviewView,game4Button);
        ImageView game5PreviewView = preview5;
        Button game5Button = new Button("JumpRun");
        game5Button.setMinSize(100, 30);
        game5PreviewView.setFitWidth(imageWidth);
        game5PreviewView.setPreserveRatio(true);
        game5Button.setOnAction( x-> startJumpRun());
        VBox game5Container = new VBox(10);
        game5Container.setAlignment(Pos.CENTER);
        game5Container.getChildren().addAll(game5PreviewView,game5Button);
        ImageView game6PreviewView = preview6; //TODO bild mit  1024x1024
        Button game6Button = new Button("Quiz");
        game6Button.setMinSize(100, 30);
        game6PreviewView.setFitWidth(imageWidth);
        game6PreviewView.setPreserveRatio(true);
        game6Button.setTranslateY(5);
        game6Button.setOnAction(x -> {startQuiz();
        AudioManager.stopBackGroundAudio();
        });
        VBox game6Container = new VBox(10);
        game6Container.setAlignment(Pos.CENTER);
        game6Container.getChildren().addAll(game6PreviewView,game6Button);
        
        HBox gamesContainer1 = new HBox(20);
        gamesContainer1.setAlignment(Pos.CENTER); 
        gamesContainer1.getChildren().addAll(game1Container, game2Container, game3Container);
        
        HBox gamesContainer2 = new HBox(20);
        gamesContainer2.setAlignment(Pos.CENTER); 
        gamesContainer2.getChildren().addAll(game4Container, game5Container,game6Container);
        
        Button backbutton = new Button("Zurück");
        backbutton.setOnAction(x -> backToMenu());
        backbutton.setTranslateY(275);
        VBox gamesVBox = new VBox(20);
        gamesVBox.setAlignment(Pos.CENTER);
        gamesVBox.getChildren().addAll(gamesContainer1, gamesContainer2);
        gamesVBox.setTranslateY(50);
        StackPane selectgame = new StackPane();
        selectgame.getChildren().addAll(titleHolder,gamesVBox,backbutton);
        selectgame.setBackground(defaultBackGround);
        Scene gamescene = new Scene(selectgame,WINDOW_WIDTH,WINDOW_HEIGHT);
        primaryStage.setScene(gamescene);
    }
    
    /**
     * Wechselt zur Einstellungsansicht für das Spiel.
     */
    
    public void selectSettings() {
        Scene previosScene = primaryStage.getScene();
        AudioManager.playSoundEffect();
        SettingsManager settingsManager = SettingsManager.getInstance(primaryStage, previosScene, defaultBackGround, WINDOW_WIDTH, WINDOW_HEIGHT);
        settingsManager.setPreviosScene(previosScene);
        primaryStage.setScene(settingsManager.getSettingsScene());
    }
    
    /**
     * Zeigt das Hauptmenü an.
     */
    
    public void backToMenu() {
        AudioManager.playSoundEffect();
        AudioManager.continueBackGroundAudio();
        createMenu();
        primaryStage.setScene(mainscene);
    }
    
    /**
     * Erstellt die Hauptmenüszene mit verschiedenen Spieloptionen.
     */
    
    public void createMenu() {
        stackpane.getChildren().clear();
        Text title = new Text("Gruppe 7 Game");
        title.setFont(new Font(100));
        title.setFill(Color.WHITE);
        Button startButton = new Button("Spiel auswählen");
        startButton.setMinSize(140, 50);
        Button settingsButton = new Button("Einstellungen");
        settingsButton.setMinSize(140, 50);
        Button exitButton = new Button("Beenden");
        exitButton.setMinSize(140, 50);
        startButton.setOnAction(x -> {selectGame();
            AudioManager.playSoundEffect();});
        settingsButton.setOnAction(x -> selectSettings());
        exitButton.setOnAction(x -> System.exit(1));
        VBox buttons = new VBox(15);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(startButton, settingsButton, exitButton);
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(title);
        stackpane.setAlignment(Pos.CENTER);
        stackpane.setBackground(defaultBackGround);
        stackpane.getChildren().addAll(vbox, buttons);
    }
    /**
     * Startet das ausgewählte Memory-Spiel mit der gewählten Schwierigkeitsstufe.
     */
    
    public void startMemory() {
        AudioManager.playSoundEffect();
        selectedDifficulty = SettingsManager.getDifficulty();
        newMemoryGame = Memory.getInstance(storedImages,defaultBackGround,WINDOW_WIDTH,WINDOW_HEIGHT,this,selectedDifficulty);
        primaryStage.setScene(newMemoryGame.getScene());
        primaryStage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                newMemoryGame.pauseTimer();
                newMemoryGame.stopAnimation();
                showPauseScreen();
            }
        });
    }
    
    /**
     * Zeigt den Game Over-Bildschirm mit Optionen zum Neustarten, zum Zurückkehren zum Menü oder zum Beenden des Spiels an.
     */
    
    public void showGameOver() {
        Button restartButton = new Button("Neustarten");
        Button menuButton = new Button("Zum Menü");
        Button exitButton = new Button("Beenden");
        restartButton.setOnAction(e -> {
        Memory.resetInstance();
        startMemory();});
        menuButton.setOnAction(e -> primaryStage.setScene(mainscene));
        exitButton.setOnAction(e -> System.exit(0));
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        //overlayRoot.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        buttonBox.getChildren().addAll(restartButton, menuButton, exitButton);
        buttonBox.setBackground(gameOverBackGround);
        Scene gameOver = new Scene(buttonBox, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(gameOver);
    }
    /**
     * Zeigt den Pausenbildschirm mit Optionen zum Fortfahren, zu den Einstellungen oder zurück zum Hauptmenü an.
     */
    
    public void showPauseScreen() {
        Text title = new Text("Pause");
        title.setFont(new Font(150));
        title.setFill(Color.BLUE);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().addAll(title);
        titleContainer.setAlignment(Pos.TOP_CENTER);
        Button weiterButton = new Button("Weiter");
        weiterButton.setMinSize(100, 30);
        weiterButton.setOnAction( x -> {
            primaryStage.setScene(newMemoryGame.getScene());
            newMemoryGame.resumeTimer();
            newMemoryGame.continueAnimation();
        });
        Button settingsButton = new Button("Settings");
        settingsButton.setMinSize(100, 30);
        settingsButton.setOnAction(x -> selectSettings());
        Button backToMenu = new Button("Hauptmenü");
        backToMenu.setMinSize(100, 30);
        backToMenu.setOnAction(x -> {
            primaryStage.setScene(mainscene);
            Memory.resetInstance();
            });
        VBox buttonsContainer = new VBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.getChildren().addAll(weiterButton,settingsButton,backToMenu);
        StackPane stack = new StackPane();
        stack.setBackground(defaultBackGround);
        stack.getChildren().addAll(titleContainer,buttonsContainer);
        Scene pauseScreen = new Scene(stack,WINDOW_WIDTH,WINDOW_HEIGHT);
        primaryStage.setScene(pauseScreen);
    }
    
    /**
     * Methode, um das Quizspiel zu starten.
     */
    
    public void startQuiz() {
        Main main = new Main();
        main.start(primaryStage);
    }
    
    /**
     * Methode, um die Singleton-Instanz von GUIController abzurufen.
     * @return Die bestehende oder eine neu erstellte Instanz von GUIController
     */
    
    public static GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
            System.out.println("Instanz für GUIController erstellt");
        }
        return instance;
    }
    /**
     * Methode, um zur Hauptszene zurückzukehren.
     */
    public void ReturnToMainScene() {
       primaryStage.setScene(mainscene);
    }
    
    /**
     * @return the imageview
     */

    public static ImageView getImageView() {
        return imageView;
    }

    /**
     * @return the imageview2
     */
    public static ImageView getImageview2() {
        return imageview2;
    }
  
    /**
     * Erzeugt eine neue Scene, die beim Verlieren von VirusSweeper angezeigt wird.
     * 
     * @author Marc Pauluhn
     */
    public void VirusSweeperSpielVerlorenBildschirm() {
        // Zeigt text "Du hast Verloren" an.
        Label text = new Label("Du hast Verloren");
        text.setFont(new Font(50));
        text.setTextFill(Color.WHITE);
        // Einen Button zum Neustarten
        Button neustartButton = new Button("Nochmal Versuchen");
        neustartButton.setOnAction(e -> VirusSweeperZuruecksetzenDesSpielfelds());
        neustartButton.setMinSize(150, 40);
        // Einen Button zum zurückkehren ins Menü
        Button menuButton = new Button("Zur Spielesammlung");
        menuButton.setOnAction(e -> {VirusSweeperZuruecksetzenDesSpielfelds();AudioManager.continueBackGroundAudio();primaryStage.setScene(mainscene);});
        menuButton.setMinSize(150, 40);
        // Einen Button zum Beenden
        Button beendenButton = new Button("Beenden");
        beendenButton.setOnAction(e -> System.exit(0));
        beendenButton.setMinSize(150, 40);
        VBox buttonsUndText = new VBox(15);
        buttonsUndText.setAlignment(Pos.CENTER);
        buttonsUndText.getChildren().addAll(text, neustartButton, menuButton, beendenButton);
        buttonsUndText.setBackground(VirusSweeperVerlorenHintergrund);
        Scene spielVerloren = new Scene(buttonsUndText, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(spielVerloren);
    }

    /**
     * Erzeugt eine neue Scene, die beim Gewinnen von VirusSweeper angezeigt wird.
     * 
     * @author Marc Pauluhn
     */
    public void VirusSweeperSpielGewonnenBildschirm() {
        // Zeigt die zeit in Form "Zeit: zahl" an.
        Label zeit = new Label("Zeit: " + newVirusSweeperGame.zeitAnzeige.getZeit());
        zeit.setFont(new Font(50));
        // Einen Button zum Neustarten
        Button neustartButton = new Button("Nochmal Versuchen");
        neustartButton.setOnAction(e -> VirusSweeperZuruecksetzenDesSpielfelds());
        neustartButton.setMinSize(150, 40);
        // Einen Button zum zurückkehren ins Menü
        Button menuButton = new Button("Zur Spielesammlung");
        menuButton.setOnAction(e -> {VirusSweeperZuruecksetzenDesSpielfelds();primaryStage.setScene(mainscene);AudioManager.continueBackGroundAudio();});
        menuButton.setMinSize(150, 40);
        // Einen Button zum Beenden
        Button beendenButton = new Button("Beenden");
        beendenButton.setOnAction(e -> System.exit(0));
        beendenButton.setMinSize(150, 40);
        VBox buttonsUndText = new VBox(15);
        buttonsUndText.setAlignment(Pos.CENTER);
        buttonsUndText.getChildren().addAll(zeit, neustartButton, menuButton, beendenButton);
        buttonsUndText.setBackground(VirusSweeperGewonnenHintergrund);
        Scene spielGewonnen = new Scene(buttonsUndText, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(spielGewonnen);
    }
    /**
     * Startet VirusSweeper Spiel.
     * 
     * @author Marc Pauluhn
     */
    public void startVirusSweeper() {
        // AudioManager.playSoundEffect();
        newVirusSweeperGame = VirusSweeper.getInstance(WINDOW_WIDTH, WINDOW_HEIGHT, this, 0);
        primaryStage.setScene(newVirusSweeperGame.getScene());
    }

    /**
     * Setzt das VirusSweeper Spiel zurück, mit der vorher ausgewählten
     * SchwierigkeitsStufe.
     * 
     * @author Marc Pauluhn
     */
    public void VirusSweeperZuruecksetzenDesSpielfelds() {
        int schwierigkeitsstufe = newVirusSweeperGame.getSchwierigkeit();
        VirusSweeper.resetInstance();
        newVirusSweeperGame = VirusSweeper.getInstance(WINDOW_WIDTH, WINDOW_HEIGHT, this, schwierigkeitsstufe);
        primaryStage.setScene(newVirusSweeperGame.getScene());

    }

    /**
     * Setzt die Scene auf eine SchwierigkeitsAuswahl für VirusSweeper.
     * 
     * @author Marc Pauluhn
     */
    public void VirusSweeperSchwierigkeitsAuswahl() {
        // Erstelle die Buttons für Anfänger, Fortgeschritten und Experte
        Button btnAnfaenger = VirusSweeperCreateSchwierigkeitsButton("Anfänger", 0);
        btnAnfaenger.setMinSize(300, 50);
        Button btnFortgeschritten = VirusSweeperCreateSchwierigkeitsButton("Fortgeschritten", 1);
        btnFortgeschritten.setMinSize(300, 50);
        Button btnExperte = VirusSweeperCreateSchwierigkeitsButton("Experte", 2);
        btnExperte.setMinSize(300, 50);
        // Fügt die Buttons zu einer HBox hinzu
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(btnAnfaenger, btnFortgeschritten, btnExperte);
        // Erstelle eine Scene für die Schwierigkeitsauswahl
        Scene schwierigkeitsScene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Setze die Scene auf die primaryStage
        primaryStage.setScene(schwierigkeitsScene);
    }

    /**
     * Erstellt ein Button mit gegebenen Namen und schwierigkeitsstufe.
     * 
     * @param text                der angezeigt werden soll
     * @param schwierigkeitsstufe
     * @return gibt buttonObjekt zurück
     * @author Marc Pauluhn
     */
    private Button VirusSweeperCreateSchwierigkeitsButton(String text, int schwierigkeitsstufe) {
        Button button = new Button(text);
        button.setOnAction(event -> {
            VirusSweeper.resetInstance();
            // Erstellt neue Instance mit gegebener schwierigkeitsstufe.
            newVirusSweeperGame = VirusSweeper.getInstance(WINDOW_WIDTH, WINDOW_HEIGHT, this, schwierigkeitsstufe);
            primaryStage.setScene(newVirusSweeperGame.getScene());
        });

        return button;
    }

    /**
     * @return gibt VirusSweeperSpielVerlorenHintergrund zurück
     * @author Marc Pauluhn
     */
    public static Background VirusSweeperGetSpielVerlorenHintergrund() {
        // Das Bild ist ein selbst erstellete Bild.
        Image bild = new Image("file:images/VirusSweeper/VirusVerlorenScreen.png");
        BackgroundImage SpielVerlorenHintergrundBild = new BackgroundImage(bild, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(WINDOW_WIDTH, WINDOW_HEIGHT, false, false, false, false));
        Background SpielVerlorenHintergrund = new Background(SpielVerlorenHintergrundBild);
        return SpielVerlorenHintergrund;

    }

    /**
     * @return gibt VirusSweeperSpielGewonnenHintergrund zurück
     * @author Marc Pauluhn
     */
    public static Background VirusSweeperSpielGewonnenHintergrund() {
        // Das Bild ist ein selbst erstellete Bild.
        Image bild = new Image("file:images/VirusSweeper/VirusGewonnenScreen.png");
        BackgroundImage SpielGewonnenHintergrundBild = new BackgroundImage(bild, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(WINDOW_WIDTH, WINDOW_HEIGHT, false, false, false, false));
        Background SpielGewonnenHintergrund = new Background(SpielGewonnenHintergrundBild);
        return SpielGewonnenHintergrund;
    }

    /**
     * Dies ist ein PopUpFenster, welches durch das VirusSweeper Easteregg
     * Getriggert wird. welches den Text "Weniger Zurücksetzen, mehr Spielen"
     * anzeigt.
     * 
     * @author Marc Pauluhn
     */
    public static void VirusSweeperEasterEggGui() {
        Stage easterEggStage = new Stage();
        easterEggStage.initModality(Modality.APPLICATION_MODAL);
        easterEggStage.setTitle("EasterEgg");
        VBox vbox = new VBox();
        Label label = new Label("Weniger Zurücksetzen, mehr Spielen");
        label.setFont(new Font(15));
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(300, 100);
        vbox.getChildren().add(label);
        Scene scene = new Scene(vbox);
        easterEggStage.setScene(scene);
        easterEggStage.setWidth(label.getPrefWidth());
        easterEggStage.setHeight(label.getPrefHeight());
        easterEggStage.setResizable(false);
        easterEggStage.showAndWait();
    }

    /**
     * Dies ist ein Neues Fenster, welches eine beschreibung für das VirusSweeper
     * Spiel anzeigt.
     * 
     * @author Marc Pauluhn
     */
    public void VirusSweeperBeschreibungGui() {
        Stage beschreibungStage = new Stage();
        beschreibungStage.setTitle("VirusSweeper-Spiel-Beschreibung");
        String beschreibung = "Ziel: \n" + "Alle Festplatten aufdecken, die keinen Virus haben.\n" + "\n"
                + "Rechtsklick auf nicht aufgedeckte Festplatte: \n"
                + "Markieren und die Markierung einer Festplatte aufheben.\n" + "\n"
                + "Linksklick auf noch nicht aufgedeckte Festplatte:\n"
                + "Dann wird eine Zahl angezeigt. Diese Zahl gibt Informationen darüber,  \n"
                + "wie viele Viren im Umkreis der Festplatte sind.\n" + "\n" + "Linksklick auf Festplatte mit Zahl:\n"
                + "Wenn die angezeigte Zahl gleich der Anzahl der markierten Felder ist,\n"
                + "werden alle Felder im Umkreis aufgedeckt.\n" + "\n" + "Linksklick auf Festplatte mit Virus:\n"
                + "Spiel verloren.\n";
        VBox vbox = new VBox();
        Label beschreibungText = new Label(beschreibung);
        beschreibungText.setFont(new Font(15));
        beschreibungText.setAlignment(Pos.CENTER);
        beschreibungText.setPrefSize(500, 400);
        vbox.getChildren().add(beschreibungText);
        Scene scene = new Scene(vbox);
        beschreibungStage.setScene(scene);
        beschreibungStage.setWidth(beschreibungText.getPrefWidth());
        beschreibungStage.setHeight(beschreibungText.getPrefHeight());
        beschreibungStage.setResizable(false);
        beschreibungStage.show();
    }
    public void startSnake() {
        AudioManager.stopBackGroundAudio();
        MainController.createInstance(primaryStage).start();
        
    }
    
    public void startJumpRun() {
        JumpAndRunMenu jp = new JumpAndRunMenu();
        AudioManager.stopBackGroundAudio();
        jp.start(primaryStage);
    }
    public void startLabyrinth() {
        AudioManager.stopBackGroundAudio();
        Labyrinth.setLabyrinth(difficulty, diff, musicstat, media, mediaPlayer, buttonTextColor, buttonFont, backgroundImage, background);
        Labyrinth.start(primaryStage);
    }
}
