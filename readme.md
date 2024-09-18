# Group gr2436 repository 
 
[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2436/gr2436?new)

[open test branch in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2436/gr2436/-/tree/15-koble-prosjektet-til-eclipse-che?new)


## Nødvendige versioner for å kjøre prosjektet:

Maven version 3.9.9
Java testet versjoner : Java 21.0.1, 21.0.4, java 23


## Inneholde
Vi har to Maven prosjekter api-server og app-gruppe36.
For øyeblikket er det bare app-gruppe36 som skal brukes.

Vi bruker UI modulen for å håndtere brukergrensesnittet. AppController, håndterer brukergrensesnittet gjennom JavaFX. App, er hovedklassen i prosjektet som starter prosjektet. Logikken blir håndtert i AppController.



## Bygging og kjøring av prosjektet

For å kjøre prosjektet må du følge disse 4 stegene:

### 1
For å bygge prosjektet må man først beffine seg i app-gruppe36.
cd app-gruppe36

### 2
så kan man bygge prosjektet med

mvn install

### 3
For å kjøre prosjektet må man først befinne seg under filen ui

cd ui

### 4 
Så kan man kjøre prosjektet

mvn javafx:run


## Brukerhistorier, Beskrivelse av prosjekt og flytdiagram
Beskrivelse av prosjektet samt annen informasjon knyttet til brukeropplevelse og brukerhistorie, finner du på README.md filen under docs/release1. Her er det også grafiske illustrasjoner.

