package it.corso.rubjdbc.dao;

import it.corso.rubjdbc.model.Contatto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.Contained;

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
        throw new UnsupportedOperationException();
    }

    public List<Contatto> trovaTutti() {
        // fino alle 12.35
        // ottiene la connessione al DB
        Connection con = null;
        Statement stat = null;
        List<Contatto> lista = new ArrayList<>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rubricarand",
                    "root",
                    "");

            // prepara select *
            stat = con.createStatement();
            String sql = "select * from contatto";

            // la esegue e ottiene un ResultSet
            ResultSet res = stat.executeQuery(sql);

            // itera sul ResultSet per leggere i campi, creare oggetti Contatto e aggiungerli ad una List<Contatto>
            while (res.next()) {
                long id = res.getLong("id");
                String fn = res.getString("nome");
                String ln = res.getString("cognome");
                String ph = res.getString("telefono");
                Contatto cx = new Contatto(fn, ln, ph);
                cx.setId(id);
                lista.add(cx);
                System.out.println("id = " + id + "; Nome = " + fn + "; Cognome = " + ln);
            }
            // ritorna la lista
            return lista;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                    // lasciato bianco intenzionalmente
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    // lasciata in bianco intenzionalmente
                }
            }
        }

    }

}
