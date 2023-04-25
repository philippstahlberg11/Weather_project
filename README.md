# Mitt program

Se [oppgaveteksten](./OPPGAVETEKST.md) til semesteroppgave 2. Denne README -filen kan du endre som en del av dokumentasjonen til programmet, hvor du beskriver for en bruker hvordan programmet skal benyttes.

-viser til kilder fra bilder på readMe - eller noe sånt


Programmet pleier å ta en del sekunder før det åpnes, dette siden det henter en god del informasjon som prosseseres før det kan vise informasjonen som vi ønsker til oss. Selve programmet er en enkel inspirasjon ifra ting som Yr.no, Storm, Pent og andre relevante sider. Med andre ord, en applikasjon som viser til hvordan været er, en god del info om forskjellige ting, som temperatur/vindstyrke-/retning/Fuktighet osv. Dette vises på det aktuelle (altså akkurat nå), i en time-for-time tabell, og en dag-for-dag tabell. Samtidig viser vi også litt slik informasjon om planeten mars, siden vi øsnker å vise at de verktøyene vi har, kan brukes mer generelt på flere ulike databaser, uavhengig av hvordan det er bygd opp kan det justeres! I time-for-time tabellen, kan vi gå framover (men ikke for langt), og bakover (men altså ikke tilbake i tid!). Noen ganger vil noe info ikke kunne vises (kan være ulike grunner til dette, og disse vil da komme med en advarsel om dette!). En kan komme seg ned til time-for-time tabellen ved å "scrolle" nedover, med piltastene eller ved musen!
En kan gå fram og tilbake i tid i time-for-time tabellene ved å trykke på "next page" og "previous... (page)", knappene forsvinner om en har komt til slutten av enhver av "sidene". 
Applikasjonen blir mer generelt brukt for å se på været, framover i tid, aktuell tid, og hvordan været til og med på mars er, dette på en forhåpentligvis lettvindt og simpel måte, men samtidig relativt informativt måte!

Vi har også lagt til et bilbiotek i xml-filen, sjekk i dependecies (står kilde der også!)
I tilegg tar vi informasjon ifra følgende json-url-ene: 
 - https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.391262&lon=5.322054 (for været generelt, justert til Bergensområdet)
 -https://api.maas2.apollorion.com/ (for informasjon ifra Mars, litt utdatert, men dette vises for brukeren også!)


# WEATHER_APPLICATION

YT - video om hvordan progammet virker i praksis:

https://youtu.be/SZXv3zqCJyU