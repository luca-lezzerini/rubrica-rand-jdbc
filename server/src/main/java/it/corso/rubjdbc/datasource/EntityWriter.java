package it.corso.rubjdbc.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface EntityWriter {

    PreparedStatement prepareForWriting(Connection con) throws SQLException;
}
