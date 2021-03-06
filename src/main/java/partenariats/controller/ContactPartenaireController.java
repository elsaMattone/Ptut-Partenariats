package partenariats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import partenariats.dao.PartenaireRepository;
import partenariats.entity.Partenaire;

@Controller
@RequestMapping(path="/partenariats/partenaire/contact")
public class ContactPartenaireController {
    
    /*Affiche une vue des contacts par partenaire
    partenaireConatct est la vue à afficher*/
    @Autowired
    private PartenaireRepository dao;

    @GetMapping
    public String contactParPartenaire (@RequestParam(name="idPartenaire", required = false) Partenaire partenaire, Model model){
        List<Partenaire> lesPartenaires = dao.findAll();
        if(partenaire == null){ //Si le partenaire est introuvable
            partenaire= lesPartenaires.get(0);
        }
        //infos transmisent à la vue
        model.addAttribute("partenaires", lesPartenaires);
        model.addAttribute("selected", partenaire);
        return "partenaireContact";
    }
}
