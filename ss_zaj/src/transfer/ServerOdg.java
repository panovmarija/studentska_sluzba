/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class ServerOdg implements Serializable{
    private int operacija;
    private Object odg;

    public ServerOdg() {
    }

    public ServerOdg(int operacija, Object odg) {
        this.operacija = operacija;
        this.odg = odg;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getOdg() {
        return odg;
    }

    public void setOdg(Object odg) {
        this.odg = odg;
    }
    
}
