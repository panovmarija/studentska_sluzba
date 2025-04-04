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
    private List<Profesor>profesori;
    private String[]kol=new String []{"ime", "prez", "zvanje","mail"};
    public ModelTabeleProf(List<Profesor>profesori)
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
        Profesor a=profesori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getIme();
            case 1:
                return a.getPrez();
            case 2:
                return a.getZvanje();
            case 3:
                return a.getMail();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kol[column];
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

}
