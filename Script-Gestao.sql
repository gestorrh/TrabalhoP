truncate TABLE public.usuario cascade;


---		GESTORES

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
	(1, 'Gestor de saúde','Quixadá', '86355694072', '1995-10-05	',
	'email1@gmail.com', 'Centro', 'SOLTEIRO', 'JOÃO',
	'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'MASCULINO',
	'88996888706', 'CE'),
	(2,	'Gestor de saúde','Quixadá','07114616350','1998-09-04',
	'emailluccas@gmail.com', 'Francisco Eneas de Lima','SOLTEIRO', 'LUCAS',
	'Brasil' ,'$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'MASCULINO',
	'88996888706', 'CE');


---		MEDICOS

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
 	(3, 'Clinico Geral','Quixadá', '91660988071', '1985-06-30',
	'email2@gmail.com', 'Centro', 'SOLTEIRO',
	'PEDRO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'SAUDE', 'MASCULINO',
	'88996888706', 'CE'),
	
	(4, 'Clinico Geral','Limoeiro', '59909698056', '1980-08-30',
	'email2@gmail.com', 'São Fransisco', 'CASADO',
	'PEDRO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'SAUDE', 'MASCULINO',
	'88996888706', 'CE');	

---		COLABORADORES

insert into usuario( id, cargo, cidade, cpf, data_nascimento, email, endereco, estado_civil, nome,
 pais, senha, setor, sexo, telefone, uf) values
 	(5, 'Operador','Limoeiro', '05438078009', '1990-02-07',
	'email4@gmail.com', 'Gran Ville', 'CASADO',
	'ALGUSTO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'MASCULINO',
	'88996888706', 'CE'),
	
	(6, 'Operadora','Limoeiro', '15287873077', '1995-05-05',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(7, 'Operadora','Limoeiro', '41359162011', '1995-05-15',
	'email6@gmail.com', 'Gran Ville', 'CASADO',
	'JOANA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(8, 'Operadora','Limoeiro', '78941576008', '1995-05-25',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'BRUNA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(9, 'Vendedora','Quixadá', '38390429098', '1995-06-06',
	'email5@gmail.com', 'CENTRO', 'CASADO',
	'BEATRIZ', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'VENDAS', 'FEMININO',
	'88996888706', 'CE'),
	
	(10, 'Vendedora','Limoeiro', '24441671040', '1995-06-16',
	'email5@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'VENDAS', 'FEMININO',
	'88996888706', 'CE'),
	
	(11, 'Atendente','Fortaleza', '15404790059', '1995-06-26',
	'email5@gmail.com', 'Messejana', 'SOLTEIRO',
	'ANA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'SUPORTE', 'FEMININO',
	'88996888706', 'CE'),
	
	(12, 'Contadora','Limoeiro', '22514562015', '1996-03-06',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'JÚLIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'FINANCEIRO', 'FEMININO',
	'88996888706', 'CE'),
	
	(13, 'Contador','Quixadá', '95882355028', '1992-02-12',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'MIGUEL', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'FINANCEIRO', 'MASCULINO',
	'88996888706', 'CE'),
	
	(14, 'Gerente de projetos','Fortaleza', '81274860008', '1996-11-09',
	'email5@gmail.com', 'Aldeota', 'CASADO',
	'LUCAS', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),
	
	(15, 'Desenvolvedor','Limoeiro', '80898497094', '1997-08-22',
	'email5@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'GABRIEL', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),
	
	(16, 'Estagiário','Limoeiro', '71726528006', '2000-07-07',
	'email5@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'ENZO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),

	(17, 'Recrutador','Quixadá', '48548208023', '1996-04-09',
	'email5@gmail.com', 'Basilio Pinto', 'DIVORCIADO',
	'ARTHUR', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'MASCULINO',
	'88996888706', 'CE'),
	
	(18, 'Recrutadora','Limoeiro', '34284499084', '1996-08-19',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'ALICE', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'FEMININO',
	'88996888706', 'CE'),
	
	(19, 'Produtor','Limoeiro', '71475746059', '1995-05-05',
	'email5@gmail.com', 'Gran Ville', 'CASADO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MARKETING', 'MASCULINO',
	'88996888706', 'CE'),
	
	(20, 'Sócia','Fortaleza', '90828867070', '1990-04-05',
	'email5@gmail.com', 'Aldeota', 'CASADO',
	'LAURA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'EXECUTIVO', 'FEMININO',
	'88996888706', 'CE'),
	
	(21, 'Presidenta','Brasilia', '50525445021', '1990-12-12',
	'email5@gmail.com', 'Lago Sul', 'CASADO',
	'ADA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'EXECUTIVO', 'FEMININO',
	'88996888706', 'CE');	
	
	
---		PAPEIS
insert into papel( usuario_id, papel )values
(1, 'GESTOR'), 		 (2, 'GESTOR'),
(3, 'MEDICO'), 		 (4, 'MEDICO'),
(5, 'COLABORADOR'),  (6, 'COLABORADOR'), 
(7, 'COLABORADOR'),  (8, 'COLABORADOR'),
(9, 'COLABORADOR'),  (10, 'COLABORADOR'),
(11, 'COLABORADOR'), (12, 'COLABORADOR'),
(13, 'COLABORADOR'), (14, 'COLABORADOR'),
(15, 'COLABORADOR'), (16, 'COLABORADOR'),
(17, 'COLABORADOR'), (18, 'COLABORADOR'),
(19, 'COLABORADOR'), (20, 'COLABORADOR'),
(21, 'COLABORADOR');

---		EXAMES
--insert into exame( id, avaliacao_medica, cid, crm_medico, data_exame, descricao, dia_proximo_exame,
--	dias_afastamento, nome_exame, status_exame, colaborador_id, medico_id )values
--		(1, 'Muito boa', '10000', '11122233', '2019-01-01', 'Descricao aqui', '2019-01-01', 0,
--		'ADMISSIONAL', 'ANDAMENTO', 5, 3),
--		(2, 'Muito boa', '10000', '11122233', '2019-07-01', 'Descricao aqui', '2019-12-01', 0,
--		'PERIODICO', 'ANDAMENTO', 5, 3),
--		(3, 'tensa', '10000', '11122233', '2019-01-01', 'Quebrou o braço', '2020-02-01', 2,
--		'ACIDENTE DE TRABALHO', 'ANDAMENTO', 4, 3),
--		(4, 'Muito boa', '10000', '11122233', '2019-01-01', '', '2020-07-01', 0,
--		'DEMISSIONAL', 'ANDAMENTO', 5, 3),
--		
--		(5, 'Muito boa', '10000', '11122233', '2019-01-01', 'Descricao aqui', '2019-01-01', 0,
--		'ADMISSIONAL', 'ANDAMENTO', 6, 3),
--		(6, 'Muito boa', '10000', '11122233', '2019-07-01', 'Descricao aqui', '2019-12-01', 0,
--		'PERIODICO', 'ANDAMENTO', 6, 3),
--	
--		
		
		;
	
	
	
