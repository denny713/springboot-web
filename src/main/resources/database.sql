-- Table: public.t_guest

-- DROP TABLE public.t_guest;

CREATE TABLE public.t_guest
(
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    address character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT t_guest_pkey PRIMARY KEY (name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.t_guest
    OWNER to postgres;
