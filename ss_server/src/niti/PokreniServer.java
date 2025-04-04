/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
radniku studentske sluzbe na klijentskoj aplikaciji omoguciti prijavu na sistem. Korisnici sistema se na server aplikaciji cuvaju u operativnoj memoriji u listi.
Za svakog radnika cuvaju se ime, prezime, mail i loz. korisnici se prijavljuju na sistem tako sto unose mail i lozinku. 
Prilikom prijave izvrsiti autentifikaciju korisnika(proveriti a li postoji u listi i ukoliko postoji korisniku prikazati glavnu ekransku formu na kojoj treba prikazati ime i prezime kojim se ulogovao, u suprotnom omoguciti da ponovo unese podatke za autentifikaciju. 
posle treceg neuspesnog pokusaja prijave na sistem, aplikaciju automatski zatvoriti. 
(je l k+s ili samo k?, valjda k)

na glavnoj ekranskoj formi na klijentskoj aplikaciji omoguciti korisniku da:
-unese novog profesora. podaci o prof se cuvaju na serveru u tabeli profesor u subp.
za svakog prof se cuvaju ime, prez, zvanje(docent, vanredni prof, redovni prof) i mail korisinka koji je uneo prof u sistem
-unese predmete na kojima je angazova prof. moze biti angazovan na najvise 3 predmeta. unos angazovanja i predmeta izvrsiti pod transakcijom.
prilikom cuvanja angazovanja cuva se i datum(unosi se u sistem) kao i mail korisnika(automatski) koji je to angazovanje uneo u sistem. 
predmeti se cuvaju u subp. za svaki predmet cuvaju se sifra, naziv, kod(tekst-10), i esbp. 
-prikazati sve predmete na kojima je angazovan prof.
-izmenu angazovanja profesora na predmetu(dodati novo/izbrisati/izmeniti postojece). kod izmene omoguciti samo izmenu datuma. izmena je pod transakcijom.

na glavnoj ekranskoj formi serverske aplikacije prikazati  u tabeli sve profesore koji postoje u sistemu i na svakih 5 sekundi osvezavati.
pored imena i zvanja,prikazati i broj predmeta na kojima je svaki profesor angazovan. 

na glavnoj ekranskoj formi serverske aplikacije prikazati u tabeli po svakom  zvanju ukupan broj profesora. ukoliko u nekom zvanju nema nastavnika,
ne prikazivati zvanje. prikaz osvezavati na svake 3 sekunde.
*/

/**
 *
 * @author USER
 */
public class PokreniServer extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket ss=new ServerSocket(9000);
            while(true)
            {
                Socket s=ss.accept();
                System.out.println("dosao klijent");
                ObradaZahteva o=new ObradaZahteva(s);
                o.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
