package Snake;

public class Spielfeld {
    private int rows;
    private int columns;
    private int square_size;
    private int width;
    private int height;
    /**0 heißt, es ist nichts im Feld, 1 heißt Food, 2 Schlange, 3 Obstacle und 4 badFood*/
    private int[][] spielfeldArray;
    public Spielfeld() {
        this.rows = 12;
        columns = rows;
        this.width = 600;
        this.height = width;
        square_size = width/columns;
        this.spielfeldArray = new int[columns][rows];
        initializeArray();
        
    }
    public void initializeArray() {
        for(int i = 0; i<columns; i++) {
            for(int j = 0; j<rows; j++) {
                spielfeldArray[i][j] = 0;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public int getSquare_Size() {
        return square_size;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int[][] getspielfeldArray() {
        return spielfeldArray;
    }
    public void changeValueInMatrix(int x, int y, int newValue) {
        spielfeldArray[x][y] = newValue;
    }
    public void freeSnakeFields() {
        for(int i = 0; i<columns; i++) {
            for(int j = 0; j<rows; j++) {
                if(spielfeldArray[i][j] == 2) {
                    spielfeldArray[i][j] = 0;
                }
            }
        }
    }
}

