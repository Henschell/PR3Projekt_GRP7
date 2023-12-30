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

DarkIT ist eine Sammlung von sechs einzigartigen Spielen, die speziell entwickelt wurden, um Kinder im Alter von 6 bis 14 Jahren auf unterhaltsame und interaktive Weise über die faszinierende Welt der Spiele und die potenziellen Gefahren des Internets aufzuklären. Jedes Spiel in der Sammlung zielt darauf ab, wichtige Lektionen über Sicherheit im Internet, Datenschutz und digitale Etikette zu vermitteln, während es gleichzeitig die kognitiven und kreativen Fähigkeiten der Kinder fördert.

Bevor Sie mit den Spielen beginnen, empfehlen wir Erwachsenen und Betreuern, sich mit den Kindern zusammenzusetzen und sie durch die folgenden Schritte zu führen:

Einführung in die Spiele: Nehmen Sie sich einen Moment Zeit, um die verschiedenen Spiele in der Sammlung zu erkunden und zu verstehen, wie jedes Spiel dazu beiträgt, wichtige Konzepte über Technologie und Sicherheit zu vermitteln.

Diskussion über das Internet und seine Gefahren: Nutzen Sie die Gelegenheit, mit den Kindern über das Internet zu sprechen. Diskutieren Sie, wie es funktioniert, welche Vorteile es bietet und welche Risiken es birgt. Stellen Sie sicher, dass sie verstehen, wie wichtig es ist, sicher und verantwortungsbewusst online zu agieren.

Spielanleitung: Führen Sie die Kinder durch die Anleitung jedes Spiels. Stellen Sie sicher, dass sie die Regeln verstehen und ermutigen Sie sie, Fragen zu stellen.

Spielen und Lernen: Lassen Sie die Kinder die Spiele spielen und unterstützen Sie sie dabei, die Lektionen, die jedes Spiel zu bieten hat, zu verstehen und zu reflektieren.

Durch die Kombination von Spaß und Bildung bietet DarkIT eine Plattform, auf der Kinder nicht nur lernen, wie Spiele funktionieren, sondern auch, wie sie sich sicher in der digitalen Welt bewegen können. Wir hoffen, dass diese Sammlung dazu beiträgt, das Bewusstsein und das Verständnis der Kinder für die Bedeutung von Cybersicherheit und digitalem Wohlbefinden zu schärfen.

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

### VirusSweeper

**Use Cases:**

 **1. Spiel starten**:
   - Der Spieler bekommt ein mit schwierigkeitsStufe Anfänger generiertes ein spielfeld 

 **2. Button  Zurücksetzen**:
   - Das Spielfeld wird für die eingestellte Schwierigkeit Neu-Generiert und die Zeit sowie Markierung-Zähler werden zurückgesetzt.

 **3. Anzeige für Viren: ...**:
   - Diese zeigt an wie viele Viren noch nicht Markiert sind.

 **4. Zeit-Anzeige**:
   - Sobald die erste Interaktion mit dem Spielfeld statfindet, wird die Zeit hochgezählt. Die Anzeige zeigt => „Minuten:Sekunden“

 **5. Aufdecken einer Festplatte**:
   - Eine Festplatte kann ein Virus sein, dann Hat man Verloren. Anderenfalls, wird eine zahl zwischen 0-8 angezeigt. diese zeigt an wie viele Viren im umkreis der Festplatte sind.

 **6. Markieren einer Festplatte**:
   - Man kann eine Festplatte Markieren wenn man denkt das es Ein Virus ist. Das verhindert das man diese dann aufdecken kann.


 **7. Schwierigkeit Button**:
   - Der Button Schwierigkeit erzeugt drei Felder, mit einer Auswahl von drei Schwierigkeitsstufen. Anfänger mit 10 Viren auf einem 9*9 Feld, Fortgeschritten mit 40 Viren auf einem 16*16 Feld und Experte mit 70Viren auf einem 18*20Feld. 


 **8. Spiel Gewonnen**:
   - Wenn man erfolgreich alle Festplatten ohne Viren aufgedeckt hat, wird der Gewonnen-Bildschrim angezeigt. Dort wird die Zeit angezeigt und die auswahl zwischen es nochmal Versuchen, zur SpieleSammlung zurückzukehren und es Komplett zu beenden.


 **9. Spiel Verloren**:
   - Wenn man eine Festplatte mit einem  Virurs aufgedeckt, wird der Verloren-Bildschrim angezeigt. Dort htat man die auswahl zwischen es nochmal Versuchen, zur SpieleSammlung zurückzukehren und es Komplett zu beenden.

## Quellen-VirusSweeper

Die Bilder und Audiodateien in diesem Projekt stammen von folgenden Quellen:

- alle Audio- und Bild-Datein wurden durch eigenen Künstlerischen Talente erstellt.
