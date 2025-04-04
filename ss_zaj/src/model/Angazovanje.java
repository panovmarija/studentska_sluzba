/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Angazovanje implements Serializable{
    private Profesor p;
    private Predmet pred;
    private Date dat;
    private String mail;
    public Angazovanje() {
    }

    public Angazovanje(Profesor p, Predmet pred, Date dat, String mail) {
        this.p = p;
        this.pred = pred;
        this.dat = dat;
        this.mail = mail;
    }

    public Profesor getP() {
        return p;
    }

    public void setP(Profesor p) {
        this.p = p;
    }

    public Predmet getPred() {
        return pred;
    }

    public void setPred(Predmet pred) {
        this.pred = pred;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Angazovanje other = (Angazovanje) obj;
        return Objects.equals(this.pred, other.pred);
    }

    @Override
    public String toString() {
        return "Angazovanje{" + "pred=" + pred + '}';
    }

 
    
}
