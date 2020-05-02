select * from profissional;
select * from estabelecimento;

CREATE TABLE estabelecimento 
(
	id SERIAL ,
	nome varchar(250) NOT NULL,
  endereco varchar (250) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO estabelecimento (nome, endereco) 
VALUES ('estabelecimento 1', 'r. Brasil')

CREATE TABLE profissional 
(
  id SERIAL,
  nome varchar(250) NOT NULL,
  endereco varchar (250) NOT NULL,
  estabelecimento_id int,
  FOREIGN KEY (estabelecimento_id)
  	REFERENCES estabelecimento (id)
);

INSERT INTO profissional (nome, endereco, estabelecimento_id) 
VALUES ('Antonio', 'r. Argentina', 1);

SELECT * from profissional  p
left join estabelecimento es on es.id = 1
;