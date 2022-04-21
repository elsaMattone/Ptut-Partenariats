package partenariats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import partenariats.dao.EtiquetteRepository;
import partenariats.entity.Etiquette;

@Controller
@RequestMapping(path="/partenariats/etiquette")
public class EtiquetteController {

	@Autowired
	private EtiquetteRepository dao;

	//affiche tous les étiquettes et affiche la vue listeCommentaires.mustache 
	@GetMapping(path = "show")
	public	String afficheToutesLesEtiquettes(Model model) {
		model.addAttribute("etiquettes", dao.findAll());
		return "listeEtiquettes";
	}			

	//ajoute une étiquette et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("etiquette") Etiquette etiquette) {
		return "formulaireEtiquette";
	}	

	//modifie une étiquette et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idEtiquette") Etiquette etiquette, Model model) {
		model.addAttribute("etiquette", etiquette);
		return "formulaireEtiquette";
	}
        
	//enregistre les modifiactions ou l'ajout sauf s'il existe déjà et redirige vers la liste
	@PostMapping(path = "save")
	public String ajouteLaEtiquettePuisMontreLaListe(Etiquette etiquette, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.save(etiquette);
			message = "L'étiquette a bien été enregistrée !";
		}catch (DataIntegrityViolationException e){
			message = "ERREUR : l'étiquette " + etiquette.getIntitule() + "existe déjà !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:/partenariats/etiquette/show"; 
	}
	
	/*
	@GetMapping(path = "delete")
	public String supprimeUneEtiquettePuisMontreLaListe(@RequestParam("idEtiquette") Etiquette etiquette) {
			dao.delete(etiquette); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
			return "redirect:/partenariats/etiquette/show"; // on se redirige vers l'affichage de la liste
	}*/

	//supprime une étiquette sauf s'il ya une dépendance et redirige vers la vue précédente
	@GetMapping(path = "delete")
	public String supprimeUneEtiquettePuisMontreLaListe(@RequestParam("idEtiquette") Etiquette etiquette, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.delete(etiquette); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
			message = "L'étiquette a été suppprimée";
		}catch(DataIntegrityViolationException e){
			message = "ERREUR : Impossible de supprimer l'étiquette !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:/partenariats/etiquette/show"; // on se redirige vers l'affichage de la liste
	}
}

