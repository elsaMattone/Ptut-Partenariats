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
import lombok.NonNull;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//Lombok
@Getter @Setter @NoArgsConstructor @ToString 
@XmlRootElement
public class Partenaire implements Serializable{

	/*les classes-entités permettent d'auto-générer la création des tables
	Ici c'est la table Partenaire
	Liste des attributs : 
	ID_Partenaire INT NOT NULL  PRIMARY KEY IDENTITY(1011,1),	
	Raison_Sociale VARCHAR(40) NOT NULL,
	Adresse VARCHAR(60), 
	Ville VARCHAR(15), 
	Region VARCHAR(24), 
	Code_postal CHAR(10), 
	Pays VARCHAR(15), 
	Telephone VARCHAR(24) default NULL,
	Mail_Partenaire VARCHAR(40)
	Vous pouvez retrouver la création des tables dans le fichier schema_sql.sql*/

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idPartenaire;

	@NonNull
	@Column(name="raison_sociale")
	private String raisonSociale;

	@Size(max = 60)
	@Column(length = 60)
	private String adresse;

	@Size(max = 15)
	@Column(length = 15)
	private String ville;

	@Size(max = 25)
	@Column(length = 25)
	private String region;

	@Size(max = 10)
	@Column(length = 10)
	private String codePostal;

	@Size(max = 15)
	@Column(length = 15)
	private String pays;

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
	
	/*Permets de générer des ID différents pour chaque partenaires*/
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

