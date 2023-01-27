  create  table  dw.BILET(
          id_zbor number(10,0) not null,
          id_client number(10,0) not null,
          pret float not null,
          id_data_achizitionarii number(5,0)not null,
          id_tip_bilet number(10,0),
          constraint bilet_pk  primary key(id_zbor, id_client),
          constraint zbor_fk foreign key(id_zbor) REFERENCES dw.zbor(id_zbor),
          constraint client_fk foreign key(id_client) REFERENCES dw.client(id_client),
          constraint tip_bilet_fk FOREIGN KEY(id_tip_bilet) REFERENCES dw.tip_bilet(id_tip_bilet),
          constraint data_achizitionarii_fk FOREIGN key(id_data_achizitionarii) REFERENCES dw.timp(id_timp)
     )
         PARTITION BY RANGE(id_data_achizitionarii)
     (
         PARTITION bilet_ianuarie2022 VALUES LESS THAN(10302),
         PARTITION bilet_februarie2022 VALUES LESS THAN(10974),
         PARTITION bilet_martie2022 VALUES LESS THAN(11718),
         PARTITION bilet_aprilie2022 VALUES LESS THAN(12438),
         PARTITION bilet_mai2022 VALUES LESS THAN(13182),
         PARTITION bilet_iunie2022 VALUES LESS THAN(13902),
         PARTITION bilet_iulie2022 VALUES LESS THAN(14646),
         PARTITION bilet_august2022 VALUES LESS THAN(15390),
         PARTITION bilet_septembrie2022 VALUES LESS THAN(16110),
         PARTITION bilet_octombrie2022 VALUES LESS THAN(16854),
         PARTITION bilet_noiembrie2022 VALUES LESS THAN(17574),
         PARTITION bilet_decembrie2022 VALUES LESS THAN(18318),
         PARTITION bilet_rest VALUES LESS THAN (MAXVALUE)
     );


INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (2,  3, 120, 12438, 1);
INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (3,  3, 130, 13182, 1);
INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (21, 3, 140, 13902, 1);
INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (2,  1, 150, 14646, 1);
INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (3,  1, 190, 15390, 1);
INSERT INTO dw.BILET (ID_ZBOR, ID_CLIENT, PRET, ID_DATA_ACHIZITIONARII, ID_TIP_BILET) VALUES (21, 1, 200, 16110, 1);

SELECT * FROM dw.BILET;

SELECT * FROM   dw.BILET PARTITION (bilet_mai2022);
SELECT * FROM   dw.BILET PARTITION (bilet_iunie2022);
SELECT * FROM   dw.BILET PARTITION (bilet_iulie2022);
SELECT * FROM   dw.BILET PARTITION (bilet_august2022);
SELECT * FROM   dw.BILET PARTITION (bilet_septembrie2022);

EXEC DBMS_STATS.gather_table_stats('DW', 'BILET', cascade => TRUE);

SELECT * from all_tab_partitions;