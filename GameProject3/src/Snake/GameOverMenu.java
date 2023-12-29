package Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameOverMenu {
    private Scene gameOverScene;
    private MainController mainController;
    
    public GameOverMenu(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void initializeGameOverScene(int score) {
        Label label = new Label("GAME OVER!");
        Label scoreLabel = new Label("YOUR SCORE: "+score);
        Button back = new Button("BACK TO MENU");
        Button again = new Button("PLAY AGAIN");
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(back, again);
        buttonBox.setSpacing(10);
        VBox root = new VBox();
        root.getChildren().addAll(label, scoreLabel, buttonBox);
        root.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        
        EventHandler<ActionEvent> backToMenu = (ActionEvent event) -> {
            mainController.switchToMenuScene();
        };
        EventHandler<ActionEvent> startAgain = (ActionEvent event) -> {
            mainController.startTheGame();
        };
        
        back.setOnAction(backToMenu);
        again.setOnAction(startAgain);
        
        gameOverScene = new Scene(root, 400, 300);
        gameOverScene.getStylesheets().add(getClass().getResource("gameOverStyle.css").toExternalForm());
    }
    public Scene getScene(int score) {
        initializeGameOverScene(score);
        return gameOverScene;
    }

}
