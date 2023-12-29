package Snake;

import javafx.scene.image.Image;

/**
 * 
 * @author Rahel
 * Diese Klasse hat die Verantwortungen:
 * das erste und letzte Element der Snake zu speichern
 * aufrücken
 * der Schlange neue Elemente hinzufügen
 */
public class SnakeController {
    private double speed;
    private SnakePart snakeHead;
    private SnakePart snakeTail;
    private Image image;
    private boolean neuesKaestchenBetreten;
    private boolean gameOverGefunden;
    private boolean foodGefunden;
    private int laengeSpeed;
    private Spielfeld spielfeld;
    
    /**
     * 
     * @param posX
     * @param posY
     * @param image
     * @param current
     * @param preferred
     * @param spielfeld
     * Erstellt einen neuen SnakeController
     */
    public SnakeController(double posX, double posY, Image image, Direction current, Direction preferred, Spielfeld spielfeld) {
        this.image = image;
        this.snakeTail = new SnakePart(posX-1, posY-1, null, this.image, current, preferred);
        this.snakeHead = new SnakePart(posX, posY, snakeTail, this.image, current, preferred);
        snakeTail.setPrevious(snakeHead);
        this.spielfeld = spielfeld;
        
        getReady(posX, posY);
    }
    public void setSnakeHead(SnakePart snakeHead) {
        this.snakeHead = snakeHead;
    }
    public void setSnakeTail(SnakePart snakeTail) {
        this.snakeTail = snakeTail;
    }
    public SnakePart getSnakeHead() {
        return snakeHead;
    }
    public boolean getGameOverGefunden() {
        return gameOverGefunden;
    }
    public boolean getFoodGefunden() {
        return foodGefunden;
    }
    /**
     * 
     * @param square_size
     * Sorgt dafür, dass die Schlange sich in die gewünschte Richtung bewegt. Prüft, ob
     * das möglich ist. Sorgt dafür, dass bei Bedarf ein neues SnakePart angehängt wird.
     */
    public void move() {
        foodGefunden = false;
        gameOverGefunden = false;
        snakeHead.fixPosition();
        
        if(neuesKaestchenBetretenTest(snakeHead)) {
            
            snakeHead.setCurrentDirectionOnpreferredDirection();
            neuesKaestchenBetreten = true;
            int snakeHeadOldX = (int)snakeHead.getX();
            int snakeHeadOldY = (int)snakeHead.getY();
            spielfeld.changeValueInMatrix(snakeHeadOldX, snakeHeadOldY, 2);
        }

        //Holen der Faktoren für die Richtung
        int[] newDirection = snakeHead.resolveDirectionIntoNumber();
        
        if(neuesKaestchenBetreten) {
            gameOverCheck(newDirection);
        }
        //Die Faktoren müssen erneut geholt werden, falls die Richtung sich durch
        //Umdrehen geändert hat
        int[] direction = snakeHead.resolveDirectionIntoNumber();
        double posNewPartX = snakeHead.getX()+(speed*direction[0]);
        double posNewPartY = snakeHead.getY()+(speed*direction[1]);
        
        SnakePart newSnakeHead = new SnakePart(posNewPartX, posNewPartY, snakeHead, this.image, snakeHead.getCurrentDirection(), snakeHead.getPreferredDirection());
        snakeHead.setPrevious(newSnakeHead);
        setSnakeHead(newSnakeHead);
        
        rueckAuf();
        neuesKaestchenBetreten = false;
    }
    /**
     * Sorgt dafür, dass das Kästchen,, wo der SnakeTail vorher war, 
     * in der Matrixwieder den Wert 0 bekommt
     */
    public void rueckAuf() {
        if(neuesKaestchenBetretenTest(snakeTail)) {
            int snakeTailX = (int)snakeTail.getX();
            int snakeTailY = (int)snakeTail.getY();
            if(snakeTailX != -1 && snakeTailY != -1) {
                spielfeld.changeValueInMatrix(snakeTailX,snakeTailY,0);
            }
        }
        snakeTail = snakeTail.getPrevious();
        snakeTail.setNext(null);
    }
    /**
     * Gibt wieder, wie viele SnakeParts inklusive tail und head sich 
     * in der Snake befinden
     */
    public int getSnakeLength() {
        int i = 1;
        SnakePart p = snakeHead;
        do {
            ++i;
            p = p.getNext();
        } while(p.getNext() != null);
        return i;
    }
    /**
     * An der Position (-1|-1) wird ein neues SnakePart erstellt. Es zeigt auf
     * den bisherigen SnakeTail und wird selbst zum neuen SnakeTail
     */
    public void addSnakePart() {
        SnakePart p = new SnakePart(-1, -1, null, this.image, null, null);
        snakeTail.setNext(p);
        p.setPrevious(snakeTail);
        snakeTail = p;
    }
    public double getSpeed() {
        return speed;
    }
    /**
     * Ruft die Methode addSnakePart so oft auf, dass ein ganzes Kästchen gefüllt wird
     */
    public void addWholeSnakePart() {
        for(int i = 0; i< (1/speed); i++) {
            addSnakePart();
        }
        
    }
    /**
     * Prüft anhand der Position des SnakeHeads, ob ein neues Kästchen betreten wurde.
     * Wenn sich die Position glatt durch 1 hoch (Anzahl der Nachkommastellen von speed)
     * teilen lässt, wurde ein neues 
     * Kästchen betreten. Um das besser testen zu können, wird alles mal zehn gerechnet.
     */
    public boolean neuesKaestchenBetretenTest(SnakePart p) {
        int factor = (int)Math.pow(10, laengeSpeed);
        double xWertMalZehn = p.getX()*factor;
        double yWertMalZehn = p.getY()*factor;
        double restX = xWertMalZehn % factor;
        double restY = yWertMalZehn % factor;
        boolean geradeX = ((restX) == 0);
        boolean geradeY = ((restY) == 0);
        return geradeX && geradeY;
    }
    
    /** Überprüft, ob der SnakeHead a) außerhalb des Spielfelds ist, b) ein Futter 
     * gefressen oder c) ein gegen ein Hindernis oder sich selber geschlängelt 
     * ist. Setzt in diesem Fällen entweder gameOverGefunden oder foodGefunden auf
     * true
     * */
    public void gameOverCheck(int[] direction) {
        int[][] spielfeldArray = spielfeld.getspielfeldArray();
        int spielfeldArrayX = (int)snakeHead.getX()+direction[0];
        int spielfeldArrayY = (int)snakeHead.getY()+direction[1];
        checkOutOfSpielfeld(spielfeldArrayX, spielfeldArrayY);
        if(!gameOverGefunden) {
            int wertKaestchen = spielfeldArray[spielfeldArrayX][spielfeldArrayY];
            if(wertKaestchen != 0) {
                if(wertKaestchen == 1) {
                    foodGefunden = true;
                } else if(wertKaestchen == 2 || wertKaestchen == 3) {
                    gameOverGefunden = true;
                } else {
                    spielfeld.freeSnakeFields();
                    upsideDown();
                }
            }
        }
    }
    
    /** Prüft, ob die Position des snakeHeads sich noch im Spielfeld befindet
     * */
    public void checkOutOfSpielfeld(int spielfeldArrayX, int spielfeldArrayY) {
        boolean ausserhalbX = (spielfeldArrayX >= spielfeld.getColumns() || spielfeldArrayX < 0);
        boolean ausserhalbY = (spielfeldArrayY >= spielfeld.getRows() || spielfeldArrayY < 0);
        if(ausserhalbX || ausserhalbY) { 
            gameOverGefunden = true;
        }
    }
    /**Setzt alles zurück, was zurück gesetzt werden muss, damit das Spiel neu 
     * starten kann
     * */
    public void clear() {
        snakeTail = snakeHead.getNext();
    }
    
    /**Tauscht snakeHead und snakeTail. Außerdem werden die Previous- und Next-
     * Zeiger getauscht. currentDirection und preferredDirection werden geändert*/
    public void upsideDown() {
        //Direction d = snakeHead.getCurrentDirection();
        SnakePart help = snakeHead;
        snakeHead = snakeTail;
        snakeTail = help;
        
        SnakePart p = snakeHead;
        while(p != null) {
            SnakePart helpP = p.getNext();
            p.setNext(p.getPrevious());
            p.setPrevious(helpP);
            p = p.getNext();
        }
        
        reverseHeadDirection();
    }
    /**
     * Diese Methode ändert die Richtung des SnakeHeads so, dass sie in entgegen-
     * gesetzte Richtung des nächsten SnakeParts zeigt. Wenn der nächste SnakePart
     * unter dem SnakeHead ist, wird die Richtung also auf UP gesetzt
     */
    public void reverseHeadDirection() {
        double x = snakeHead.getX();
        double y = snakeHead.getY();
        double xNext = snakeHead.getNext().getX();
        double yNext = snakeHead.getNext().getY();
        if(x > xNext) {
            snakeHead.setCurrentDirection(Direction.RIGHT);
            snakeHead.setPreferredDirection(Direction.RIGHT);
        } else if(x < xNext) {
            snakeHead.setCurrentDirection(Direction.LEFT);
            snakeHead.setPreferredDirection(Direction.LEFT);
        } else if(y > yNext) {
            snakeHead.setCurrentDirection(Direction.DOWN);
            snakeHead.setPreferredDirection(Direction.DOWN);
        } else if(y < yNext) {
            snakeHead.setCurrentDirection(Direction.UP);
            snakeHead.setPreferredDirection(Direction.UP);
        }
    }
    
    /**Methode, die am Anfang des Spiels ausgeführt wird, um alles zurückzusetzen.
     * Sahen, die nur einmal bei der Erstellung des SnakeControllers ausgeführt werden
     * müssen, werden nicht erneut ausgeführt*/
    public void getReady(double posX, double posY) {
        snakeHead.setX(10);
        snakeHead.setY(10);
        snakeHead.setCurrentDirection(Direction.UP);
        snakeHead.setPreferredDirection(Direction.UP);
        neuesKaestchenBetreten = false;
        speed = 0.1;
        laengeSpeed = 1;
    }
}