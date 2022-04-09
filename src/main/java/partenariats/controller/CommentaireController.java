package partenariats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import partenariats.dao.EtiquetteRepository;
import partenariats.entity.Etiquette;

@Controller
@RequestMapping(path="/partenariats/commentaire")
public class CommentaireController {
    
    @Autowired
	private EtiquetteRepository dao;

	
	@GetMapping(path = "show")
	public	String afficheToutesLesCategories(Model model) {
		model.addAttribute("commentaires", dao.findAll());
		return "showCommentaires";
	}	
		

	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("commentaire") Etiquette commentaire) {
		return "formulaireCommentaire";
	}
	

	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idEtiquette") Etiquette commentaire, Model model) {
		model.addAttribute("commentaire", commentaire);
		return "formulaireCommentaire";
	}
        
	@PostMapping(path = "save")
	public String ajouteUnCommentairePuisMontreLaListe(Etiquette commentaire) {
		// cf. https://www.baeldung.com/spring-data-crud-repository-save
		dao.save(commentaire); // Ici on peut avoir une erreur (doublon dans un libellé par exemple)
		return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
	}	

	@GetMapping(path = "delete")
	public String supprimeUneCategoriePuisMontreLaListe(@RequestParam("idEtiquette") Etiquette commentaire) {
		dao.delete(commentaire); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
		return "redirect:show"; // on se redirige vers l'affichage de la liste
	}
}
