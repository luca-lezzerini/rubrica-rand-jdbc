package it.corso.rubjdbc.datasource;

import it.corso.rubjdbc.model.Contatto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetReader<T> {

    List<T> readFromResultSet(ResultSet rs) throws SQLException;
}
