package it.corso.rubjdbc.controller;

import it.corso.rubjdbc.model.Contatto;
import it.corso.rubjdbc.service.RubricaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RubricaController {

    @Autowired
    RubricaService rubricaService;

    @RequestMapping("/aggiungi")
    @ResponseBody
    public List<Contatto> aggiungi(@RequestBody Contatto c) {
        return rubricaService.salva(c);
    }

    @RequestMapping("/rimuovi")
    @ResponseBody
    public List<Contatto> rimuovi(@RequestBody Contatto c) {
        return rubricaService.rimuovi(c);

    }

    @RequestMapping("/trova-tutti")
    @ResponseBody
    public List<Contatto> trovaTutti() {
        return rubricaService.trovaTutti();
    }
}
