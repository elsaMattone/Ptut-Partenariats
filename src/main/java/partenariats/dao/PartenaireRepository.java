package partenariats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Partenaire;

public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {

    /*
     * SELECT PARTENAIRE.RAISON_SOCIALE, PARTENAIRE.VILLE, CONTACT.FONCTION,
     * CONTACT.NOM, CONTACT.PRENOM
     * FROM PARTENAIRE
     * INNER JOIN CONTACT ON PARTENAIRE.ID_PARTENAIRE = CONTACT. PARTENAIRE;
     */

}
