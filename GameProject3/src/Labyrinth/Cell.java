package Labyrinth;

/**
 * Die Klasse repräsentiert eine Zelle im Labyrinth.
 */
public class Cell {
    final int x;
    final int y;
    boolean isVisited = false;
    boolean[] walls = {false, false, false, false};

    /**
     * Konstruktor für die Zelle mit den gegebenen Koordinaten.
     *
     * @param x Die x-Koordinate der Zelle.
     * @param y Die y-Koordinate der Zelle.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Markiert die Zelle als besucht.
     */
    void visit(){
        isVisited = true;
    }

    /**
     * Öffnet die Wand zwischen dieser Zelle und einer anderen Zelle.
     *
     * @param otherCell Die andere Zelle, zu der die Wand geöffnet wird.
     */
    void openWith(Cell otherCell){
        // Ermitteln der Position der anderen Zelle relativ zu dieser Zelle
        if(otherCell.y == this.y){
            switch (otherCell.x - this.x) {
                case -1:
                    walls[3] = true;
                    otherCell.walls[1] = true;
                    break;
                case 1:
                    walls[1] = true;
                    otherCell.walls[3] = true;
                    break;
                default:
                    // Zelle ist kein Nachbar
                    return;
            }
        }else if(otherCell.x == this.x){
            switch (otherCell.y - this.y) {
                case -1:
                    walls[0] = true;
                    otherCell.walls[2] = true;
                    break;
                case 1:
                    walls[2] = true;
                    otherCell.walls[0] = true;
                    break;
                default:
                    // Zelle ist kein Nachbar
                    return;
            }
        }else{
            // Zelle ist kein Nachbar
            return;
        }
    }
}

