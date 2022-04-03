package partenariats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    
}
