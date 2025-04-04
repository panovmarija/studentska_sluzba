/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Angazovanje;
import model.Izmena;
import model.Predmet;
import model.Profesor;
import model.Zvanje;
import model.Wrapper;
import model.Wrapprof;

/**
 *
 * @author USER
 */
public class DBB {

    public List<Profesor> vratiProf() {
            List<Profesor> l=new LinkedList<>();
        try {
            String u="select * from prof";
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                Profesor p=new Profesor(rs.getInt("id"), rs.getString("ime"), rs.getString("prez"), rs.getString("mail"), Zvanje.valueOf(rs.getString("zvanje")));
                l.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public List<Predmet>  vratiPred() {
            List<Predmet> l=new LinkedList<>();
        try {
            String u="select * from pred";
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                Predmet p=new Predmet(rs.getInt("sifra"), rs.getInt("espb"), rs.getString("naz"), rs.getString("kod"));
                System.out.println(p);
                l.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public boolean sacuvaj_prof_i_ang(Izmena i) {
        try {
            Profesor p = i.getP();
            int id = vratiId();
            p.setId(id);
//        id,ime,prez,zvanje,mail
            String u1 = "insert into prof values(?,?,?,?,?)";
            PreparedStatement ps1 = Konekcija.getInstance().getConn().prepareStatement(u1);
            ps1.setInt(1, id);
            ps1.setString(2, p.getIme());
            ps1.setString(3, p.getPrez());
            ps1.setString(4, p.getZvanje() + "");
            ps1.setString(5, p.getMail());
            int afr = ps1.executeUpdate();
            if (afr == 0) {
                return false;
            }
//        prof,pred, dat, mail
            String u2 = "insert into ang values (?,?,?,?)";
            PreparedStatement ps2 = Konekcija.getInstance().getConn().prepareStatement(u2);
            for (Angazovanje a : i.getA()) {
                ps2.setInt(1, id);
                ps2.setInt(2, a.getPred().getSifra());
                ps2.setDate(3, a.getDat() == null ? null : new java.sql.Date(a.getDat().getTime()));
                ps2.setString(4, a.getMail());
                ps2.addBatch();
            }
            ps2.executeBatch();
            Konekcija.getInstance().getConn().commit();
        } catch (SQLException ex) {
            try {
                Konekcija.getInstance().getConn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private int vratiId() {
            int l=1;
        try {
            String u="select case when max(id)+1 is null then 1 else max(id)+1 end as id from prof";
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                l=rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public  List<Angazovanje> vrati_angazovanja(Profesor p) {
            System.out.println(p.getId());
            List<Angazovanje> l=new LinkedList<>();
        try {
            String u="select * from ang a join pred p on(a.pred=p.sifra) where a.prof="+p.getId();
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                Predmet pred=new Predmet(rs.getInt("sifra"), rs.getInt("espb"), rs.getString("naz"), rs.getString("kod"));
                Angazovanje a=new Angazovanje(p, pred, rs.getDate("dat")==null?null:new Date(rs.getDate("dat").getTime()), rs.getString("a.mail"));
                l.add(a);
                System.out.println(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public boolean izmeni_angazovanja(Izmena i) {
        List<Angazovanje>model=i.getA();
        Profesor p=i.getP();
        List<Angazovanje>baza=vrati_angazovanja(p);
        try{
        for(Angazovanje a:model)
        {
            if(baza.contains(a))izmeni(a);
            else dodaj(a);
        }
        for(Angazovanje a:baza)
        {
            if(!model.contains(a))izbrisi(a);
        }
        Konekcija.getInstance().getConn().commit();
        }
        catch(SQLException e)
        {
            try {
                Konekcija.getInstance().getConn().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        return true;
    }

    private void izmeni(Angazovanje a) throws SQLException {
            String u = "update ang set dat=? where prof=? and pred=? ";
            PreparedStatement ps = Konekcija.getInstance().getConn().prepareStatement(u);
                ps.setDate(1, a.getDat() == null ? null : new java.sql.Date(a.getDat().getTime()));
                ps.setInt(2, a.getP().getId());
                ps.setInt(3, a.getPred().getSifra());
                ps.executeUpdate();
    }

    private void dodaj(Angazovanje a) throws SQLException {
            String u = "insert into ang values (?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConn().prepareStatement(u);
                ps.setInt(1, a.getP().getId());
                ps.setInt(2, a.getPred().getSifra());
                ps.setDate(3, a.getDat() == null ? null : new java.sql.Date(a.getDat().getTime()));
                ps.setString(4, a.getMail());
                ps.executeUpdate();
    }

    private void izbrisi(Angazovanje a) throws SQLException {
            String u = "delete from ang where prof=? and pred=? ";
            PreparedStatement ps = Konekcija.getInstance().getConn().prepareStatement(u);
                ps.setInt(1, a.getP().getId());
                ps.setInt(2, a.getPred().getSifra());
                ps.executeUpdate();
    }

    public List<Wrapper> vratiBroj() {
            List<Wrapper> l=new LinkedList<>();
        try {
            String u = "select zvanje,count(*) broj from prof group by zvanje ";
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                Wrapper w=new Wrapper(Zvanje.valueOf(rs.getString("zvanje")), rs.getInt("broj"));
                l.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public List<Wrapprof> vratiProf_i_brAng() {
            List<Wrapprof> l=new LinkedList<>();
        try {
            String u = "SELECT  p.id,p.ime, p.prez, p.zvanje ,COUNT(*) broj FROM prof p LEFT JOIN ang a ON(a.prof=p.id) GROUP BY p.id,p.ime, p.prez, p.zvanje";
            Statement s=Konekcija.getInstance().getConn().createStatement();
            ResultSet rs=s.executeQuery(u);
            while(rs.next())
            {
                Wrapprof w=new Wrapprof(rs.getString("ime")+" "+rs.getString("prez"), Zvanje.valueOf(rs.getString("zvanje")), rs.getInt("broj"));
                l.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
        

    
}
