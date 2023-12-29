package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Autor: Henschell Hans Rackebrandt
 * 
 * ImageManager ist eine Klasse, die für die Verwaltung von Bildern in einer Anwendung verantwortlich ist.
 * Sie bietet Methoden zum Laden von Bildern aus einem Ordner, zum Erstellen von Bildpaaren für ein Memory-Spiel,
 * und zum Abrufen von Hintergrundbildern und Vorschaubildern.
 * 
 * Die Klasse verwendet die JavaFX-Bibliothek für die Bildverarbeitung und erfordert daher die Einbindung der entsprechenden Bibliothek in das Projekt.
 * 
 * Die Pfade zu den Bilddateien sind fest codiert und müssen daher bei einer Änderung der Dateistruktur oder des Dateinamens angepasst werden.
 * 
 * Die Klasse ist statisch und erfordert daher nicht das Erstellen einer Instanz zum Aufrufen ihrer Methoden.
 */

public class ImageManager {
    
    /**
     * Lädt alle JPG-Bilder aus dem Ordner "images".
     * @return Eine ArrayList von Image-Objekten, die die geladenen Bilder repräsentieren.
     */
    
    public static ArrayList<Image> loadJPGImagesFromBin(){
        
        File imagesDirectory = new File("images");
        File[] files = imagesDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
        ArrayList<Image> images = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                images.add(new Image("file:" + file.getPath()));
            }
        }
        return images;
    }
    
    /**
     * Erstellt eine Liste von 24 zufällig ausgewählten Bildern aus der übergebenen Liste und verdoppelt diese.
     * Die resultierende Liste wird gemischt und zurückgegeben.
     * @param storedImages Eine Liste von Image-Objekten, aus denen die Auswahl getroffen wird.
     * @return Eine gemischte Liste von 48 Image-Objekten.
     */
    
    public static ArrayList<Image> create24Paair(ArrayList<Image> storedImages){
        ArrayList<Image> choosenImages = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            choosenImages.add(storedImages.get(i));
        }
        ArrayList<Image> copiedChoosenImages = new ArrayList<>(choosenImages);
        choosenImages.addAll(copiedChoosenImages);
        Collections.shuffle(choosenImages);
        return choosenImages;
    }
    
    /**
     * Erstellt und gibt ein Background-Objekt zurück, das das Standardhintergrundbild repräsentiert.
     * @return Ein Background-Objekt, das das Standardhintergrundbild repräsentiert.
     */
    
    public static Background getDefaultBackground() {
        BackgroundImage defaultbackGroundImage = new BackgroundImage(getBackgroundImage(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background defaultBackGround = new Background(defaultbackGroundImage);
        return defaultBackGround;
       
    }
    
    /**
     * Erstellt und gibt ein Background-Objekt zurück, das das Game-Over-Hintergrundbild repräsentiert.
     * @return Ein Background-Objekt, das das Game-Over-Hintergrundbild repräsentiert.
     */
    
    public static Background getGameOverBackground() {
        BackgroundImage gameOverBackGroundImage = new BackgroundImage(getGameOverScreenImage(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background gameOverBackGround = new Background(gameOverBackGroundImage);
        return gameOverBackGround;
       
    }
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das Standardhintergrundbild repräsentiert.
     * @return Ein ImageView-Objekt, das das Standardhintergrundbild repräsentiert.
     */
    
    public static ImageView getBackgroundImageView() {
        ImageView backgroundview = new ImageView(getBackgroundImage());
        return backgroundview;
    }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das Standardhintergrundbild repräsentiert.
     * @return Ein ImageView-Objekt, das das Standardhintergrundbild repräsentiert.
     */
    
    public static Image getBackgroundImage() {
        Image backgroundimage = new Image("file:images/Memory/BA2.gif");
        return backgroundimage;
    }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das erste Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview1() {
       ImageView gamePreview1 = new ImageView(new Image("file:images/Memory/Memory.jpg"));
       return gamePreview1;
    }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das zweite Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview2() {
        ImageView gamePreview1 = new ImageView(new Image("file:images/VirusSweeper/VirusSweeperPreview.png"));
        return gamePreview1;
     }
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das dritte Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview3() {
        ImageView gamePreview1 = new ImageView(new Image("file:images/Memory/Loading3.gif"));
        return gamePreview1;
     }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das vierte Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview4() {
        ImageView gamePreview1 = new ImageView(new Image("file:images/gifs/Loading1.gif"));
        return gamePreview1;
     }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das fünfte Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview5() {
        ImageView gamePreview1 = new ImageView(new Image("file:images/JumpRun/JumpRun.gif"));
        return gamePreview1;
     }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das sechste Vorschaubild für ein Spiel repräsentiert.
     * @return Ein ImageView-Objekt, das das erste Vorschaubild repräsentiert.
     */
    
    public static ImageView getGamePreview6() {
        ImageView gamePreview1 = new ImageView(new Image("file:images/Quiz/Quiz.gif"));
        return gamePreview1;
     }
    
    /**
     * Erstellt und gibt ein ImageView-Objekt zurück, das das Game-Over-Bild repräsentiert.
     * @return Ein ImageView-Objekt, das das Game-Over-Bild repräsentiert.
     */
    
    public static ImageView getGameOverScreenView() {
        ImageView gameOverImage = new ImageView(getGameOverScreenImage());
        return gameOverImage;
    }
    
    /**
     * Gibt ein Image-Objekt zurück, das das Game-Over-Bild repräsentiert.
     * @return Ein Image-Objekt, das das Game-Over-Bild repräsentiert.
     */
    
    public static Image getGameOverScreenImage() {
        Image gOver = new Image("file:images/Memory/GameOver1.gif");
        return gOver;
    }
}
