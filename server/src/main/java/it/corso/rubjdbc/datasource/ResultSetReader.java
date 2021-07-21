package it.corso.rubjdbc.datasource;

import it.corso.rubjdbc.model.Contatto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Questa interfaccia rappresenta un oggetto in grado di tradurre un result set in una istanza di entità.
 * In poche parole, è un ORM in embrione.
 * E' un'interfaccia funzionale.
 * 
 * @param <T> 
 */
@FunctionalInterface
public interface ResultSetReader<T> {

    List<T> readFromResultSet(ResultSet rs) throws SQLException;
}
