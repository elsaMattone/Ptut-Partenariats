package partenariats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Etiquette;

public interface EtiquetteRepository extends JpaRepository<Etiquette, Integer> {
    
    Etiquette findByIntitule(String intitule);

    List<Etiquette> findByIntituleContaining(String substring);
}
