/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author USER
 */
public class Izmena implements Serializable {
    private List<Angazovanje> a;
    private Profesor p;

    public Izmena() {
    }

    public Izmena(List<Angazovanje> a, Profesor p) {
        this.a = a;
        this.p = p;
    }

    public List<Angazovanje> getA() {
        return a;
    }

    public Profesor getP() {
        return p;
    }
    
    
}
