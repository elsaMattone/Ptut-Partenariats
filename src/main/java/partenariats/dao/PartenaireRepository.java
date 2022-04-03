package partenariats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Partenaire;

public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {
    
}
