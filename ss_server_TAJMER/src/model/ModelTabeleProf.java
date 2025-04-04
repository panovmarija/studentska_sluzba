/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */
public class ModelTabeleProf extends AbstractTableModel {
    private List<Wrapprof>profesori;
    private String[]kol=new String []{"profesor", "zvanje","broj_predmeta"};
    public ModelTabeleProf(List<Wrapprof>profesori)
    {
     this.profesori=profesori;   
    }
    @Override
    public int getRowCount() {
        return profesori.size();
    }

    @Override
    public int getColumnCount() {
        return kol.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Wrapprof a=profesori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getIme();
            case 1:
                return a.getZ();
            case 2:
                return a.getBroj();
             default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kol[column];
    }

    public List<Wrapprof> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Wrapprof> profesori) {
        this.profesori = profesori;
    }

}
