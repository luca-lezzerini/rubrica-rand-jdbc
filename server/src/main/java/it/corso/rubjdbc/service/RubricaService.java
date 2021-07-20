package it.corso.rubjdbc.service;

import it.corso.rubjdbc.model.Contatto;
import java.util.List;

public interface RubricaService {

    List<Contatto> salva(Contatto c);

    List<Contatto> rimuovi(Contatto c);

    List<Contatto> trovaTutti();
}
