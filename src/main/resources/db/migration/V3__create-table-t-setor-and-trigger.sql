--============================================================
-- TABELA: t_setor
--============================================================
CREATE TABLE t_setor (
    id_set   INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    nm_setor VARCHAR2(100) NOT NULL,
    nr_andar INTEGER NOT NULL,
    CONSTRAINT t_setor_pk PRIMARY KEY (id_set)
);