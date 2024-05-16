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

Die Bilder von /images wurden mit Bing Image Creater künstlich generiert:

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

### CyberSnake

**Use Cases:**
 **1. Anleitung öffnen**:
 - Klickt man auf den Manulal-Button, öffnet sich eine neue Stage und eine kurze Spielanleitung wird geöffnet

 **2. Spiel starten**:
 - Klickt man auf den Play-Button, öffnet sich eine neue Szene und das Spiel beginnt sofort

 **3. Die Schlange bewegen**:
 - Mit den Pfeiltasten oder den Tasten a w s d lässt sich die Schlange in die gewünschte Richtung bewegen. Sie darf nicht gegen sich
 selbst, gegen den rand des Spielfelds oder gegen die Hindernisse fahren, sonst hat man verloren.

 **4. Einen Email-Virus fressen**:
 - Bewegt sich die Schlange über das gleiche Feld wie ein Email-Virus, wird dieser gefressen. Der Score wird einen Punkt nach oben gezählt, die Schlange verlängert sich um ein Kästchen und ein neuer Email-Virus an einer anderen Stelle taucht auf und ein neues Hindernis erscheinzt, sofern man Hindernisse eingestellt hat

 **5. Einen bösen Virus fressen**:
 - Bewegt sich die Schlange vor ein Feld mit einem bösen Virus, kann dieser nicht gefressen werden. Die Schlnage dreht sich um, d.h. der Kopf taucht da auf, wo vorher das Schlangenende war

 **6. Hindernisse**:
 - Ist eingestellt, dass man mit Hindernissen spielt, taucht nach jedem gefressenen Virus ein neues Hindernis auf. Da nur 10 Hindernisse gleichzeitig auf dem Spielfeld sein sollen, verschwinden die Hindernisse nach 10 gefressenen Viren wieder

 **7. Game Over**:
 - Bewegt sich die Schlange gegen ein Hindernis, sich selbst, oder gegen den Spielfeldrand, hat man verloren. Eine neue Stage erscheint, auf der ein play-again-Button und ein zurück-Button ersceinen. Der erspielte Score wird auch angezeigt

 **8. Während des Spiels zurück**
 - Möchte man während des Spiels urück zum Menü, kann entweder die escape-tase oder die Backspace-Taste gedrückt werden. Das Spiel bricht ohne etwas zu Speichern ab und man kommt zurück Snake-Menü

 **9. Einstelungen**:
 - Auf dem Snake-Menü kann eingestellt werden, ob die bösen Viren sichtbar sein sollen und ob man mit Hindernissen spielen möchte. Es gibt auch eine Checkbox, die die Einstellungen zufällig auswählt

 ## Quellen-Snake

 - `virus.png` [Quellen]: (https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/36229/computer-virus-email-clipart-xl.png)
 - `stein.png` [Quellen]: (https://kurz-natursteine.de/wp-content/uploads/334780-225x300.png)
 - `glowGreen.png` [Quellen]: (https://www.computerworld.ch/img/1/5/8/1/0/4/5/hacker-cybercrime_geralt-pixabay_w960_h640.jpg)
 - `background.mp3` [Quellen] (https://pixabay.com/music)
 - `happs.wav` [Quellen] (https://freesound.com)

 Folgende Bilder wurden künstlich kreiert:
 - `snakeHead.png`
 - `snakeBody.png` 

## CrypticMaze
**Dieser Spiel wurde von Mohammad Freej erstellt**

**Use Cases:**

**1. Spiel starten:**

-Der Spieler startet das Spiel. Das Labyrinth wird zufällig generiert.

**2. Roboter bewegen:**

-Mit den Pfeiltasten kann man den Roboter in alle vier Richtungen bewegen.

**3. Ziel erreichen:**

-Der Roboter muss zum Tor gehen. Man drückt die Leertaste, um durch das Tor zu gehen.

**4. Zeit ist Geld:**

-Es gibt einen Timer, deshalb muss man schnell und schlau sein.

**5. Viren sind überall:**

-Die Viren werden ebenfalls zufällig generiert. Es gibt einige Viren, die man nicht vermeiden kann. Wenn der Roboter ein Virus berührt, verliert er Zeit.

**6. Einstellungen:**

-Man kann den Schwierigkeitsgrad wählen. Dies ändert die Größe des Labyrinths.
-Musik kann an- und ausgeschaltet werden.

**7. Überraschung:**

-Kannst du das Spiel auf "Hard" schaffen? Es wartet mehr auf dich dort.

##Quellen CrypticMaze:

- `door.gif`: https://deutschtec.de/wp-content/uploads/2018/06/Primedrive-TBS-close.gif
- `keycard.png`: https://cdn-icons-png.flaticon.com/512/663/663294.png
- `robot.gif`: https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/bdeb6916-ce62-4d75-abae-220bc9f666d2/d15ljb8-49022d5c-9486-4fb7-a21a-3313f26abd4f.gif
- `virus.png`: https://banner2.cleanpng.com/20180430/yxq/kisspng-malware-analysis-computer-virus-computer-icons-com-5ae78bf6482a86.9817066915251240862956.jpg
- `youwin.mp3`: you win YuGiOh Forbidden Memories OST
- `background-music.mp3`: The Front Hall Resident Evil 2 OST
- `You Lose.gif`: künstlich hergestellt
- `You Win.gif`: künstlich hergestellt
- `BGImage3.gif`: künstlich hergestell
