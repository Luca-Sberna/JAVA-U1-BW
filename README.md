# Buildweek-Java
Il sistema deve permettere l'emissione dei biglietti,sia da distributori automatici che da rivenditori autorizzati, oltre che l'emissionne di abbonamenti
settimanali e mensili di tipo nominativo ad utenti dotati di tessera. La tessera ha validità annuale e deve essere rinnovata alla scadenza. Ogni biglietto ed 
abbonamento è identificato da un codice univoco. Deve essere possibile tenere traciia del numero di biglietti e/o abbonamenti emessi in un dato periodo di tempo
in totale e per punto di emissione. I distributori automatici possono essere attivi o fuori servizio. Occorre inoltre permettere la verifica rapida della validità 
di un abbonamento in base al numero di tessere dell'utente controllato.

Il sistema deve inoltre prevedere la gestione del parco mezzo. I mezzi possono essere tram o autobus ed avere una certa capienza in base al tipo di mezzo.Ogni mezzo
può essere in servizio o in manutenzione ed occorre tenere traccia dei periodi di servizio o manutenzione di ogni mezzo. Quando un biglietto viene vidimato su un
mezzo esso deve essere annullato e deve essere possibile acquisire il numero di biglietti vidimati su un particolare mezzo o in totale in un periodo di tempo.

Ogni mezzo in servizio può essere assegnato ad una tratta, che è caretterizzata da una zona di partenza, un capolinea ed un tempo medio di percorrrenza. Occorre 
tenere traccia del numero di volte che un mezzo percorre una tappa e del tempo effettivo di percorrenza di ogni tratta

## Diagramma ER

![Diagramma ER - Mezzi Pubblici(1)](https://github.com/matteovcc/Buildweek-Java/assets/113930607/f5b6288b-78f9-402f-a21d-39c3660ba82b)
