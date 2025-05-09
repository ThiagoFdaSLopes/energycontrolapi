--============================================================
-- TABELA: t_alertas
--============================================================
CREATE TABLE t_alertas (
    id_alt          INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    dt_hora         TIMESTAMP NOT NULL,
    ds_alerta       VARCHAR2(200) NOT NULL,
    t_limite_id_lmt INTEGER NOT NULL,
    CONSTRAINT t_alertas_pk PRIMARY KEY (id_alt),
    CONSTRAINT t_alertas_t_limite_fk FOREIGN KEY (t_limite_id_lmt)
        REFERENCES t_limite (id_lmt)
);