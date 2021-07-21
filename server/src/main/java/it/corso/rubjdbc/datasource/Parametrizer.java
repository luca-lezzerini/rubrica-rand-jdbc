package it.corso.rubjdbc.datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface Parametrizer {

    /**
     * Popola il PreparedStatement con i valori dei parametri
     * @param p prepared statement da popolare
     * @throws SQLException 
     */
    void parametrize(PreparedStatement p) throws SQLException;
}
