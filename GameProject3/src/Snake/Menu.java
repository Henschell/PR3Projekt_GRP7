package Snake;

import java.io.File;
import java.util.Random;

import application.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;

public class Menu{
    
    private Scene scene;
    private MainController mainController;
    private GameController gameController;
    private Image image;
    private Media media;
    
    public Menu(MainController mainController, GameController gameController) {
        this.media = new Media(new File("Audio/Snake/background.mp3").toURI().toString());
        this.mainController = mainController;
        this.gameController = gameController;
        image = new Image("file:images/snake/glowGreen.jpg");
        if(image == null) {
            System.out.println("Bild wird nicht geladen");
        }
        initializeMenu();
    }
    private void initializeMenu() {
       
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(25);
        
        Label label = new Label();
        label.setText("Welcome to CyberSnake!");
        
        Button button = new Button("Play!");
        CheckBox obstacles = new CheckBox("Obstacles");
        CheckBox badFoodVisible = new CheckBox("Bad Food is visible");
        CheckBox surpriseMe = new CheckBox("Surprise me!");
        
        obstacles.setSelected(true);
        badFoodVisible.setSelected(true);
        surpriseMe.setSelected(false);
        
        Button manualButton = new Button("QUICK MANUAL");
        Button back = new Button ();
        Image backImage = new Image("file:images/snake/back.png");
        ImageView imageview = new ImageView(backImage);
        imageview.setFitHeight(20);
        imageview.setFitWidth(20);
        back.setGraphic(imageview);
        back.setPrefSize(20, 20);
        VBox backBox = new VBox();
        backBox.getChildren().add(back);
        backBox.setAlignment(Pos.TOP_LEFT);
        
        root.getChildren().addAll(backBox, label, obstacles, badFoodVisible, surpriseMe, manualButton, button);
        VBox.setVgrow(button, Priority.ALWAYS);
        root.setAlignment(Pos.CENTER);
        
        EventHandler<ActionEvent> manualHandler = (ActionEvent event) -> {
            mainController.showManual();
        };
        EventHandler<ActionEvent> backHandler = (ActionEvent e) -> {
            mainController.stopAudio();
            GUIController.getInstance().backToMenu();
        };
        EventHandler<ActionEvent> startHandler = (ActionEvent event) -> {
            if(!surpriseMe.isSelected()) {
                gameController.setObstacles(obstacles.isSelected());
                gameController.setBadFoodVisible(badFoodVisible.isSelected());
            } else {
                Random random = new Random();
                int obstacle = random.nextInt(2);
                int visible = random.nextInt(2);
                gameController.setObstacles(obstacle == 1);
                gameController.setBadFoodVisible(visible == 1);
            }
            mainController.startTheGame();
        };
        back.setOnAction(backHandler);
        button.setOnAction(startHandler);
        manualButton.setOnAction(manualHandler);
        BackgroundSize bs = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.CENTER, bs);
        Background bg = new Background(background);
        root.setBackground(bg);
        
        this.scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
    }  
    public Media getMedia() {
        return media;
    }
    
    public Scene getScene() {
        return scene;
    }
    
}
