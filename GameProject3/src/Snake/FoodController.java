package Snake;

import java.util.ArrayList;

import javafx.scene.image.Image;
/**
 * Diese Klasse hat folgende Verantwortungen:
 *  Neues Food erstellen, wenn sie dazu aufgefordert wird
 */
public class FoodController {
    private GameObject food;
    private GameObject badFood;
    private Image foodImage;
    private Image badFoodImage;
    private Spielfeld spielfeld;
    
    public FoodController(Image foodImage, Image badFoodImage, Spielfeld spielfeld) {
        this.foodImage = foodImage; 
        this.badFoodImage = badFoodImage;
        this.spielfeld = spielfeld;
    }
    /**
     * 
     * @param snakeController
     * @param obstacleList
     */
    public void generateFood(SnakeController snakeController, ArrayList<GameObject> obstacleList){
        GameObject g = MiniLibrary.generateGameObject(spielfeld, snakeController,obstacleList, this.foodImage);
        this.food = g;
        food.setImage(foodImage);
        spielfeld.changeValueInMatrix((int)food.getX(), (int)food.getY(), 1);
    }
    public void generateBadFood(SnakeController snakeController, ArrayList<GameObject> obstacleList) {
        GameObject g = MiniLibrary.generateGameObject(spielfeld, snakeController,obstacleList, this.foodImage);
        this.badFood = g;
        badFood.setImage(badFoodImage);
        spielfeld.changeValueInMatrix((int)badFood.getX(), (int)badFood.getY(), 4);
    }
    public GameObject getFood() {
        return food;
    }
    public GameObject getBadFood() {
        return badFood;
    }
    public void clear() {
        badFood = null;
    }

}
