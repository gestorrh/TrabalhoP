--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.15
-- Dumped by pg_dump version 9.6.15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: documento; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.documento (
    id integer NOT NULL,
    nome character varying(255),
    observacao character varying(255),
    inscricao_id integer
);


ALTER TABLE public.documento OWNER TO proficiencia_user;

--
-- Name: documento_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documento_id_seq OWNER TO proficiencia_user;

--
-- Name: exame; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.exame (
    id integer NOT NULL,
    data_hora_prova timestamp without time zone,
    data_resultado date,
    descricao text,
    fase character varying(20) NOT NULL,
    inicio_inscricoes timestamp without time zone NOT NULL,
    inicio_reavaliacao timestamp without time zone,
    local_prova character varying(255),
    numero_vagas integer NOT NULL,
    termino_inscricoes timestamp without time zone NOT NULL,
    termino_reavaliacao timestamp without time zone,
    titulo character varying(255),
    CONSTRAINT exame_numero_vagas_check CHECK ((numero_vagas >= 1))
);


ALTER TABLE public.exame OWNER TO proficiencia_user;

--
-- Name: exame_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.exame_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exame_id_seq OWNER TO proficiencia_user;

--
-- Name: exame_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: proficiencia_user
--

ALTER SEQUENCE public.exame_id_seq OWNED BY public.exame.id;


--
-- Name: inscricao; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.inscricao (
    id integer NOT NULL,
    area_concentracao character varying(255),
    data_hora_inscricao timestamp without time zone,
    instituicao character varying(255),
    linha_pesquisa character varying(255),
    nota_avaliacao double precision,
    nota_reavaliacao double precision,
    programa_pos character varying(255),
    reavaliacao_solicitada boolean,
    status_avaliacao character varying(255),
    status_inscricao character varying(255),
    exame_id integer,
    usuario_id integer
);


ALTER TABLE public.inscricao OWNER TO proficiencia_user;

--
-- Name: inscricao_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.inscricao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inscricao_id_seq OWNER TO proficiencia_user;

--
-- Name: noticia; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.noticia (
    id integer NOT NULL,
    corpo character varying(255),
    data_hora timestamp without time zone,
    titulo character varying(255),
    exame_id integer
);


ALTER TABLE public.noticia OWNER TO proficiencia_user;

--
-- Name: noticia_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.noticia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.noticia_id_seq OWNER TO proficiencia_user;

--
-- Name: papel; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.papel (
    usuario_id integer NOT NULL,
    papel character varying(255)
);


ALTER TABLE public.papel OWNER TO proficiencia_user;

--
-- Name: token; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.token (
    id integer NOT NULL,
    hash character varying(255),
    validade timestamp without time zone,
    usuario_id integer
);


ALTER TABLE public.token OWNER TO proficiencia_user;

--
-- Name: token_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.token_id_seq OWNER TO proficiencia_user;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: proficiencia_user
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    cidade character varying(255),
    cpf character varying(255),
    data_nascimento date,
    email character varying(255),
    endereco character varying(255),
    estado_civil character varying(255),
    necessidade_especial character varying(255),
    nome character varying(255),
    observacao_necessidade character varying(255),
    pais character varying(255),
    senha character varying(255),
    sexo character varying(255),
    telefone character varying(255),
    uf character varying(255)
);


ALTER TABLE public.usuario OWNER TO proficiencia_user;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: proficiencia_user
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO proficiencia_user;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: proficiencia_user
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: exame id; Type: DEFAULT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.exame ALTER COLUMN id SET DEFAULT nextval('public.exame_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Data for Name: documento; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--



--
-- Name: documento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.documento_id_seq', 1, false);


--
-- Data for Name: exame; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--



--
-- Name: exame_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.exame_id_seq', 1, false);


--
-- Data for Name: inscricao; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--



--
-- Name: inscricao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.inscricao_id_seq', 1, false);


--
-- Data for Name: noticia; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--



--
-- Name: noticia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.noticia_id_seq', 1, false);


--
-- Data for Name: papel; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--

INSERT INTO public.papel VALUES (1, 'COORDENADOR');


--
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--



--
-- Name: token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.token_id_seq', 1, false);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: proficiencia_user
--

INSERT INTO public.usuario VALUES (1, 'Quixeramobim', '60896489302', '1996-03-24', 'bleniciosilva@gmail.com', 'Rua João Jorge, 132, Antonio Cisnando', 'SOLTEIRO', NULL, 'Ruben Blenicio Tavares Silva', NULL, 'Brasil', '$2a$10$anWandkCncMhMPHyEpczbOQ.FUpW0QM77uj9AglERhTZnayOr/Lby', 'MASCULINO', '88994324330', 'CE');


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: proficiencia_user
--

SELECT pg_catalog.setval('public.usuario_id_seq', 1, true);


--
-- Name: documento documento_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.documento
    ADD CONSTRAINT documento_pkey PRIMARY KEY (id);


--
-- Name: exame exame_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.exame
    ADD CONSTRAINT exame_pkey PRIMARY KEY (id);


--
-- Name: inscricao inscricao_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_pkey PRIMARY KEY (id);


--
-- Name: noticia noticia_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.noticia
    ADD CONSTRAINT noticia_pkey PRIMARY KEY (id);


--
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: token fkd2osvu7wryxr71q3lkg5w9m93; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkd2osvu7wryxr71q3lkg5w9m93 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: inscricao fkfpsylsqrd2hnnekke5igf92fk; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT fkfpsylsqrd2hnnekke5igf92fk FOREIGN KEY (exame_id) REFERENCES public.exame(id);


--
-- Name: papel fkgdv3lgngvhc1l65tfkv305v3o; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.papel
    ADD CONSTRAINT fkgdv3lgngvhc1l65tfkv305v3o FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: noticia fkldmvue3tewdd15fo823ffofk3; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.noticia
    ADD CONSTRAINT fkldmvue3tewdd15fo823ffofk3 FOREIGN KEY (exame_id) REFERENCES public.exame(id);


--
-- Name: inscricao fkrkq5mg0q7wvm0tcd1k2gnm4x7; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT fkrkq5mg0q7wvm0tcd1k2gnm4x7 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: documento fkrwtor27i52eukot0fn5wr8j8w; Type: FK CONSTRAINT; Schema: public; Owner: proficiencia_user
--

ALTER TABLE ONLY public.documento
    ADD CONSTRAINT fkrwtor27i52eukot0fn5wr8j8w FOREIGN KEY (inscricao_id) REFERENCES public.inscricao(id);


--
-- proficiencia_userQL database dump complete
--

