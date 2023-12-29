package Labyrinth;
import java.util.Random;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;

/**
 * Die Klasse repräsentiert einen Roboter im Labyrinth.
 */
public class Robot {
    private int cellX;
    private int cellY;
    private double posX;
    private double posY;
    private double prespective = 1;
    private boolean left = false;
    private Image graphic = new Image("file:robot.gif");

    /**
     * Konstruktor für einen Roboter mit den gegebenen Startkoordinaten und der Zellenbreite.
     *
     * @param cX         Die Start-X-Koordinate des Roboters.
     * @param cY         Die Start-Y-Koordinate des Roboters.
     * @param cellWidth  Die Breite einer Zelle im Labyrinth.
     */
    public Robot(int cX, int cY, int cellWidth) {
        Random rand = new Random();
        cellX = cX;
        cellY = rand.nextInt(cY - 1);
        posX = cellX * cellWidth;
        posY = cellY * cellWidth;
    }
    
    /**
     * Gibt die X-Koordinate der Zelle zurück, in der sich der Roboter befindet.
     *
     * @return Die X-Koordinate der Zelle.
     */
    public int getCellX() {
        return cellX;
    }

    /**
     * Setzt die X-Koordinate der Zelle, in der sich der Roboter befindet.
     *
     * @param cellX Die neue X-Koordinate der Zelle.
     */
    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    /**
     * Gibt die Y-Koordinate der Zelle zurück, in der sich der Roboter befindet.
     *
     * @return Die Y-Koordinate der Zelle.
     */
    public int getCellY() {
        return cellY;
    }

    /**
     * Setzt die Y-Koordinate der Zelle, in der sich der Roboter befindet.
     *
     * @param cellY Die neue Y-Koordinate der Zelle.
     */
    public void setCellY(int cellY) {
        this.cellY = cellY;
    }

    /**
     * Gibt die X-Position des Roboters auf dem Bildschirm zurück.
     *
     * @return Die X-Position des Roboters.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Setzt die X-Position des Roboters auf dem Bildschirm.
     *
     * @param posX Die neue X-Position des Roboters.
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * Gibt die Y-Position des Roboters auf dem Bildschirm zurück.
     *
     * @return Die Y-Position des Roboters.
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Setzt die Y-Position des Roboters auf dem Bildschirm.
     *
     * @param posY Die neue Y-Position des Roboters.
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    /**
     * Gibt die Blickrichtung des Roboters zurück.
     *
     * @return Die Blickrichtung des Roboters.
     */
    public double getPrespective() {
        return prespective;
    }

    /**
     * Setzt die Blickrichtung des Roboters.
     *
     * @param perspective Die neue Blickrichtung des Roboters.
     */
    public void setPrespective(double prespective) {
        this.prespective = prespective;
    }

    /**
     * Getter zur Zustand des Robots, ob er in der Labyrinth ist oder nicht.
     *
     * @param left true, wenn der Roboter durch die Tür gegangen ist, ansonsten false.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Setzt den Zustand des Robots, ob er immernoch in den Labyrinth ist oder raus gegangen ist.
     *
     * @param left true, wenn der Roboter durch die Tür gegangen ist, ansonsten false.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Gibt das grafische Bild des Roboters zurück.
     *
     * @return Das grafische Bild des Roboters.
     */
    public Image getGraphic() {
        return graphic;
    }

    /**
     * Setzt das grafische Bild des Roboters.
     *
     * @param graphic Das neue grafische Bild des Roboters.
     */
    public void setGraphic(Image graphic) {
        this.graphic = graphic;
    }

    /**
     * Bewegt den Roboter in die angegebene Richtung.
     *
     * @param direction Die Richtung, in die sich der Roboter bewegen soll.
     */
    public void move(KeyCode direction) {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
             // Andere Tasten ignorieren
        }
    }
    
    /**
     * Bewegt den Roboter nach oben, sofern möglich.
     */
    private void moveUp() {
        if (cellY > 0 && MazeGenerate.getMaze().cells[cellY][cellX].walls[0]) {
            System.out.println(cellY);
            cellY--;
            System.out.println(cellY);
            posY = cellY * MazeGenerate.getCellWidth();
            System.out.println("moved up");
        }
    }
    
    /**
     * Bewegt den Roboter nach unten, sofern möglich.
     */
    private void moveDown() {
        if (cellY < MazeGenerate.getMaze().rows - 1 && MazeGenerate.getMaze().cells[cellY][cellX].walls[2]) {
            System.out.println(cellY);
            cellY++;
            System.out.println(cellY);
            posY = cellY * MazeGenerate.getCellWidth();
            System.out.println("moved down");
        }
    }

    /**
     * Bewegt den Roboter nach links, sofern möglich.
     */
    private void moveLeft() {
        if (cellX > 0 && MazeGenerate.getMaze().cells[cellY][cellX].walls[3]) {
        System.out.println(cellX);
            cellX--;
            System.out.println(cellX);
            posX = cellX * MazeGenerate.getCellWidth();
            System.out.println("moved left");
            if(prespective == 1) {
                prespective = -1;
            }
        }
    }

    /**
     * Bewegt den Roboter nach rechts, sofern möglich.
     */
    private void moveRight() {
        if (cellX < MazeGenerate.getMaze().clms - 1 && MazeGenerate.getMaze().cells[cellY][cellX].walls[1]) {
            System.out.println(cellX);
            cellX++;
            System.out.println(cellY);
            posX = cellX * MazeGenerate.getCellWidth();
            System.out.println("moved right");
            if(prespective == -1) {
                prespective = 1;
            }
        }
    }
    
    /**
     * Lässt den Roboter das Spiel verlassen, wenn die Leertaste gedrückt wird.
     *
     * @param key Die gedrückte Taste.
     */
    public void leave(KeyCode key) {
        if (key == KeyCode.SPACE) {
            System.out.println("You Won");
            left = true;
        }
    }

}
