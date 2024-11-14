# Group gr2436 repository

[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2436/gr2436?new)

## Nødvendige versjoner for å kjøre prosjektet:

Maven version 3.9.9

Java testet og fungerende versjoner : Java 21.0.1, 21.0.2, 21.0.3 og 21.0.4

#### GitLab CI Pipeline docker-image brukt:

##### Første:

maven:3.8.3-openjdk-17

##### Nåværende:

maven:3.9.9-ibm-semeru-21-jammy

## Innhold

Prosjektet ligger i mappe app-gruppe36.

Vi bruker view-modulen for å håndtere brukergrensesnittet. View-kontrollerne håndterer brukergrensesnittet gjennom JavaFX. App, er hovedklassen i prosjektet som starter prosjektet. Logikken blir håndtert i kontrollerene, som kaller på metoder i viewmodel-klasser i model-modulen. Disse kaller igjen på model-klasser i samme modul.

## Bygging og kjøring av prosjektet

For å kjøre prosjektet må du følge disse 7 stegene:

### 1

For å bygge prosjektet må man først befinnne seg i app-gruppe36 ved å bruke følgende kommando:

cd app-gruppe36

### 2

Deretter kan man bygge prosjektet med (Om du får feilmeldinger etter clean install, kjør den en gang til):

mvn clean install

### 3

For å kjøre applikasjonen må man befinne seg i view:

cd view

### 4

Deretter kan man kjøre prosjektet

mvn javafx:run

### NB!

Full funksjonalitet for applikasjonen vil ikke fungere uten server, siden applikasjonen er designet og bygget rundt en server-klient arkitektur.

### 5

For å starte serveren må man åpne en ny terminal

cd app-gruppe36

### 6

Deretter må man bevege seg inn i servermappen

cd restserver

### 7

For å starte Springboot serveren

mvn spring-boot:run

### NB!

Server kan ikke kjøre dersom tester skal kjøres!

## Brukerhistorier, Beskrivelse av prosjekt og flytdiagram

Beskrivelse av prosjektet samt annen informasjon knyttet til brukeropplevelse og brukerhistorie, finner du i README.md filen under docs/release1 og docs/release3. Her er det også grafiske illustrasjoner, og beskrivelser av brukerhistoriene.

## Testrapporter fra JaCoCo, Checkstyle og SpotBugs

Testrapportene fra JaCoCo, Checkstyle og SpotBugs er å finne i target-mappen i hver modul etter å ha kjørt følgende kommandoer:

### Jacoco versjon (0.8.12):

- mvn jacoco:prepare-agent test jacoco:report

### Checkstyle (10.3.4):

- mvn checkstyle:check

### SpotBugs (versjon 4.8.6.0):

- mvn spotbugs:spotbugs

## Mer om arbeidsflyt, arbeidsvaner og kodekvalitet

Beskrivelse av arbeidsflyt, arbeidsvaner og testdekning finner man i README.md under docs/release3.

## Pakkediagram, Klassediagram, Sekvensdiagram og MVVM logical view

Disse diagrammene finner man under docs/release2 og docs/release3.

## Shippable produkt

For å holde README-filen ryddig er all informasjon om hvordan man går frem for å opprette shippable-produkt i docs/release3/shippable-produkt.md.

## AI/KI-deklarasjon

For hver del av prosjektet er det vedlagt en egen AI/KI deklarsjon, som heter "ai-tools.md". Dette finnes under hver release-mappe.
