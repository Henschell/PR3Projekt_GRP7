package Labyrinth;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Key {
    private int cellX;
    private int cellY;
    private double posX;
    private double posY;
    private Image graphic = new Image("file:keycard.png");
    private ImageView view = new ImageView(this.graphic);
    
    public Key(int cX, int cY, int cellWidth) {
        Random rand = new Random();
        cellX = rand.nextInt(cX);
        cellY = rand.nextInt(cY);
        posX = cellX * cellWidth;
        posY = cellY * cellWidth;
    }

    public int getCellX() {
        return cellX;
    }

    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public void setCellY(int cellY) {
        this.cellY = cellY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Image getGraphic() {
        return graphic;
    }

    public void setGraphic(Image graphic) {
        this.graphic = graphic;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }
}
