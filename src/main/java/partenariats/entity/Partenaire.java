package partenariats.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//Lombok
@Getter @Setter @NoArgsConstructor @ToString 
@XmlRootElement
public class Partenaire implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idPartenaire;

	@NotNull
	private String raisonSociale;

	@Embedded
	private AdressePostale adresse;
	/* faire sans adresse postale  pbm à l'affichage */

	@Size(max = 24)
	@Column(length = 24)
	private String telephone;

	@Size(max = 40)
	@Column(length = 40)
	private String mailPartenaire;

	@JsonIgnore // Ne pas inclure dans le format JSON
	@XmlTransient  // Ne pas inclure dans le format XML
	@ToString.Exclude  // Ne pas inclure dans le toString
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "partenaire")
	private List<Contact> contacts;

	@ManyToMany
	private List<Etiquette> etiquettes; 

	@ManyToMany
	private List<Commentaire> commentaires; 
	  
    @Override
	public int hashCode() {
		int hash = 0;
		hash += (idPartenaire != null ? idPartenaire.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Partenaire)) {
			return false;
		}
		Partenaire other = (Partenaire) object;
		return ((this.idPartenaire == null && other.idPartenaire == null) || (this.idPartenaire != null && this.idPartenaire.equals(other.idPartenaire)));
	}
}

