package partenariats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import partenariats.dao.CommentaireRepository;
import partenariats.entity.Commentaire;

@Controller
@RequestMapping(path="/partenariats/commentaire")
public class CommentaireController {
    
    @Autowired
	private CommentaireRepository dao;

	
	@GetMapping(path = "show")
	public	String afficheToutesLesCategories(Model model) {
		model.addAttribute("commentaires", dao.findAll());
		return "listeCommentaires";
	}	
		

	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("commentaire") Commentaire commentaire) {
		return "formulaireCommentaire";
	}
	

	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idCommentaire") Commentaire commentaire, Model model) {
		model.addAttribute("commentaire", commentaire);
		return "formulaireCommentaire";
	}
        
	@PostMapping(path = "save")
	public String ajouteUnCommentairePuisMontreLaListe(Commentaire commentaire, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.save(commentaire);
			message = "Le commentaire a bien été enregistré !";
		}catch (DataIntegrityViolationException e){
			message = "ERREUR : le commentaire " + commentaire.getTexte() + "existe déjà ! ";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
	}	

	@GetMapping(path = "delete")
	public String supprimeUneCategoriePuisMontreLaListe(@RequestParam("idCommentaire") Commentaire commentaire, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.delete(commentaire); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
			message = "Le commentaire a été supprimé !";
		}catch(DataIntegrityViolationException e){
			message = "ERREUR : Impossible de supprimer le commentaire !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:show"; // on se redirige vers l'affichage de la liste
	}
}
