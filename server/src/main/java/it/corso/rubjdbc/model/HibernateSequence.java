package it.corso.rubjdbc.model;

import it.corso.rubjdbc.datasource.ResultSetReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateSequence  implements ResultSetReader<HibernateSequence>{

    Long nextVal;

    public HibernateSequence() {
    }

    public HibernateSequence(Long nextVal) {
        this.nextVal = nextVal;
    }

    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public String toString() {
        return "HibernateSequence{" + "nextVal=" + nextVal + '}';
    }

    @Override
    public List<HibernateSequence> readFromResultSet(ResultSet rs) throws SQLException {
        List<HibernateSequence> lista = new ArrayList<>();
        while (rs.next()) {
            HibernateSequence cx = new HibernateSequence(rs.getLong("next_val"));
            lista.add(cx);
        }
        return lista;
    }


}
