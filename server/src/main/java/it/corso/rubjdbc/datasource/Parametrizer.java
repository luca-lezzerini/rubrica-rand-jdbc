package it.corso.rubjdbc.datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Parametrizer {
    void parametrize(PreparedStatement p) throws SQLException;
}
