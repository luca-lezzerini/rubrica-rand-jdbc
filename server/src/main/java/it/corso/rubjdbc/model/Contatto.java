package it.corso.rubjdbc.model;

import it.corso.rubjdbc.datasource.EntityWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//public class Contatto implements ResultSetReader<Contatto>{
public class Contatto implements EntityWriter{

    Long id;

    String nome;
    String cognome;
    String telefono;

    public Contatto() {
    }

    public Contatto(String nome, String cognome, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
    }

    public Contatto(Long id, String nome, String cognome, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Contatto{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + '}';
    }

//    @Override
//    public List<Contatto> readFromResultSet(ResultSet rs) throws SQLException {
//        List<Contatto> lista = new ArrayList<>();
//        while (rs.next()) {
//            Contatto cx = new Contatto(rs.getLong("id"),
//                    rs.getString("cognome"),
//                    rs.getString("nome"),
//                    rs.getString("telefono"));
//            lista.add(cx);
//        }
//        return lista;
//    }
    
//    @Override
//    public List<Contatto> readFromResultSet(ResultSet rs) throws SQLException {
    public static List<Contatto> leggiQua(ResultSet rs) throws SQLException {
        List<Contatto> lista = new ArrayList<>();
        while (rs.next()) {
            Contatto cx = new Contatto(rs.getLong("id"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("telefono"));
            lista.add(cx);
        }
        return lista;
    }

    @Override
    public PreparedStatement prepareForWriting(Connection con) throws SQLException{
        String sql = "insert into Contatto (\"id\",\"nome\",\"cognome\",\"telefono\")"
                + " values (?,?,?,?)";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setLong(1,getId());
        prep.setString(2,getNome());
        prep.setString(3,getCognome());
        prep.setString(4,getTelefono());
        return prep;
    }

}
