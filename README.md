# MTTPP projekt - Automatizirano testiranje web mjesta

Za temu ovog projekta odabrala sam automatsko testiranje web mjesta pomoću Selenium WebDrivera. Web mjesto koje se testira u projektu je službena stranice Formule 1, čiji je URL: https://www.formula1.com/

U ovom repozitoriju nalaze se testovi kojima se isprobava kretanje kroz stranicu, te neke ključne funkcionalnosti poput navigacije, prijave i promjene postavki prikaza. Svi testovi su pisani u Java programskom jeziku koristeći IntelliJ IDEA razvojno okruženje te Selenium, TestNG i Maven ovisnosti.


### Arhitektura projekta
Projekt je organiziran pomoću Page Object Modela dizajna, odnosno projekt je podijeljen radi preglednosti:
* **pages paket** - sadrži klasu F1Page.java u kojoj se nalaze lokatori i metode koje se kasnije pozivaju u testovima.
* **test paket** - sadrži testove koji su jednostavnije strukture jer se dio metoda poziva iz F1Page klase.
* **BaseTest.java** - je glavna klasa koja se brine za pokretanje i zatvaranje preglednika prije i poslije svakog testa. Nju nasljeđuju testne klase.

### Testni slučajevi:
1. **Open and check website test** - Osnovni test koji otvara stranicu i prihvaća kolačiće kako bi se osiguralo da je stranica dostupna.
2. **Login with invalid credentials test**- test koji pokušava obaviti prijavu s pogrešnom email adresom ili lozinkom, odnosno nepostojećim korisnikom. Provjerava se pojavljuje li se poruka o grešci, čime se potvrđuje da sustav prepoznaje neispravne podatke.
3. **F1 TV page load test** - provjerava otvara li se poveznica na F1 TV web mjesto uspješno i u novoj kartici.
4. **Filter race results by year test** - test provjerava odlazak na rezultate utrka ("Results" stranica) i u dropdown izborniku odabire 2021. godinu te provjerava je li se URL ispravno osvježio, tj. jesu li prikazani rezultati iz odabrane godine.
5. **Display mode change test** - test kojim se odlazi (scrolla) na dno stranice, odnosno dok se ne pojavi "Display mode" gumb te se mijenja tema prvo u Dark, pa zatim u Light. 

### Alati
* **Preglednik**: Google Chrome.
* **Driver**: Korišten je WebDriverManager pa nije potrebno ručno skidati driver.

### Pokretanje testova
* Unutar IntelliJ IDEA - desni klik na datoteku testSuite.xml u korjenu projekta i odabir opcije Run
