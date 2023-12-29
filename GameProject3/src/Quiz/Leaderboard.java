package Quiz;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Leaderboard extends Application {
    
    // ObservableList, um Änderungen in der Liste automatisch zu aktualisieren
    private ObservableList<Player> players = FXCollections.observableArrayList();
    
    // Name der Datei für die Datenspeicherung
    private final String dataFileName = "leaderboard_data.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Leaderboard");

        
        // Lade zuvor gespeicherte Daten
        loadSavedData();

        
        // TableView zur Anzeige der Spielerdaten
        TableView<Player> tableView = new TableView<>();
        tableView.setEditable(false);
        
        
        // Spalten für Name und Level
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setMinWidth(100);

        
        TableColumn<Player, Integer> levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty().asObject());
        levelColumn.setMinWidth(100);

        
        // Hinzufügen der Spalten zur TableView
        tableView.getColumns().addAll(nameColumn, levelColumn);

        
        // Setzen der Spielerdaten in die TableView
        tableView.setItems(players);

        
        // Textfelder und Button für die Eingabe neuer Spielerdaten
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        
        TextField levelInput = new TextField();
        levelInput.setPromptText("Level");

        
        Button addButton = new Button("Hinzufügen");
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
         // Überprüfen, ob die Level-Eingabe eine gültige Ganzzahl im Bereich von 1 bis 3 ist
            try {
                int level = Integer.parseInt(levelInput.getText());
                if (level < 1 || level > 3) {
                    showAlert("Ungültiges Level. Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
                    return; // Beende die Methode, um die Spieler nicht hinzuzufügen
                }

                // Füge den Spieler hinzu und speichere die aktualisierten Daten
                players.add(new Player(name, level));
                nameInput.clear();
                levelInput.clear();
                saveData();
            } catch (NumberFormatException ex) {
                showAlert("Ungültiges Level. Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
            }
        });

        
        // Erstelle einen Zurück-Button
        Button backButton = new Button("Zurück");
        backButton.setOnAction(event -> {
            // Hier können Sie den Code hinzufügen, um zur Hauptseite zurückzukehren
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });

        
        // HBox für die Anordnung der Eingabefelder und des Hinzufügen-Buttons
        HBox inputBox = new HBox(nameInput, levelInput, addButton);
        inputBox.setSpacing(10);

        
        // VBox für die Anordnung von TableView, Eingabefeldern und Zurück-Button
        VBox layout = new VBox();
        layout.getChildren().addAll(tableView, inputBox, backButton);

        
        // Erstellen der Szene
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    // Player-Klasse für die Repräsentation eines Spielers
    public class Player {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty level;

        public Player(String name, int level) {
            this.name = new SimpleStringProperty(name);
            this.level = new SimpleIntegerProperty(level);
        }

        public String getName() {
            return name.get();
        }

        public int getLevel() {
            return level.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public SimpleIntegerProperty levelProperty() {
            return level;
        }
    }

    
    // Methode zum Speichern der Daten in einer Datei
    private void saveData() {
        try (PrintWriter writer = new PrintWriter(dataFileName)) {
            for (Player player : players) {
                writer.println(player.getName() + "," + player.getLevel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Methode zum Laden der zuvor gespeicherten Daten
    private void loadSavedData() {
        File dataFile = new File(dataFileName);
        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int level = Integer.parseInt(parts[1]);
                        players.add(new Player(name, level));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Methode zum Anzeigen von Warnmeldungen
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warnung");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}