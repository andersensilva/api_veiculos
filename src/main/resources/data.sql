-- Inserir marcas
INSERT INTO marca (nome_marca) VALUES ('CHEVROLET');
INSERT INTO marca (nome_marca) VALUES ('VOLKSWAGEN');

-- Inserir modelos
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('ONIX PLUS', 50000, 1);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('JETTA', 49000, 2);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('HILLUX SW4', 47500, 2);

-- Inserir carros
INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696539488, 2015, 'FLEX', 4, 'BEGE', 1);

INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696531234, 2014, 'FLEX', 4, 'AZUL', 2);

INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696535432, 1993, 'DIESEL', 4, 'AZUL', 3);
