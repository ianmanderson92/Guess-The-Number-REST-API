USE guessnum;

CREATE TABLE game(
id INT PRIMARY KEY AUTO_INCREMENT,
thousands INT NOT NULL,
hundreds INT NOT NULL,
tens INT NOT NULL,
units INT NOT NULL,
finished BOOLEAN DEFAULT false);

INSERT INTO game(id, thousands, hundreds, tens, units, finished)
VALUES
(1, 8, 7, 6, 5, true),
(2, 3, 4, 5, 6, false);