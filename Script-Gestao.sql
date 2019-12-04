truncate TABLE public.usuario cascade;
truncate TABLE public.exame cascade;



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
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(7, 'Operadora','Limoeiro', '41359162011', '1995-05-15',
	'email6@gmail.com', 'Gran Ville', 'CASADO',
	'JOANA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(8, 'Operadora','Limoeiro', '78941576008', '1995-05-25',
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'BRUNA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MONTAGEM', 'FEMININO',
	'88996888706', 'CE'),
	
	(9, 'Vendedora','Quixadá', '38390429098', '1995-06-06',
	'email@gmail.com', 'CENTRO', 'CASADO',
	'BEATRIZ', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'VENDAS', 'FEMININO',
	'88996888706', 'CE'),
	
	(10, 'Vendedora','Limoeiro', '24441671040', '1995-06-16',
	'email@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'VENDAS', 'FEMININO',
	'88996888706', 'CE'),
	
	(11, 'Atendente','Fortaleza', '15404790059', '1995-06-26',
	'email@gmail.com', 'Messejana', 'SOLTEIRO',
	'ANA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'SUPORTE', 'FEMININO',
	'88996888706', 'CE'),
	
	(12, 'Contadora','Limoeiro', '22514562015', '1996-03-06',
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'JÚLIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'FINANCEIRO', 'FEMININO',
	'88996888706', 'CE'),
	
	(13, 'Contador','Quixadá', '95882355028', '1992-02-12',
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'MIGUEL', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'FINANCEIRO', 'MASCULINO',
	'88996888706', 'CE'),
	
	(14, 'Gerente de projetos','Fortaleza', '81274860008', '1996-11-09',
	'email@gmail.com', 'Aldeota', 'CASADO',
	'LUCAS', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),
	
	(15, 'Desenvolvedor','Limoeiro', '80898497094', '1997-08-22',
	'email@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'GABRIEL', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),
	
	(16, 'Estagiário','Limoeiro', '71726528006', '2000-07-07',
	'email@gmail.com', 'Gran Ville', 'SOLTEIRO',
	'ENZO', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'TI', 'MASCULINO',
	'88996888706', 'CE'),

	(17, 'Recrutador','Quixadá', '48548208023', '1996-04-09',
	'email@gmail.com', 'Basilio Pinto', 'DIVORCIADO',
	'ARTHUR', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'MASCULINO',
	'88996888706', 'CE'),
	
	(18, 'Recrutadora','Limoeiro', '34284499084', '1996-08-19',
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'ALICE', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'RH', 'FEMININO',
	'88996888706', 'CE'),
	
	(19, 'Produtor','Limoeiro', '71475746059', '1995-05-05',
	'email@gmail.com', 'Gran Ville', 'CASADO',
	'MARIA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'MARKETING', 'MASCULINO',
	'88996888706', 'CE'),
	
	(20, 'Sócia','Fortaleza', '90828867070', '1990-04-05',
	'email@gmail.com', 'Aldeota', 'CASADO',
	'LAURA', 'Brasil', '$2a$10$8pXDUzKBFiPAW83kQtLDCeq3LsN8uGmAf01YLATvKUIuGEg1O.rH.', 'EXECUTIVO', 'FEMININO',
	'88996888706', 'CE'),
	
	(21, 'Presidenta','Brasilia', '50525445021', '1990-12-12',
	'email@gmail.com', 'Lago Sul', 'CASADO',
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

insert into exame(avaliacao_medica, cid, crm_medico, data_exame, descricao, dia_proximo_exame,
	dias_afastamento, nome_exame, status_exame, colaborador_id, medico_id )values
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 5, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 5, 3),
		
		( 'Descanço e remédios', '1', '11122233', '2018-08-12', 'Braço quebrado - motivo..', '2018-09-02', 30,
		'ACIDENTE DE TRABALHO',
		'ANDAMENTO', 5, 3),
		
		( 'Ressalvas', '1', '11122233', '2018-10-15', 'Avaliação completa', '2018-10-15', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 5, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 6, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 6, 3),
		
		( 'Ressalvas', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 6, 3),
		
		( 'Repouso e remédios', '1', '11122233', '2018-03-01', 'Queimadura grau I', '2019-04-01', 30,
		'ACIDENTE DE TRABALHO',
		'ANDAMENTO', 6, 3),
		
		( 'Ressalvas', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 6, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-02-01', 0,
		'PERIODICO',
		'ANDAMENTO', 6, 3),
	
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 7, 3),
		
		( 'Ressalvas', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 7, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 7, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 7, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-07-01', 'Avaliação completa', '2019-07-01', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 7, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 8, 4),
		
		( 'Ressalvas', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 8, 4),
		
		( 'Ressalvas', '1', '33222111', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 8, 4),
		
		( 'Repouso e remédios', '1', '33222111', '2018-03-01', 'Queimadura grau I', '2018-04-01', 30,
		'ACIDENTE DE TRABALHO',
		'ANDAMENTO', 8, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 8, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-09-01', 'Avaliação completa', '2020-02-01', 0,
		'PERIODICO',
		'ANDAMENTO', 8, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 9, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 9, 4),
		
		( 'Ressalvas', '1', '33222111', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 9, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-02-10', 'Avaliação completa', '2019-02-10', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 9, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 10, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 10, 4),
		
		( 'Ressalvas', '1', '33222111', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 10, 4),
		
		( 'Ótimo estado', '1', '33222111', '2019-09-01', 'Avaliação completa', '2020-03-01', 0,
		'PERIODICO',
		'ANDAMENTO', 10, 4),
		
		( 'Ressalvas', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 11, 4),
	
		( 'Ressalvas', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 11, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-08-07', 'Avaliação completa', '2018-08-07', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 11, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 12, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 12, 4),
		
		( 'Ressalvas', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 13, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-03-07', 'Avaliação completa', '2018-03-07', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 13, 4),
		
		( 'Ressalvas', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 14, 3),
		
		( 'Ressalvas', '1', '11122233', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 14, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 14, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-03-01', 0,
		'PERIODICO',
		'ANDAMENTO', 14, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 15, 3),
		
		( 'Ressalvas', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 15, 4),
			
		( 'Ressalvas', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 15, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-07-01', 'Avaliação completa', '2018-07-01', 0,
		'DEMISSIONAL',
		'ANDAMENTO', 15, 3),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 16, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 16, 4),
		
		( 'Ótimo estado', '1', '11122233', '2019-08-20', 'Avaliação completa', '2019-08-20', 0,
		'PERIODICO',
		'ANDAMENTO', 16, 3),
		
		( 'Ressalvas', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 17, 4),

		( 'Ressalvas', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 17, 4),
		
		( 'Ressalvas', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 17, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 17, 3),
		
		( 'Ótimo estado', '1', '33222111', '2019-09-01', 'Avaliação completa', '2020-03-01', 0,
		'PERIODICO',
		'ANDAMENTO', 17, 4),
		
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 18, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 18, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 18, 3),
		
		( 'Ressalvas', '1', '33222111', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 18, 4),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-03-01', 0,
		'PERIODICO',
		'ANDAMENTO', 18, 3),
		
		( 'Ótimo estado', '1', '33222111', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 19, 4),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 19, 4),
		
		( 'Ótimo estado', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 19, 3),
		
		( 'Ressalvas', '1', '33222111', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 19, 4),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-02-01', 0,
		'PERIODICO',
		'ANDAMENTO', 19, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 20, 3),
		
		( 'Ótimo estado', '1', '33222111', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 20, 4),
		
		( 'Ressalvas', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 20, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 20, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-02-01', 0,
		'PERIODICO',
		'ANDAMENTO', 20, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-01-01', 'Avaliação completa', '2018-01-01', 0,
		'ADMISSIONAL',
		'ANDAMENTO', 21, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-06-01', 'Avaliação completa', '2018-11-01', 0,
		'PERIODICO',
		'ANDAMENTO', 21, 3),
		
		( 'Ótimo estado', '1', '11122233', '2018-11-01', 'Avaliação completa', '2019-04-01', 0,
		'PERIODICO',
		'ANDAMENTO', 21, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-04-01', 'Avaliação completa', '2019-09-01', 0,
		'PERIODICO',
		'ANDAMENTO', 21, 3),
		
		( 'Ótimo estado', '1', '11122233', '2019-09-01', 'Avaliação completa', '2020-02-01', 0,
		'PERIODICO',
		'ANDAMENTO', 21, 3);