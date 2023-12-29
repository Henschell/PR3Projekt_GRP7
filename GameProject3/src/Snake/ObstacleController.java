package Snake;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class ObstacleController{
    private Image image;
    private ArrayList<GameObject> obstacleList;
    
    public ObstacleController(Image image) {
        obstacleList = new ArrayList<GameObject>();
        this.image = image;
    }
    public void generateObstacle(int rows, int columns, SnakeController snakeController, Spielfeld spielfeld) {
        GameObject obstacle = MiniLibrary.generateGameObject(spielfeld, snakeController, this.obstacleList, this.image);
        
        obstacleList.add(obstacle);
        while(obstacleList.size() > (rows/2)) {
            obstacleList.get(0).fixPosition();
            int xValueDelete = (int)obstacleList.get(0).getX();
            int yValueDelete = (int)obstacleList.get(0).getY();
            spielfeld.changeValueInMatrix(xValueDelete, yValueDelete, 0);
            obstacleList.remove(0);
        }
        spielfeld.changeValueInMatrix((int)obstacle.getX(), (int)obstacle.getY(), 3);
    }
    public ArrayList<GameObject> getObstacleList() {
        return obstacleList;
    }
    public void clear() {
        obstacleList = new ArrayList<GameObject>();
    }
}
