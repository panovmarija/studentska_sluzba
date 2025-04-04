/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Radnik implements Serializable {
    private String ime, prez, mail,loz;

    public Radnik() {
    }

    public Radnik(String ime, String prez, String mail, String loz) {
        this.ime = ime;
        this.prez = prez;
        this.mail = mail;
        this.loz = loz;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrez() {
        return prez;
    }

    public void setPrez(String prez) {
        this.prez = prez;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLoz() {
        return loz;
    }

    public void setLoz(String loz) {
        this.loz = loz;
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
        final Radnik other = (Radnik) obj;
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return Objects.equals(this.loz, other.loz);
    }

    @Override
    public String toString() {
        return ime+" "+prez;
    }
    
}
