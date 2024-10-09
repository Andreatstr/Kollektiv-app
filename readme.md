# Group gr2436 repository 
 
[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2436/gr2436?new)

## Nødvendige versioner for å kjøre prosjektet:

Maven version 3.9.9
Java testet versjoner : Java 21.0.1, 21.0.4, java 23


## Innhold
Vi har to Maven prosjekter: api-server og app-gruppe36.
For øyeblikket er det bare app-gruppe36 som skal brukes.

Vi bruker view-modulen for å håndtere brukergrensesnittet. View-kontrollerne håndterer brukergrensesnittet gjennom JavaFX. App, er hovedklassen i prosjektet som starter prosjektet. Logikken blir håndtert i kontrollerene, som kaller på metoder i viewmodel-klasser i model-modulen. Disse kaller igjen på model-klasser i samme modul.


## Bygging og kjøring av prosjektet

For å kjøre prosjektet må du følge disse 4 stegene:

### 1
For å bygge prosjektet må man først befinnne seg i app-gruppe36 ved å bruke følgende kommando:
cd app-gruppe36

### 2
Deretter kan man bygge prosjektet med:

mvn install

### 3
For å kjøre prosjektet må man befinne seg i view:

cd view

### 4 
Deretter kan man kjøre prosjektet

mvn javafx:run



## Brukerhistorier, Beskrivelse av prosjekt og flytdiagram


Beskrivelse av prosjektet samt annen informasjon knyttet til brukeropplevelse og brukerhistorie, finner du på README.md filen under docs/release1. Her er det også grafiske illustrasjoner.


## Testrapporter fra JaCoCo, Checkstyle og SpotBugs

Testrapportene fra JaCoCo, Checkstyle og SpotBugs er å finne i target-mappen i hver modul etter å ha kjørt følgende kommandoer:

### Jacoco:
 - mvn jacoco:prepare-agent test jacoco:report

### Ckeckstyle:
 - mvn checkstyle:check

### SpotBugs:
 - mvn spotbugs:spotbugs

Beskrivelse av prosjektet samt annen informasjon knyttet til brukeropplevelse og brukerhistorie, finner man i README.md filen under docs/release1. Her er det også grafiske illustrasjoner.

## Mer om arbeidsflyt, arbeidsvaner og kodekvalitet
Beskrivelse arbeidsflyt, arbeidsvaner og testdekning finner man i README.md under docs/release2.

