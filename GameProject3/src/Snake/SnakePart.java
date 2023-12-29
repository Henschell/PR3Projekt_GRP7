package Snake;

import java.text.DecimalFormat;

import javafx.scene.image.Image;

public class SnakePart extends GameObject{
    private Direction currentDirection;
    private Direction preferredDirection;
    private SnakePart next;
    private SnakePart previous;
    
    public SnakePart(double x, double y, SnakePart next, Image image, Direction current, Direction preferred) {
        super(x, y, image);
        setNext(next);
        this.currentDirection = current;
        this.preferredDirection = preferred;
        
    }
    public Image getImage() {
        return image;
    }
    
    public SnakePart getNext() {
        return next;
    }
    
    public void setNext(SnakePart next) {
        this.next = next;
    }
    public SnakePart getPrevious() {
        return previous;
    }
    public void setPrevious(SnakePart previous) {
        this.previous = previous;
    }
    
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
        
    }
    
    public Direction getPreferredDirection() {
        return preferredDirection;
    }
    
    public void setPreferredDirection(Direction preferredDirection) {
        this.preferredDirection = preferredDirection;
    }
    public int[] resolveDirectionIntoNumber() {
        int[] i = new int[2];
        if(currentDirection == Direction.RIGHT) {  
            i[0] = 1;
            i[1] = 0;
        } else if(currentDirection == Direction.LEFT) {
            i[0] = -1;
            i[1] = 0;
        } else if(currentDirection == Direction.UP) {
            i[0] = 0;
            i[1] = -1;
        } else if (currentDirection == Direction.DOWN) {
            i[0] = 0;
            i[1] = 1;
        }
        return i; 
    }
    public void setCurrentDirectionOnpreferredDirection() {
        currentDirection = preferredDirection;
    }
   
    
}
