DROP  TABLE IF EXISTS Partenaire, Contact, Etiquette, Commentaire, PartenaireEtiquette, ContactEtiquette, PartenaireCommentaire, ContactCommentaire;

CREATE TABLE Partenaire(
	ID_Partenaire INT NOT NULL PRIMARY KEY  ,
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
	ID_Contact INT NOT NULL PRIMARY KEY,
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
	ID_Etiquette INT NOT NULL PRIMARY KEY, 
	Intitule VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Commentaire (
	ID_Commentaire INT NOT NULL PRIMARY KEY, 
	Texte VARCHAR(255) DEFAULT NULL,
	Date_Commentaire DATE NOT NULL,
	Auteur VARCHAR(30) DEFAULT ('Marion')
);

CREATE TABLE PartenaireEtiquette (
	ID_PE INT PRIMARY KEY,
	Partenaire_etq  INT,
	Etiquette_Partenaire INT REFERENCES Etiquette (ID_Etiquette),
	CONSTRAINT FK_Partenaire_etq FOREIGN KEY (Partenaire_etq) REFERENCES Partenaire(ID_Partenaire),
	CONSTRAINT FK_Etiquette_Partenaire FOREIGN KEY (Etiquette_Partenaire) REFERENCES Etiquette(ID_Etiquette)
);

CREATE TABLE ContactEtiquette (
	ID_CE INT PRIMARY KEY,
	Contact_etq  INT ,
	Etiquette_contact INT ,
	CONSTRAINT FK_Contact_etq FOREIGN KEY (Contact_etq) REFERENCES Contact(ID_Contact),
	CONSTRAINT FK_Etiquette_contact FOREIGN KEY (Etiquette_contact) REFERENCES Etiquette(ID_Etiquette)
);

CREATE TABLE PartenaireCommentaire (
	ID_PC INT PRIMARY KEY,
	Partenaire_com INT,
	Commentaire_Partenaire INT,
	CONSTRAINT FK_Partenaire_com FOREIGN KEY (Partenaire_com) REFERENCES Partenaire(ID_Partenaire),
	CONSTRAINT FK_Commentaire_Partenaire FOREIGN KEY (Commentaire_Partenaire) REFERENCES Commentaire (ID_Commentaire)
);

CREATE TABLE ContactCommentaire (
	ID_CC INT PRIMARY KEY,
	Contact_com  INT,
	Commentaire_contact INT,
	CONSTRAINT FK_Contact_com FOREIGN KEY (Contact_com) REFERENCES Contact(ID_Contact),
	CONSTRAINT FK_Commentaire_contact FOREIGN KEY (Commentaire_contact) REFERENCES Commentaire (ID_Commentaire)
);


INSERT INTO Etiquette(ID_Etiquette, Intitule) VALUES
	(1, 'Stage'),
	(2, 'Taxe d''apprentissage'),
	(3, 'Contrat d''apprentissage'),
	(4, 'Contrat professionnalisation'),
	(5, 'Prestataire'),
	(6, 'Industriel'),
	(7, 'Institution'),
	(8, 'Etablissement de sante'),
	(9, 'Etablissement scolaire'),
	(10, 'Partenaire'),
	(11, 'Vacataire'),
	(12, 'Forum Entreprise ISIS');

--- pour des raisons de confidentialité ce ne sont pas les vrais noms des Partenaires
INSERT INTO Partenaire(id_Partenaire, raison_sociale, adresse, ville, region, code_postal, pays, telephone, mail_Partenaire) VALUES 
	(1001,'Insitut de la e-santé','30 rue des lys', 'Tours', 'Centre-Val de Loire', '3700', 'France', '06.85.23.78.63', 'contact@institut-esante.fr'),
	(1002,'CH de Sabran' , '101 Avenue Jean Moulin', 'Sabran','Occitanie','30200','France', NULL, 'contact.ch@ch-sabran.fr'),
	(1003, 'CHRU de Toulouse', '256 Route d''Albi', 'Toulouse', 'Occitanie', '31000', 'France','04.78.96.54.21', 'chru.tls.contact@chru-tls.com'),
	(1004, 'EntrepriseA' , '13 rue des marguerites', 'Albi', 'Occitanie','81000','France', NULL,'contacts-entrepriseA@entrepriseA.com'),
	(1005, 'Entrprise B' , '10 impasse des corbeaux', 'Castres','Occitanie','81100','France', '05.96.78.45.36','contact@entrpriseB.com'),
	(1006, 'Entreprise C' , '23 boulevard des lilas', 'Paris','Ile de France','75007','France', '04.23.14.19.73','contacts@entrepriseC.com'),
	(1007, 'CH de Dijon' , '54 place Simone Veil', 'Dijon','Bourgogne-Franche-Comté','21000','France', '05.73.48.62.15','contacts@ch-dijon.fr'),
	(1008, 'INSERM de Toulouse' , '3 rue Romain Gary', 'Toulouse','Occitanie','31000','France','0578134696', 'contacts-insrem@inserm-toulouse.com'),
	(1009, 'Clinique de Castres' , '48 avenue des hortensias', 'Castres','Tarn','81100','France', '02.36.58.97.41','contacts@cliniqueCastres.fr'),
	(1010, 'Entreprise D' , '70 avenue Sun Tzu', 'Paris','Ile de France','75012','France', NULL,'contacts@entrepriseD.com');

INSERT INTO Contact (ID_Contact, Nom, Prenom, Mail, Tel_fixe, Tel_portable, Fonction, Partenaire_contact) VALUES
	(2001,'Dupont','Agnès', 'dupont.agnes@institut-esante.fr', NULL, '06.53.48.78.96', 'Responsable Informatique', 1001),
	(2002, 'Dupuy' , 'Yvan', 'direction@ch-sabran.fr','05.36.96.36.58',NULL,'Directeur', 1002),
	(2003, 'Dujardin', 'Stéphanie', 'rsi@chru-tls.com', '05.45.63.21.89', '07.89.45.12.36', 'Responsable Systemes d''Informations',1003),
	(2004, 'Dupond' , 'Paul', 'drh@chru-tls.com', NULL,'07.89.12.35.63','Directeur RH', 1003),
	(2005, 'Georges' , 'Robert', 'robert.georges@entrepriseA.com','05.30.32.56.78','07.11.22.33.44','Responsable R&D', 1004),
	(2006, 'Martin' , 'Julie', 'directrice@entrepriseB.com','09.78.63.14.25', NULL, 'Directrice', 1005),
	(2007, 'Michel' , 'Simone', 'rsi@entrepriseC.com','09.1456.36.89','06.45.89.36.25', 'Responsable SI', 1006),
	(2008, 'Petit' , 'Anne', 'anne.petit@entrepriseC.com', NULL,'07.50.56.36.98','Expert SI', 1006),
	(2009, 'Dubray' , 'Marie', 'marie.dubray@ch-dijon.fr', NULL,'07.1313.65.45','Chef de projets informatiques',1007),
	(2010, 'Fabre' , 'Natalie', 'natalie.fabre@ch-dijon.fr', NULL,'07.50.12.47.49','Responsable Interop', 1007),
	(2011, 'Thomas', 'Marc', 'rsi@inserm-toulouse.com', '05.47.12.21.78', '07.10.02.12.36', 'Responsable Systemes d''Informations',1008),
	(2012, 'Bernard', 'Christian', 'drh@cliniqueCastres.fr', '05.7806.36.97', '07.58.78.10.25', 'Directuer RH',1009),
	(2013, 'Dubois', 'Christophe', 'dubois.christophe@entrepriseD.com', NULL, '07.10.02.32.58', 'Ingenieur developpeur',1010),
	(2014, 'Durand', 'Claude', 'durand.claude@entrepriseD.com', '05.78.45.36.20', '07.81.20.22', 'Directeur Informatique ',1010),
	(2015, 'Fournier', 'Stéphane', 'fournier.stephanie@entreprise.comm', NULL, '06.20.22.20.23', 'Chef de produits',1010),
	(2016, 'Mercier', 'Laura', 'mercier.laure@entrepriseD.com', NULL, '06.20.01.20.24', 'Chef de projets',1010);


INSERT INTO Commentaire(ID_Commentaire, Texte, Date_Commentaire, Auteur) VALUES 
	(4001,'Prise de contact au Winter Camp 2022', '2022-02-02', 'Marion'),
	(4002, 'Toujours présents à tous les événements ', '2022-03-01', 'Marion');

INSERT INTO PartenaireEtiquette (ID_PE, Partenaire_etq, Etiquette_Partenaire) VALUES
	(6001, 1001, 7),
	(6002, 1001, 10),
	(6003, 1001, 12),
	(6004, 1001, 1),
	(6005, 1002, 8),
	(6006, 1002, 3),
	(6007, 1002, 2),
	(6008, 1002, 10),
	(6009, 1003, 1),
	(6010, 1003, 4),
	(6011, 1003, 8),
	(6012, 1003, 10),
	(6013, 1003, 12),
	(6014, 1004, 1),
	(6015, 1004, 6),
	(6016, 1004, 12),
	(6017, 1005, 6),
	(6018, 1006, 4),
	(6019, 1006, 6),
	(6020, 1006, 12),
	(6021, 1006, 10),
	(6022, 1007, 8),
	(6023, 1007, 10),
	(6024, 1007, 2),
	(6025, 1007, 3),
	(6026, 1007, 12),
	(6027, 1008, 7),
	(6028, 1008, 1),
	(6029, 1008, 4),
	(6030, 1009, 1),
	(6031, 1009, 8),
	(6032, 1009, 10),
	(6033, 1010, 6),
	(6034, 1010, 1),
	(6035, 1010, 2),
	(6036, 1010, 3),
	(6037, 1010, 4),
	(6038, 1010, 5),
	(6039, 1010, 10),
	(6040, 1010, 12);

INSERT INTO ContactEtiquette (ID_CE, Contact_etq , 	Etiquette_contact) VALUES
	(8001, 2013, 11);

INSERT INTO PartenaireCommentaire (ID_PC, Partenaire_com, Commentaire_Partenaire ) VALUES
	(9001, 1001, 4002),
	(9002, 1010, 4002),
	(9003, 1007, 4002);

INSERT INTO ContactCommentaire (ID_CC, Contact_com, Commentaire_Contact) VALUES
	(11001, 2008, 4002),
	(11002, 2005, 4002);

