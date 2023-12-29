package VirusSweeper;

import application.AudioManager;
import application.GUIController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * TODO beschreibung
 * @author Marc Pauluhn
 */
public class VirusSweeper {

    private static VirusSweeper instance = null;
    private Scene VirusSweeperScene;
    private int schwierigkeit; // 0 = Anfänger, 1= Fortgeschritten, 2 = Experte
    public ZeitAnzeige zeitAnzeige;
    private GUIController guiController;
    private VBox myVBox;
    private GridPane spielfeld;
    private final int GRID_GROESSE[] = { 9, 16, 22 };
    private final int VIRUS_ANZAHL[] = { 10, 40, 100 };
    private final int NICHT_VIRUS_ANZAHL[] = { 71, 216, 384 };
    private VirusSweeperFestplatte[][] spielfeld_array;
    private VirusCounter virusCounter;
    private int height;
    private int width;
    private int aufgedeckteFelder;
    private boolean ende = false;
    private static int easteregg = 0;

    /**
     * Konstruktor für VirusSweeper
     * 
     * @param height
     * @param width
     * @param guiController
     * @param schwierigkeit
     */
    private VirusSweeper(int height, int width, GUIController guiController, int schwierigkeit) {
        this.guiController = guiController;
        this.schwierigkeit = schwierigkeit;
        this.height = height;
        this.width = width;
        this.aufgedeckteFelder = 0;
        myVBox = new VBox();
        // erstelle das Spielfed.
        spielfeld = erstelleSpielfeld();
        // Und packe es in eine Vbox mit der TopLeiste.
        myVBox.getChildren().addAll(top(), spielfeld);
        // setze die Scene dann auf das Spiel
        VirusSweeperScene = new Scene(myVBox, height, width);
    }

    /**
     * Diese Methode erstellt das Spielfeld in Form eines Gridpanes. Mit
     * VirusSweeperFestplatten die anzahl hängt von der Schwierigkeitsstufe ab.
     * 
     * @return gibt Gridpane mit Spielfeld zurück
     */
    private GridPane erstelleSpielfeld() {
        int groesse = GRID_GROESSE[schwierigkeit];
        spielfeld_array = new VirusSweeperFestplatte[groesse][groesse];
        spielfeld = new GridPane();
        // Erstelle das Spielfeld der VirusSweeperFestplatte mit der aktuellen
        // schwierigkeit.
        for (int zeile = 0; zeile < groesse; zeile++) {
            for (int spalte = 0; spalte < groesse; spalte++) {
                spielfeld_array[zeile][spalte] = new VirusSweeperFestplatte(zeile, spalte, 0, false, false, false);
                spielfeld_array[zeile][spalte].setMinSize(height / GRID_GROESSE[schwierigkeit],
                        (width - 30) / GRID_GROESSE[schwierigkeit]);

                final int z = zeile;
                final int s = spalte;
                // Und füge ihnen die Interaktion mit linksKlick und rechtsKlick hinzu.
                spielfeld_array[zeile][spalte].setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        linksKlick(z, s);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        rechtsKlick(z, s);
                    }
                });

                spielfeld.add(spielfeld_array[zeile][spalte], spalte, zeile);
            }
        }
        // Und füge für die Schwierigkeit passende Anzahl der Viren Passend hinzu.
        for (int i = 0; i < VIRUS_ANZAHL[schwierigkeit]; i++) {
            /*
             * erzeugt Zufallspositionen, bis eine Position gefunden ist, die Auf Virus
             * gesetzt werden kann.
             */
            int reihe, spalte;
            do {
                reihe = (int) (Math.random() * GRID_GROESSE[schwierigkeit]);
                spalte = (int) (Math.random() * GRID_GROESSE[schwierigkeit]);
            } while (spielfeld_array[spalte][reihe].getVirus() == true);

            spielfeld_array[spalte][reihe].setVirus(true);
        }
        // Und Setze für jede VirusSweeperFestplatte die Anzahl der UmliegenderViren.
        for (int zeile = 0; zeile < groesse; zeile++) {
            for (int spalte = 0; spalte < groesse; spalte++) {
                int anzahlViren = zähleViren(zeile, spalte);
                spielfeld_array[zeile][spalte].setAnzahlUmliegenderViren(anzahlViren);
            }
        }

        return spielfeld;

    }

    /**
     * Diese Methode ist dafür das wenn man linksKlick auf eine
     * VirusSweeperFestplatte macht Folgende Methoden triggert.
     * 
     * @param zeile
     * @param spalte
     */
    private void linksKlick(int zeile, int spalte) {
        if (!ende) {
            // wenn nicht Markiert ist
            if (spielfeld_array[zeile][spalte].getMarkierung() == false) {
                // und sichtbar, wenn die Markierugnen die gleiche anzahl wie die Anzhal der
                // VirenImUmkreis ist. Dann decke die umliegenden Felder auf.
                if (spielfeld_array[zeile][spalte].getSichtbarkeit()) {
                    if (checkMarkierungen(zeile, spalte)) {
                        deckeUmliegendeNichtMarkierteFelderAuf(zeile, spalte);
                    }
                }
                // wenn die Zeithochzählung noch nicht gestartet wurde, dann starte sie.
                if (!zeitAnzeige.isRunning()) {
                    zeitAnzeige.startTimer();
                }
                // Wenn es kein Virus ist
                int Viren = spielfeld_array[zeile][spalte].getAnzahlUmliegenderViren();
                // und Viren im Umkreis sind deckeauf.
                if (Viren > 0) {
                    deckeAuf(zeile, spalte);
                } else {
                    // sonst decke zusätzlich die LeerenFelder auf.
                    deckeAuf(zeile, spalte);
                    aufdeckenLeereFelder(zeile, spalte);
                }
                // Prüfe die Gewinn Kondition
                if (aufgedeckteFelder == NICHT_VIRUS_ANZAHL[schwierigkeit]) {
                    ende = true;
                    easteregg = 0;
                    zeitAnzeige.stopTimer();
                    AudioManager.VirusSweeperPlayGewonnenSound();
                    // Verzöger um 2 Sekunden, bevor der GewonnenBildschrim Gezeigt wird
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        guiController.VirusSweeperSpielGewonnenBildschirm();
                    }));
                    timeline.play();
                }

            }
        }
    }

    /**
     * Diese Methode deckt die nicht Markierten VirusSweeperFestplatte im umkereis
     * von 1 auf.
     * 
     * @param zeile
     * @param spalte
     */
    private void deckeUmliegendeNichtMarkierteFelderAuf(int zeile, int spalte) {
        // Iteriert durch Zeilen
        for (int i = -1; i <= 1; i++) {
            // Iteriert durch spalten
            for (int j = -1; j <= 1; j++) {
                int z = zeile + i;
                int s = spalte + j;

                /*
                 * überprüft ob position innerhalb des Spielfelds liegt und prüft ob es noch
                 * nicht Sichtbar ist und nicht Markiert ist.
                 */
                if (z >= 0 && z < GRID_GROESSE[schwierigkeit] && s >= 0 && s < GRID_GROESSE[schwierigkeit]
                        && !spielfeld_array[z][s].getSichtbarkeit() && !spielfeld_array[z][s].getMarkierung()) {
                    // Und deckt diese dann auf.
                    deckeAuf(z, s);
                    if (spielfeld_array[z][s].getAnzahlUmliegenderViren() == 0) {
                        aufdeckenLeereFelder(z, s);
                    }
                }
            }
        }
    }

    /**
     * Diese Methode ist dafür das wenn man rechtsklick auf eine
     * VirusSweeperFestplatte macht Folgende Methoden triggert.
     * 
     * @param zeile
     * @param spalte
     */
    private void rechtsKlick(int zeile, int spalte) {
        if (!ende) {
            /*
             * Wenn noch nicht aufgedeckt und nicht markiert. Dann markiere es.
             */
            if (spielfeld_array[zeile][spalte].getSichtbarkeit() == false
                    && spielfeld_array[zeile][spalte].getMarkierung() == false) {
                AudioManager.VirusSweeperPlayMarkieren();
                spielfeld_array[zeile][spalte].setMarkierung(true);
                spielfeld_array[zeile][spalte].setBild();
                virusCounter.plusFlag();
                /*
                 * Wenn noch nicht aufgedeckt und markiert. Dann hebe die Markierung auf.
                 */
            } else if (spielfeld_array[zeile][spalte].getSichtbarkeit() == false
                    && spielfeld_array[zeile][spalte].getMarkierung() == true) {
                AudioManager.VirusSweeperPlayMarkierungentfernen();
                spielfeld_array[zeile][spalte].setMarkierung(false);
                spielfeld_array[zeile][spalte].setBild();
                virusCounter.minusFlag();

            }
            updateVirusCounter();
        }
    }

    /**
     * Diese Methode zählt die Viren im Umkreis von 1 von einer
     * VirusSweeperFestplatte.
     * 
     * @param zeile
     * @param spalte
     * @return
     */
    private int zähleViren(int zeile, int spalte) {
        int viren = 0;
        // Iteriert durch Zeilen
        for (int i = -1; i <= 1; i++) {
            // Iteriert durch spalten
            for (int j = -1; j <= 1; j++) {
                // Berechnet die Zeilenposition der NachbarVirusSweeperFestplatte
                int r = zeile + i;
                // Berechnet die Spaltenposition der NachbarVirusSweeperFestplatte
                int c = spalte + j;
                /*
                 * überprüft ob position innerhalb des Spielfelds liegt und prüft ob es ein
                 * Virus ist
                 */
                if (r >= 0 && r < GRID_GROESSE[schwierigkeit] && c >= 0 && c < GRID_GROESSE[schwierigkeit]
                        && spielfeld_array[r][c].getVirus() == true) {
                    viren++;
                }
            }
        }
        return viren;
    }

    /**
     * Diese Methode deckt Rekursiv die LeerenFelder auf.
     * 
     * @param zeile
     * @param spalte
     */
    private void aufdeckenLeereFelder(int zeile, int spalte) {
        // durchläuft die Zeilen um die aktuelle VirusSweeperFestplatte
        for (int i = -1; i <= 1; i++) {
            // durchläuft die Spalten um die aktuelle VirusSweeperFestplatte
            for (int j = -1; j <= 1; j++) {
                int z = zeile + i;
                int s = spalte + j;
                /*
                 * überprüft ob position innerhalb des Spielfelds liegt und prüft ob die
                 * VirusSweeperFestplatte bereits sichtbar ist und ob sie auch nicht markiert
                 * ist.
                 */
                if (z >= 0 && z < GRID_GROESSE[schwierigkeit] && s >= 0 && s < GRID_GROESSE[schwierigkeit]
                        && !spielfeld_array[z][s].getSichtbarkeit() && !spielfeld_array[z][s].getMarkierung()) {
                    deckeAuf(z, s);
                    /*
                     * Wenn die Anzahl der umliegenden Viren dieser VirusSweeperFestplatte ebenfalls
                     * 0 ist, wird die Methode rekursiv für diese Zelle aufgerufen.
                     */
                    if (spielfeld_array[z][s].getAnzahlUmliegenderViren() == 0) {
                        aufdeckenLeereFelder(z, s);
                    }
                }
            }
        }
    }

    /**
     * Diese Methode deckt die VirusSweeperFestplatte auf.
     * 
     * @param zeile
     * @param spalte
     */
    private void deckeAuf(int zeile, int spalte) {
        // Wenn die VirusSweeperFestplatte ein Virus ist
        if (spielfeld_array[zeile][spalte].getVirus()) {
            // Löse das ende aus
            if (!ende) {
                ende = true;
                easteregg = 0;
                spielfeld_array[zeile][spalte].setSichtbarkeit(true);
                spielfeld_array[zeile][spalte].setBild();
                zeitAnzeige.stopTimer();
                AudioManager.VirusSweeperPlayVerlorenSound();
                // Verzöger um 2 Sekunden, bevor der
                // SpielVerlorenBildschrim gezeigt wird
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    guiController.VirusSweeperSpielVerlorenBildschirm();
                }));
                timeline.play();
            }
        } else {
            // Wenn es kein Virus ist decke es auf und Schreibe in die
            // VirusSweeperFestplatte die AnzahlUmliegenderViren in anzeige. Und setze die
            // Farbe auf die passene Farbe
            AudioManager.VirusSweeperPlayClickSound();
            int textGroessenArray[] = { 50, 25, 20 };
            int anzahlUmliegenderViren = spielfeld_array[zeile][spalte].getAnzahlUmliegenderViren();
            String color = getColorForNumber(anzahlUmliegenderViren);
            Text text = new Text(String.valueOf(anzahlUmliegenderViren));
            text.setFont(Font.font("Verdana", FontWeight.BOLD, textGroessenArray[schwierigkeit]));
            text.setFill(Paint.valueOf(color));
            spielfeld_array[zeile][spalte].setGraphic(text);
            // Wenn es eine VirusSweeperFestplatte ist die noch nicht Sichtbar ist. Dann
            // setze die Scihtbarkeit auf true und zähle die aufgedeckteFelder hoch für die
            // Gewinn Kondition.
            if (!spielfeld_array[zeile][spalte].getSichtbarkeit()) {
                spielfeld_array[zeile][spalte].setSichtbarkeit(true);
                aufgedeckteFelder++;
            }
        }
    }

    /**
     * Diese Methode ist dazu da um für die Zahl die Angezeigt wird, die passende
     * Farb Id als String zu bekommen.
     * 
     * @param number
     * @return gibt Farb Id als String
     */
    private String getColorForNumber(int number) {
        switch (number) {
        case 1:
            return "#2A4B9A"; // blue
        case 2:
            return "#0D8136"; // green
        case 3:
            return "#E52621"; // red
        case 4:
            return "#283067"; // dark blue
        case 5:
            return "#7E1712"; // brown
        case 6:
            return "#058181"; // Cyan
        case 7:
            return "#010101"; // Black
        case 8:
            return "#808181"; // Grey
        default:
            return "White";
        }
    }

    /**
     * Setter für den VirusCounter
     * 
     * @param virusCounter
     */
    public void setVirusCounter(VirusCounter virusCounter) {
        this.virusCounter = virusCounter;
    }

    /**
     * Diese Methode updated den virusCounter auf den aktuellenWert.
     */
    public void updateVirusCounter() {
        if (virusCounter != null) {
            virusCounter.setText("Viren : " + virusCounter.getMarkierungen());
        }
    }

    /**
     * Deise Methode erstellt die Leiste über dem Spieleld, die mehrere Infos und
     * Buttons hat.
     * 
     * @return HBox mit allen objekten die dargestellt werden.
     */
    private HBox top() {
        setVirusCounter(new VirusCounter(schwierigkeit));
        zeitAnzeige = new ZeitAnzeige();
        // Erstellt Button zum Zurücksetzen
        Button btnReset = new Button("_Zurücksetzen");
        btnReset.setMinSize(90, 30);
        btnReset.setMnemonicParsing(true); // Damit man mit Alt + Z den Button betätigen kann
        btnReset.setOnAction(event -> guiController.VirusSweeperZuruecksetzenDesSpielfelds());
        // Erstellt Button zum aufrufen der Beschreibung
        Button btnBeschreibung = new Button("_Beschreibung");
        btnBeschreibung.setMinSize(90, 30);
        btnBeschreibung.setMnemonicParsing(true); // Damit man mit Alt + B den Button betätigen kann
        btnBeschreibung.setOnAction(event -> guiController.VirusSweeperBeschreibungGui());
        // Erstellt Button zum aufrufen der Schwierigkeits einstellungen
        Button BtnSchwierigkeit = new Button("_Schwierigkeit");
        BtnSchwierigkeit.setMinSize(90, 30);
        BtnSchwierigkeit.setMnemonicParsing(true); // Damit man mit Alt + S den Button betätigen kann
        BtnSchwierigkeit.setOnAction(event -> guiController.VirusSweeperSchwierigkeitsAuswahl());
        // packt virusCounter, BtnReset, btnBeschreibung, BtnSchwierigkeit, zeitAnzeige
        // in eine HBox(Zeile) mit einem abstand von 80
        HBox top = new HBox(80);
        top.getChildren().addAll(virusCounter, btnReset, btnBeschreibung, BtnSchwierigkeit, zeitAnzeige);
        top.setMinHeight(30);
        top.setMaxHeight(30);
        HBox.setHgrow(zeitAnzeige, Priority.ALWAYS);
        zeitAnzeige.setAlignment(Pos.CENTER_RIGHT);
        virusCounter.setAlignment(Pos.CENTER_LEFT);
        return top;
    }

    /**
     * Guckt ob im Spielfeld an der gegeben zeile und gegeben spalte. Die
     * AnzahlUmliegenderViren der VirusSweeperFestplatte die gleiche anzahl ist wie
     * die von den Markierten VirusSweeperFestplatte im Umkreis.
     * 
     * @param zeile
     * @param spalte
     * @return true wenn anzahl der Markierungen im umkreis = AnzahlUmliegenderViren
     *         ist
     */
    private boolean checkMarkierungen(int zeile, int spalte) {
        int markierungen = 0;
        /*
         * läuft im Spielfeld Array, einmal um alle VirusSweeperFestplatte im Umkreis
         * von 1 und erhöht die lokale Variable markierungen, wenn eine
         * VirusSweeperFestplatte markiert ist
         */
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int z = zeile + i;
                int s = spalte + j;
                if (z >= 0 && z < GRID_GROESSE[schwierigkeit] && s >= 0 && s < GRID_GROESSE[schwierigkeit]) {
                    if (spielfeld_array[z][s].getMarkierung()) {
                        markierungen++;
                    }
                }
            }
        }
        return markierungen == spielfeld_array[zeile][spalte].getAnzahlUmliegenderViren();
    }

    /**
     * 
     * @param height
     * @param width
     * @param guiController
     * @param schwierigkeit
     * @return gibt die VirusSweeper instance zurück
     */
    public static VirusSweeper getInstance(int height, int width, GUIController guiController, int schwierigkeit) {
        if (instance == null) {
            instance = new VirusSweeper(height, width, guiController, schwierigkeit);
        }
        return instance;
    }

    /**
     * @return gibt den SchwierigkeitsGrad zurück, mit welcher gerade gespielt wird.
     */
    public int getSchwierigkeit() {
        return schwierigkeit;
    }

    /**
     * @return gibt die VirusSweeperScene zurück
     */
    public Scene getScene() {
        return VirusSweeperScene;
    }

    /**
     * Diese Methode resettet die Instance. Außerdem wird die Easteregg kondition
     * geprüft.
     */
    public static void resetInstance() {
        if (easteregg == 10) {
            GUIController.VirusSweeperEasterEggGui();
            easteregg = 0;
        }
        easteregg = easteregg + 1;
        instance = null;
    }
}