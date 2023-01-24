--random template
--SELECT 
--     R.*
--FROM DUAL
--JOIN (
--    SELECT 
--         1
--    FROM DUAL
--    CONNECT BY LEVEL <= 10
--) N ON 1 = 1 --generate N rows
--JOIN (
--    SELECT 
--         CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER) AS random_int
--        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
--        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/2000', 'dd/mm/yyyy'), 'J'),TO_CHAR(SYSDATE, 'J'))),'J') AS random_date
--    FROM DUAL
--) R ON 1 = 1; --random values



INSERT INTO OLTP.ZONA_COVID(ID_ZONA_COVID, ZONA, DESCRIERE) VALUES (1, 'Verde', 'Pericol mic');
INSERT INTO OLTP.ZONA_COVID(ID_ZONA_COVID, ZONA, DESCRIERE) VALUES (2, 'Galben', 'Pericol mediu');
INSERT INTO OLTP.ZONA_COVID(ID_ZONA_COVID, ZONA, DESCRIERE) VALUES (3, 'Rosu', 'Pericol mare');


INSERT INTO OLTP.TIP_BILET(ID_TIP_BILET, DENUMIRE, DESCRIERE) VALUES (1, 'Economy', 'Pret mic');
INSERT INTO OLTP.TIP_BILET(ID_TIP_BILET,DENUMIRE, DESCRIERE) VALUES (2, 'Business', 'Pret mediu');
INSERT INTO OLTP.TIP_BILET(ID_TIP_BILET,DENUMIRE, DESCRIERE) VALUES (3, 'First class', 'Pret mare');



INSERT INTO OLTP.TARA(
     ID_TARA
    ,DENUMIRE
    ,ABREVIERE
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,UPPER(SUBSTR(random_string, 1, 2)) AS ABREVIERE
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 100
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
        INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.JUDET(
     ID_JUDET
    ,DENUMIRE
    ,ABREVIERE
    ,ID_TARA
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,UPPER(SUBSTR(random_string, 1, 2)) AS ABREVIERE
    ,random_int AS ID_TARA
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 1000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 100) AS INTEGER) AS random_int
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.ORAS(
     ID_ORAS
    ,DENUMIRE
    ,ABREVIERE
    ,ID_JUDET
    ,ID_ZONA_COVID
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,UPPER(SUBSTR(random_string, 1, 2)) AS ABREVIERE
    ,random_int1 AS ID_JUDET
    ,random_int2 AS ID_ZONA_COVID
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 5000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 1000) AS INTEGER) AS random_int1
        ,CAST(DBMS_RANDOM.VALUE(1, 3) AS INTEGER) AS random_int2
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.ADRESA(
     ID_ADRESA
    ,COD_POSTAL
    ,STRADA
    ,ID_ORAS
)
SELECT
     IDX
    ,random_string1 AS COD_POSTAL
    ,random_string2 AS STRADA
    ,random_int AS ID_ORAS
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 10000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 5000) AS INTEGER) AS random_int
        ,DBMS_RANDOM.STRING('x', CAST(DBMS_RANDOM.VALUE(5, 9) AS INTEGER)) AS random_string1
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(10, 40) AS INTEGER))) AS random_string2
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.AEROPORT(
     ID_AEROPORT
    ,DENUMIRE
    ,CAPACITATE
    ,AN_CONSTRUCTIE
    ,ID_ADRESA
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,random_int1 AS CAPACITATE
    ,random_int2 AS AN_CONSTRUCTIE
    ,random_int3 AS ID_ADRESA
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 1000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(50, 30000) AS INTEGER) AS random_int1
        ,CAST(DBMS_RANDOM.VALUE(1970, 2022) AS INTEGER) AS random_int2
        ,CAST(DBMS_RANDOM.VALUE(1, 10000) AS INTEGER) AS random_int3
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.CONTACT(
     ID_CONTACT
    ,EMAIL
    ,TELEFON
    ,WEBSITE
)
SELECT
     IDX
    ,random_string1 || '@gmail.com' AS EMAIL
    ,'07' || CAST(random_int AS VARCHAR2(8)) AS TELEFON
    ,'www.' || random_string2 || '.com' AS WEBSITE
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 20000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(100000, 999999) AS INTEGER) AS random_int
        ,DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(8, 15) AS INTEGER)) AS random_string1
        ,DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(6, 15) AS INTEGER)) AS random_string2
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.COMPANIE_AERIANA(
     ID_COMPANIE_AERIANA
    ,DENUMIRE
    ,ANUL_INFIINTARII
    ,ID_CONTACT
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,random_int1 AS ANUL_INFIINTARII
    ,random_int2 AS ID_CONTACT
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 100
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1950, 2022) AS INTEGER) AS random_int1
        ,CAST(DBMS_RANDOM.VALUE(1, 20000) AS INTEGER) AS random_int2
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.AVION(
     ID_AVION
    ,DENUMIRE
    ,CAPACITATE
    ,AN_CONSTRUCTIE
    ,ID_COMPANIE_AERIANA
)
SELECT
     IDX
    ,random_string AS DENUMIRE
    ,random_int1 AS CAPACITATE
    ,random_int2 AS AN_CONSTRUCTIE
    ,random_int3 AS ID_COMPANIE_AERIANA
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 10000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(3, 545) AS INTEGER) AS random_int1
        ,CAST(DBMS_RANDOM.VALUE(1950, 2022) AS INTEGER) AS random_int2
        ,CAST(DBMS_RANDOM.VALUE(1, 100) AS INTEGER) AS random_int3
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.CLIENT(
     ID_CLIENT
    ,DATA_NASTERII
    ,PRENUME
    ,NUME
    ,ID_CONTACT
)
SELECT
     IDX
    ,random_date AS DATA_NASTERII
    ,random_string1 AS PRENUME
    ,random_string2 AS NUME
    ,random_int AS ID_CONTACT
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 10000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 20000) AS INTEGER) AS random_int
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string1
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string2
        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/1925', 'dd/mm/yyyy'), 'J'),TO_CHAR(TO_DATE('01/01/2018', 'dd/mm/yyyy'), 'J'))),'J') AS random_date
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.PILOT(
     ID_PILOT
    ,DATA_NASTERII
    ,DATA_ANGAJARII
    ,PRENUME
    ,NUME
    ,ID_COMPANIE_AERIANA
)
SELECT
     IDX
    ,random_date1 AS DATA_NASTERII
    ,random_date2 AS DATA_ANGAJARII
    ,random_string1 AS PRENUME
    ,random_string2 AS NUME
    ,random_int AS ID_COMPANIE_AERIANA
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 1000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 100) AS INTEGER) AS random_int
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string1
        ,INITCAP(DBMS_RANDOM.STRING('l', CAST(DBMS_RANDOM.VALUE(4, 15) AS INTEGER))) AS random_string2
        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/1925', 'dd/mm/yyyy'), 'J'),TO_CHAR(TO_DATE('01/01/1980', 'dd/mm/yyyy'), 'J'))),'J') AS random_date1
        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/2000', 'dd/mm/yyyy'), 'J'),TO_CHAR(SYSDATE, 'J'))),'J') AS random_date2
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.ZBOR(
     ID_ZBOR
    ,DATA_PLECARE_ESTIMATA
    ,DATA_SOSIRE_ESTIMATA
    ,DATA_PLECARE_REALA
    ,DATA_SOSIRE_REALA
    ,ID_AEROPORT_PLECARE
    ,ID_AEROPORT_SOSIRE
    ,ID_AVION
    ,ID_COMPANIE_AERIANA
    ,ID_COPILOT
    ,ID_PILOT
)
SELECT
     IDX
    ,random_date AS DATA_PLECARE_ESTIMATA
    ,random_date + (1/1440*random_int7) AS DATA_SOSIRE_ESTIMATA
    ,random_date + (1/1440*random_int8) AS DATA_PLECARE_REALA
    ,random_date + (1/1440*random_int7) + (1/1440*random_int9) AS DATA_SOSIRE_REALA
    ,random_int1 AS ID_AEROPORT_PLECARE
    ,random_int2 AS ID_AEROPORT_SOSIRE
    ,random_int3 AS ID_AVION
    ,random_int4 AS ID_COMPANIE_AERIANA
    ,random_int5 AS ID_COPILOT
    ,random_int6 AS ID_PILOT
FROM DUAL
JOIN (
    SELECT 
         LEVEL AS IDX
    FROM DUAL
    CONNECT BY LEVEL <= 10000
) N ON 1 = 1 --generate N rows
JOIN (
    SELECT 
         CAST(DBMS_RANDOM.VALUE(1, 1000) AS INTEGER) AS random_int1
        ,CAST(DBMS_RANDOM.VALUE(1, 1000) AS INTEGER) AS random_int2
        ,CAST(DBMS_RANDOM.VALUE(1, 10000) AS INTEGER) AS random_int3
        ,CAST(DBMS_RANDOM.VALUE(1, 100) AS INTEGER) AS random_int4
        ,CAST(DBMS_RANDOM.VALUE(1, 1000) AS INTEGER) AS random_int5
        ,CAST(DBMS_RANDOM.VALUE(1, 1000) AS INTEGER) AS random_int6
        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/1970', 'dd/mm/yyyy'), 'J'),TO_CHAR(SYSDATE, 'J'))),'J') + 1/86400 * CAST(DBMS_RANDOM.VALUE(1, 86400) AS INTEGER) AS random_date
        ,CAST(DBMS_RANDOM.VALUE(30, 720) AS INTEGER) AS random_int7
        ,CAST(DBMS_RANDOM.NORMAL() * 20 AS INTEGER) AS random_int8
        ,CAST(DBMS_RANDOM.NORMAL() * 20 AS INTEGER) AS random_int9
    FROM DUAL
) R ON 1 = 1; --random values


INSERT INTO OLTP.BILET(
     ID_CLIENT
    ,ID_ZBOR
    ,DATA_ACHIZITIONARE
    ,PRET
    ,ID_TIP_BILET
)
SELECT
     random_int2 AS ID_CLIENT
    ,random_int1 AS ID_ZBOR
    ,random_date AS DATA_ACHIZITIONARE
    ,random_int3 AS PRET
    ,random_int4 AS ID_TIP_BILET
FROM DUAL
JOIN (
    SELECT DISTINCT
         CAST(DBMS_RANDOM.VALUE(1, 10000) AS INTEGER) AS random_int1
        ,CAST(IDX / 10 + 0.5 AS INTEGER) AS random_int2
    FROM DUAL
    JOIN (
        SELECT 
             LEVEL AS IDX
        FROM DUAL
        CONNECT BY LEVEL < 100000
    ) N ON 1 = 1 --generate N rows
) R1 ON 1 = 1
JOIN (
    SELECT
         CAST(DBMS_RANDOM.VALUE(50, 4000) AS INTEGER) AS random_int3
        ,CAST(DBMS_RANDOM.VALUE(1, 3) AS INTEGER) AS random_int4
        ,TO_DATE(TRUNC(DBMS_RANDOM.VALUE(TO_CHAR(TO_DATE('01/01/1970', 'dd/mm/yyyy'), 'J'),TO_CHAR(SYSDATE, 'J'))),'J') AS random_date
    FROM DUAL
) R2 ON 1 = 1; --random values