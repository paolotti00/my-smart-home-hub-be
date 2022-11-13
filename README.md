# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact

appunti 
#my smarthome
Usare mqtt:
1 ogni dispositivo deve avere il suo id , quindi scodare o leggere solo i messaggi che sono per lui (o per tutti) - magari una topic per i messaggi personali , una tipica per i messaggi broadcast
2 accoppiamento :
1 il dispositivo si collega per la prima volta
2 dal cell si mettono wifi e pass
3 il disp manda un messaggio dove dice , ciao sono un dispositivo di tipo (luce , sensore o chissà )
- dove manda il messaggio?
-- su coda mqtt? 
--- come fa a sapere come collegarsi alla topica? 
-- con chiamata rest? come fa a sapere quale è l'indirizzo da chiamare?
--- se si riuscisse a fare una chiamata rest sarebbe buono. perchè puoi rispondere in maniera sincrona alla chiamata, non hai bisogno di vericare se funziona.
- che dati dare in risposta?
- 

3.1  l hub risponde al messaggio , ciao ti ho registrato questo é il tuo ID . Questi sono alcuni dati che ti possono servire
3.2 il dispositivo si salva che é registrato e prende nota del suo id .
Comunicazione :
1 Il dispositivo ogni 5 minuti (poi si vede ) trasmette il suo stato e i suoi dati , se é un sensore
2 l hub salva questa lettura su mongo
--
1 mandare i comandi
1 l.hub manda un comando (specificando l Id del dispositivo e un numero progressivo (magari un timestamp) oppure un progressivo di comando

discovering :
usare 
- mDNS : https://bloggerbrothers.com/2017/01/08/name-your-pis-with-mdns-forget-the-ips-with-zeroconf/
--flow :
--- il dispositivo cerca l'hub (che deve avere attivo l'mdns  - il raspebbery ce l ha attivo di default e ci sta la libreria mdns per python)
--- il dispositivo sa l'indirizzo - può fare la chiamata di preregitrazione  (invia il proprio ip)
--- l'utente attiva il nuovo dispositivo 
---- da un nome al dispositivo
---- verifica che i sensori siano veri ecc ecc 
---- mette il dispositivo in un gruppo
--- l'hub fa una chiamata rest al dispositivo dove gli comunica che è stato attivato e l'indirizzo di mqtt
--- d'ora in poi i due dispositivi comunicano con mqtt
-- pro/ contro
- mi pare na cosa custom
- SSDP : 
flow :
-- l'hub cerca fra tutti i dispositivi 
-- trova un dispositivo nuovo 
-- lo salva tra i dispositivi da attivare 
-- l'utente attiva il nuovo dispositivo  
-- ....
-- ....
-- pro/ contro
--- pro
---- magari supporta altri sensori (da verificare)
--- contro 
---- dos attacc

--- i comandi in uscita li mandiamo in rest (tipo accendi luci , fai questo fai quello cosi si sa la risposta)
--- la lettura dei sensori li riceviamo in mqtt
--- ci sarà un metodo in mqtt che dirà aggiorna i tuoi dati (cosi il dispositivo reinvia i dati , per esempio l'ip. magari lo fa ogni tot da solo)