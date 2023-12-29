package Snake;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class MiniLibrary {
    /**
     * Berechnet die Position für ein neues GameObject, erstellt dieses und gobt es zurück.
     * Das erstellte GameObject darf weder die gleiche Position wie ein bereits vorhandenes
     * haben, noch darf es sich auf einem feld direkt daneben befinden
     * Die Position des neues gameObjects wird direkt im SpielfeldArray festgehalten
    */
    public static GameObject generateGameObject(Spielfeld spielfeld, SnakeController snakeController, ArrayList<GameObject> obstacleList, Image image) {
        GameObject newGameObject;
        Random random = new Random();
       
        start:
        while(true) {
            int x = random.nextInt(spielfeld.getColumns());
            int y = random.nextInt(spielfeld.getRows());

            newGameObject = new GameObject((double)x, (double)y, image);
            newGameObject.fixPosition();
            int[][] array = spielfeld.getspielfeldArray();
            
            boolean downIsNotFree;
            boolean upIsNotFree;
            boolean leftIsNotFree;
            boolean rightIsNotFree;
            if(x-1 <= 0) {
                leftIsNotFree = false;
            } else {
                leftIsNotFree = (array[x-1][y] != 0);
            }
            if(x + 1 >= spielfeld.getColumns()) {
                rightIsNotFree = false;
            } else {
                rightIsNotFree = array[x+1][y] != 0;
            }
            if(y-1 <= 0) {
                upIsNotFree = false;
            } else {
                upIsNotFree = array[x][y-1] != 0;
            }
            if(y + 1 >= spielfeld.getRows()) {
                downIsNotFree = false;
            } else {
                downIsNotFree = array[x][y+1] != 0;
            }
            if((array[x][y] != 0) || upIsNotFree || downIsNotFree || leftIsNotFree || rightIsNotFree) {
                continue start;
            }
            
            break;
        }
        return newGameObject;
    }
    
    public static Direction reverseDirection(Direction currentDirection) {
        switch(currentDirection) {
            case UP: currentDirection = Direction.DOWN;
                break;
            case DOWN: currentDirection = Direction.UP;
                break;
            case LEFT: currentDirection = Direction.RIGHT;
                break;
            case RIGHT: currentDirection = Direction.LEFT;
                break;
            default: currentDirection = Direction.LEFT;
                break;
        }
        return currentDirection;
    }
    
    
}
