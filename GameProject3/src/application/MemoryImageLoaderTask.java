package application;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

/**
 * Autor: Henschell Hans Rackebrandt
 * 
 * MemoryImageLoaderTask ist eine Klasse, die einen Task für das Laden von Bildern in einem Memory-Spiel implementiert.
 * Sie erbt von der Task-Klasse von JavaFX und überschreibt die call-Methode, um das Laden der Bilder durchzuführen.
 * 
 * Die Klasse verwendet die ImageManager-Klasse zum Laden der Bilder.
 */

public class MemoryImageLoaderTask extends Task<ArrayList<Image>>{

    /**
     * Lädt alle JPG-Bilder aus dem Ordner "images" und gibt sie als ArrayList von Image-Objekten zurück.
     * @return Eine ArrayList von Image-Objekten, die die geladenen Bilder repräsentieren.
     * @throws Exception Wenn beim Laden der Bilder ein Fehler auftritt.
     */
    
    @Override
    protected ArrayList<Image> call() throws Exception {
        return ImageManager.loadJPGImagesFromBin();
    }

}
