package it.corso.rubjdbc.dao;

import it.corso.rubjdbc.model.Contatto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// gestito come Singleton
public class RubricaDao {

    private static RubricaDao dao = new RubricaDao();

    private RubricaDao() {
    }

    public static RubricaDao getDao() {
        return dao;
    }

    public Contatto salva(Contatto c) {
        throw new UnsupportedOperationException();
    }

    public void rimuovi(Contatto c) {
        // ottiene la connessione al DB
        String sql = "delete from contatto where id = ?"; // 1-dichiaro la stringa SQL con i ?
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rubricarand",
                "root",
                "");  PreparedStatement stat = con.prepareStatement(sql)) {   // 2-preparo lo statement) {

            // prepara delete su chiave primaria
            stat.setLong(1, c.getId()); // 3-valorizzo il parametro i-esimo

            // non conviene per illegibilità della query e ripetizione ogni volta di parsing e execution plan
//            String sql = "delete from contatto where id = " + c.getId();
            // la esegue 
            int num = stat.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Contatto> trovaTutti() {
        // ottiene la connessione al DB
        List<Contatto> lista = new ArrayList<>();
        String sql = "select * from contatto";
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rubricarand",
                "root",
                "");  
                Statement stat = con.createStatement();  
                ResultSet res = stat.executeQuery(sql);) {

            // prepara select *
            // la esegue e ottiene un ResultSet
            // itera sul ResultSet per leggere i campi, creare oggetti Contatto e aggiungerli ad una List<Contatto>
            while (res.next()) {
                long id = res.getLong("id");
                String fn = res.getString("nome");
                String ln = res.getString("cognome");
                String ph = res.getString("telefono");
                Contatto cx = new Contatto(id, fn, ln, ph);
                lista.add(cx);
                System.out.println("id = " + id + "; Nome = " + fn + "; Cognome = " + ln);
            }
            // ritorna la lista
            return lista;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}
