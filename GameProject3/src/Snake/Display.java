package Snake;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Display {
    private final GraphicsContext gc;

    
    private Canvas canvas;
    
    public Display(GameController gameController, int width, int height) {
       
        this.canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
      
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public void paintImage(Image image, double width, double height) {
        gc.drawImage(image, 0, 0, width, height);
    }
    
    public void paintSpielfeld(int rows, int columns, int square_size) {
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {
                if(((i+j)%2) == 0) {
                    gc.setFill(Color.LIMEGREEN);
                } else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(i*square_size, j*square_size, square_size, square_size);
            }
        }
    }
    public void paintBackgroundImage() {
        
    }
    public void paintGameObject(GameObject gameObject, int square_size) {
        double xCoordinate = gameObject.getX()*square_size;
        double yCoordinate = gameObject.getY()*square_size;
        gc.drawImage(gameObject.getImage(), xCoordinate, yCoordinate, square_size, square_size);
    }
    
    public void paintWholeSnake(SnakePart snakePart, int length, int square_size) {
        SnakePart[] array = new SnakePart[length];
        for(int i = 0; i< length; i++) {
            array[i] = snakePart;
            snakePart = snakePart.getNext();
        }
        for(int i = length-1; i>=0; i--) {
            paintGameObject(array[i], square_size);
        }
    }
    
    public void paintGameOver(int width, int height) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("GAME OVER!", width/3.5, height/2);

    }
    public void paintScore(int score, int width, int height) {
        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("Score = "+score, width/3.5, height/2);
        
    }
    public void paintObstacles(ObstacleController obstacleController, int square_size) {
        ArrayList<GameObject> list = obstacleController.getObstacleList();
        for(GameObject o: list) {
            paintGameObject(o, square_size);
        }
    }

}
