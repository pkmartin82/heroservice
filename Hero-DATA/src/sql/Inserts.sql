 INSERT INTO Universe (universeId, name, insertUser, insertTime) 
 VALUES (616, 'Marvel Universe Proper', 'pmartin', now());
 
 INSERT INTO Universe (universeId, name, insertUser, insertTime) 
 VALUES (1610, 'Ultimate Universe', 'pmartin', now());
 
 INSERT INTO Hero (name, secretIdentity, universeId, insertUser, insertTime) 
 VALUES ('Mr. Fantastic', 'Reed Richards', 616, 'pmartin', now());

 INSERT INTO Hero (name, secretIdentity, universeId, insertUser, insertTime) 
 VALUES ('Invisible Woman', 'Susan Storm', 616, 'pmartin', now());
 
 
 -- Mr. Fantastic and Invisible Woman
 INSERT INTO Alliances(firstHeroId, secondHeroId, insertUser, insertTime)
 VALUES (2, 3, 'pmartin', now());
 
 SELECT *
 FROM Hero 
    INNER JOIN Alliances
     ON Hero.heroId = Alliances.secondHeroId
 WHERE Hero.heroId = Alliances.secondHeroId