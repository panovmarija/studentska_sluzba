/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import baza.DBB;
import java.util.LinkedList;
import java.util.List;
import model.Angazovanje;
import model.Izmena;
import model.Predmet;
import model.Profesor;
import model.Radnik;
import model.Wrapper;
import model.Wrapprof;
import model.Zvanje;

/**
 *
 * @author USER
 */
public class Kontroler {
    private DBB dbb;
    private List<Radnik>radnici;
    private static Kontroler instance; 
    public static Kontroler getInstance() {
        if(instance==null)
            instance=new Kontroler();
        return instance;
    }

    private Kontroler() {
        radnici=new LinkedList<>();
        Radnik a=new Radnik("a", "a", "a", "a");
        Radnik b=new Radnik("b", "b", "b", "b");
        Radnik c=new Radnik("a", "c", "c", "c");
        radnici.add(a);
        radnici.add(b);
        radnici.add(c);
        dbb=new DBB();
        
    }

    public List<Radnik> getRadnici() {
        return radnici;
    }

    public Radnik login(Radnik r) {
        if(!radnici.contains(r))return r;
        return radnici.get(radnici.indexOf(r));
    }

    public List<Profesor>  vratiProf() {
        return dbb.vratiProf();
    }

    public List<Predmet>   vratiPred() {
        return dbb.vratiPred();
    }

    public boolean sacuvaj_prof_i_ang(Izmena i) {
        return dbb.sacuvaj_prof_i_ang(i);
    }

    public List<Angazovanje> vrati_angazovanja(Profesor profesor) {
        return dbb.vrati_angazovanja( profesor);
    }

    public boolean izmeni_angazovanja(Izmena i) {
        return dbb.izmeni_angazovanja(i);
    }

    public List<Wrapper> vrati_broj() {
        return dbb.vratiBroj();
    }

    public List<Wrapprof> vratiProf_i_brAng() {
        return dbb.vratiProf_i_brAng();
    }
    
    
}
