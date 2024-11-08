# Beskrivelse av prosjektet (release 3)


## REST-tjenesten

- Dokumentasjon av REST-tjenesten, altså (format for) forespørslene som støttes.


## Refleksjon rundt implisitt lagring (krav fra øving 2)

I dette prosjektet benyttes implisitt lagring istedenfor dokumentmetafor, da dette er mest gunstig med tanke på brukeropplevelsen og appens funksjon. I forhold til en dokumentmetafor, hvor brukeren er direkte involvert med lagring av filene sine, gjør dette applikasjonen mer brukervennlig, da bruker slippet å bekymre seg for tap av data om en glemmer å trykke på en "lagre"-knapp og annen filhåndtering. All data som lagres er knyttet til en kollektiv-ID, og lagres automatisk i en JSON-fil, og er sikret kontinuerlig synkronisering og konsistens ved slik automatisk lagring. Det bør dog merkes at det finnes noen ulemper med denne løsninger, for eksempel mangel på versjonshåndtering og mindre kontroll for brukeren på hva som lagres hvor og når. Gruppen ser likevel at implisitt lagring gir best brukeropplevelse i forhold til applikajsonens bruksområde og tenkt brukermåte.

## Brukerhistorie

En brukerhistorie som beskriver den nye boss-funksjonen aiv applikasjonen:
![En brukerhistorie som beskriver et brukstilfellet av applikasjonen](diagrams/brukerhistorie2.png)


## Arkitekur og diagrammer

Vi har laget tre diagrammer som beskriver arkitekturen og strukturen i prosjektet med forskjellige mengde abstraksjon. 


### Pakkediagram

Pakkediagrammet viser hvordan pakkene og modulene i prosjektet kommuniserer med hverandre. Datapakken er et bibliotek for klasser som trengs av flere andre pakker samtidig.
- Selve pakkediagrammet


### Klassediagram

Klassediagrammet viser de viktisgte klassene i systemet og hvordan de er koblet sammen. For å gjøre diagrammet mer oversiktlig har vi valgt å se bort ifra data pakken.
- Selve klassediagrammet


### Sekvensdiagram

Sekvensdiagrammet viser koblingen mellom brukerinteraksjon og det som skjer inni systemet for et viktig brukstilfellet. Brukstilfellet er når bruker oppretter et nytt kollektiv med tilhørende ID, og videre oppretter en ny vaskeplan i applikasjonen.

![](diagrams/sequenceDiagram.png)