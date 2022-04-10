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

	/**
	 * Affiche toutes les catégories dans la base
	 * @param model pour transmettre les informations à la vue
	 * @return le nom de la vue à afficher ('showEtiquettes.html')
	 */
	@GetMapping(path = "show")
	public	String afficheToutesLesEtiquettes(Model model) {
		model.addAttribute("etiquettes", dao.findAll());
		return "listeEtiquettes";
	}	
		
	/**
	 * Montre le formulaire permettant d'ajouter une catégorie
	 * @param etiquette initialisé par Spring, valeurs par défaut à afficher dans le formulaire 
	 * @return le nom de la vue à afficher ('formulaireEtiquette.html')
	 */
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("etiquette") Etiquette etiquette) {
		return "formulaireEtiquette";
	}
	
	/**
	 * Appelé par le lien 'Modifier' dans 'showEtiquettes.html'
	 * Montre le formulaire permettant de modifier une catégorie
	 * @param etiquette à partir du code de la catégorie transmis en paramètre, 
	 *                  Spring fera une requête SQL SELECT pour chercher la catégorié dans la base
	 * @param model pour transmettre les informations à la vue
	 * @return le nom de la vue à afficher ('formulaireEtiquette.html')
	 */
	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idEtiquette") Etiquette etiquette, Model model) {
		model.addAttribute("etiquette", etiquette);
		return "formulaireEtiquette";
	}
        
	/**
	 * Appelé par 'formulaireEtiquette.html', méthode POST
	 * @param etiquette Une catégorie initialisée avec les valeurs saisies dans le formulaire
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
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
		return "redirect:show"; 
	}
	/**
	 * Appelé par le lien 'Supprimer' dans 'showEtiquettes.html'
	 * @param etiquette à partir du code de la catégorie transmis en paramètre, 
	 *                  Spring fera une requête SQL SELECT pour chercher la catégorié dans la base
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
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
		return "redirect:show"; // on se redirige vers l'affichage de la liste
	}
}
