package partenariats.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@XmlRootElement
public class Etiquette implements Serializable {

	/*les classes-entités permettent d'auto-générer la création des tables
	Ici c'est la table Etiquette
	Liste des attributs : ID_Etiquette INT NOT NULL PRIMARY KEY IDENTITY(13,1),  Intitule VARCHAR(40) NOT NULL UNIQUE
	Vous pouvez retrouver la création des tables dans le fichier schema_sql.sql*/

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idEtiquette;

	@Basic(optional = false)
	@NonNull // lombok
	@NotNull // Java validation
	@Size(min = 1, max = 40)	
	@Column(nullable = false, unique = true, length = 40)
	private String intitule;

	/*Relation ManyToMany avec contact 
	Une étiquette peut être lié à plusieurs contact
	et un contact peut avoir plusieurs étiquettes*/
	@JsonIgnore
	@XmlTransient
	@ToString.Exclude
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="etiquette_contact", joinColumns = @JoinColumn(name ="idEtiquette"),inverseJoinColumns = @JoinColumn(name="idContact"))
	private List<Contact> contacts; 

	/*Relation ManyToMany avec partenaire 
	Une étiquette peut être lié à plusieurs partenaire
	et un partenaire peut avoir plusieurs étiquettes*/
	@JsonIgnore
	@XmlTransient
	@ToString.Exclude
	@ManyToMany(cascade= CascadeType.MERGE)
	@JoinTable(name="etiquette_partenaire", joinColumns = @JoinColumn(name="idEtiquette"), inverseJoinColumns = @JoinColumn(name="idPartenaire"))
	private List<Partenaire> partenaires; 	

	/*Permets de générer des ID différents pour chaque étiquettes*/
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEtiquette != null ? idEtiquette.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Etiquette)) {
			return false;
		}
		Etiquette other = (Etiquette) object;
		return (this.idEtiquette == null && other.idEtiquette == null)
				|| (this.idEtiquette != null && this.idEtiquette.equals(other.idEtiquette));
	}
}
