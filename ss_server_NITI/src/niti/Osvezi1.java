/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Osvezi1 extends Thread {
    private ServerForma sf;

    public Osvezi1(ServerForma sf) {
        this.sf = sf;
    }
    
    @Override
    public void run() {
        while(true)
        {
            sf.osvezi1();
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Osvezi1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
