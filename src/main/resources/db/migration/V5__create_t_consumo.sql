--============================================================
-- TABELA: t_consumo
--============================================================
CREATE TABLE t_consumo (
    id_con_eng              INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    dt_hora                 TIMESTAMP NOT NULL,
    kw_consumo              NUMBER NOT NULL,
    t_equipamentos_id_equip INTEGER NOT NULL,
    CONSTRAINT t_consumo_pk PRIMARY KEY (id_con_eng),
    CONSTRAINT t_consumo_t_equipamentos_fk FOREIGN KEY (t_equipamentos_id_equip)
        REFERENCES t_equipamentos (id_equip)
);