package VirusSweeper;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Diese Klasse VirusSweeperFestplatte stellt einen Button da. Und wird dafür
 * verwendet, um als Interaktieve Objekte im Spiel darzustellen. Die verschiede
 * eigenschaften haben können.
 * 
 * @author Marc Pauluhn
 */
public class VirusSweeperFestplatte extends Button {
    private static final Image TRANSPARENT_IMAGE = createTransparentImage();
    private ImageView imageView = new ImageView();
    static Image virusBild;
    static Image markierungBild;
    private int zeile;
    private int spalte;
    // Anzahl der umliegenden Viren (0-9)
    private int anzahlUmliegenderViren;
    // Zeigt an, ob die Zelle ein Virus enthält
    private boolean virus;
    // Zeigt an, ob die Zelle sichtbar ist (aufgedeckt)
    private boolean sichtbarkeit;
    // Zeigt an, ob die Zelle markiert ist
    private boolean markierung;

    /**
     * Konstruktor für die VirusSweeperFestplatte-Klasse
     * 
     * @param zeile                  des Buttons
     * @param spalte                 des Buttons
     * @param anzahlUmliegenderViren des Buttons
     * @param virus                  oder kein Virus
     * @param sichtbar               oder nicht sichtbar
     * @param markiert               oder nicht markiert
     */
    VirusSweeperFestplatte(int zeile, int spalte, int anzahlUmliegenderViren, boolean virus, boolean sichtbarkeit,
            boolean markierung) {
        // Initialisierung der Zelleneigenschaften
        this.setZeile(zeile);
        this.setSpalte(spalte);
        this.setAnzahlUmliegenderViren(anzahlUmliegenderViren);
        this.setVirus(virus);
        this.setSichtbarkeit(sichtbarkeit);
        this.setMarkierung(markierung);
        // Setzen des Bildes basierend auf der Sichtbarkeit und der Größe
        setBild();
        imageView.setFitWidth(this.getWidth());
        imageView.setFitHeight(this.getHeight());
        // EventListener für die Größenänderungen des Buttons hinzufügen
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                aktualisiereBildGroesse();
            }
        });
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                aktualisiereBildGroesse();
            }
        });
    }

    /**
     * @return Gibt die Zeilenposition der VirusSweeperFestplatte zurück
     */
    public int getZeile() {
        return zeile;
    }

    /**
     * Setzt die Zeilenposition der VirusSweeperFestplatte
     * 
     * @param zeilen position
     */
    public void setZeile(int zeile) {
        this.zeile = zeile;
    }

    /**
     * @return Gibt die Spaltenposition der VirusSweeperFestplatte zurück
     */
    public int getSpalte() {
        return spalte;
    }

    /**
     * Setzt die Spaltenposition der VirusSweeperFestplatte
     * 
     * @param spalten position
     */
    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }

    /**
     * @return Gibt die Anzahl der umliegenden Viren zurück
     */
    public int getAnzahlUmliegenderViren() {
        return anzahlUmliegenderViren;
    }

    /**
     * Setzt die Anzahl der umliegenden Viren
     * 
     * @param anzahlUmliegenderViren
     */
    public void setAnzahlUmliegenderViren(int anzahlUmliegenderViren) {
        this.anzahlUmliegenderViren = anzahlUmliegenderViren;
    }

    /**
     * @return Gibt zurück, ob die VirusSweeperFestplatte ein Virus ist
     */
    public boolean getVirus() {
        return virus;
    }

    /**
     * Setzt ob es ein Virus ist oder nicht
     * 
     * @param virus
     */
    public void setVirus(boolean virus) {
        this.virus = virus;
    }

    /**
     * @return Gibt zurück, ob die VirusSweeperFestplatte sichtbar ist (aufgedeckt)
     */
    public boolean getSichtbarkeit() {
        return sichtbarkeit;
    }

    /**
     * setzt ob es sichtbar ist oder nicht
     * 
     * @param sichtbarkeit
     */
    public void setSichtbarkeit(boolean sichtbarkeit) {
        this.sichtbarkeit = sichtbarkeit;
    }

    /**
     * @return Gibt zurück, ob die VirusSweeperFestplatte markiert ist
     */
    public boolean getMarkierung() {
        return markierung;
    }

    /**
     * Setzt ob es Markiert ist oder nicht
     * 
     * @param markierung
     */
    public void setMarkierung(boolean markierung) {
        this.markierung = markierung;
    }

    /**
     * @return bild des Virus
     */
    public static Image getVirusbild() {
        if (virusBild == null) {
            setVirusbild();
        }
        return virusBild;
    }

    /**
     * sezt das Bild des Virus
     */
    public static void setVirusbild() {
        try {
            String bildPfad = "images/VirusSweeper/Virus.png";
            File datei = new File(bildPfad);
            // Überprüfe, ob die Datei existiert, bevor das image auf null gesezt wird
            if (datei.exists()) {
                virusBild = new Image("file:" + bildPfad);
            } else {
                // wenn es die Datei nicht findet, wird folgendes ausgegeben
                System.out.println("Virusbild nicht gefunden: Die Datei " + bildPfad + " existiert nicht.");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden des Virusbild:");
            e.printStackTrace();
        }
    }

    /**
     * @return bild der Markierung
     */
    public static Image getMarkierungbild() {
        if (markierungBild == null) {
            setMarkierungbild();
        }
        return markierungBild;
    }

    /**
     * sezt das Bild der Markierung
     */
    public static void setMarkierungbild() {
        try {
            String bildPfad = "images/VirusSweeper/Markierung.png";
            File datei = new File(bildPfad);
            // Überprüfe, ob die Datei existiert, bevor das image auf null gesezt wird
            if (datei.exists()) {
                markierungBild = new Image("file:" + bildPfad);
            } else {
                // wenn es die Datei nicht findet, wird folgendes ausgegeben
                System.out.println("Markierungsbild nicht gefunden: Die Datei " + bildPfad + " existiert nicht.");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden des Markierungsbilds:");
            e.printStackTrace();
        }
    }

    /**
     * Setzt das Bild der VirusSweeperFestplatte bassierend auf zustand
     */
    void setBild() {
        // wenn es nicht aufgedeckt ist
        if (!getSichtbarkeit()) {
            // setze es auf ein durchsichtiges Bild
            imageView.setImage(TRANSPARENT_IMAGE);
            // wenn es Markiert ist
            if (getMarkierung()) {
                // setze es auf das markierungsbild
                imageView.setImage(getMarkierungbild());
            }
        }
        // wenn es aufgedeckt ist und Sichtbar
        if (getVirus() && getSichtbarkeit()) {
            // setze es auf Virusbild
            imageView.setImage(getVirusbild());
        }
        setGraphic(imageView);
        aktualisiereBildGroesse();
    }

    /**
     * Methode zum Aktualisieren der Größe des Bildes basierend auf der Größe des
     * Buttons anzupassen
     */
    void aktualisiereBildGroesse() {
        Node graphic = getGraphic();
        if (graphic instanceof ImageView) {
            ImageView imageView = (ImageView) graphic;
            imageView.setFitWidth(this.getWidth());
            imageView.setFitHeight(this.getHeight());
        }
    }

    /**
     * @return gibt ein transparentes(durchsichtitges) Image zurück
     */
    private static Image createTransparentImage() {
        int imageSize = 1000;
        WritableImage transparentImage = new WritableImage(imageSize, imageSize);
        PixelWriter pixelWriter = transparentImage.getPixelWriter();
        // Füllen Sie das Bild mit transparenter Farbe (ARGB: 0x00FFFFFF)
        for (int y = 0; y < imageSize; y++) {
            for (int x = 0; x < imageSize; x++) {
                pixelWriter.setArgb(x, y, 0x00FFFFFF);
            }
        }
        return transparentImage;
    }
}