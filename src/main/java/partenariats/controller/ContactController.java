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

import partenariats.dao.ContactRepository;
import partenariats.entity.Contact;

@Controller
@RequestMapping(path="/partenariats/contact")
public class ContactController {

    @Autowired
	private ContactRepository dao;

	//affiche tous les contacts et affiche la vue listeCommentaires.mustache 
	@GetMapping(path = "show")
	public	String afficheToutesLesContacts(Model model) {
		model.addAttribute("contacts", dao.findAll());
		return "listeContacts";
	}			
	
	//ajoute un contact et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "add")
	public String montreLeFormulairePourAjout(@ModelAttribute("contact") Contact contact) {
		return "formulaireContact";
	}	
	
	//modifie un contact et affiche la vue formulaireCommentaire.mustache
	@GetMapping(path = "edit")
	public String montreLeFormulairePourEdition(@RequestParam("idContact") Contact contact, Model model) {
		model.addAttribute("contact", contact);
		return "formulaireContact";
	}        
	
	//enregistre les modifiactions ou l'ajout sauf s'il existe déjà et redirige vers la liste
	@PostMapping(path = "save")
	public String ajouteLaContactPuisMontreLaListe(Contact contact, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.save(contact);
			message = "Le contact a bien été enregistré";
		}catch (DataIntegrityViolationException e){
			message = "ERREUR : le contact " + contact.getNom()+' '+contact.getPrenom() + "existe déjà !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:/partenariats/contact/show"; 
	}

	//supprime un contact sauf s'il ya une dépendance et redirige vers la vue précédente	
	@GetMapping(path = "delete")
	public String supprimeUneContactPuisMontreLaListe(@RequestParam("idContact") Contact contact, RedirectAttributes redirectInfo) {
		String message;
		try{
			dao.delete(contact);
			message = "Le contact n'a pas été suppprimé";
		}catch(DataIntegrityViolationException e){
			message = "ERREUR : Impossible de supprimer le contact !";
		}
		redirectInfo.addFlashAttribute("message", message);
		return "redirect:/partenariats/contact/show"; 
	}
    
}
