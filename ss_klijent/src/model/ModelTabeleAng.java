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
public class ModelTabeleAng extends AbstractTableModel {
    private boolean f=false;
    private List<Angazovanje>ang;
    private String[]kol=new String []{"pred", "dat", "mail",/*"prof"*/};
    public ModelTabeleAng(List<Angazovanje>ang)
    {
     this.ang=ang;   
    }
    @Override
    public int getRowCount() {
        return ang.size();
    }

    @Override
    public int getColumnCount() {
        return kol.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje a=ang.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getPred();
            case 1:
                if(a.getDat()==null)return "";
                return new SimpleDateFormat("dd.MM.yyyy.").format(a.getDat());
            case 2:
                return a.getMail();
//            case 3:
//                return a.getP();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kol[column];
    }

    public List<Angazovanje> getAng() {
        return ang;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return f&&columnIndex==1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue==null)return ;
        String dat=String.valueOf(aValue);
        try {
        ang.get(rowIndex).setDat(new SimpleDateFormat("dd.MM.yyyy.").parse(dat));
        } catch (Exception e) {
        }
    }
    
    public void setF(boolean f) {
        this.f = f;
    }
    
}
