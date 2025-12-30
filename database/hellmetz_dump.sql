--
-- PostgreSQL database dump
--

\restrict vsEEZFGYP4GYlL2l4hdn4bsN2jb16YGsTeFGhxILtKBQxJFWfqfwpATCqMf3XYW

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: groupe; Type: TABLE; Schema: public; Owner: hellmetz_user
--

CREATE TABLE public.groupe (
    id_groupe integer NOT NULL,
    nom character varying(100) NOT NULL,
    style character varying(100),
    pays character varying(100),
    description text,
    site_web character varying(255),
    image_url character varying(255)
);


ALTER TABLE public.groupe OWNER TO hellmetz_user;

--
-- Name: groupe_id_groupe_seq; Type: SEQUENCE; Schema: public; Owner: hellmetz_user
--

CREATE SEQUENCE public.groupe_id_groupe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.groupe_id_groupe_seq OWNER TO hellmetz_user;

--
-- Name: groupe_id_groupe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hellmetz_user
--

ALTER SEQUENCE public.groupe_id_groupe_seq OWNED BY public.groupe.id_groupe;


--
-- Name: groupe id_groupe; Type: DEFAULT; Schema: public; Owner: hellmetz_user
--

ALTER TABLE ONLY public.groupe ALTER COLUMN id_groupe SET DEFAULT nextval('public.groupe_id_groupe_seq'::regclass);


--
-- Data for Name: groupe; Type: TABLE DATA; Schema: public; Owner: hellmetz_user
--

COPY public.groupe (id_groupe, nom, style, pays, description, site_web, image_url) FROM stdin;
4	Gojira	Progressive / Death Metal	France	L’un des groupes de metal français les plus influents, connus pour leurs thèmes écologiques.	https://www.gojira-music.com	/img/groups/gojira.jpg
5	Metallica	Thrash Metal	États-Unis	Groupe légendaire pionnier du thrash metal fondé en 1981.	https://www.metallica.com	/img/groups/metallica.jpg
6	Slipknot	Nu Metal / Alternative Metal	États-Unis	Groupe connu pour leurs masques, leurs performances énergiques et leur son agressif.	https://slipknot1.com	/img/groups/slipknot.jpg
7	Lamb of God	Groove Metal	États-Unis	Icône du groove metal moderne, actif depuis les années 2000.	https://www.lamb-of-god.com	/img/groups/lambofgod.jpg
8	Nightwish	Symphonic Metal	Finlande	Groupe pionnier du metal symphonique, célèbre pour ses compositions orchestrales.	https://www.nightwish.com	/img/groups/nightwish.jpg
9	Epica	Symphonic Metal	Pays-Bas	Groupe combinant metal et chant lyrique avec des thématiques philosophiques.	https://www.epica.nl	/img/groups/epica.jpg
10	Amon Amarth	Melodic Death Metal	Suède	Leaders du death metal mélodique à thématique viking.	https://www.amonamarth.com	/img/groups/amonamarth.jpg
11	Sabaton	Power Metal	Suède	Connu pour leurs chansons inspirées de faits historiques et batailles.	https://www.sabaton.net	/img/groups/sabaton.jpg
12	Rammstein	Industrial Metal	Allemagne	Célèbre pour son style industriel et ses shows pyrotechniques impressionnants.	https://www.rammstein.de	/img/groups/rammstein.jpg
13	Lofofora	Fusion Metal / Punk	France	Groupe français engagé mêlant metal, punk et fusion.	https://www.lofofora.com	/img/groups/lofofora.jpg
14	Dagoba	Groove Metal	France	Groupe marseillais reconnu pour son groove metal énergique.	https://www.dagobaofficial.com	/img/groups/dagoba.jpg
15	Behemoth	Blackened Death Metal	Pologne	Figure majeure du black/death metal extrême.	https://behemoth.pl	/img/groups/behemoth.jpg
16	Arch Enemy	Melodic Death Metal	Suède	Groupe phare du death mélodique avec une chanteuse à la voix extrême.	https://www.archenemy.net	/img/groups/archenemy.jpg
17	Powerwolf	Power Metal	Allemagne	Groupe connu pour son esthétique religieuse et théâtrale.	https://www.powerwolf.net	/img/groups/powerwolf.jpg
18	Within Temptation	Symphonic Metal	Pays-Bas	Pionniers du metal symphonique néerlandais.	https://www.within-temptation.com	/img/groups/withintemptation.jpg
19	Mastodon	Progressive Sludge Metal	États-Unis	Groupe reconnu pour son style progressif et ses albums conceptuels.	https://www.mastodonrocks.com	/img/groups/mastodon.jpg
20	Parkway Drive	Metalcore	Australie	L’un des groupes metalcore les plus influents de leur génération.	https://www.parkwaydriverock.com	/img/groups/parkwaydrive.jpg
21	Trivium	Metalcore / Thrash	États-Unis	Groupe polyvalent mélangeant thrash, metalcore et heavy metal.	https://www.trivium.org	/img/groups/trivium.jpg
22	Eluveitie	Folk Metal / Melodic Death	Suisse	Mélange unique entre instruments traditionnels celtiques et death mélodique.	https://www.eluveitie.ch	/img/groups/eluveitie.jpg
23	Avatar	Avant-garde Metal	Suède	Groupe suédois théâtral aux influences heavy, groove et avant-gardistes.	https://www.avatarmetal.com	/img/groups/avatar.jpg
\.


--
-- Name: groupe_id_groupe_seq; Type: SEQUENCE SET; Schema: public; Owner: hellmetz_user
--

SELECT pg_catalog.setval('public.groupe_id_groupe_seq', 23, true);


--
-- Name: groupe groupe_pkey; Type: CONSTRAINT; Schema: public; Owner: hellmetz_user
--

ALTER TABLE ONLY public.groupe
    ADD CONSTRAINT groupe_pkey PRIMARY KEY (id_groupe);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT USAGE ON SCHEMA public TO hellmetz_user;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES TO hellmetz_user;


--
-- PostgreSQL database dump complete
--

\unrestrict vsEEZFGYP4GYlL2l4hdn4bsN2jb16YGsTeFGhxILtKBQxJFWfqfwpATCqMf3XYW

