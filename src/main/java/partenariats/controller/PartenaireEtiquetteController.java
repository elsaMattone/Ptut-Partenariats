package partenariats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import partenariats.dao.EtiquetteRepository;
import partenariats.entity.Etiquette;

@Controller
@RequestMapping(path="/partenariats/etiquette/partenaire")
public class PartenaireEtiquetteController {
    
    @Autowired
    private EtiquetteRepository dao;

    @GetMapping
    public String partenaireParEtiquette (@RequestParam(name="idEtiquette", required = false) Etiquette etiquette, Model model){
        List<Etiquette> lesEtiquettes = dao.findAll();
        if(etiquette == null){
            etiquette= lesEtiquettes.get(0);
        }
        model.addAttribute("etiquettes", lesEtiquettes);
        model.addAttribute("selected", etiquette);
        return "etiquettePartenaire";
    }
}
