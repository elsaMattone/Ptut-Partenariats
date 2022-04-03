/*DROP  TABLE IF EXISTS Partenaire, Contact, Etiquette, Commentaire, PartenaireEtiquette, ContactEtiquette, PartenaireCommentaire, ContactCommentaire;

CREATE TABLE Partenaire(
	ID_Partenaire INT NOT NULL  PRIMARY KEY IDENTITY(1011,1),
	-- start with (le nuéro a partir du quel on peut générer automatiquement)
	Raison_Sociale VARCHAR(40) NOT NULL,
	Adresse VARCHAR(60), 
	Ville VARCHAR(15), 
	Region VARCHAR(24), 
	Code_postal CHAR(10), 
	Pays VARCHAR(15), 
	Telephone VARCHAR(24) default NULL,
	Mail_Partenaire VARCHAR(40)
	--Personid int IDENTITY(1,1)
	
);

CREATE TABLE Contact (
	ID_Contact INT NOT NULL PRIMARY KEY IDENTITY(2017,1),
	Nom VARCHAR(30) NOT NULL,
	Prenom VARCHAR(30) NOT NULL,
	Mail VARCHAR(40),
	Tel_portable VARCHAR(24),
	Tel_fixe VARCHAR(24),
	Fonction VARCHAR(40),
	Partenaire_contact INT NOT NULL,
	CONSTRAINT FK_Partenaire FOREIGN KEY (Partenaire_contact) REFERENCES Partenaire(ID_Partenaire)
	
);

CREATE TABLE Etiquette (
	ID_Etiquette INT NOT NULL PRIMARY KEY IDENTITY(13,1), 
	Intitule VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Commentaire (
	ID_Commentaire INT NOT NULL PRIMARY KEY IDENTITY(4003,1), 
	Texte VARCHAR(255) DEFAULT NULL,
	Date_Commentaire DATE NOT NULL,
	Auteur VARCHAR(30) DEFAULT ('Marion')
);

CREATE TABLE PartenaireEtiquette (
	ID_PE INT PRIMARY KEY IDENTITY(6041,1),
	Partenaire_etq  INT,
	Etiquette_Partenaire INT REFERENCES Etiquette (ID_Etiquette),
	CONSTRAINT FK_Partenaire_etq FOREIGN KEY (Partenaire_etq) REFERENCES Partenaire(ID_Partenaire),
	CONSTRAINT FK_Etiquette_Partenaire FOREIGN KEY (Etiquette_Partenaire) REFERENCES Etiquette(ID_Etiquette)
);

CREATE TABLE ContactEtiquette (
	ID_CE INT PRIMARY KEY IDENTITY(8002,1),
	Contact_etq  INT ,
	Etiquette_contact INT ,
	CONSTRAINT FK_Contact_etq FOREIGN KEY (Contact_etq) REFERENCES Contact(ID_Contact),
	CONSTRAINT FK_Etiquette_contact FOREIGN KEY (Etiquette_contact) REFERENCES Etiquette(ID_Etiquette)
);

CREATE TABLE PartenaireCommentaire (
	ID_PC INT PRIMARY KEY IDENTITY(9004,1),
	Partenaire_com INT,
	Commentaire_Partenaire INT,
	CONSTRAINT FK_Partenaire_com FOREIGN KEY (Partenaire_com) REFERENCES Partenaire(ID_Partenaire),
	CONSTRAINT FK_Commentaire_Partenaire FOREIGN KEY (Commentaire_Partenaire) REFERENCES Commentaire (ID_Commentaire)
);

CREATE TABLE ContactCommentaire (
	ID_CC INT PRIMARY KEY IDENTITY(11003,1),
	Contact_com  INT,
	Commentaire_contact INT,
	CONSTRAINT FK_Contact_com FOREIGN KEY (Contact_com) REFERENCES Contact(ID_Contact),
	CONSTRAINT FK_Commentaire_contact FOREIGN KEY (Commentaire_contact) REFERENCES Commentaire (ID_Commentaire)
)*/