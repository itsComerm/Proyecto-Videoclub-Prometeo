--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2026-05-07 12:52:14

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
-- TOC entry 224 (class 1259 OID 16572)
-- Name: alquileres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alquileres (
    id_alquiler integer NOT NULL,
    id_cliente integer NOT NULL,
    id_copia integer NOT NULL,
    fecha_alquiler date DEFAULT CURRENT_DATE NOT NULL,
    fecha_limite date NOT NULL,
    fecha_devolucion date,
    precio numeric DEFAULT 2.00 NOT NULL
);


ALTER TABLE public.alquileres OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16571)
-- Name: alquileres_id_alquiler_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alquileres_id_alquiler_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alquileres_id_alquiler_seq OWNER TO postgres;

--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 223
-- Name: alquileres_id_alquiler_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alquileres_id_alquiler_seq OWNED BY public.alquileres.id_alquiler;


--
-- TOC entry 218 (class 1259 OID 16527)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id_cliente integer NOT NULL,
    nombre character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    dni character varying(9) NOT NULL,
    email character varying(100),
    telefono integer NOT NULL
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16526)
-- Name: clientes_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clientes_id_cliente_seq OWNER TO postgres;

--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 217
-- Name: clientes_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_id_cliente_seq OWNED BY public.clientes.id_cliente;


--
-- TOC entry 226 (class 1259 OID 16593)
-- Name: devoluciones_tardias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.devoluciones_tardias (
    id_incidencia integer NOT NULL,
    id_alquiler integer NOT NULL,
    dias_retraso integer NOT NULL,
    importe numeric NOT NULL,
    pagado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.devoluciones_tardias OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16592)
-- Name: devoluciones_tardias_id_incidencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.devoluciones_tardias_id_incidencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.devoluciones_tardias_id_incidencia_seq OWNER TO postgres;

--
-- TOC entry 4950 (class 0 OID 0)
-- Dependencies: 225
-- Name: devoluciones_tardias_id_incidencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.devoluciones_tardias_id_incidencia_seq OWNED BY public.devoluciones_tardias.id_incidencia;


--
-- TOC entry 220 (class 1259 OID 16536)
-- Name: peliculas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.peliculas (
    id_pelicula integer NOT NULL,
    titulo character varying(150) NOT NULL,
    director character varying(100),
    anio smallint,
    genero character varying(40)
);


ALTER TABLE public.peliculas OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16535)
-- Name: peliculas_id_pelicula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.peliculas_id_pelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.peliculas_id_pelicula_seq OWNER TO postgres;

--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 219
-- Name: peliculas_id_pelicula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.peliculas_id_pelicula_seq OWNED BY public.peliculas.id_pelicula;


--
-- TOC entry 222 (class 1259 OID 16558)
-- Name: stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stock (
    id_articulo integer NOT NULL,
    id_pelicula integer NOT NULL,
    estado character varying(25) DEFAULT 'disponible'::character varying NOT NULL,
    CONSTRAINT estado CHECK (((estado)::text = ANY ((ARRAY['disponible'::character varying, 'alquilada'::character varying])::text[])))
);


ALTER TABLE public.stock OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16557)
-- Name: stock_id_articulo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.stock_id_articulo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.stock_id_articulo_seq OWNER TO postgres;

--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 221
-- Name: stock_id_articulo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.stock_id_articulo_seq OWNED BY public.stock.id_articulo;


--
-- TOC entry 4766 (class 2604 OID 16575)
-- Name: alquileres id_alquiler; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquileres ALTER COLUMN id_alquiler SET DEFAULT nextval('public.alquileres_id_alquiler_seq'::regclass);


--
-- TOC entry 4762 (class 2604 OID 16530)
-- Name: clientes id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_cliente_seq'::regclass);


--
-- TOC entry 4769 (class 2604 OID 16613)
-- Name: devoluciones_tardias id_incidencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devoluciones_tardias ALTER COLUMN id_incidencia SET DEFAULT nextval('public.devoluciones_tardias_id_incidencia_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 16539)
-- Name: peliculas id_pelicula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.peliculas ALTER COLUMN id_pelicula SET DEFAULT nextval('public.peliculas_id_pelicula_seq'::regclass);


--
-- TOC entry 4764 (class 2604 OID 16561)
-- Name: stock id_articulo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock ALTER COLUMN id_articulo SET DEFAULT nextval('public.stock_id_articulo_seq'::regclass);


--
-- TOC entry 4940 (class 0 OID 16572)
-- Dependencies: 224
-- Data for Name: alquileres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alquileres (id_alquiler, id_cliente, id_copia, fecha_alquiler, fecha_limite, fecha_devolucion, precio) FROM stdin;
1	1	4	2025-04-21	2025-04-28	2025-04-27	2.00
2	2	6	2025-04-21	2025-04-28	2025-04-29	2.00
3	3	9	2025-04-22	2025-04-28	2025-04-29	2.00
4	4	13	2025-04-22	2025-04-27	2025-04-29	2.00
5	5	4	2025-04-23	2025-04-28	2025-04-28	2.00
6	1	6	2025-04-27	2025-05-04	2026-05-06	2.00
7	2	9	2025-04-28	2025-05-05	2026-05-06	2.00
17	3	3	2026-05-06	2026-05-13	\N	2
8	3	13	2025-04-25	2025-05-02	2026-05-06	2.00
18	1	1	2026-05-06	2026-05-13	2026-05-06	2
19	1	1	2026-05-07	2026-05-14	\N	2
\.


--
-- TOC entry 4934 (class 0 OID 16527)
-- Dependencies: 218
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientes (id_cliente, nombre, apellidos, dni, email, telefono) FROM stdin;
5	Javier	Romero	39	30	30
1	Carlos	García	12345678A	carlos@email.com	612345678
2	María	Martínez	12345678B	maria@email.com	612345678
3	Juan	Fernández	12345678C	juan@email.com	612345678
4	Anna	Sánchez	1234523	aoege@mail.com	1122333
8	Alejandro	Herrador	30303030J	alejandro@email.com	676767676
\.


--
-- TOC entry 4942 (class 0 OID 16593)
-- Dependencies: 226
-- Data for Name: devoluciones_tardias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.devoluciones_tardias (id_incidencia, id_alquiler, dias_retraso, importe, pagado) FROM stdin;
1	2	1	0.50	t
2	3	1	0.50	t
3	4	2	1.00	f
4	6	2	1	f
5	7	1	0.5	f
6	8	4	2	f
\.


--
-- TOC entry 4936 (class 0 OID 16536)
-- Dependencies: 220
-- Data for Name: peliculas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.peliculas (id_pelicula, titulo, director, anio, genero) FROM stdin;
1	El Rey León	Roger Allers	1994	Animación
2	Pulp Fiction	Quentin Tarantino	1994	Thriller
3	Forrest Gump	Robert Zemeckis	1994	Drama
4	El Señor de los Anillos	Peter Jackson	2001	Fantasía
5	Matrix	Lana Wachowski	1999	Ciencia Ficción
6	Titanic	James Cameron	1997	Romance
7	Gladiator	Ridley Scott	2000	Acción
8	El Sexto Sentido	M. Night Shyamalan	1999	Terror
9	American Beauty	Sam Mendes	1999	Drama
10	Toy Story	John Lasseter	1995	Animación
\.


--
-- TOC entry 4938 (class 0 OID 16558)
-- Dependencies: 222
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stock (id_articulo, id_pelicula, estado) FROM stdin;
1	1	alquilada
2	1	disponible
4	2	alquilada
5	3	disponible
7	4	disponible
8	5	disponible
10	6	disponible
11	7	disponible
12	7	disponible
14	9	disponible
15	10	disponible
6	3	disponible
9	5	disponible
3	2	alquilada
13	8	disponible
\.


--
-- TOC entry 4953 (class 0 OID 0)
-- Dependencies: 223
-- Name: alquileres_id_alquiler_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alquileres_id_alquiler_seq', 19, true);


--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 217
-- Name: clientes_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 9, true);


--
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 225
-- Name: devoluciones_tardias_id_incidencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.devoluciones_tardias_id_incidencia_seq', 6, true);


--
-- TOC entry 4956 (class 0 OID 0)
-- Dependencies: 219
-- Name: peliculas_id_pelicula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.peliculas_id_pelicula_seq', 30, true);


--
-- TOC entry 4957 (class 0 OID 0)
-- Dependencies: 221
-- Name: stock_id_articulo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stock_id_articulo_seq', 105, true);


--
-- TOC entry 4773 (class 2606 OID 16534)
-- Name: clientes DNI; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT "DNI" UNIQUE (dni);


--
-- TOC entry 4781 (class 2606 OID 16581)
-- Name: alquileres alquileres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquileres
    ADD CONSTRAINT alquileres_pkey PRIMARY KEY (id_alquiler);


--
-- TOC entry 4775 (class 2606 OID 16532)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 4783 (class 2606 OID 16601)
-- Name: devoluciones_tardias devoluciones_tardias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devoluciones_tardias
    ADD CONSTRAINT devoluciones_tardias_pkey PRIMARY KEY (id_incidencia);


--
-- TOC entry 4777 (class 2606 OID 16541)
-- Name: peliculas peliculas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.peliculas
    ADD CONSTRAINT peliculas_pkey PRIMARY KEY (id_pelicula);


--
-- TOC entry 4779 (class 2606 OID 16565)
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id_articulo);


--
-- TOC entry 4787 (class 2606 OID 16602)
-- Name: devoluciones_tardias alquiler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devoluciones_tardias
    ADD CONSTRAINT alquiler FOREIGN KEY (id_alquiler) REFERENCES public.alquileres(id_alquiler) NOT VALID;


--
-- TOC entry 4785 (class 2606 OID 16587)
-- Name: alquileres articulo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquileres
    ADD CONSTRAINT articulo FOREIGN KEY (id_copia) REFERENCES public.stock(id_articulo);


--
-- TOC entry 4786 (class 2606 OID 16582)
-- Name: alquileres cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquileres
    ADD CONSTRAINT cliente FOREIGN KEY (id_cliente) REFERENCES public.clientes(id_cliente);


--
-- TOC entry 4784 (class 2606 OID 16566)
-- Name: stock pelicula; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT pelicula FOREIGN KEY (id_pelicula) REFERENCES public.peliculas(id_pelicula);


-- Completed on 2026-05-07 12:52:14

--
-- PostgreSQL database dump complete
--

