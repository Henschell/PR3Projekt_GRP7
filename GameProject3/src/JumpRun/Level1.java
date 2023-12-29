package JumpRun;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level1 extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 65;
    private static final int GROUND_HEIGHT = 10;
    private static final double PLAYER_SPEED = 8.0;
    static Double PLAYER_SPEED_HORIZONTAL = 2.0;
    static int orientation = 0;


    private Pane root;
    private Rectangle player;
    private List<Rectangle> blocks;
    private List<Rectangle> objects;
    private int playerDirection = 1; // 1 für rechts, -1 für links

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        root = new Pane();
        blocks = new ArrayList<>();
        objects = new ArrayList<>();

        // Hintergrundbild laden
        Image backgroundImage = new Image("file:images/JumpRun/level1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(WIDTH);
        backgroundImageView.setFitHeight(HEIGHT);
        root.getChildren().add(backgroundImageView);

        // Spielfigur laden
        Image playerRightImage = new Image("file:images/JumpRun/player_right.png");
        Image playerLeftImage = new Image("file:images/JumpRun/player_left.png");

        player = new Rectangle(PLAYER_SIZE, PLAYER_SIZE);
        player.setFill(new ImagePattern(playerRightImage));
        player.setTranslateY(HEIGHT - GROUND_HEIGHT - PLAYER_SIZE);
        root.getChildren().add(player);


        Scale playerScale = new Scale(playerDirection, 1);
        player.getTransforms().add(playerScale);

        // Blöcke erstellen und positionieren
        createBlock(650, HEIGHT - GROUND_HEIGHT - 100);
        createBlock(550, HEIGHT - GROUND_HEIGHT - 180);
        createBlock(450, HEIGHT - GROUND_HEIGHT - 260);
        createBlock(350, HEIGHT - GROUND_HEIGHT - 350);
        createBlock(320, HEIGHT - GROUND_HEIGHT - 350);
        createBlock(270, HEIGHT - GROUND_HEIGHT - 350);
        createBlock(220, HEIGHT - GROUND_HEIGHT - 350);
        createBlock(120, HEIGHT - GROUND_HEIGHT - 450);
        createBlock(120, HEIGHT - GROUND_HEIGHT - 200);

        // Objekte erstellen und positionieren
        createObject(300, HEIGHT - GROUND_HEIGHT - 150);
        createObject(655, HEIGHT - GROUND_HEIGHT - 150);
        createObject(455, HEIGHT - GROUND_HEIGHT - 310);
        createObject(120, HEIGHT - GROUND_HEIGHT - 500);
        createObject(30, HEIGHT - GROUND_HEIGHT - 320);

        // Hinzufügen von Buttons mit Bildern
        Button optionsButton = createButtonWithImage("file:images/JumpRun/exit.png", e -> handleBeenden());
        optionsButton.setLayoutX(735);
        optionsButton.setLayoutY(15);

        Button backButton = createButtonWithImage("file:images/JumpRun/back.png", e -> handleBackToMenu(primaryStage));
        backButton.setLayoutX(680);
        backButton.setLayoutY(15);

        root.getChildren().addAll(optionsButton, backButton);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            // Spielerbewegung und Kollision mit Blöcken und Fensterrand
            if (player.getTranslateY() < HEIGHT - GROUND_HEIGHT - PLAYER_SIZE) {
                boolean isOnBlock = false;
                for (Rectangle block : blocks) {
                    if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
                        // Spieler steht auf einem Block, daher kann er nicht fallen
                        isOnBlock = true;
                        break;
                    }
                }
                if (!isOnBlock) {
                    player.setTranslateY(player.getTranslateY() + 5);
                }
            }

            // Kollisionsprüfung für Blöcke und Spieler
            for (Rectangle block : blocks) {
                if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
                    // Spieler steht auf einem Block, daher kann er springen
                    if (player.getTranslateY() + PLAYER_SIZE/2 <= block.getTranslateY() &&
                            player.getTranslateY() + PLAYER_SIZE >= block.getTranslateY() - 5) {
                        player.setTranslateY(block.getTranslateY() - PLAYER_SIZE);
                        break;
                    }
                }
            }

         // Kollisionsprüfung für Objekte und Spieler
            Iterator<Rectangle> iterator = objects.iterator();
            while (iterator.hasNext()) {
                Rectangle object = iterator.next();
                if (player.getBoundsInParent().intersects(object.getBoundsInParent())) {
                    // Spieler hat ein Objekt erreicht, entferne das Objekt
                    iterator.remove();
                    root.getChildren().remove(object);

                    // Überprüfe, ob alle Objekte gesammelt wurden
                    if (objects.isEmpty()) {
                        // Alle Objekte gesammelt, wechsle zu Level2
                        switchToLevel2();
                    }
                    break;
                }
            }
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A && player.getTranslateX() > 0) {
                playerDirection = 1; // Spieler geht nach links
                playerScale.setX(playerDirection);
                player.setFill(new ImagePattern(playerLeftImage)); // Änderung des Bildes
                player.setTranslateX(player.getTranslateX() - PLAYER_SPEED);
                orientation = 1;
            } else if (event.getCode() == KeyCode.D && player.getTranslateX() < WIDTH - PLAYER_SIZE) {
                playerDirection = -1; // Spieler geht nach rechts
                playerScale.setX(-1 * playerDirection);
                player.setFill(new ImagePattern(playerRightImage)); // Änderung des Bildes
                player.setTranslateX(player.getTranslateX() + PLAYER_SPEED);
                PLAYER_SPEED_HORIZONTAL = 2.0;
                orientation = 1;
                
            } else if (event.getCode() == KeyCode.J && orientation != 0) {
                // Springen nur erlauben, wenn sich der Spieler auf einem Block oder auf dem Boden befindet
                boolean onBlockOrGround = false;

                // Überprüfen, ob der Spieler auf einem Block steht oder den Boden erreicht hat
                for (Rectangle block : blocks) {
                    if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
                        double playerBottom = player.getTranslateY() + PLAYER_SIZE;
                        double playerTop = player.getTranslateY();
                        double blockBottom = block.getTranslateY() + block.getHeight();
                        double blockTop = block.getTranslateY();

                        // Überprüfen, ob der Spieler die Seiten des Blocks berührt
                        boolean fromLeft = player.getTranslateX() + PLAYER_SIZE >= block.getTranslateX() && player.getTranslateX() < block.getTranslateX();
                        boolean fromRight = player.getTranslateX() <= block.getTranslateX() + block.getWidth() && player.getTranslateX() + PLAYER_SIZE > block.getTranslateX() + block.getWidth();

                        if (playerBottom >= blockTop && playerTop <= blockBottom && (fromLeft || fromRight)) {
                            onBlockOrGround = true;
                            break;
                        }
                    }
                }

                // Überprüfen, ob der Spieler auf dem Boden steht
                if (player.getTranslateY() == HEIGHT - GROUND_HEIGHT - PLAYER_SIZE) {
                    onBlockOrGround = true;
                }

                if (onBlockOrGround) {
                    // Nur springen, wenn der Spieler auf einem Block oder dem Boden steht
                    Timeline jumpTimeline = new Timeline(new KeyFrame(Duration.millis(8), jumpEvent -> {
                        if (player.getTranslateY() > 0) {
                            // Spieler nach oben bewegen, solange er nicht das obere Fensterende erreicht hat
                            player.setTranslateY(player.getTranslateY() - 5);
                            
                            // Add horizontal movement during the jump
                            double newTranslateX = player.getTranslateX() - (PLAYER_SPEED_HORIZONTAL * playerDirection);
                            // Ensure the player stays within the bounds of the game window
                            newTranslateX = Math.max(0, Math.min(WIDTH - PLAYER_SIZE, newTranslateX));
                            player.setTranslateX(newTranslateX);
                        }
                        orientation = 0;
                    }));
                    jumpTimeline.setCycleCount(30); // Die Dauer des Sprungs
                    jumpTimeline.play();
                } 
                
            } else if (event.getCode() == KeyCode.J && orientation == 0) {
                // Springen nur erlauben, wenn sich der Spieler auf einem Block oder auf dem Boden befindet
                boolean onBlockOrGround = false;

                // Überprüfen, ob der Spieler auf einem Block steht oder den Boden erreicht hat
                for (Rectangle block : blocks) {
                    if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
                        double playerBottom = player.getTranslateY() + PLAYER_SIZE;
                        double playerTop = player.getTranslateY();
                        double blockBottom = block.getTranslateY() + block.getHeight();
                        double blockTop = block.getTranslateY();

                        // Überprüfen, ob der Spieler die Seiten des Blocks berührt
                        boolean fromLeft = player.getTranslateX() + PLAYER_SIZE >= block.getTranslateX() && player.getTranslateX() < block.getTranslateX();
                        boolean fromRight = player.getTranslateX() <= block.getTranslateX() + block.getWidth() && player.getTranslateX() + PLAYER_SIZE > block.getTranslateX() + block.getWidth();

                        if (playerBottom >= blockTop && playerTop <= blockBottom && (fromLeft || fromRight)) {
                            onBlockOrGround = true;
                            break;
                        }
                    }
                }

                // Überprüfen, ob der Spieler auf dem Boden steht
                if (player.getTranslateY() == HEIGHT - GROUND_HEIGHT - PLAYER_SIZE) {
                    onBlockOrGround = true;
                }

                if (onBlockOrGround) {
                    // Nur springen, wenn der Spieler auf einem Block oder dem Boden steht
                    Timeline jumpTimeline = new Timeline(new KeyFrame(Duration.millis(8), jumpEvent -> {
                        if (player.getTranslateY() > 0) {
                            // Spieler nach oben bewegen, solange er nicht das obere Fensterende erreicht hat
                            player.setTranslateY(player.getTranslateY() - 5);
                            
                            // Add horizontal movement during the jump
                            double newTranslateX = player.getTranslateX();
                            // Ensure the player stays within the bounds of the game window
                            newTranslateX = Math.max(0, Math.min(WIDTH - PLAYER_SIZE, newTranslateX));
                            player.setTranslateX(newTranslateX);
                        }
                    }));
                    jumpTimeline.setCycleCount(30); // Die Dauer des Sprungs
                    jumpTimeline.play();
                }
            }

        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Jump and Run Game");
        primaryStage.show();
    }

    private void switchToLevel2() {
        // Die Methode damit Man von Level1 zu Level2 wechseln kann, wenn der Spieler alle Objekte aufgesammelt hat.
        Level2 level2 = new Level2();
        Stage level2Stage = new Stage();
        level2.start(level2Stage);
        primaryStage.close();
            }

    // Methode zum Erstellen von Blöcken
    private void createBlock(double x, double y) {
        Rectangle block = new Rectangle(60, 20); // Breite: 100, Höhe: 20
        block.setFill(new ImagePattern(new Image("file:images/JumpRun/block.png")));
        block.setTranslateX(x);
        block.setTranslateY(y);
        root.getChildren().add(block);
        blocks.add(block);
    }

    // Methode zum Erstellen von Objekten
    private void createObject(double x, double y) {
        Rectangle object = new Rectangle(50, 50); // Breite: 50, Höhe: 50
        object.setFill(new ImagePattern(new Image("file:images/JumpRun/object.png")));
        object.setTranslateX(x);
        object.setTranslateY(y);
        root.getChildren().add(object);
        objects.add(object);
    }

    // Methode zum Behandeln der Optionen
    private void handleBeenden() {
        // Hier können Sie Aktionen für den "Optionen"-Button definieren
        primaryStage.close();
    }

    // Methode zum Zurückkehren zum Startmenü
    private void handleBackToMenu(Stage primaryStage) {
        JumpAndRunMenu menu = new JumpAndRunMenu();
        menu.start(primaryStage);
    }
    
    @SuppressWarnings("unused")
    private void changespeed(Double d) {
        if(d == 0.0) {
            d = 2.0;
        } else if(d == 2.0) {
            d = 0.0;
        }
    }

    // Hilfsmethode zum Erstellen eines Buttons mit einem Bild
    private Button createButtonWithImage(String imagePath, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30); // Passen Sie die Breite nach Bedarf an
        imageView.setFitHeight(30); // Passen Sie die Höhe nach Bedarf an

        Button button = new Button();
        button.setGraphic(imageView);
        button.setOnAction(handler);

        return button;
    }
}