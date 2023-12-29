package JumpRun;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

public class Enemy extends Rectangle {
    private int direction = 1; // Richtung, in die sich der Gegner bewegt
    private double minX;
    private double maxX;
    private double speed = 5.0; // Geschwindigkeit des Gegners

    public Enemy(double x, double y, double minX, double maxX, double speed) {
        super(75, 65); // Breite: 50, Höhe: 50 (passen Sie dies nach Bedarf an)
        Image enemyImage = new Image("file:images/JumpRun/enemy.png"); // Pfad zum Bild des Gegners
        setFill(new ImagePattern(enemyImage));
        setTranslateX(x);
        setTranslateY(y);
        this.minX = minX;
        this.maxX = maxX;
        this.speed = speed;
    }

    public void move() {
        double newX = getTranslateX() + direction * speed;
        if (newX < minX || newX > maxX) {
            // Wenn der Gegner die Grenzen erreicht, ändere die Richtung
            direction *= -1;
        }
        setTranslateX(newX);
    }
}