package partenariats.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Getter @Setter @NoArgsConstructor @ToString
@XmlRootElement // Pour générer du XML
public class Contact implements Serializable {

	/*les classes-entités permettent d'auto-générer la création des tables
	Ici c'est la table Contact
	Liste des attributs : ID_Contact INT, Nom VARCHAR(30), Prenom VARCHAR(30), Mail VARCHAR(40), Tel_portable VARCHAR(24),
	Tel_fixe VARCHAR(24), Fonction VARCHAR(40), Partenaire_contact INT ,
	Vous pouvez retrouver la création des tables dans le fichier schema_sql.sql*/

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idContact;

    @Size(max = 30)
	@Column(length = 30)
	@NotNull
    private String nom;

    @Size(max = 30)
	@Column(length = 30)
    private String prenom;

    @Size(max = 50)
	@Column(length = 50, name="mail")
	private String mail;

    @Size(max = 24)
	@Column(length = 24)
	@NonNull
    private String telFixe;

    @Size(max = 24)
	@Column(length = 24)
	@NonNull
    private String telPortable;

    @Size(max = 40)
	@Column(length = 40)
    private String fonction;

	/*Un contact est rattaché à un partenaire(entreprise)*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="partenaire",referencedColumnName="idPartenaire")
    private Partenaire partenaire;

	@ManyToMany
	private List<Etiquette> etiquettes; 

	@ManyToMany
	private List<Commentaire> commentaires; 

	/*Permets de générer des ID différents pour chaque contacts*/
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


