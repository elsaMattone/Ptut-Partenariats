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

import partenariats.dao.PartenaireRepository;
import partenariats.entity.Partenaire;

@Controller
@RequestMapping(path="/partenariats/partenaire")
public class PartenaireController {

    @Autowired
	private PartenaireRepository dao;

	//affiche tous les partenaires et affiche la vue listeCommentaires.mustache 
	public PartenaireController(PartenaireRepository dao){
		this.dao=dao;
	}
	
	@GetMapping(path = "show")
	public	String afficheToutesLesPartenaires(Model model) {
		model.addAttribute("partenaires", dao.findAll());
		return "listePartenaires";
	}			
	
	//ajoute un partenaire et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("partenaire") Partenaire partenaire) {
		return "formulairePartenaire";
	}	
	
	//modifie un partenaire et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idPartenaire") Partenaire partenaire, Model model) {
		model.addAttribute("partenaire", partenaire);
		return "formulairePartenaire";
	}
	
	/*
	@PostMapping(path = "save")
	public String ajouteLaPartenairePuisMontreLaListe(Partenaire partenaire) {
		dao.save(partenaire);		
		return "redirect:/partenariats/partenaire/show"; 
	}
	
	@GetMapping(path = "delete")
	public String supprimeUnePartenairePuisMontreLaListe(@RequestParam("idPartenaire") Partenaire partenaire) {
		dao.delete(partenaire);// Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)		
		return "redirect:/partenariats/partenaire/show"; // on se redirige vers l'affichage de la liste
	}*/
	
	//enregistre les modifiactions ou l'ajout sauf s'il existe déjà et redirige vers la liste
	@PostMapping(path = "save")
	public String ajouteLaPartenairePuisMontreLaListe(Partenaire partenaire, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.save(partenaire);
			message = "Le partenaire a bien été enregistré";
		}catch (DataIntegrityViolationException e){
			message = "ERREUR : le partenaire " + partenaire.getRaisonSociale() + " existe déjà !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:partenariats/partenaire/show"; 
	}

	//supprime un partenaire sauf s'il ya une dépendance et redirige vers la vue précédente	
	@GetMapping(path = "delete")
	public String supprimeUnePartenairePuisMontreLaListe(@RequestParam("idPartenaire") Partenaire partenaire, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.delete(partenaire); 
			message = "Le partenaire n'a pas été suppprimé";
		}catch(DataIntegrityViolationException e){
			message = "ERREUR : Impossible de supprimer le partenaire !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:partenariats/partenaire/show"; 
}
