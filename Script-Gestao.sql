---		GESTORES

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
	(1, 'Gestor','Quixadá', '86355694072', '1995-10-05	',
	'email1@gmail.com', 'Centro', 'SOLTEIRO', 'JOÃO',
	'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'MASCULINO',
	'99887766', 'CE'),
	(2,	'Gerente de projetos','Quixada','07114616350','1998-09-04',
	'emailluccas@gmail.com', 'Francisco Eneas de Lima','SOLTEIRO', 'LUCAS',
	'Brasil' ,'$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE');


	
---		MEDICOS

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
 	(3, 'Clinico Geral','Quixadá', '91660988071', '1990-06-30',
	'email2@gmail.com', 'Centro', 'Solteiro',
	'PEDRO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'SAUDE', 'MASCULINO',
	'99887766', 'CE');	
	
---		COLABORADORES

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
 	(4, 'Operador','Limoeiro', '05438078009', '1993-02-07',
	'email4@gmail.com', 'Gran Ville', 'CASADO',
	'ALGUSTO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'MASCULINO',
	'99887766', 'CE'),
	
	(5, 'Operadora','Limoeiro', '15287873077', '1995-05-05',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'99887766', 'CE');	
	
	
---		PAPEIS
insert into papel( usuario_id, papel )values
(1, 'GESTOR'),
(2, 'GESTOR'),
(3, 'MEDICO'),
(4, 'COLABORADOR'),
(5, 'COLABORADOR');

---		EXAMES
insert into exame( id, avaliacao_medica, cid, crm_medico, data_exame, descricao, dia_proximo_exame,
	dias_afastamento, nome_exame, status_exame, colaborador_id, medico_id )values
		(1, 'Muito boa', '10000', '11122233', '2019-01-01', 'Descricao aqui', '2019-01-01', 0,
		'ADMISSIONAL', 'ANDAMENTO', 4, 3),
		(2, 'Muito boa', '10000', '11122233', '2019-07-01', 'Descricao aqui', '2019-12-01', 0,
		'PERIODICO', 'ANDAMENTO', 4, 3),
		(3, 'tensa', '10000', '11122233', '2019-01-01', 'Quebrou o braço', '2020-02-01', 2,
		'ACIDENTE', 'ANDAMENTO', 4, 3),
		(4, 'Muito boa', '10000', '11122233', '2019-01-01', '', '2020-07-01', 0,
		'DEMISSIONAL', 'ANDAMENTO', 4, 3);
