package Snake;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class Manual {
    private Scene scene;
    
    public Manual() {
        Label label = new Label();
        String manualText = String.format("Welcome to CyberSnake! \nPress \"Play\" to start the game.\n"
                +"Sammel so viel Eis ein wie moeglich. \nAber Vorsicht! Die Schlange darf \nnicht an die  "
                +"Hindernisse stossen!\n"
                +"Benutz die Pfeiltasten oder wasd \num die Schlange zu navigieren. \n"
                +"Klick \"N\" um das Eis an einer \nanderen Stelle zu platzieren. \n"
                +"Klick escape oder backspace um direkt \nzurück zum Menu zu kommen.\n");
        label.setText(manualText);
        ScrollPane root = new ScrollPane(label);
        scene = new Scene(root, 500, 300);
        scene.getStylesheets().add(getClass().getResource("manualStyle.css").toExternalForm());
    }
    public Scene getScene() {
        return scene;
    }
}
