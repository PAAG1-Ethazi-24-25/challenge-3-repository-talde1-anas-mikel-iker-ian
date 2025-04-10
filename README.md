# AnasBrahim-challenge-3-repository-talde1-anas-mikel-iker-ian

# SecondHandSaleAdmin

## **Sarrera: Aplikazioaren helburua**

**SecondHandSaleAdmin** aplikazioaren helburua da bigarren eskuko produktuen salmenta eta kudeaketa erraztea. JavaFX erabiliz sortu dugu administrazio-tresna hau, bezero, langile, produktu, kategoria eta salmenten datuak erregistratzeko, bistatzeko, aldatzeko eta ezabatzeko aukera ematen duena. Helburua da administratzaileek erabiltzeko erraza den sistema garbia eta arina eskaintzea.

---

## **Klase diagrama**

 [sakatu Hemen klase diagrama ikus ahal izateko](informe.pdf)

---

1. **Hasierako menu nagusia**: Erabiltzaileak zer ekintza burutu nahi duen aukeratzen du (Txertatu, Bistaratu, Aldatu, Ezabatu).
2. **Aukeratutako ekintzaren arabera**:
   - Bezero, langile, produktu, salmenta edo kategoria taulak bistaratzen dira.
   - Erabiltzaileak formularioen bidez datuak gehitu edo editatu ditzake.
3. **Itzulera menura**: Ekintza amaitutakoan, erabiltzailea berriro menura itzuli daiteke beste ekintza bat aukeratzeko.

Eszena bakoitza JavaFX bidez kudeatzen da eta CSS estiloekin pertsonalizatuta dago interfaze garbiago eta erakargarriago baterako.

---

## **Interfaze Grafikoaren fluxua**

##  **Oharrak**

- **Funtzionamendua**: Aplikazioaren funtzionalitate nagusi guztiak ondo funtzionatzen dute. Datuen sarrera eta kontsulta ondo funtzionatzen dute, baita ere balidazioak ere ondo daude.
- **Harrotasun puntua**: datu-basearen atzipen logika garbia da gure iritziz eta baita ere adibidez produktu bat sartzean erabiltzailea errazago edukitzeko choicebox-ak edikitza id-a sartu beharrean.
- **Balorazioa**: Gure ustez, egindako lanak 8/10 gutxienez merezi du. Egitura garbia, funtzionalitate osoa eta erabilerraztasuna kontuan hartuta, baita ere erabiltzaileak, produktuak, langileak, eta bezeroak kudeatu ditzakezulako programa brzerean.

---

## **Proiektuaren egitura**

```plaintext
secondhandsaleadmin/
└── src/
    └── main/
        ├── java/
        │   └── devworks/
        │       ├── App.java                    # Aplikazio nagusia
        │       ├── controller/                 # Eszena kontrolatzaileak
        │       ├── model/                      # atzipenak konsultak eta metodoak edukitzako
        │       └── base/                       # oinarrizko klaseak
        └── resources/
            └── devworks/
                └── css/                        # Itxura pertsonalizatzeko estiloak

