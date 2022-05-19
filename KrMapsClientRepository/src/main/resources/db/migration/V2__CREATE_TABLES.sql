CREATE TABLE public.buss_stations
(
    id integer NOT NULL,
    geom geometry(Point,4326),
    nume character varying COLLATE pg_catalog."default",
    adresa character varying COLLATE pg_catalog."default",
    latitude double precision,
    longitude double precision,
    description character varying COLLATE pg_catalog."default",
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT buss_stations_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE INDEX sidx_buss_stations_geom
    ON public.buss_stations USING gist
        (geom)
    TABLESPACE pg_default;

CREATE TABLE public.hospital
(
    id integer NOT NULL,
    geom geometry(Point,4326),
    nume character varying COLLATE pg_catalog."default",
    adresa character varying COLLATE pg_catalog."default",
    latitude double precision,
    longitude double precision,
    tel character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT hospital_pkey PRIMARY KEY (id)
);

CREATE TABLE public.pharmacy
(
    id integer NOT NULL,
    geom geometry(Point,4326),
    nume character varying COLLATE pg_catalog."default",
    adresa character varying COLLATE pg_catalog."default",
    latitude double precision,
    longitude double precision,
    tel character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT pharmacy_pkey PRIMARY KEY (id)
);

CREATE TABLE public.schools
(
    id integer NOT NULL,
    geom geometry(Point,4326),
    nume character varying COLLATE pg_catalog."default",
    adresa character varying COLLATE pg_catalog."default",
    latitude double precision,
    longitude double precision,
    tel character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT schools_pkey PRIMARY KEY (id)
);

CREATE TABLE public.university
(
    id integer NOT NULL,
    geom geometry(Point,4326),
    nume character varying COLLATE pg_catalog."default",
    adresa character varying COLLATE pg_catalog."default",
    latitude double precision,
    longitude double precision,
    tel character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT university_pkey PRIMARY KEY (id)
);