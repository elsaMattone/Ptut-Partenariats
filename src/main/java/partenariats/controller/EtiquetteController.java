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
@RequestMapping(path="/partenariats/etiquette")
public class EtiquetteController {

	@Autowired
	private EtiquetteRepository dao;

	/**
	 * Affiche toutes les catégories dans la base
	 * @param model pour transmettre les informations à la vue
	 * @return le nom de la vue à afficher ('showCategories.html')
	 */
	@GetMapping(path = "show")
	public	String afficheToutesLesCategories(Model model) {
		model.addAttribute("categories", dao.findAll());
		return "showCategories";
	}	
		
	/**
	 * Montre le formulaire permettant d'ajouter une catégorie
	 * @param etiquette initialisé par Spring, valeurs par défaut à afficher dans le formulaire 
	 * @return le nom de la vue à afficher ('formulaireCategorie.html')
	 */
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("etiquette") Etiquette etiquette) {
		return "formulaireCategorie";
	}
	
	/**
	 * Appelé par le lien 'Modifier' dans 'showCategories.html'
	 * Montre le formulaire permettant de modifier une catégorie
	 * @param etiquette à partir du code de la catégorie transmis en paramètre, 
	 *                  Spring fera une requête SQL SELECT pour chercher la catégorié dans la base
	 * @param model pour transmettre les informations à la vue
	 * @return le nom de la vue à afficher ('formulaireCategorie.html')
	 */
	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idEtiquette") Etiquette etiquette, Model model) {
		model.addAttribute("etiquette", etiquette);
		return "formulaireCategorie";
	}
        
	/**
	 * Appelé par 'formulaireCategorie.html', méthode POST
	 * @param etiquette Une catégorie initialisée avec les valeurs saisies dans le formulaire
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
	@PostMapping(path = "save")
	public String ajouteLaCategoriePuisMontreLaListe(Etiquette etiquette) {
		// cf. https://www.baeldung.com/spring-data-crud-repository-save
		dao.save(etiquette); // Ici on peut avoir une erreur (doublon dans un libellé par exemple)
		return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
	}	

	/**
	 * Appelé par le lien 'Supprimer' dans 'showCategories.html'
	 * @param etiquette à partir du code de la catégorie transmis en paramètre, 
	 *                  Spring fera une requête SQL SELECT pour chercher la catégorié dans la base
	 * @return une redirection vers l'affichage de la liste des catégories
	 */
	@GetMapping(path = "delete")
	public String supprimeUneCategoriePuisMontreLaListe(@RequestParam("idEtiquette") Etiquette etiquette) {
		dao.delete(etiquette); // Ici on peut avoir une erreur (Si il y a des produits dans cette catégorie par exemple)
		return "redirect:show"; // on se redirige vers l'affichage de la liste
	}
}
