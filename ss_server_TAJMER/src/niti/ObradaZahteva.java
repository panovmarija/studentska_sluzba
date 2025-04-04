/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import model.Angazovanje;
import model.Izmena;
import model.Profesor;
import model.Radnik;
import transfer.KlijentZahtev;
import transfer.ServerOdg;

/**
 *
 * @author USER
 */
public class ObradaZahteva extends Thread {
    private Socket s;
    private Kontroler k;
    public ObradaZahteva(Socket s) {
        this.s = s;
        k=Kontroler.getInstance();
    }

    @Override
    public void run() {
        KlijentZahtev kz;
        while((kz=primiZahtev())!=null)
        {
            ServerOdg so=new ServerOdg();
            switch (kz.getOperacija()) {
                case operacije.Operacije.login:
                    Radnik r=(Radnik) kz.getParam();
//                    vraca istu instancu radnika ako ne postoji, ako postoji nalazi ga i vraca ga
                    so.setOdg(k.login(r));
                    break;
                case operacije.Operacije.vrati_profesore:
                    so.setOdg(k.vratiProf());
                    break;
                case operacije.Operacije.vrati_predmete:
                    so.setOdg(k.vratiPred());
                    break;
                case operacije.Operacije.sacuvaj_prof_i_ang:
                    so.setOdg(k.sacuvaj_prof_i_ang((Izmena)kz.getParam()));
                    break;
                case operacije.Operacije.vrati_angazovanja:
                    so.setOdg(k.vrati_angazovanja((Profesor)kz.getParam()));
                    break;
                case operacije.Operacije.izmeni_angazovanja:
                    so.setOdg(k.izmeni_angazovanja((Izmena)kz.getParam()));
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdg(so);
        }
        if(s!=null && !s.isClosed())
            try {
                s.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(s.isClosed());
                
    }
    
    public KlijentZahtev primiZahtev()
    {
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (KlijentZahtev) ois.readObject();
        } catch (IOException ex) {
            System.out.println("klijent odvezan/pad mreze/gasenje forme");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void posaljiOdg(ServerOdg so)
    {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
