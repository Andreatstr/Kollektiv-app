# Group gr2436 repository

[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2436/gr2436?new)

## Nødvendige versioner for å kjøre prosjektet:

Maven version 3.9.9
Java testet og fungerende versjoner : Java 21.0.1 og 21.0.4

## Innhold

Prosjektet ligger i mappe app-gruppe36.

Vi bruker view-modulen for å håndtere brukergrensesnittet. View-kontrollerne håndterer brukergrensesnittet gjennom JavaFX. App, er hovedklassen i prosjektet som starter prosjektet. Logikken blir håndtert i kontrollerene, som kaller på metoder i viewmodel-klasser i model-modulen. Disse kaller igjen på model-klasser i samme modul.

## Bygging og kjøring av prosjektet

For å kjøre prosjektet må du følge disse 4 stegene:

### 1

For å bygge prosjektet må man først befinnne seg i app-gruppe36 ved å bruke følgende kommando:

cd app-gruppe36

### 2

Deretter kan man bygge prosjektet med:

mvn clean install

### 3

For å kjøre applikasjonen må man befinne seg i view:

cd view

### 4

Deretter kan man kjøre prosjektet

mvn javafx:run

### 5

For å starte serveren må man åpne en ny terminal

cd app-gruppe36

### 6

Deretter må man bevege seg inn i servermappen

cd restserver

### 7

For å starte Springboot serveren

mvn spring-boot:run

### Nb!

Applikasjonen vil ikke fungere uen server. Applikasjonen er designet og bygget rundt en server-klient ar

## Brukerhistorier, Beskrivelse av prosjekt og flytdiagram

Beskrivelse av prosjektet samt annen informasjon knyttet til brukeropplevelse og brukerhistorie, finner du på README.md filen under docs/release1 og docs/release3. Her er det også grafiske illustrasjoner, og beskrivelser av brukerhistoriene.

## Testrapporter fra JaCoCo, Checkstyle og SpotBugs

Testrapportene fra JaCoCo, Checkstyle og SpotBugs er å finne i target-mappen i hver modul etter å ha kjørt følgende kommandoer:

### Jacoco:

- mvn jacoco:prepare-agent test jacoco:report

### Ckeckstyle:

- mvn checkstyle:check

### SpotBugs:

- mvn spotbugs:spotbugs

## Mer om arbeidsflyt, arbeidsvaner og kodekvalitet

Beskrivelse arbeidsflyt, arbeidsvaner og testdekning finner man i README.md under docs/release2.

## Pakkediagram, Klassediagram, Sekvensdiagram og MVVM logical view

Finner man under docs/release2 og docs/release3.

## AI/KI deklarasjon

For hver del av prosjektet er det vedlagt en egen AI/KI deklarsjon, som heter "ai-tools.md". Dette finnes under hver release mappe.
