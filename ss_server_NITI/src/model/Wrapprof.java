/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Wrapprof {
    private String ime;
    private Zvanje z;
    private int broj;

    public Wrapprof() {
    }

    public Wrapprof(String ime, Zvanje z, int broj) {
        this.ime = ime;
        this.z = z;
        this.broj = broj;
    }

    public String getIme() {
        return ime;
    }

    public int getBroj() {
        return broj;
    }

    public Zvanje getZ() {
        return z;
    }
    
}
