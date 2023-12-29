# PR3Projekt_GRP7
Programmierprojekt 3. Semester (BIN), WiSe 2023/24, Holitschke
Ein JavaFX-Spielprojekt der Gruppe 7.

## Mitglieder der Gruppe

- Rackebrandt Henschell Hans
- Freej Mohammad
- Kasumovic Carballeira Dario
- von Legat Rahel Antonia
- Mobasher Goljani Mohammad Jalal
- Pauluhn Marc
- Betreuer: Peter Robert

## Inhaltsverzeichnis

- [Beschreibung](#beschreibung)
- [Voraussetzungen](#voraussetzungen)
- [Installation](#installation)
- [Verwendung](#verwendung)
- [Spiele und Use Cases](#spiele-und-use-cases)
- [Dateistruktur](#dateistruktur)
- [Bilder und Audio](#bilder-und-audio)
- [Lizenz](#lizenz)

## Beschreibung

Dieses Projekt ist ein JavaFX-Spielprojekt, das von Gruppe 7 entwickelt wurde. Es bietet verschiedene Spiele zur Auswahl.

darkIT ist eine Spielesammlung, speziell entwickelt für Kinder im Alter von 6 bis 14 Jahren. Die Sammlung dreht sich um das Thema: „Die Schattenseiten der Informationstechnologie”. Hier können Kinder spielerisch den Umgang mit Computern erlernen, während sie gleichzeitig Hintergrundwissen über die potenziellen Gefahren der Informationstechnologie erwerben. Die Spiele sind darauf ausgerichtet, die Kreativität der Kinder zu fördern, und bieten eine unterhaltsame Möglichkeit, sich mit dieser wichtigen Thematik auseinanderzusetzen.

## Voraussetzungen

- Java [19]
- JavaFX [JDK11-Full]

## Installation

Kommt noch.

## Verwendung

Kommt noch.

## Spiele und Use Cases

In diesem Abschnitt sind die verschiedenen Spiele des Projekts aufgeführt, sowie ihre use cases.

### Memory

**Prototyp:**

- [Link zum Menu & Memory-GUI-Prototyp](https://www.figma.com/proto/VIw0yghOrrEsgfaIH1iB05/Memory-Game?type=design&node-id=2-40&t=kuklCUOqxG4OCoBC-8&scaling=contain&page-id=0%3A1&hide-ui=1)
- [Link zum UML](Link)
- [Link zum Javadoc](Link)

**Use Cases:**

 **1. Spiel starten**:
   - Der Spieler startet das Spiel. Das System mischt die Karten und legt sie verdeckt auf das Spielfeld.

 **2. Karte aufdecken**:
   - Der Spieler wählt eine verdeckte Karte aus. Das System deckt die gewählte Karte auf.

 **3. Paar gefunden**:
   - Wenn der Spieler zwei gleiche Karten aufdeckt, entfernt das System diese vom Spielfeld und aktualisiert den Punktestand des Spielers.

 **4. Kein Paar gefunden**:
   - Wenn der Spieler zwei unterschiedliche Karten aufdeckt, deckt das System die Karten nach einer kurzen Pause wieder zu.

 **5. Neue Runde**:
   - Wenn alle Karten aufgedeckt wurden, startet automatisch eine neue Runde mit einem frisch gemischten Kartenfeld. Dies setzt sich solange fort, bis die vorgegebene Zeit abgelaufen ist.
   
 **6. Spiel beenden**:
   - Wenn die Zeit abgelaufen ist, zeigt das System den Endbildschirm mit dem finalen Punktestand an.

## Dateistruktur

- `src/`: Enthält den Quellcode des Projekts.
- `Audio/`: Enthält Audio-Dateien.
- `images/`: Enthält Bild-Dateien.

## Quellen-Memory

Die Bilder und Audiodateien in diesem Projekt stammen von folgenden Quellen:

- `Undertale_93.mp3`: [Quellen](Link)
- `SE1.wav`: [Quellen](https://mixkit.co/free-sound-effects/game/?page=2) [Download](https://assets.mixkit.co/active_storage/sfx/253/253.wav)
- `BA2.gif`: [Quellen](https://i.pinimg.com/originals/63/24/3a/63243aacfe563f25e472f9e187723df1.gif)

## Bilder-Memory

Die folgenden Bilder wurden mit Bing Image Creater künstlich generiert:

- `Memory2.jpg`
- `Preview.jpg`
- `Cardback.jpg`
- `Hacker.jpg`
- `Virus.jpg`

Da es sich um künstlich generierte Bilder handelt, gibt keine spezifische externe Quelle.


