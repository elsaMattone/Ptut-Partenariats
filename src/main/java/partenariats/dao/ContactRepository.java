package partenariats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import partenariats.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}
