package it.corso.rubjdbc.dao;

import it.corso.rubjdbc.datasource.JdbcDataSource;
import it.corso.rubjdbc.model.Contatto;
import it.corso.rubjdbc.model.HibernateSequence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            JdbcDataSource ds = new JdbcDataSource();
            List<HibernateSequence> rs_hs = ds.querySelect("select * from hibernate_sequence", new HibernateSequence());
            List<Contatto> rs_contatti = ds.querySelect("select * from contatti", Contatto::leggiQua);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public Contatto salvaOld(Contatto c) {
        Contatto result;
        long new_id;

        try ( Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rubrica?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin&useSSL=false",
                "root",
                "");  Statement hib_not_found = conn.createStatement();  ResultSet hib_rs = conn.createStatement().executeQuery("SELECT next_val FROM hibernate_sequence");  PreparedStatement hib_update = conn.prepareStatement("UPDATE hibernate_sequence SET next_val = ?");  PreparedStatement new_contatto = conn.prepareStatement("INSERT INTO contatto (id, cognome, nome, telefono) VALUES (?, ?, ?, ?)");  PreparedStatement contatto_stmt = conn.prepareStatement("SELECT * FROM contatto WHERE id = ?")) {

            if (hib_rs.next()) {
                new_id = hib_rs.getLong("next_val") + 1;
            } else {
                hib_not_found.executeUpdate("INSERT INTO hibernate_sequence (next_val) VALUES (1)");
                new_id = 1;
            }

            new_contatto.setLong(1, new_id);
            new_contatto.setString(2, c.getCognome());
            new_contatto.setString(3, c.getNome());
            new_contatto.setString(4, c.getTelefono());

            new_contatto.executeUpdate();

            hib_update.setLong(1, new_id);
            hib_update.executeUpdate();

            contatto_stmt.setLong(1, new_id);

            try ( ResultSet rs = contatto_stmt.executeQuery()) {
                if (rs.next()) {
                    result = new Contatto(rs.getLong("id"),
                            rs.getString("cognome"),
                            rs.getString("nome"),
                            rs.getString("telefono"));
                    return result;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                "");  Statement stat = con.createStatement();  ResultSet res = stat.executeQuery(sql);) {

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
