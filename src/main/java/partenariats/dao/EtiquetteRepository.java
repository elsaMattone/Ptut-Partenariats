package partenariats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Etiquette;

public interface EtiquetteRepository extends JpaRepository<Etiquette, Integer> {
    
    Etiquette findByIntitule(String intitule);

    List<Etiquette> findByIntituleContaining(String substring);

    /*SELECT ETIQUETTE .INTITULE , PARTENAIRE .RAISON_SOCIALE 
    FROM ETIQUETTE INNER JOIN ETIQUETTE_PARTENAIRE ON ETIQUETTE.ID_ETIQUETTE =ETIQUETTE_PARTENAIRE .ID_ETIQUETTE 
    INNER JOIN PARTENAIRE ON ETIQUETTE_PARTENAIRE.ID_PARTENAIRE =PARTENAIRE .ID_PARTENAIRE */
}
