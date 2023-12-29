package Labyrinth;

import javafx.scene.layout.Pane;

public class VirusContainer {
    private Pane pane;
    private Virus virus;
    
    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Virus getVirus() {
        return virus;
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

    public VirusContainer(Virus virus) {
        this.virus = virus;
        pane = new Pane();
        pane.getChildren().add(virus.getView());
    }
    
}
