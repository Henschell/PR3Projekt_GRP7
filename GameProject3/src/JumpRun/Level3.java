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
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Level3 extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 65;
    private static final int GROUND_HEIGHT = 10;
    private static final double PLAYER_SPEED = 8.0;
    static Double PLAYER_SPEED_HORIZONTAL = 2.0;
    static int orientation = 0;
    int count = 5;

    private Pane root;
    private Rectangle player;
    private List<Rectangle> blocks;
    private List<Rectangle> objects;
    private Enemy enemy1;
    private Enemy enemy2;
    private List<Rectangle> collectedObjects; // Liste der aufgesammelten Objekte
    
    private int playerDirection = 1; // 1 für rechts, -1 für links

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        root = new Pane();
        blocks = new ArrayList<>();
        objects = new ArrayList<>();
        collectedObjects = new ArrayList<>();

        // Hintergrundbild laden
        Image backgroundImage = new Image("file:images/JumpRun/level3.jpg");
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
        createBlock(100, HEIGHT - GROUND_HEIGHT - 120);
        createBlock(150, HEIGHT - GROUND_HEIGHT - 120);
        createBlock(200, HEIGHT - GROUND_HEIGHT - 120);
        createBlock(250, HEIGHT - GROUND_HEIGHT - 120);
        createBlock(300, HEIGHT - GROUND_HEIGHT - 120);
        createBlock(400, HEIGHT - GROUND_HEIGHT - 220);
        createBlock(450, HEIGHT - GROUND_HEIGHT - 220);
        createBlock(500, HEIGHT - GROUND_HEIGHT - 220);
        createBlock(600, HEIGHT - GROUND_HEIGHT - 300);
        createBlock(700, HEIGHT - GROUND_HEIGHT - 400);
        createBlock(210, HEIGHT - GROUND_HEIGHT - 290);
        createBlock(260, HEIGHT - GROUND_HEIGHT - 290);
        createBlock(260, HEIGHT - GROUND_HEIGHT - 290);
        createBlock(500, HEIGHT - GROUND_HEIGHT - 400);
        createBlock(450, HEIGHT - GROUND_HEIGHT - 400);
        createBlock(400, HEIGHT - GROUND_HEIGHT - 400);
        createBlock(350, HEIGHT - GROUND_HEIGHT - 400);


        // Objekte erstellen und positionieren
        createObject(350, HEIGHT - GROUND_HEIGHT - 170);
        createObject(230, HEIGHT - GROUND_HEIGHT - 350);
        createObject(600, HEIGHT - GROUND_HEIGHT - 350);
        createObject(535, HEIGHT - GROUND_HEIGHT - 450);
        createObject(320, HEIGHT - GROUND_HEIGHT - 450);
        createObject(710, HEIGHT - GROUND_HEIGHT - 450);



        // Gegner erstellen
        enemy1 = new Enemy(100, HEIGHT - GROUND_HEIGHT - 170, 100, 300);
        enemy2 = new Enemy(500, HEIGHT - GROUND_HEIGHT - 450, 330, 520);
        root.getChildren().addAll(enemy1, enemy2);

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
                    if (player.getTranslateY() + PLAYER_SIZE <= block.getTranslateY() &&
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
                    count--;
                    root.getChildren().remove(object);

                    // Überprüfe, ob alle Objekte gesammelt wurden
                    if (objects.isEmpty()) {
                        // Alle Objekte gesammelt, wechsle zu Level2
                        switchToGewinnseite();
                    }
                    break;
                }
                
            }
            if (player.getBoundsInParent().intersects(enemy1.getBoundsInParent())) {
                // Der Spieler hat den Gegner getroffen, Spiel zurücksetzen
                resetGame();
                Random rand = new Random();
                for(int i = 0; i < 6-count; i++) {
                    createObject((double)rand.nextInt((600 - 1) + 200), (double)rand.nextInt(HEIGHT - GROUND_HEIGHT) + GROUND_HEIGHT);
                }
                return; // Hier abbrechen, um die Objekt-Kollision nicht zu beeinträchtigen
            }
            
             if (player.getBoundsInParent().intersects(enemy2.getBoundsInParent())) {
                // Der Spieler hat den Gegner getroffen, Spiel zurücksetzen
                resetGame();
                Random rand = new Random();
                for(int i = 0; i < 6-count; i++) {
                    createObject((double)rand.nextInt((600 - 1) + 200), (double)rand.nextInt(HEIGHT - GROUND_HEIGHT) + GROUND_HEIGHT);
                }
                return; // Hier abbrechen, um die Objekt-Kollision nicht zu beeinträchtigen
            }

             // Bewegung des Gegners
            enemy1.move();
            enemy2.move();
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
    private void switchToGewinnseite() {
        // Die Methode damit Man von Level2 zu Level3 wechseln kann, wenn der Spieler alle Objekte aufgesammelt hat.
        youwin youwin = new youwin();
        Stage youwinStage = new Stage();
        youwin.start(youwinStage);
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

    private void resetGame() {
        resetPlayer();
        // Hier können Sie Logik für das Zurücksetzen des Spiels implementieren
        player.setTranslateX(0);
        player.setTranslateY(HEIGHT - GROUND_HEIGHT - PLAYER_SIZE);
        // Löschen Sie den Gegner und erstellen Sie einen neuen an einer anderen Position
        root.getChildren().remove(enemy1);
        enemy1 = new Enemy(100, HEIGHT - GROUND_HEIGHT - 170, 100, 300); // Beispielposition
        root.getChildren().add(enemy1);
        
        root.getChildren().remove(enemy2);
        enemy2 = new Enemy(500, HEIGHT - GROUND_HEIGHT - 450, 330, 520); // Beispielposition
        root.getChildren().add(enemy2);

        // Alle gesammelten Objekte wieder hinzufügen
        for (Rectangle object : collectedObjects) {
            root.getChildren().add(object);
        }
     // Aktualisierte Kollisionsprüfung für den Gegner und Spieler
        if (player.getBoundsInParent().intersects(enemy1.getBoundsInParent())) {
            // Der Spieler hat den Gegner getroffen, Spieler zurücksetzen, aber gesammelte Objekte beibehalten
            resetPlayer();
            // Hier könntest du weitere Aktionen für den Treffer des Gegners hinzufügen, falls erforderlich
        return;
        }
        // Aktualisierte Kollisionsprüfung für den Gegner und Spieler
        if (player.getBoundsInParent().intersects(enemy2.getBoundsInParent())) {
            // Der Spieler hat den Gegner getroffen, Spieler zurücksetzen, aber gesammelte Objekte beibehalten
            resetPlayer();
            // Hier könntest du weitere Aktionen für den Treffer des Gegners hinzufügen, falls erforderlich
        return;
        }
        collectedObjects.clear(); // Die Liste der gesammelten Objekte leeren, da sie alle wieder hinzugefügt wurden
    }
    private void resetPlayer() {
        player.setTranslateX(0);
        player.setTranslateY(HEIGHT - GROUND_HEIGHT - PLAYER_SIZE);        
    }
    
    // Innere Klasse für den Gegner
    private class Enemy extends Rectangle {
        private double speed = 2.0; // Geschwindigkeit des Gegners
        private double minX, maxX; // Minimale und maximale X-Position für die Bewegung

        public Enemy(double x, double y, double minX, double maxX) {
            super(75, 65); // Breite: 50, Höhe: 50 (passen Sie dies nach Bedarf an)
            Image enemyImage = new Image("file:images/JumpRun/enemy.png"); // Pfad zum Bild des Gegners
            setFill(new ImagePattern(enemyImage));
            setTranslateX(x);
            setTranslateY(y);

            this.minX = minX;
            this.maxX = maxX;
        }

        public void move() {
            // Bewegung des Gegners in horizontaler Richtung
            setTranslateX(getTranslateX() + speed);

            // Überprüfen, ob der Gegner die Grenzen erreicht hat
            if (getTranslateX() < minX || getTranslateX() > maxX) {
                // Richtung umkehren, wenn die Grenzen erreicht sind
                speed = -speed;
            }
        }
    }
}