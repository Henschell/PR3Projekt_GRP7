package Snake;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainController {
    private Stage primaryStage;
    private GameController gameController;
    private Menu menu;
    private GameOverMenu gameOverMenu;
    private Stage gameOverStage;
    private Stage manualStage;
    private MediaPlayer menuPlayer;
    private Manual manual;
    private static MainController instance;
    

    private MainController(Stage primaryStage) {
        gameOverStage = new Stage();
        manualStage = new Stage();
        
        initialize(primaryStage);
        menuPlayer = new MediaPlayer(menu.getMedia());
        
    }
    public void start() {
        switchToMenuScene();
        primaryStage.setTitle("CyberSnake!");
        primaryStage.show();
        EventHandler<WindowEvent> handler = (WindowEvent event) -> {
            if(gameOverStage != null) {
                gameOverStage.close();
            }
            if(manualStage != null) {
                manualStage.close();
            }
        };
        primaryStage.setOnCloseRequest(handler);
    }
    public void initialize(Stage stage){
        this.primaryStage = stage;
        gameController = new GameController(this);
        menu = new Menu(this, this.gameController);
        gameOverMenu = new GameOverMenu(this);
        manual = new Manual();
        //gameController.initializeGame(this);
        
    }
    
    private void switchScene(Scene newScene) {
        gameOverStage.close();
        primaryStage.setScene(newScene);
    }
    
    public void switchToGameScene() {
        switchScene(gameController.getScene());
    }
    public void switchToMenuScene() {
        menuPlayer.play();
        switchScene(menu.getScene());
    }
    public void startTheGame() {
        menuPlayer.stop();
        switchToGameScene();
        gameController.startGame();
    }
    public void gameOver(int score) {
        gameOverStage.setScene(gameOverMenu.getScene(score));
        gameOverStage.show();
    }
    public void showManual() {
        manualStage.setScene(manual.getScene());
        manualStage.show();
    }
    public static void resetInstance() {
        instance = null;
    }
    public static MainController createInstance(Stage primaryStage) {
        if( instance == null) {
            instance = new MainController(primaryStage);
        } 
        return instance;
    }
    public void stopAudio() {
        menuPlayer.stop();
    }
    
}
