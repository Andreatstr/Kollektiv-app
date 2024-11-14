# Testdekning
Vi har en høy testdekning på prosjektet med et snitt på rundt 80%. Dette er noe høyere enn anvist ettersom dette bare innkluderer enhetstestser. Vi har også integrasjons tester men disse viser ikke testdekning. Vi har også programert defansivt, som vil si at vi har programmert for å håndtere eventuelle feil som uventede null verdier. Vi har ikke testet alle mulige kombinasjoner av feil som gjør testdekningsgraden noe lavere enn om vi ikke hadde prøvd å håntere feil. 

## Core:

![Core testdekning](diagrams/CoreTestReport.png)

## Model:
Vi har en høy testdekningsgrad i model på 80%, det er noen funksjoner vi ikke har testet her ettersom det ikke er mulig, eller ikke er vits å innkuldere som enhetstester. For eksempel har vi ikke tester på ServerApi classen ettersom denne krever at vi har en server kjørende. 
![Model testdekning](diagrams/ModelTestReport.png)

## View:

![View testdekning](diagrams/ViewTestReport.png)

## Integrasjonstesting
Vi har integrasjonstesting i restserver.
Disse testene starter en server og sjekker at funksjoner mellom client og server fungerer som de skal.
Her blir alle funksjoner i api-en testet.
Men vi har ikke test-rapporter for denne delen ettersom vi ikke klarte å få de laget.

