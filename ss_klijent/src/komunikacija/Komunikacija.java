/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Radnik;
import transfer.KlijentZahtev;
import transfer.ServerOdg;

/**
 *
 * @author USER
 */
public class Komunikacija {
    private Socket s;
    private static Komunikacija instance; 
    public static Komunikacija getInstance() {
        if(instance==null)
            instance=new Komunikacija();
        return instance;
    }

    private Komunikacija() {
        try {
            s=new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public ServerOdg primiOdg()
    {
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (ServerOdg) ois.readObject();
        } catch (IOException ex) {
            System.out.println("server odvezan/pad mreze/gasenje forme");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void posaljiZahtev(KlijentZahtev kz)
    {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriSoket() {
        if(s!=null && !s.isClosed())
            try {
                s.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(s.isClosed());
    }
    

}
