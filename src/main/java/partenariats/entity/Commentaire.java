package partenariats.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@XmlRootElement
public class Commentaire implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(nullable = false)
	private Integer idCommentaire;

	@Size(max = 255)
	@Column(length = 255)
	@NotNull
	@NonNull
	private String texte;

    private LocalDate date_Commentaire;

    @Size(max = 40)
	@Column(length = 40)
    private String auteur;

	@JsonIgnore
	@XmlTransient
	@ToString.Exclude
	@ManyToMany (cascade= CascadeType.MERGE)
	@JoinTable(name="commentaire_contact", joinColumns = @JoinColumn(name="idCommentaire"), inverseJoinColumns = @JoinColumn(name="idContact"))
	private List<Contact> contacts; 

	@JsonIgnore
	@XmlTransient
	@ToString.Exclude
	@ManyToMany(cascade= CascadeType.MERGE)
	@JoinTable(name="commentaire_partenaire", joinColumns = @JoinColumn(name="idCommentaire"), inverseJoinColumns = @JoinColumn(name="idPartenaire"))
	private List<Partenaire> partenaires;

    @Override
	public int hashCode() {
		int hash = 0;
		hash += (idCommentaire != null ? idCommentaire.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Commentaire)) {
			return false;
		}
		Commentaire other = (Commentaire) object;
		return (this.idCommentaire == null && other.idCommentaire == null) || (this.idCommentaire != null && this.idCommentaire.equals(other.idCommentaire));
	}
}
