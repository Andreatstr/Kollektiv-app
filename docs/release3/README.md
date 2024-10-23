# Beskrivelse av prosjektet (release 3)


## REST-tjenesten

- Dokumentasjon av REST-tjenesten, altså (format for) forespørslene som støttes.


## Refleksjon rundt implisitt lagring (krav fra øving 2)

I dette prosjektet benyttes implisitt lagring istedenfor dokumentmetafor, da dette er mest gunstig med tanke på brukeropplevelsen og appens funksjon. I forhold til en dokumentmetafor, hvor brukeren er direkte involvert med lagring av filene sine, gjør dette applikasjonen mer brukervennlig, da bruker slippet å bekymre seg for tap av data om en glemmer å trykke på en "lagre"-knapp og annen filhåndtering. All data som lagres er knyttet til en kollektiv-ID, og lagres automatisk i en JSON-fil, og er sikret kontinuerlig synkronisering og konsistens ved slik automatisk lagring. Det bør dog merkes at det finnes noen ulemper med denne løsninger, for eksempel mangel på versjonshåndtering og mindre kontroll for brukeren på hva som lagres hvor og når. Gruppen ser likevel at implisitt lagring gir best brukeropplevelse i forhold til applikajsonens bruksområde og tenkt brukermåte.

## Brukerhistorie

En brukerhistorie som beskriver den nye boss-funksjonen aiv applikasjonen:
![En brukerhistorie som beskriver et brukstilfellet av applikasjonen](brukerhistorie2.png)


## Arkitekur og diagrammer

Vi har laget tre diagrammer som beskriver arkitekturen og strukturen i prosjektet med forskjellige mengde abstraksjon. 


### Pakkediagram

- Litt om pakkediagrammet: Et pakkedigram for løsningen...osv
- Selve pakkediagrammet


### Klassediagram

- Litt om klassediagrammet: Et klassediagram for viktigste deler av systemet...osv
- Selve klassediagrammet


### Sekvensdiagram

- Litt om sekvensdiagrammet: Et sekvensdiagram for et viktig brukstilfelle, som viser koblingen mellom brukerinteraksjon og hva som skjer inni systemet inkl. REST-kall... osv
- Selve sekvensdiagrammet