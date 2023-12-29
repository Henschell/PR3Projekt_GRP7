package Snake;

import javafx.scene.image.Image;

public class GameObject {
    private double x;
    private double y;
    Image image;
    
    public GameObject(double x, double y, Image image) {
        setX(x);
        setY(y);
        this.image = image;
    }
    public void setX(double d) {
        this.x = d;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public boolean samePosition(GameObject other) {
        //return this.x == other.x && this.y == other.y;
        return Math.abs(this.x - other.x)<1E-12 && Math.abs(this.y - other.y)<1E-12;
    }
    public void fixPosition() {
        double großX = getX()*100.0;
        double großY = getY()*100.0;
        double gerundetX = Math.round(großX);
        double gerundetY = Math.round(großY);
        double besserX = gerundetX/100.0;
        double besserY = gerundetY/100.0;
        setX(besserX);
        setY(besserY);
    }
}
