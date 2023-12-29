package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Autor: Henschell Hans Rackebrandt
 * Datum: Das aktuelle Datum
 * 
 * MemoryCard ist eine Klasse, die eine Karte für ein Memory-Spiel repräsentiert.
 * Sie erbt von StackPane und enthält zwei ImageView-Objekte, die das Vorder- und Rückbild der Karte repräsentieren.
 * 
 * Die Klasse bietet Methoden zum Umdrehen der Karte, zum Überprüfen, ob zwei Karten übereinstimmen, und zum Löschen einer Karte aus dem Spiel.
 * 
 * Die Klasse verwendet die JavaFX-Bibliothek für die Bildverarbeitung und erfordert daher die Einbindung der entsprechenden Bibliothek in das Projekt.
 */

public class MemoryCard extends StackPane{

    private ImageView frontImage;
    private ImageView backImage;
    private boolean isFlipped;
    final int size = 80;
    
    /**
     * Konstruktor für die MemoryCard-Klasse.
     * Initialisiert die Vorder- und Rückseite der Karte mit den übergebenen Bildern und fügt sie dem StackPane hinzu.
     * @param front Das Bild für die Vorderseite der Karte.
     * @param back Das Bild für die Rückseite der Karte.
     */
    
    public MemoryCard(Image front,Image back) {
        frontImage = new ImageView(front);
        frontImage.setFitWidth(size);
        frontImage.setFitHeight(size);
        backImage = new ImageView(back);
        backImage.setFitWidth(size);
        backImage.setFitHeight(size);
        isFlipped = false;
        this.getChildren().addAll(frontImage,backImage);
    }
    
    /**
     * Dreht die Karte um. Wenn die Karte bereits umgedreht ist, wird sie zurückgedreht, und umgekehrt.
     * Spielt außerdem einen Soundeffekt ab, wenn die Karte umgedreht wird.
     */
    
    public void flipImage() {
        AudioManager.playSoundEffect();
        if (isFlipped) {
            backImage.toFront();
        } else {
            frontImage.toFront();
        }
        isFlipped = !isFlipped;
    }
    
    /**
     * Überprüft, ob diese Karte mit einer anderen Karte übereinstimmt.
     * Zwei Karten gelten als übereinstimmend, wenn ihre Vorderseitenbilder gleich sind.
     * @param other Die andere Karte, mit der verglichen werden soll.
     * @return true, wenn die Karten übereinstimmen, false sonst.
     */
    
    public boolean matches(MemoryCard other) {
        return this.frontImage.getImage().equals(other.frontImage.getImage());
    }
    
    /**
     * Entfernt die Karte aus dem Spiel, indem sie unsichtbar und deaktiviert gemacht wird.
     */
    
    public void deleteCard() {
        //this.getChildren().clear();
        this.setVisible(false);
        this.setDisable(true);
    }
}
