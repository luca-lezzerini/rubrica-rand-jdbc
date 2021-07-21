package it.corso.rubjdbc.datasource;

import it.corso.rubjdbc.model.Contatto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContattoOrm implements ResultSetReader<Contatto>{

    @Override
    public List<Contatto> readFromResultSet(ResultSet rs) throws SQLException {
        return Contatto.leggiQua(rs);
    }

}
