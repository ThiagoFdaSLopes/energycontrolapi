CREATE OR REPLACE TRIGGER trg_consumo_alerta
    AFTER INSERT ON t_consumo
    FOR EACH ROW
DECLARE
    v_equip_name t_equipamentos.nm_equipamento%TYPE;
BEGIN
    SELECT nm_equipamento
    INTO v_equip_name
    FROM t_equipamentos
    WHERE id_equip = :NEW.t_equipamentos_id_equip;

    FOR limite IN (
        SELECT l.lt_consumo, l.id_lmt
        FROM t_limite l
        WHERE l.t_equipamentos_id_equip = :NEW.t_equipamentos_id_equip
        ) LOOP
            IF :NEW.kw_consumo > limite.lt_consumo THEN
                INSERT INTO t_alertas (
                    dt_hora,
                    ds_alerta,
                    t_limite_id_lmt
                )
                VALUES (
                                   SYSTIMESTAMP,
                                   'Consumo do equipamento "' || v_equip_name || '" excedeu o limite: '
                                       || :NEW.kw_consumo || ' kW (limite: ' || limite.lt_consumo || ' kW)',
                                   limite.id_lmt
                       );
            END IF;
        END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        COMMIT;
END;
/

CREATE OR REPLACE TRIGGER TRG_ALERTAS_LOG
    AFTER INSERT ON T_ALERTAS
    FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    INSERT INTO T_LOGS (LOG_MENSAGEM, DATA_INC, TEMPO_INC)
    VALUES (
               'Alerta emitido: ' || :NEW.DS_ALERTA,
               SYSDATE,
               SYSTIMESTAMP
           );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        COMMIT;
END;
/

CREATE OR REPLACE TRIGGER trg_equipamentos_log
    AFTER INSERT ON T_EQUIPAMENTOS
    FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    INSERT INTO T_LOGS (LOG_MENSAGEM, DATA_INC, TEMPO_INC)
    VALUES (
               'Equipamento adicionado: ' || :NEW.NM_EQUIPAMENTO,
               SYSDATE,
               SYSTIMESTAMP
           );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        COMMIT;
END;
/

CREATE OR REPLACE TRIGGER TRG_SETOR_LOG
    AFTER INSERT ON T_SETOR
    FOR EACH ROW
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    INSERT INTO T_LOGS (LOG_MENSAGEM, DATA_INC, TEMPO_INC)
    VALUES (
               'Setor adicionado: ' || :NEW.NM_SETOR || ' Andar: ' || :NEW.NR_ANDAR,
               SYSDATE,
               SYSTIMESTAMP
           );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        COMMIT;
END;
/

INSERT INTO T_SETOR (NM_SETOR, NR_ANDAR)
VALUES ('Laboratório', 1);

INSERT INTO T_EQUIPAMENTOS (NM_EQUIPAMENTO, TIPO, T_SETOR_ID_SET)
VALUES ('Ar-condicionado', 'Elétrico', 1);

INSERT INTO T_LIMITE (LT_CONSUMO, HR_INICIO, HR_FIM, t_equipamentos_id_equip)
VALUES (50, SYSTIMESTAMP, SYSTIMESTAMP, 1);

INSERT INTO T_CONSUMO (DT_HORA, t_equipamentos_id_equip, KW_CONSUMO)
VALUES (SYSTIMESTAMP, 1, 75);

INSERT INTO TBL_USERS (
    USER_ID,
    USER_NAME,
    USER_PASSWORD,
    USER_EMAIL,
    USER_ROLE
) VALUES (
    SQ_USER.NEXTVAL,
    'Admin',
    '$2y$10$US48MadiqdgvWGWhvhQpxeOLjHZycnbOyp1o8FKyGK1v3hAPF1Jg6', --senha é admin
    'admin@exemplo.com',
    'ADMIN'
);