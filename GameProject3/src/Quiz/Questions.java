package Quiz;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.GUIController;
/**
 * Die Hauptklasse der Quiz-Anwendung.
 */
public class Questions extends Application {
    // Eine Liste der Fragen
    private String[] questions = {
        "Was ist das Internet?",
        "Was ist ein häufiges Risiko im Internet für Kinder?",
        "Welche Art von Informationen sollten Kinder nicht online teilen?",
        "Was ist ein Passwort im Zusammenhang mit dem Internet?",
        "Welche Art von Website ist für Kinder ungeeignet?",
        "Was sollten Kinder tun, wenn sie online auf unangemessene Inhalte stoßen?",
        "Was ist Cybermobbing?",
        "Warum sollten Kinder ihre Online-Aktivitäten mit ihren Eltern besprechen?",
        "Was sollten Kinder tun, wenn sie von Fremden im Internet kontaktiert werden?",
        "Was ist ein Virenschutzprogramm?",
        "Welche Art von Informationen sollten Kinder in sozialen Medien nicht teilen?",
        "Warum ist es wichtig, starke Passwörter zu verwenden?",
        "Was sollten Kinder tun, wenn sie in einem Online-Spiel auf unhöfliche Spieler stoßen?",
        "Was ist Phishing?",
        "Warum ist es wichtig, keine persönlichen Informationen online preiszugeben?",
        "Was sollten Kinder tun, wenn sie unangemessene Bilder online sehen?",
        "Was ist Online-Sucht?",
        "Warum sollten Kinder nicht Online-Spiele von Fremden herunterladen?",
        "Was ist Chatroom-Sicherheit?",
        "Wie können Kinder sicher im Internet surfen?",
        "Was sollten Kinder tun, wenn sie online gemobbt werden?",
        "Warum ist es wichtig, die Bildschirmzeit zu begrenzen?",
        "Was ist Grooming?",
        "Wie können Kinder in sozialen Medien sicherer sein?",
        "Warum sollten Kinder keine illegalen Downloads durchführen?",
        "Was ist digitales Footprint?",
        "Warum ist es wichtig, Kinder über die Gefahren des Internets aufzuklären?",
        "Was ist Geolokalisierung?",
        "Warum sollten Kinder keine fremden Dateianhänge öffnen?",
        "Wie können Kinder erkennen, ob eine Website sicher ist?",
        "Was ist Datenschutz im Zusammenhang mit dem Internet?",
        "Warum sollten Kinder keine Gewaltvideos im Internet ansehen?",
        "Was ist Netiquette?",
        "Warum sollten Kinder keine persönlichen Treffen mit Online-Bekanntschaften vereinbaren?",
        "Was ist Ransomware?",
        "Wie können Kinder verhindern, Opfer von Identitätsdiebstahl zu werden?",
        "Warum sollten Kinder keine unbekannten Links in E-Mails öffnen?",
        "Was ist Zwei-Faktor-Authentifizierung?",
        "Warum ist es wichtig, Kindern beizubringen, niemandem online Geld zu schicken?",
        "Was ist Netzsicherheit?",
        "Warum sollten Kinder in sozialen Medien keine Beziehungsstatus-Änderungen vornehmen?",
        "Was ist Online-Überwachung?",
        "Warum sollten Kinder niemals Hasskommentare online verfassen?",
        "Was ist Elternaufsicht im Internet?",
        "Warum sollten Kinder keine persönlichen Informationen in öffentlichen Chats teilen?",
        "Was ist Sexting?",
        "Warum sollten Kinder keine E-Mails von unbekannten Absendern öffnen?",
        "Was ist Bildrechte?",
        "Warum sollten Kinder keine Online-Tests mit persönlichen Informationen machen?",
        "Was sollten Kinder tun, wenn sie beleidigende Nachrichten erhalten?",
        "Was ist Privatsphäre?",
        "Warum sollten Kinder ihre Passwörter geheim halten?",
        "Was ist ein Online-Forum?",
        "Warum sollten Kinder keine Software aus unsicheren Quellen herunterladen?",
        "Was ist Trolling?",
        "Warum sollten Kinder in sozialen Medien keine genauen Standorte teilen?",
        "Was sollten Kinder tun, wenn sie unangemessene Anfragen von Online-Freunden erhalten?",
        "Warum sollten Kinder keine illegalen Streaming-Seiten nutzen?",
        "Was ist Cookies?",
        "Warum sollten Kinder in sozialen Medien keine Hassgruppen oder gewalttätigen Inhalten folgen?",
        "Was versteht man unter Social Engineering in Bezug auf Online-Gefahren?",
        "Was ist der Zweck von Doxing in Bezug auf die Gefahren im Internet?",
        "Warum kann das Verwenden öffentlicher WLAN-Netzwerke ein Sicherheitsrisiko darstellen",
        "Was ist Zero-Day-Exploitation in Bezug auf Internetgefahren?",
        "Warum ist es wichtig, regelmäßig Software-Updates durchzuführen, um sich vor Online-Bedrohungen zu schützen?"
    };
    // ... (Liste der Fragen hier)
    // Eine Liste der Antwortmöglichkeiten für jede Frage
    private String[][] answerChoices = {
        {"Ein Buch", "Ein Spiel",
         "Ein weltweites Computernetzwerk", "Ein Musikinstrument"},
        {"Körperliche Verletzungen", "Cybermobbing",
         "Gesunde Ernährung", "Malen nach Zahlen"},
        {"Ihre Lieblingsfarbe","Ihre Adresse und Telefonnummer",
         " Ihr Lieblingsessen","Ihr Haustiernamen"},
        {"Ein besonderes Buch","Ein geheimes Wort oder eine Zeichenkombination",
         "Ein Ort, an dem man sich ausruht"," Ein Musikstück" },
        {"Lernspiele","Kinderfreundliche Zeichentrickfilme",
         "Social-Media-Seiten","Rezepte für Kuchen"},
        {"Es ignorieren und weitermachen","Die Eltern oder Erwachsenen informieren",
         "Es mit Freunden teilen","Einen Online-Kommentar hinterlassen"},
        {"Eine Form von Online-Mobbing und Belästigung","Eine beliebte Tanzshow im Internet",
         "Ein Computerspiel","Ein Brettspiel"},
        {" Weil es Spaß macht"," Weil es eine Schulregel ist",
         "Um Vertrauen aufzubauen und Unterstützung zu erhalten"," Weil Eltern immer Recht haben"},
        {"Ihre Adresse teilen","Freundschaftsanfragen annehmen",
         "Den Kontakt zu Fremden abbrechen und ihre Eltern informieren","Fotos teilen"},
        {"Eine Online-Nachricht","Ein Computerprogramm, das schädliche Software blockiert",
         "Ein Videospiel"," Eine Sehenswürdigkeit"},
        {"Ihre Lieblingsbücher","Ihre Hobbys und Interessen",
         "Ihre Meinung über das Wetter"," Persönliche Identifikationsdaten wie die Sozialversicherungsnummer"},
        {"Weil es Spaß macht","Um die Anmeldung zu verhindern",
         "Um persönliche Informationen zu schützen","Weil es die Internetregeln sind"},
        {"Den Spielverlauf verlassen"," Die Spieler beleidigen",
         "Den Eltern oder Moderatoren Bescheid sagen","Den Spielspaß genießen"},
        {"Eine Angeltechnik","Eine Art von Online-Betrug, bei dem versucht wird, persönliche Informationen zu stehlen",
         "Ein Angelsport"," Ein Buch über Fische"},
        {"Weil es langweilig ist","Um neue Freunde zu gewinnen",
         "Weil es keine Rolle spielt","Um Identitätsdiebstahl zu verhindern"},
        {"Sie den Eltern zeigen","Sie ausdrucken und an die Schule bringen",
         "Sie ignorieren"," Sie herunterladen und mit Freunden teilen"},
        {"Ein Zustand, in dem man zu viel Zeit im Internet verbringt und andere Aktivitäten vernachlässigt","Ein lustiges Online-Spiel",
         "Ein Freizeitpark im Internet","Ein Buch über das Surfen"},
        {"Weil es Spaß macht","Um Geld zu sparen",
         "Weil es gefährlich sein kann und Schadsoftware enthalten könnte","Weil ihre Freunde es auch tun"},
        {"Ein Sicherheitsdienst für Veranstaltungsorte","Die Verwendung sicherer Passwörter",
         "Die Vermeidung persönlicher Gespräche mit Fremden in Online-Chatrooms","Eine Art von Spiel"},
        {"Durch das Teilen aller persönlichen Informationen"," Durch das Ignorieren von Elternratschlägen",
         "Durch das Teilen von Passwörtern mit Freunden"," Durch die Verwendung von sicheren Passwörtern"},
        {"Die Mobber loben"," Den Eltern oder Lehrern Bescheid sagen",
         "Die Mobber ignorieren"," Sich selbst verteidigen"},
        {"Um genug Schlaf zu bekommen und andere Aktivitäten zu fördern","Weil es das Tageslicht blockiert",
         "Weil Bildschirme teuer sind","Weil es Spaß macht"},
        {"Eine Frisur"," Eine Vorbereitung auf den Schulunterricht",
         "Die Methode, mit der Fremde versuchen, Vertrauen von Kindern online zu gewinnen und sie zu missbrauchen"," Ein Sport"},
        {"Durch die Veröffentlichung von allem, was sie tun"," Durch die Verwendung von geheimen Codes",
         "Durch das Teilen von Fotos mit Fremden","Durch die Verwendung von Datenschutzeinstellungen und das Akzeptieren von Freundschaftsanfragen von Bekannten"},
        {"Weil es cool ist","Weil es Spaß macht",
         "Weil es gegen das Gesetz ist und Schadsoftware enthalten kann","Weil es keine Rolle spielt"},
        {"Die Menge an Daten, die online über eine Person verfügbar ist","Ein cooles Schuhdesign",
         "Eine Fußmassage im Internet","Eine Art von Tanz"},
        {"Weil Eltern gerne Geschichten erzählen","Um ihnen die Fähigkeiten zur sicheren Internetnutzung beizubringen",
         "Um sie zu verängstigen","Weil es langweilig ist"},
        {"Eine Form von Online-Sport","Ein neuer Kartentrend",
         "Ein Buch über Orte","Die Identifizierung des Standorts einer Person mithilfe von GPS oder Mobilfunkdaten"},
        {"Weil ihre Freunde es auch tun","Weil es keine Rolle spielt",
         "Um ihre Computer vor Viren und Schadsoftware zu schützen","Weil es Spaß macht"},
        {"Durch Überprüfen der URL und der Datenschutzrichtlinien","Indem sie auf bunte Bilder klicken",
         "Durch die Verwendung von starken Passwörtern","Indem sie auf Pop-up-Anzeigen klicken"},
        {"Der Schutz persönlicher Daten und Informationen vor unbefugtem Zugriff","Das Teilen aller persönlichen Informationen mit jedem",
         "Das Hochladen von Fotos auf Social-Media-Seiten","Ein Buch über Geheimnisse"},
        {"Weil es Spaß macht","Weil es ihre Psyche beeinflussen kann",
         "Weil es die beste Methode ist, um Selbstverteidigung zu lernen","Weil es keine Rolle spielt"},
        {"Eine virtuelle Teeparty","Ein neuer Internettrend",
         "Ein Buch über das Surfen im Netz","Die richtige und höfliche Verhaltensweise im Internet"},
        {"Weil es Spaß macht","Weil es cool ist",
         "Weil es gefährlich sein kann und zu unvorhersehbaren Situationen führen kann","Weil es keine Rolle spielt"},
        {"Eine Art von Schadsoftware, die Dateien verschlüsselt und Lösegeld erpresst","Eine beliebte Fernsehserie",
         "Ein neuer Tanz","Ein Buch über die Entführung von Daten"},
        {"Durch das Teilen von Passwörtern mit Freunden","Durch die Verwendung von starken Passwörtern und die sichere Aufbewahrung persönlicher Daten",
         "Durch das Teilen von Kreditkarteninformationen online","Durch das Ignorieren von Datenschutzrichtlinien"},
        {"Weil ihre Eltern das nicht mögen","Weil es Spaß macht",
         "Weil es cool ist","Weil es gefährlich sein kann und zu schädlichen Websites führen kann"},
        {"Eine Zahlenübung","Ein Buch über Sicherheit",
         "Eine Sicherheitsmethode, bei der Benutzer neben einem Passwort eine zweite Identifikationsmethode verwenden","Ein Online-Spiel"},
        {"Weil es gefährlich ist und zu Betrug führen kann","Weil es Spaß macht",
         "Weil es cool ist","Weil es keine Rolle spielt"},
        {"Das Veröffentlichen von allem online","Der Schutz vor Online-Bedrohungen und die Sicherung persönlicher Daten",
         "Ein Online-Computerspiel","Ein Buch über das Internet"},
        {"Weil es keine Rolle spielt","Weil es Spaß macht",
         "Weil es cool ist","Weil es ihre Privatsphäre schützt und potenzielle Gefahren minimiert"},
        {"Das Anschauen von Videos im Internet","Ein neuer Sport im Internet",
         "Die gezielte Beobachtung der Online-Aktivitäten von Kindern durch Eltern oder Erziehungsberechtigte","Ein Buch über das Beobachten"},
        {"Weil es gegen die Netiquette verstößt und andere verletzen kann","Weil es keine Rolle spielt",
         "Weil es ihre Online-Freunde beeindruckt","Weil es Spaß macht"},
        {"Ein Online-Rollenspiel","Die Kontrolle und Überwachung der Online-Aktivitäten von Kindern durch Eltern oder Erziehungsberechtigte",
         "Ein neuer Film","Ein Buch über Eltern"},
        {"Weil es ihre Privatsphäre schützt und potenzielle Gefahren minimiert","Weil es Spaß macht",
         "Weil es cool ist","Weil es keine Rolle spielt"},
        {"Eine Art von Sport","Ein Computerspiel",
         "Das Versenden von sexuell expliziten Nachrichten, Fotos oder Videos über das Internet","Ein neuer Buchtyp"},
        {"Weil es Spaß macht","Weil ihre Freunde das nicht mögen",
         "Weil es gefährlich sein kann und schädliche Anhänge enthalten könnte","Weil es cool ist"},
        {"Ein Buch über Bilder","Ein neuer Trend im Internet",
         "Die Rechte eines Bildes, das in einem Museum hängt","Das Recht, ein Foto oder Bild zu verwenden, zu teilen oder zu veröffentlichen"},
        {"Weil es ihre Intelligenz beweist","Weil es gefährlich sein kann und zu Datenmissbrauch führen kann",
         "Weil es Spaß macht","Weil es keine Rolle spielt"},
        {"Die Nachrichten ignorieren und den Vorfall melden","Die Nachrichten beantworten und sich verteidigen",
         "Die Nachrichten an ihre Freunde weiterleiten","Die Nachrichten veröffentlichen"},
        {"Die Tatsache, dass man immer alleine sein möchte","Ein neuer Raum im Internet",
         "Ein Buch über Geheimnisse","Das Recht, persönliche Informationen zu schützen und zu kontrollieren, wer darauf zugreifen kann"},
        {"Weil es Spaß macht","Um zu vergessen",
         "Weil es keine Rolle spielt","Weil es die Sicherheit ihrer Online-Konten gewährleistet"},
        {"Ein virtuelles Schlafzimmer","Ein neuer Partytrend im Internet",
         "Eine Online-Plattform, auf der Benutzer über verschiedene Themen diskutieren können","Ein Buch über Foren"},
        {"Weil es Spaß macht","Weil es gefährlich sein kann und Schadsoftware enthalten könnte",
         "Weil es kostet","Weil es cool ist"},
        {"Das gezielte Stören oder Provokieren anderer Benutzer im Internet","Das Angeln im Internet",
         "Ein neuer Freizeitsport im Internet","Ein Buch über das Ärgern von Menschen"},
        {"Weil es gefährlich sein kann und ihre Sicherheit gefährdet","Weil es Spaß macht",
         "Weil es cool ist","Weil es keine Rolle spielt"},
        {"Die Anfragen annehmen und sich darauf einlassen","Die Anfragen ignorieren und den Vorfall melden",
         "Die Anfragen weiterleiten","Die Anfragen beantworten"},
        {"Weil es Spaß macht","Weil es cool ist","Weil es keine Rolle spielt",
         "Weil es gegen das Gesetz ist und zu rechtlichen Konsequenzen führen kann"},
        {"Leckere Kekse, die online bestellt werden können","Ein neues Rezept im Internet",
         "Kleine Dateien, die von Websites auf Ihrem Computer gespeichert werden, um Informationen zu verfolgen","Ein Buch über Backen"},
        {"Weil es Spaß macht","Weil es cool ist",
         "Weil es ihre Denkweise beeinflussen kann und zu negativem Verhalten führen kann","Weil es keine Rolle spielt"},
        {"Eine beliebte Online-Marketingstrategie","Eine Methode, bei der Angreifer versuchen, durch Manipulation persönliche Informationen zu erhalten",
         "Ein soziales Netzwerk für Ingenieure","Eine App zur Verbesserung der sozialen Fähigkeiten"},
        {"Eine Art von Online-Spiel", "Der Schutz vor Cybermobbing",
         "Das Erstellen von sicheren Passwörtern","Das Teilen von persönlichen Informationen absichtlich online"},
        {"Weil sie immer langsam sind"," Weil sie von der Regierung überwacht werden",
         "Weil Hacker möglicherweise auf private Daten zugreifen können","Weil sie teuer sind"},
        {"Eine Technik, bei der Hacker eine Schwachstelle ausnutzen, bevor sie behoben wird","Ein Tag ohne Internetverbindung",
         "Ein sicherer Online-Zahlungsdienst","Eine Null-Toleranz-Politik für Online-Betrug"},
        {"Um mehr Funktionen hinzuzufügen","Um die Benutzeroberfläche zu ändern",
         "Um Sicherheitslücken zu schließen und Schwachstellen zu beheben","Um die Geschwindigkeit des Computers zu erhöhen"}
        };
    // ... (Liste der Antwortmöglichkeiten hier) |
    // Eine Liste der Indexe der richtigen Antworten für jede Frage
    private int[] correctAnswers = {2,1,1,1,2,1,0,2,2,1,3,2,2,1,
            3,0,0,2,2,3,1,0,2,3,2,0,1,3,2,0,0,1,3,2,0,1,3,2,0,1,3,2,0,
            1,0,2,2,3,1,0,3,3,2,1,0,0,1,3,2,2,2,3,2,0,2};
    // Die Hauptansicht der Anwendung
    private VBox root = new VBox(10);
    // Eine Liste der zufällig gemischten Fragen-Indizes
    private List<Integer> shuffledQuestionIndexes = new ArrayList<>();
    // Der Index der aktuellen Frage
    private int currentQuestionIndex = 0;
    // Die Anzahl der korrekten Antworten
    private int correctAnswerCount = 0;
    // Die Anzahl der Fragen, die dem Benutzer gestellt werden
    private int numberOfQuestionsToAsk = 20;
    // Änderndie Anzahl der Fragen hier
    private int currentLevel = 1;
    // Aktuelles Level
    private int correctAnswersInCurrentLevel = 0;
    // Anzahl der richtigen Antworten im aktuellen Level
    private Stage primaryStage; // Speichere eine Referenz auf das Hauptfenster

    /**
     * Diese Methode wird aufgerufen, wenn die Anwendung gestartet wird.
     * Hier wird das Hauptfenster initialisiert und die erste Frage angezeigt.
     * @param primaryStage Das Hauptfenster der Anwendung.
     */
    @Override
    public void start(Stage primaryStage) {
        // Erstelle ein StackPane für die Optionen
        StackPane pane = new StackPane();

        // Lade das GIF-Bild
//        Image backgroundImageView = new Image(new File("Back.gif").toURI().toString());
//        ImageView imageView = new ImageView(backgroundImageView);
        pane.getChildren().add(GUIController.getImageView());
        // Speichere eine Referenz auf das Hauptfenster
        this.primaryStage = primaryStage;
        // Setze das Layout der Hauptansicht auf zentrierte Ausrichtung
        root.setAlignment(Pos.CENTER);
        pane.getChildren().add(root);
        // Erstelle eine neue Szene mit der Hauptansicht und den festgelegten Dimensionen
        Scene scene = new Scene(pane, 800, 600);
        // Setze die Szene als Szene des Hauptfensters
        primaryStage.setScene(scene);
        // Mischen Sie die Fragen-Indizes zufällig
        shuffleQuestions();
        // Zeige die erste Frage an
        showNextQuestion();
        // Setze das Level auf 1
        currentLevel = 1; // Setze das Level auf 1
        // Aktualisiere den Titel des Hauptfensters mit dem aktuellen Level
        updateWindowTitle();
        // Setze den Titel des Hauptfensters
        primaryStage.setTitle("Quiz-App (Level " + currentLevel + ")");
        // Zeige das Hauptfenster an
        primaryStage.show();
    }

    /**
     * Diese Methode mischt die Fragen-Indizes zufällig, um eine zufällige Reihenfolge der Fragen zu erstellen.
     * Die gemischten Indizes werden in der Liste 'shuffledQuestionIndexes' gespeichert.
     */
    private void shuffleQuestions() {
        // Iteriere über alle Fragen-Indizes
        for (int i = 0; i < questions.length; i++) {
            // Füge jeden Index zur Liste 'shuffledQuestionIndexes' hinzu
            shuffledQuestionIndexes.add(i);
        }
        // Mische die Reihenfolge der Fragen-Indizes in der Liste
        Collections.shuffle(shuffledQuestionIndexes);
    }

    /**
     * Diese Methode startet eine neue Quiz-Runde, indem sie die notwendigen Variablen zurücksetzt,
     * die Fragen neu mischt und die Anzeige für die nächste Frage vorbereitet.
     */
    private void startNewQuiz() {
        // Setze den Index der aktuellen Frage zurück
        currentQuestionIndex = 0;
        // Setze den Zähler für die korrekten Antworten insgesamt zurück
        correctAnswerCount = 0;
        // Setze den Zähler für die korrekten Antworten im aktuellen Level zurück
        correctAnswersInCurrentLevel = 0;
        // Lösche alle Kinder-Elemente (z.B., Buttons, Labels) aus der Wurzel, um Platz für neue anzuzeigen
        root.getChildren().clear();
        // Mische die Fragen-Indizes für die neue Runde
        shuffleQuestions();
        // Zeige die erste Frage der neuen Runde an
        showNextQuestion();
    }
    /**
     * Diese Methode zeigt die nächste Frage und ihre Antwortmöglichkeiten an.
     * Je nach Fortschritt im Quiz werden entweder die Fragen oder das Endergebnis angezeigt.
     */
    private void showNextQuestion() {
        if (currentQuestionIndex < numberOfQuestionsToAsk) {
            // Wenn noch nicht alle Fragen gestellt wurden:
            int originalQuestionIndex = shuffledQuestionIndexes.get(currentQuestionIndex);

            // Erstelle einen Button für die Frage mit dem entsprechenden Text
            Button questionButton = new Button("Frage " + (currentQuestionIndex + 1) + ": " + questions[originalQuestionIndex]);
            // Setze die gewünschte Breite und Höhe
//            questionButton.setMinSize(400, 80);
            questionButton.setPrefSize(500, 100);
            questionButton.setWrapText(true);
            questionButton.setTextAlignment(TextAlignment.CENTER);
            // Ändere die Hintergrundfarbe des Frage-Buttons
            questionButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
            root.getChildren().add(questionButton);


            // Erstelle Buttons für die Antwortmöglichkeiten
            for (int i = 0; i < 4; i++) {
                int answerIndex = i;
                Button answerButton = new Button("Antwort " + (i + 1) + ": " + answerChoices[originalQuestionIndex][i]);
                answerButton.setOnAction(event -> handleAnswer(originalQuestionIndex, answerIndex));
                // Setze die Mindestgröße der Antwortbuttons
                answerButton.setPrefSize(400, 60);
//                answerButton.setMinSize(300, 60);
//                // Setze die Breite für alle Antwort-Buttons
//                answerButton.setMinWidth(400);
//                answerButton.setMaxHeight(Double.MAX_VALUE);
                // Der Text wird auf mehrere Zeilen umgebrochen
                answerButton.setWrapText(true);
                // Zentriere den Text
                answerButton.setAlignment(Pos.CENTER);
                // Zentriere den Text horizontal
                answerButton.setTextAlignment(TextAlignment.CENTER);
                root.getChildren().add(answerButton);
                
                // Setze den Abstand (Padding) zwischen den Buttons
                VBox.setMargin(answerButton, new Insets(10, 0, 0, 0));
            }

            // Hinzufügen des Zurück-Buttons für die Runde
            Button backButton = new Button("Zurück zur Hauptseite");
            backButton.setOnAction(event -> {
                // Hier können Sie den Code hinzufügen, um zur Hauptseite zurückzukehren
                Main mainApp = new Main();
                mainApp.start(primaryStage);
            });
            root.getChildren().add(backButton);
            
        } else {
            // Hinzufügen des Zurück-Buttons für die Runde
            // Überprüfe, ob das Level aufgestiegen ist
            if (correctAnswersInCurrentLevel >= 16 && currentLevel < 3) {
                currentLevel++;
            }


            // Setze den Titel mit dem aktualisierten Level
            updateWindowTitle();

            // Zeige das Endergebnis und die Möglichkeit, eine weitere Runde zu starten
            Button resultButton = new Button("Ergebnis (Level " + currentLevel + "): " + correctAnswerCount + " von 20 Fragen richtig beantwortet");
            root.getChildren().add(resultButton);

            // Button für eine weitwere Runde
            Button startNewRoundButton = new Button("Nächste Runde starten");
            startNewRoundButton.setOnAction(event -> startNewQuiz());
            root.getChildren().add(startNewRoundButton);
        }
    }
    /**
     * Diese Methode wird aufgerufen, wenn der Benutzer eine Antwort auswählt.
     * Sie überprüft die Korrektheit der Antwort, ändert 
     * die Farbe des ausgewählten Buttons entsprechend
     * und zeigt dann die nächste Frage oder das Endergebnis an.
     *
     * @param originalQuestionIndex Der Index der ursprünglichen Frage vor dem Mischen.
     * @param answerIndex           Der Index der ausgewählten Antwortmöglichkeit.
     */
    private void handleAnswer(int originalQuestionIndex, int answerIndex) {
        // Überprüfe, ob die Antwort korrekt ist
        boolean isCorrect = (answerIndex == correctAnswers[originalQuestionIndex]);

        // Ändere die Farbe des gedrückten Buttons
        // Der +1-Korrekturfaktor für den Button-Index
        Button pressedButton = (Button) root.getChildren().get(answerIndex + 1);
        if (isCorrect) {
            // Richtig beantwortet
            pressedButton.setStyle("-fx-background-color: green;");
            correctAnswerCount++;
            correctAnswersInCurrentLevel++;
        } else {
            // Falsch beantwortet
            pressedButton.setStyle("-fx-background-color: red;");
        }

        // Warte einen Moment, bevor die nächste Frage angezeigt wird
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
        pause.setOnFinished(event -> {
            // Lösche den aktuellen Inhalt
            root.getChildren().clear();

            currentQuestionIndex++;

            if (currentQuestionIndex < shuffledQuestionIndexes.size() && currentQuestionIndex < numberOfQuestionsToAsk) {
                // Es gibt noch Fragen, zeige die nächste Frage
                showNextQuestion();
            } else {
                // Alle Fragen wurden beantwortet, zeige das Endergebnis
                // Überprüfe, ob das Level aufgestiegen ist
                if (correctAnswersInCurrentLevel >= 16 && currentLevel == 1) {
                    currentLevel++;
                    // Setze den Zähler für das neue Level zurück
                    correctAnswersInCurrentLevel = 0;
                } else if (correctAnswersInCurrentLevel == 20 && currentLevel == 2) {
                    currentLevel++;
                    // Setze den Zähler für das neue Level zurück
                    correctAnswersInCurrentLevel = 0;
                }

                // Setze den Titel mit dem aktualisierten Level
                updateWindowTitle();

                // Zeige das Endergebnis und die Möglichkeit, eine weitere Runde zu starten
                Button resultButton = new Button("Ergebnis (Level " + currentLevel + "): " + correctAnswerCount + " von 20 Fragen richtig beantwortet");
                root.getChildren().add(resultButton);

                if (correctAnswerCount == 20) {
                    // Glückwünsche für perfektes Ergebnis mit Flackereffekt
                    Label congratulationsLabel = new Label("Herzlichen Glückwunsch! Perfektes Ergebnis!");
                    // Setze die Textfarbe auf Weiß
                    congratulationsLabel.setTextFill(Color.WHITE);


                    // Definiere den Flackereffekt
                    FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.seconds(0.5), congratulationsLabel);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.setCycleCount(Animation.INDEFINITE);
                    fadeIn.setAutoReverse(true);
                    fadeIn.play();
                    root.getChildren().add(congratulationsLabel);
                }

                Button startNewRoundButton = new Button("Nächste Runde starten");
                startNewRoundButton.setOnAction(e -> startNewQuiz());
                root.getChildren().add(startNewRoundButton);
            }
        });

        pause.play();
    }
    /**
     * Methode zur Aktualisierung der Titelleiste des Fensters.
     */
    private void updateWindowTitle() {
        primaryStage.setTitle("Quiz-App (Level " + currentLevel + ")");
    }

    /**
     * Die Hauptmethode zum Starten der Java-Anwendung.
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


