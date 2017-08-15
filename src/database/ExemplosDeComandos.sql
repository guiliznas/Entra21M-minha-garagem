#Seleciona todas colunas da tabela
SELECT COUNT(*) FROM carros;

#Qtd de registros
SELECT COUNT(*) FROM carros;

#Chamando colunas especificas e colocando apelido
SELECT nome AS 'Nome', fabricante AS 'Fabricante', cor AS 'Cor', qtd_portas AS 'Portas' FROM carros;

#inserindo valores predefinindo colunas
INSERT INTO carros
(nome, cor, fabricante, placa, chassi, quilometragem, potencia, data_compra, esta_funcionando,
permitida_circulacao, qtd_portas, qtd_batidas, ano_fabricacao, ano_lancamento, tipo_pneu,
renavam, descricao)
VALUE
("Gol", "Azul", "VW", "ABC-2000", "2a2da234tg4", 65165165, 85, '2017-08-07', TRUE, FALSE,
4, 0, 2017, 2016, 175, 655615, "");

#inserido colunas especificas
INSERT INTO carros
(nome, cor, fabricante, potencia, data_compra)
VALUES
("Palio", "Verde", "Fiat", 65, '2013-04-29');

#atualizar
UPDATE carros SET nome = "Batmovel" WHERE id = 1;

INSERT INTO carros (cor, nome)
VALUES
("Branco", "Golf"),
("Vermelho", "Jetta"),
("Verde", "Fusca"),
("Azul", "Up");

UPDATE carros SET cor = "Branco" WHERE cor = "Vermelho";

UPDATE carros SET ano_fabricacao = 2017, ano_lancamento = 2016
WHERE cor = "Branco";

SELECT nome, cor, ano_fabricacao, ano_lancamento FROM carros WHERE id = 4;

SELECT nome FROM carros ORDER BY nome ASC;
SELECT nome FROM carros ORDER BY nome;

SELECT nome FROM carros ORDER BY nome DESC;

#rola colocar mais de uma coluna, preferencia de acordo c a ordem q coloquei
SELECT nome FROM carros ORDER BY fabricante ASC, nome ASC, cor ASC;

SELECT cor, COUNT(cor) FROM carros GROUP BY cor;

SELECT cor, COUNT(cor) FROM carros GROUP BY cor ORDER BY COUNT(cor) DESC;