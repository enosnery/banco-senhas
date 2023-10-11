CREATE TABLE colaborador(
                            id serial,
                            nome varchar(255),
                            senha varchar(255) ,
                            nivel_hierarquia int,
                            forca_senha int,
                            constraint check_hierarquia check (nivel_hierarquia in( 1, 2, 3, 4))
);

INSERT INTO colaborador (id, nome, senha, nivel_hierarquia, forca_senha) VALUES(1, 'Davi', 'kcn9p9oprp', 1, 44);
INSERT INTO colaborador (id, nome, senha, nivel_hierarquia, forca_senha) VALUES(2, 'Beatriz', '1o43denlJI', 2, 80);
INSERT INTO colaborador (id, nome, senha, nivel_hierarquia, forca_senha) VALUES(3, 'Pedro', '123456', 3, 4);


