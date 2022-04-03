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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
// Lombok
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@XmlRootElement // Pour générer du XML
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idContact;

    @Size(max = 30)
	@Column(length = 30)
    private String nom;

    @Size(max = 30)
	@Column(length = 30)
    private String prenom;

    @Size(max = 30)
	@Column(length = 30)
	private String mail;

    @Size(max = 24)
	@Column(length = 24)
    private String telephonePortable;

    @Size(max = 24)
	@Column(length = 24)
    private String telephoneFixe;

    @Size(max = 30)
	@Column(length = 30)
    private String fonction;

	@ManyToOne
    private Partenaire partenaireContact;
	
    /*
    @JsonIgnore // Ne pas inclure dans le format JSON
	@XmlTransient  // Ne pas inclure dans le format XML
	@ToString.Exclude  // Ne pas inclure dans le toString	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
	//private List<Commande> commandes;*/

    @Override
	public int hashCode() {
		int hash = 0;
		hash += (idContact != null ? idContact.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Contact)) {
			return false;
		}
		Contact other = (Contact) object;
		return ((this.idContact == null && other.idContact == null) || (this.idContact != null && this.idContact.equals(other.idContact)));
	}
}


