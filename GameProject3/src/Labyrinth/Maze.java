package Labyrinth;

import java.util.ArrayList;
import java.util.Random;


/**
 * Die Klasse repräsentiert ein Labyrinth mit Zellen.
 */
public class Maze {
    final int rows; // Zeilenanzahl
    final int clms; // Spaltenanzahl
    Cell[][] cells; // 2D Zellenarray
    int count = 0; // Counter zum Rechnen der Prozentsatz von dem Generierenprozess
    int threadDuration = 0; //Dauer zum erzeugen einer Zelle in Milliesekunden

    /**
     * Standardkonstruktor für das Labyrinth.
     *
     * @param rows Die Anzahl der Zeilen im Labyrinth.
     * @param clms Die Anzahl der Spalten im Labyrinth.
     */
    public Maze(int rows, int clms) { 
        this.rows = rows;
        this.clms = clms;
        cells = new Cell[rows][clms];

     // Zellen generieren
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(j, i);
    }
    
    /**
     * Konstruktor für das Labyrinth mit einer Zeitdauer zwischen dem Erzeugen jeder Zelle.
     *
     * @param rows            Die Anzahl der Zeilen im Labyrinth.
     * @param clms            Die Anzahl der Spalten im Labyrinth.
     * @param threadDuration  Die Zeitdauer zwischen dem Erzeugen jeder Zelle in Millisekunden.
     */
    public Maze(int rows, int clms, int threadDuration){
        this(rows, clms);
        this.threadDuration = threadDuration;
    }

    /**
     * Methode zum Erzeugen des Labyrinths.
     */
    public void createMaze() {
        var startTime = System.currentTimeMillis();
        System.out.println("Labyrinth Generierprozess angefangen für ein " + rows + "x " + clms + " Labyrinth");
        generate(cells[0][0]);
        var endTime = System.currentTimeMillis();
        System.out.println("\nLabyrinth generiert");
        System.out.println("Es hat " + (endTime - startTime) / 1000 + " Sekunden gedauert");
    }

    /**
     * Methode zum Generieren des Labyrinths rekursiv.
     *
     * @param curntCell Die aktuelle Zelle im Generierungsprozess.
     */
    void generate(Cell curntCell) {
        Random rand = new Random();
        curntCell.visit();
        count++;
        System.out.print(String.format("\r%d%% vervollständigt (%d/%d)", count * 100 / (rows * clms), count, rows * clms));
        try {
            Thread.sleep(threadDuration);
        } catch (InterruptedException e) {
        }

        ArrayList<Cell> nextCells = getUnvisitedNeighbours(curntCell); // gibt an, welche benachbarten Zellen unbesucht sind
        while(nextCells.size() > 0){
            // Wähle eine benachbarte Zelle von der Liste
            // Prüfe, ob die Zelle wirklich unbesucht ist, da es möglich ist, dass die Zelle vor dem erzeugen der Liste bereits besucht ist
            // Falls die Zelle besucht ist, lösche sie von der Liste

            var nextIndx = rand.nextInt(nextCells.size()); //wähle den nächsten Zelle zufällig anhand die nextCells Liste
            if(!nextCells.get(nextIndx).isVisited){ //Wenn dieser Zelle nicht besucht ist, dann setze sie als besucht und rufe die generate Methode 
                //rekursiv wieder mit der benachbarte Zelle als parameter.
                //Ansonsten baue ein Wand zwischen die Zellen durch die openWith methode
                curntCell.openWith(nextCells.get(nextIndx));
                generate(nextCells.get(nextIndx));
                nextCells.remove(nextIndx);
            }else
                nextCells.remove(nextIndx);
        }
    }

    /**
     * Gibt eine ArrayList aller unbesuchten Nachbarn der Zelle zurück.
     *
     * @param cell Die Zelle, für die die unbesuchten Nachbarn gefunden werden sollen.
     * @return Eine ArrayList der unbesuchten Nachbarn.
     */ 
    ArrayList<Cell> getUnvisitedNeighbours(Cell cell){
        ArrayList<Cell> output = new ArrayList<Cell>();

        for (int i = 0; i < 4; i++) {
            // i/2 == 0? + : -
            // i%2 == 0? y : x
            int cX = i%2 == 0? cell.x: i/2 == 0? cell.x+1: cell.x-1;
            int cY = i%2 != 0? cell.y: i/2 == 0? cell.y+1: cell.y-1;
            try {
                if(!cells[cY][cX].isVisited)
                output.add(cells[cY][cX]);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return output;
    }

}