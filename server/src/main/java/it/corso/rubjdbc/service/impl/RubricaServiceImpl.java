package it.corso.rubjdbc.service.impl;

import it.corso.rubjdbc.dao.RubricaDao;
import it.corso.rubjdbc.model.Contatto;
import it.corso.rubjdbc.service.RubricaService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RubricaServiceImpl implements RubricaService {

    RubricaDao dao = RubricaDao.getDao();

    @Override
    public List<Contatto> salva(Contatto c) {
        dao.salva(c);
        return dao.trovaTutti();
    }

    @Override
    public List<Contatto> rimuovi(Contatto c) {
        dao.rimuovi(c);
        return dao.trovaTutti();
    }

    @Override
    public List<Contatto> trovaTutti() {
//        RubricaDao dao = new RubricaDao();
        return dao.trovaTutti();
    }

}
