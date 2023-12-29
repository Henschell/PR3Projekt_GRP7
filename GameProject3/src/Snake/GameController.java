package Snake;
	

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;



public class GameController{
    private MainController mainController;
    private FoodController foodController;
    private SnakeController snakeController;
    private ObstacleController obstacleController;
    private Spielfeld spielfeld;
    private Display display;
    private boolean gameOver = false;
    private boolean obstacles;
    private boolean badFoodVisible;
    private int score;
    private int counter;
    private Label scoreLabel;
    private Scene gameScene;
    private Timeline timeline;
    private Image backgroundImage;
    private AudioClip biteSound;
    
    public GameController(MainController mainController) {
        initializeGame(mainController);
    }
	
	//GameLoop
	public void run() {
	    if(gameOver) {
	        display.paintGameOver(spielfeld.getWidth(), spielfeld.getHeight());
	        timeline.stop();
	        mainController.gameOver(score);
	    }
	    if(counter == (int)(1.0/snakeController.getSpeed())) {
	        foodController.generateBadFood(snakeController, obstacleController.getObstacleList());
            counter = -1;
	    }
	    if(counter != -1) {
	        ++counter;
	    } 

	    scoreLabel.setText("Score = "+score);
	    display.paintSpielfeld(spielfeld.getRows(), spielfeld.getColumns(), spielfeld.getSquare_Size());
	    if(obstacles) {
	        display.paintObstacles(obstacleController, spielfeld.getSquare_Size());
	    }
	    display.paintWholeSnake(snakeController.getSnakeHead(), snakeController.getSnakeLength(), spielfeld.getSquare_Size());
	    display.paintGameObject(foodController.getFood(), spielfeld.getSquare_Size());
	    if(counter == -1 && badFoodVisible) {
	        display.paintGameObject(foodController.getBadFood(), spielfeld.getSquare_Size());
	    }

	    snakeController.move();
        gameOverUpdate();
        eatFood();
	}
	/**
	 * Übenimmt gameOver-boolean von dem SnakeController
	 */
	public void gameOverUpdate() {
	    gameOver = snakeController.getGameOverGefunden();
	}
	/**
	 * Wenn der SnakeController anzeigt, dass ein Food gefunden wurde, wird der Bite-Sound
	 * abgespielt, ein neues Food wird kreiert und die alte Position des Foods auf dem 
	 * Spielfeld wird freigegeben
	 * Spielt der Spieler mit Hindernissen, wird ein neues Hindernis erstellt
	 */
	public void eatFood() {
	    if(snakeController.getFoodGefunden()) {
	        biteSound.play();
	        createFood();
	        counter = 0;
	        
	        snakeController.addWholeSnakePart();
            ++score;
            if(obstacles) {
                obstacleController.generateObstacle(spielfeld.getRows(), spielfeld.getColumns(), snakeController, spielfeld);
            }
	    }
	}
	public void createFood() {
	    spielfeld.changeValueInMatrix((int)foodController.getFood().getX(), (int)foodController.getFood().getY(), 0);
        spielfeld.changeValueInMatrix((int)foodController.getBadFood().getX(), (int)foodController.getBadFood().getY(), 0);
        foodController.generateFood(snakeController, obstacleController.getObstacleList());
	}

	public void obstacleCheck() {
	    for(GameObject o: obstacleController.getObstacleList()) {
	        if(snakeController.getSnakeHead().samePosition(o)) {
	            gameOver = true;
	        }
	    }
	}
	
	public void initializeScene() {
	    
	    HBox root = new HBox();
        scoreLabel = new Label("Score = ");
        scoreLabel.setPrefSize(100, 10);
        root.getChildren().addAll(scoreLabel, display.getCanvas());
        BackgroundSize bs = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage bgi = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.CENTER, bs);
        Background background = new Background(bgi);
        root.setBackground(background);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("gamescene.css").toExternalForm());
        this.gameScene = scene;
	}
	
	public void initializeGame(MainController mainController) {
	    setMainController(mainController);
	    //String srcBite = getClass().getResource("Audio/Snake/happs.wav").toString();
	    biteSound = new AudioClip(new File("Audio/Snake/happs.wav").toURI().toString());
        spielfeld = new Spielfeld();
        Image foodImage = new Image("file:images/snake/virus.png");
        Image badFoodImage = new Image("file:images/snake/malware.png");
        foodController = new FoodController(foodImage, badFoodImage, spielfeld);
        Image snakeHeadImage = new Image("file:images/snake/snakeHead.png");
        snakeController = new SnakeController(10, 11, snakeHeadImage, Direction.UP, Direction.UP, this.spielfeld);
        Image obstacle = new Image("file:images/snake/stein.png");
        obstacleController = new ObstacleController(obstacle);
        
        backgroundImage = new Image("file:images/snake/hackercode.jpg");
        
        display = new Display(this, spielfeld.getWidth(), spielfeld.getHeight());
        

        initializeScene();
        
        EventHandler<KeyEvent> handler = (KeyEvent k) -> {
            
            KeyCode code = k.getCode();
            Direction current = snakeController.getSnakeHead().getCurrentDirection();
            if(code == KeyCode.RIGHT || code == KeyCode.D) {
                if(current != Direction.RIGHT && current != Direction.LEFT) {
                    snakeController.getSnakeHead().setPreferredDirection(Direction.RIGHT);
                }
            } else if(code == KeyCode.LEFT || code == KeyCode.A) {
                if(current != Direction.LEFT && current != Direction.RIGHT) {
                    snakeController.getSnakeHead().setPreferredDirection(Direction.LEFT);
                }
            } else if(code == KeyCode.UP || code == KeyCode.W) {
                if(current != Direction.UP && current != Direction.DOWN) {
                    snakeController.getSnakeHead().setPreferredDirection(Direction.UP);
                }
            } else if(code == KeyCode.DOWN || code == KeyCode.S) {
                if(current != Direction.DOWN && current != Direction.UP) {
                    snakeController.getSnakeHead().setPreferredDirection(Direction.DOWN);
                }
            } else if(code == KeyCode.ESCAPE || code == KeyCode.BACK_SPACE) {
                timeline.stop();
                mainController.switchToMenuScene();
            } else if(code == KeyCode.N) {
                createFood();
            }
        };
        gameScene.setOnKeyPressed(handler);
        
        Duration d = Duration.millis(20);
        KeyFrame keyFrame = new KeyFrame(d, e-> run());
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
	}
	
	public void startGame() {
	    spielfeld.initializeArray();
	    snakeController.clear();
	    snakeController.getReady(10, 10);
	    obstacleController.clear();
	    
	    foodController.generateFood(snakeController, obstacleController.getObstacleList());
	    foodController.generateBadFood(snakeController, obstacleController.getObstacleList());
	    display.paintSpielfeld(spielfeld.getRows(), spielfeld.getColumns(), spielfeld.getSquare_Size());
        display.paintGameObject(foodController.getFood(), spielfeld.getSquare_Size());
        display.paintWholeSnake(snakeController.getSnakeHead(), snakeController.getSnakeLength(), spielfeld.getSquare_Size());
        score = 0;
        counter = -1;
	    
        timeline.play();
        
        
	}
	public Scene getScene() {
	    return gameScene;
	}

	public void setMainController(MainController mainController) {
	    this.mainController = mainController;
	}
	public void setObstacles(boolean obstaclesOn) {
	    this.obstacles = obstaclesOn;
	}
	public void setBadFoodVisible(boolean badFoodVisible) {
	    this.badFoodVisible = badFoodVisible;
	}
}
