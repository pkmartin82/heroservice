
SHOW tables;

DROP TABLE Hero;

SHOW tables;

CREATE TABLE Universe (
	universeId MEDIUMINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	insertUser VARCHAR(10) NOT NULL,
	insertTime TIMESTAMP NOT NULL,
	updateUser VARCHAR(10) NULL,
	updateTime TIMESTAMP NULL,
	PRIMARY KEY (universeId)
);

SHOW tables;

CREATE TABLE Hero (
     heroId MEDIUMINT NOT NULL AUTO_INCREMENT,
     name VARCHAR(30) NOT NULL,
     secretIdentity VARCHAR(30) NOT NULL,
     numOthersWhoKnowSecret MEDIUMINT NULL,
     catchphrase VARCHAR(100) NULL,
     universeId MEDIUMINT NOT NULL,
     insertUser VARCHAR(10) NOT NULL,
     insertTime TIMESTAMP NOT NULL,
     updateUser VARCHAR(10) NULL,
     updateTime TIMESTAMP NULL,
     FOREIGN KEY Universe(universeId)
     REFERENCES Universe(universeId),
     PRIMARY KEY (heroId)
 );
 
SHOW tables;

CREATE TABLE Alliances (
     firstHeroId MEDIUMINT NOT NULL,
     secondHeroId MEDIUMINT NOT NULL,
     insertUser VARCHAR(25) NOT NULL,
     insertTime TIMESTAMP NOT NULL,
     updateUser VARCHAR(25) NULL,
     updateTime TIMESTAMP NULL,
     FOREIGN KEY (firstHeroId) REFERENCES Hero(heroId) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (secondHeroId) REFERENCES Hero(heroId) ON DELETE CASCADE ON UPDATE CASCADE,
     PRIMARY KEY (firstHeroId, secondHeroId)
);


ALTER TABLE Hero MODIFY insertUser VARCHAR(25);
ALTER TABLE Hero MODIFY updateUser VARCHAR(25);
ALTER TABLE Universe MODIFY insertUser VARCHAR(25);
ALTER TABLE Universe MODIFY updateUser VARCHAR(25);

SHOW tables;