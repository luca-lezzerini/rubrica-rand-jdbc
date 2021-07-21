package it.corso.rubjdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcDataSource {

    Connection con;
    Statement stmt;
    PreparedStatement prep;
    ResultSet res;

//    public <T extends ResultSetReader> List<T> querySelect(String sql, T entity) throws SQLException {
    public <T> List<T> querySelect(String sql, ResultSetReader<T> entity) throws SQLException {
        con = getConnection();
        stmt = con.createStatement();
        res = stmt.executeQuery(sql);
        return entity.readFromResultSet(res);
    }

    /**
     *
     * @param <T> tipo dell'entità da ritornare
     * @param sql stringa sql con parametri (?)
     * @param entity ORM embrionale per mappare da result set a POJO
     * @param p
     * @return lista delle entità recuperate dalla query
     * @throws SQLException
     */
    public <T> List<T> parametricQuerySelect(String sql, ResultSetReader<T> entity, Parametrizer p) throws SQLException {
        con = getConnection();
        prep = con.prepareStatement(sql);
        p.parametrize(prep);
        res = prep.executeQuery();
        return entity.readFromResultSet(res);
    }

    /**
     *
     * @param sql stringa sql con parametri (?)
     * @param p
     * @return numero dei record modificati
     * @throws SQLException
     */
    public int parametricQueryUpdate(String sql, Parametrizer p) throws SQLException {
        con = getConnection();
        prep = con.prepareStatement(sql);
        p.parametrize(prep);
        int i = prep.executeUpdate();
        return i;
    }

    public int insertQuery(EntityWriter entity) throws SQLException {
        con = getConnection();
        prep = entity.prepareForWriting(con);
        int i = prep.executeUpdate();
        return i;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/rubrica?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin&useSSL=false",
                "root",
                "");
        return conn;
    }
}
