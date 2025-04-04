/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USER
 */
public class ModelServer extends AbstractTableModel{
    private List<Wrapper>w;
    private String[]kol=new String []{"zvanje", "broj"};
    public ModelServer(List<Wrapper>w)
    {
     this.w=w;   
    }
    @Override
    public int getRowCount() {
        return w.size();
    }

    @Override
    public int getColumnCount() {
        return kol.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Wrapper a=w.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getZ();
            case 1:
                return a.getBr();
             default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kol[column];
    }

    public List<Wrapper> getW() {
        return w;
    }

    public void setW(List<Wrapper> w) {
        this.w = w;
    }
    
}
