package Labyrinth;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerate {
    
    /// Enter the rows, clms, and delay duration in milliseconds
    private static Maze maze;
    private static int cellWidth;
    private char difficulty;
    private static int tds;
    private static final int TDSE = 120;
    private static final int TDSM = 300;
    private static final int TDSH = 420;
    private Timeline timeline;
    private int tdss;
    private static boolean status = true;
    
    
    
    public static Maze getMaze() {
        return maze;
    }

    public static void setMaze(Maze maze) {
        MazeGenerate.maze = maze;
    }

    public static int getCellWidth() {
        return cellWidth;
    }

    public static void setCellWidth(int cellWidth) {
        MazeGenerate.cellWidth = cellWidth;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(char difficulty) {
        this.difficulty = difficulty;
    }

    public static int getTds() {
        return tds;
    }

    public static void setTds(int tds) {
        MazeGenerate.tds = tds;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public int getTdss() {
        return tdss;
    }

    public void setTdss(int tdss) {
        this.tdss = tdss;
    }

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        MazeGenerate.status = status;
    }

    public static int getTdse() {
        return TDSE;
    }

    public static int getTdsm() {
        return TDSM;
    }

    public static int getTdsh() {
        return TDSH;
    }

    public MazeGenerate() {
        if(Labyrinth.getDiff() == 'E') {
            tdss = TDSE;
            tds = tdss;
            System.out.println(tdss);
            this.difficulty = Labyrinth.getDiff();
            maze = new Maze(13, 20, 20);
            cellWidth = 40;
        } else if(Labyrinth.getDiff()== 'M') {
            tdss = TDSM;
            tds = tdss;
            this.difficulty = Labyrinth.getDiff();
            maze = new Maze(26, 40, 2);
            cellWidth = 20;
        } else if(Labyrinth.getDiff() == 'H') {
            tdss = TDSH;
            tds = tdss;
            this.difficulty = Labyrinth.getDiff();
            maze = new Maze(26, 40, 2);
            cellWidth = 20;
        }
    }
    
    public void winning(Stage primaryStage) {
        Image youWin = new Image("file:YouWin.gif");
        ImageView imageView = new ImageView(youWin);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
//        Image background = new Image("file:MazeBackground");
//        ImageView backgroundView = new ImageView(background);
//        backgroundView.setFitWidth(800);
//        backgroundView.setFitHeight(600);
        StackPane root = new StackPane();
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setPrefSize(200, 60);
//        playAgainButton.setBackground(Background.EMPTY);
        playAgainButton.setTextFill(Labyrinth.getButtonTextColor());
        playAgainButton.setFont(Labyrinth.getButtonFont());
        playAgainButton.setOnAction(event -> {
            Labyrinth.setGen(new MazeGenerate());
            Labyrinth.getGen().setScene(primaryStage);
        });
        
        Button changedifficultyButton = new Button("Difficulty: " + Labyrinth.getDifficulty());
        changedifficultyButton.setPrefSize(200, 60);

        // Event handler for changing the difficulty
        changedifficultyButton.setOnAction(event -> {
            // Toggle between "Easy" and "Hard" difficulty
            Labyrinth.setDifficulty(Labyrinth.getDifficulty().equals("Easy") ? "Medium" : Labyrinth.getDifficulty().equals("Medium") ? "Hard" : "Easy");
            switch (Labyrinth.getDifficulty()) {
                case "Easy":
                    Labyrinth.setDiff('E');
                    break;
                case "Medium":
                    Labyrinth.setDiff('M');
                    break;
                case "Hard":
                    Labyrinth.setDiff('H');
                    break;
                default:
                    Labyrinth.setDiff('U'); // 'U' for Unknown or an appropriate default value
                    break;
            }
            System.out.println("Difficulty changed to: " + Labyrinth.getDifficulty());
            changedifficultyButton.setText("Difficulty: " + Labyrinth.getDifficulty());
        });
        changedifficultyButton.setTextFill(Labyrinth.getButtonTextColor());
//        changedifficultyButton.setBackground(Background.EMPTY);
        changedifficultyButton.setFont(Labyrinth.getButtonFont());
        
        
        Button returnButton = new Button("Back to Main Menu");
        returnButton.setPrefSize(200, 60);
//        returnButton.setBackground(Background.EMPTY);
        returnButton.setTextFill(Labyrinth.getButtonTextColor());
        returnButton.setFont(Labyrinth.getButtonFont());
        returnButton.setOnAction(event -> {
            Labyrinth.setGen(null);
            primaryStage.setScene(Labyrinth.menu(primaryStage));
        });
        
        HBox buttons = new HBox(50);
        buttons.getChildren().addAll(playAgainButton, changedifficultyButton, returnButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        
        root.getChildren().addAll(imageView, buttons);
        timeline.stop();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        
        
    }
    
    public void losing(Stage primaryStage) {
        Image youLose = new Image("file:YouLose.gif");
        ImageView imageView = new ImageView(youLose);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
//        Image background = new Image("file:MazeBackground");
//        ImageView backgroundView = new ImageView(background);
//        backgroundView.setFitWidth(800);
//        backgroundView.setFitHeight(600);
        StackPane root = new StackPane();
        
        Button retryButton = new Button("Retry");
        retryButton.setPrefSize(200, 60);
//        retryButton.setBackground(Background.EMPTY);
        retryButton.setTextFill(Labyrinth.getButtonTextColor());
        retryButton.setFont(Labyrinth.getButtonFont());
        retryButton.setOnAction(event -> {
            Labyrinth.setGen(new MazeGenerate());
            Labyrinth.getGen().setScene(primaryStage);
        });
        
        Button changedifficultyButton = new Button("Difficulty: " + Labyrinth.getDifficulty());
        changedifficultyButton.setPrefSize(200, 60);

        // Event handler for changing the difficulty
        changedifficultyButton.setOnAction(event -> {
            // Toggle between "Easy" and "Hard" difficulty
            Labyrinth.setDifficulty(Labyrinth.getDifficulty().equals("Easy") ? "Medium" : Labyrinth.getDifficulty().equals("Medium") ? "Hard" : "Easy");
            switch (Labyrinth.getDifficulty()) {
                case "Easy":
                    Labyrinth.setDiff('E');
                    break;
                case "Medium":
                    Labyrinth.setDiff('M');
                    break;
                case "Hard":
                    Labyrinth.setDiff('H');
                    break;
                default:
                    Labyrinth.setDiff('U'); // 'U' for Unknown or an appropriate default value
                    break;
            }
            System.out.println("Difficulty changed to: " + Labyrinth.getDifficulty());
            changedifficultyButton.setText("Difficulty: " + Labyrinth.getDifficulty());
        });
        changedifficultyButton.setTextFill(Labyrinth.getButtonTextColor());
//        changedifficultyButton.setBackground(Background.EMPTY);
        changedifficultyButton.setFont(Labyrinth.getButtonFont());
        
        
        Button returnButton = new Button("Back to Main Menu");
        returnButton.setPrefSize(200, 60);
//        returnButton.setBackground(Background.EMPTY);
        returnButton.setTextFill(Labyrinth.getButtonTextColor());
        returnButton.setFont(Labyrinth.getButtonFont());
        returnButton.setOnAction(event -> {
            Labyrinth.setGen(null);
            primaryStage.setScene(Labyrinth.menu(primaryStage));
        });
        
        HBox buttons = new HBox(50);
        buttons.getChildren().addAll(retryButton, changedifficultyButton, returnButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        
        root.getChildren().addAll(imageView, buttons);
        timeline.stop();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        
    }
    
    public void setScene(Stage primaryStage) {
        Pane root = new Pane();
        Pane groot = new Pane();
        Pane vroot = new Pane();
        Pane kroot = new Pane();
        VBox vbox = new VBox();
        StackPane sroot = new StackPane();
        StackPane uroot = new StackPane();
        uroot.setAlignment(Pos.TOP_CENTER);
        uroot.setPrefSize(800, 80);
        sroot.setAlignment(Pos.BOTTOM_CENTER);
        
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        vbox.setPrefSize(800, 600);
        sroot.setPrefSize(800, 520);
        Canvas canvas = new Canvas(maze.clms * cellWidth, maze.rows * cellWidth);
        root.getChildren().add(canvas);
        Label timerLabel = new Label(formatTime(tds));
        timerLabel.setPrefSize(50, 50);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                print(canvas.getGraphicsContext2D());
            }
        };
        

        
     // Create a ProgressBar to visually represent the timer
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefSize(300, 50);
        uroot.getChildren().addAll(progressBar, timerLabel);
        timerLabel.setLayoutX(50);
        timerLabel.setLayoutY(50);
        // Create a Timeline for the visual timer
//        Timer gtimer = new Timer(tds);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event ->{
            
            tds--;
            timerLabel.setText(formatTime(tds));
            progressBar.setProgress((double)(tds)/tdss);
            if (tds <= 0) {
                // Perform any action when the timer reaches zero
                System.out.println("Time's up");
                timeline.stop();
                losing(primaryStage);
                
            }
    }));
       
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        timeline.play();
        
        
        Random rand = new Random();
        int vs = rand.nextInt(((maze.rows * maze.clms) / 10));
        ArrayList<VirusContainer> viruses = new ArrayList<VirusContainer>();
        for(int i = 0; i < vs; i++) {
            Virus virus = new Virus(maze.clms, maze.rows, cellWidth);
            VirusContainer vc = new VirusContainer(virus);
            vc.getVirus().getView().setFitWidth(cellWidth);
            vc.getVirus().getView().setFitHeight(cellWidth);
            viruses.add(vc);
        }
        
        //Erzeugen eines Robot Objekt
        Robot robot = new Robot(0, maze.rows, cellWidth);
        ImageView robotView = new ImageView(robot.getGraphic());
        robotView.setFitWidth(cellWidth);
        robotView.setFitHeight(cellWidth);
        groot.getChildren().add(robotView);
        groot.setLayoutX(robot.getCellX());
        groot.setLayoutY(robot.getCellY());
        sroot.setMargin(groot, new Insets(robot.getPosY(), 0, 0, robot.getPosX()));
        // Ende Von Erzeugen eines Robot Objekt
        
        Door door = new Door(maze.clms, maze.rows, cellWidth);
        door.getView().setFitWidth(cellWidth);
        door.getView().setFitHeight(cellWidth);
        vroot.getChildren().add(door.getView());
        vroot.setLayoutX(door.getCellX());
        vroot.setLayoutY(door.getCellY());
        door.getView().setVisible(difficulty == 'E' || difficulty == 'M');
        sroot.setMargin(vroot, new Insets(door.getPosY(), 0, 0, door.getPosX()));
        Key key = new Key(maze.clms, maze.rows, cellWidth);
        key.getView().setFitWidth(cellWidth);
        key.getView().setFitHeight(cellWidth);
        kroot.getChildren().add(key.getView());
        kroot.setLayoutX(key.getCellX());
        kroot.setLayoutY(key.getCellY());
        key.getView().setVisible(difficulty == 'H');
        sroot.setMargin(kroot, new Insets(key.getPosY(), 0, 0, key.getPosX()));
        
        
        
        sroot.getChildren().addAll(root, groot, vroot, kroot);
     // Set the alignment of the StackPane within the VBox (bottom-center)
        VBox.setVgrow(sroot, javafx.scene.layout.Priority.ALWAYS);
        vbox.getChildren().addAll(uroot, sroot);
        for(int i = 0; i < viruses.size(); i++) {
            viruses.get(i).getPane().setLayoutX(viruses.get(i).getVirus().getCellX());
            viruses.get(i).getPane().setLayoutY(viruses.get(i).getVirus().getCellY());
            sroot.setMargin(viruses.get(i).getPane(), new Insets(viruses.get(i).getVirus().getPosY(), 0, 0,viruses.get(i).getVirus().getPosX()));
            sroot.getChildren().add(viruses.get(i).getPane());
        }
        
        
        
        Thread thread = new Thread(() -> {
            maze.createMaze();
        });
        
        
        
        ToggleButton musicmute = new ToggleButton("Music: " + (Labyrinth.isMusicstat() ? "ON" : Labyrinth.isMusicstat()==false ? "OFF" : "ON"));
        musicmute.setPrefSize(200, 60);

        // Event handler for the mute button
        musicmute.setOnAction(event -> {
            if (musicmute.isSelected() && Labyrinth.isMusicstat()) {
                Labyrinth.getMediaPlayer().setMute(true); // Mute the music
                musicmute.setText("Music: OFF");
                Labyrinth.setMusicstat(false);
            } else {
                Labyrinth.getMediaPlayer().setMute(false); // Unmute the music
                musicmute.setText("Music: ON");
                Labyrinth.setMusicstat(true);
            }
        });
        musicmute.setTextFill(Labyrinth.getButtonTextColor());
//        musicmute.setBackground(Background.EMPTY);
        musicmute.setFont(Labyrinth.getButtonFont());
        
        Button back = new Button("Back to Menu");
        back.setPrefSize(200, 60);
        back.setTextFill(Labyrinth.getButtonTextColor());
//        back.setBackground(Background.EMPTY);
        back.setFont(Labyrinth.getButtonFont());
        
        back.setOnAction(event -> {
            status = true;
            Labyrinth.setGen(null);
            primaryStage.setScene(null);
            primaryStage.setScene(Labyrinth.menu(primaryStage));
        });
        
        
        Counter counter = new Counter(vs);
        Rectangle overlay = new Rectangle(0, 0, 800, 600);
        overlay.setFill(Color.rgb(0, 0, 0, 0.5));
        Text pausedText = new Text("Game Paused");
        pausedText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 57));
        pausedText.setFill(Color.BLUE);
        scene.setOnKeyPressed(event -> {
            if (status == false && event.getCode() != KeyCode.ESCAPE) {
                // Game is paused, block all inputs except ESCAPE
                return;
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                if (status == true) {
                    timeline.stop();
                    status = false;
                    pausedText.setLayoutX(800);
                    pausedText.setLayoutY(600);
                    pausedText.setTextAlignment(TextAlignment.CENTER);
                    sroot.getChildren().add(overlay);
                    uroot.setMargin(musicmute, new Insets(0, 0, 0, -600));
                    uroot.setMargin(back, new Insets(0, 0, 0, 600));
                    uroot.getChildren().addAll(pausedText, musicmute, back);
                    progressBar.setVisible(false);
                    timerLabel.setVisible(false);
                    
                } else {
                    timeline.play();
                    status = true;
                    sroot.getChildren().remove(overlay);
                    uroot.getChildren().removeAll(pausedText, musicmute, back);
                    progressBar.setVisible(true);
                    timerLabel.setVisible(true);
                }
            } else {
                // Process other keyboard inputs only if the game is not paused
                robot.move(event.getCode());
                updateRobotViewPosition(robotView, robot, groot, sroot);
                for (int i = 0; i < viruses.size(); i++) {
                    if (robot.getCellX() == viruses.get(i).getVirus().getCellX() && robot.getCellY() == viruses.get(i).getVirus().getCellY()) {
                        viruses.get(i).getVirus().getView().setVisible(false);
                        viruses.remove(i);
                        counter.setCount(counter.getCount() - 1);
                        System.out.println(counter.getCount());
                        System.out.println(viruses.size());
                        tds = tds - 5;
                    }
                }
                if (robot.getCellX() == door.getCellX() && robot.getCellY() == door.getCellY()) {
                    robot.leave(event.getCode());
                    if (robot.isLeft() == true) {
                        winning(primaryStage);
                    }
                }
                if (difficulty == 'H' && robot.getCellX() == key.getCellX() && robot.getCellY() == key.getCellY()) {
                    key.getView().setVisible(false);
                    door.getView().setVisible(true);
                }
            }
        });

        
        
        timer.start();
        thread.start();
    }
    
    
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
    
    
    void updateRobotViewPosition(ImageView robotView, Robot robot, Pane pane, StackPane spane) {
        robotView.setScaleX(robot.getPrespective());
        pane.setLayoutY(robot.getCellY());
        pane.setLayoutX(robot.getCellX());
        spane.setMargin(pane, new Insets(robot.getPosY(), 0, 0, robot.getPosX()));
    }
    
    
    void print(GraphicsContext gc){
        gc.setStroke(Color.BLUE);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (int i = 0; i < maze.cells.length; i++) {
            for (int j = 0; j < maze.cells[i].length; j++) {
                printCell(gc, maze.cells[i][j]);
            }
        }
    }

    void printCell(GraphicsContext gc, Cell cell){
        int startX = cell.x * cellWidth;
        int startY = cell.y * cellWidth;

        // print color
        if(cell.isVisited){
            gc.setFill(Color.WHITE);
            gc.fillRect(startX, startY, cellWidth, cellWidth);
        }
            
        // print top line
        if(!cell.walls[0])
            gc.strokeLine(startX, startY, startX + cellWidth, startY);

        // print right line
        if(!cell.walls[1])
            gc.strokeLine(startX + cellWidth, startY, startX + cellWidth, startY + cellWidth);

        // print bottom line
        if(!cell.walls[2])
            gc.strokeLine(startX, startY + cellWidth, startX + cellWidth, startY + cellWidth);

        // print left line
        if(!cell.walls[3])
            gc.strokeLine(startX, startY, startX, startY + cellWidth);

    }
    

    
    
}