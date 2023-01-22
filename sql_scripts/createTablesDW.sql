create sequence dw.id_avion start with 1;
create table dw.avion(
    id_avion number(5,0) default dw.id_avion.nextval not null enable,
    denumire varchar2(50),
    capacitate number(4,0),
    an_constructie number(4,0),
    id_companie_aeriana number(5),
    constraint companie_aeriana_fk foreign key(id_companie_aeriana) references dw.companie_aeriana(id_companie_aeriana),
    constraint avion_pk primary key(id_avion)
 
);

create sequence dw.id_companie_aeriana start with 1;
create table dw.companie_aeriana(
    id_companie_aeriana number(5,0) default dw.id_companie_aeriana.nextval not null enable,
    denumire varchar2(50),
    vechime number(3),
    email varchar2(20),
    telefon varchar2(10),
    website varchar2(20),
    constraint companie_aeriana_pk primary key(id_companie_aeriana)
);

create sequence dw.id_pilot start with 1;
create table dw.pilot(
    id_pilot number(5,0) default dw.id_pilot.nextval not null enable,
    prenume varchar2(20),
    nume varchar2(20),
    varsta number(3,0),
    experienta varchar2(10),
    id_companie_aeriana number(5),
    constraint companie_aeriana_pilot_fk foreign key(id_companie_aeriana) references dw.companie_aeriana(id_companie_aeriana),
    constraint pilot_pk primary key(id_pilot)
 
);

create sequence dw.id_tip_bilet start with 1;
create table dw.tip_bilet(
    id_tip_bilet number(5) default dw.id_tip_bilet.nextval not null enable,
    denumire varchar2(50),
    descriere varchar2(50),
    constraint tip_bilet_pk primary key(id_tip_bilet)
);

create sequence dw.id_client start with 1;
create table dw.client(
    id_client number(5,0) default dw.id_client.nextval not null enable,
    prenume varchar2(20),
    nume varchar2(20),
    cnp varchar2(13),
    varsta number(3,0),
    email varchar2(30),
    telefon varchar2(10),
    constraint client_pk primary key(id_client)
 
);

create sequence dw.id_aeroport start with 1;
create table dw.aeroport(
    id_aeroport number(5,0) default dw.id_aeroport.nextval not null enable,
    denumire varchar2(50),
    capacitate number(4,0),
    an_constructie number(4,0),
    tara varchar2(20),
    judet varchar2(20),
    oras varchar2(20),
    strada varchar2(30),
    cod_postal varchar2(10),
    constraint aeroport_pk primary key(id_aeroport)
 
);

create sequence dw.id_timp start with 1;
create table dw.timp(
    id_timp number(5,0) default dw.id_timp.nextval not null enable,
    an number(4),
    luna varchar2(3),
    zi varchar2(3),
    ora varchar2(3),
    constraint timp_pk primary key(id_timp)
);
    
);
create or replace function  dw.generate_time (   
    start_date date, end_date date,  
    day_increment integer default 1 ,
    date_type varchar2 default 'dd',
    date_type2 varchar2 default 'mm',
    date_type3 varchar2 default 'yyyy',
    date_type4 varchar2 default 'hh24'
  )   
    
    return varchar2 sql_macro as  
    stmt varchar2(4000);  
  begin  
    
    stmt := 'select 
             to_char(start_date  + ( level - 1 ) * day_increment/24, date_type4) as hours,   
             to_char(start_date  + ( level - 1 ) * day_increment/24, date_type) as days,
             to_char(start_date  + ( level - 1 ) * day_increment/24, date_type2) as months,
             to_char(start_date  + ( level - 1 ) * day_increment/24, date_type3) as years
             
    from   dual  
    connect by level <= (  
      ( 24*( end_date - start_date ) + 1 ) / day_increment  
    )';  


    return stmt;  

  end generate_time;


insert into dw.timp(an,luna,zi,ora)
        select years,months,days,hours from dw.generate_time(
        date '2020-12-01',
        date '2030-12-01');


create sequence dw.id_zbor start with 1;
create table dw.zbor(
    id_zbor number(5,0) default dw.id_zbor.nextval not null,
    durata_zbor number(3),
    intarziere number(3),
    id_avion number(5,0) not null,
    id_pilot number(5,0) not null,
    id_copilot number(5,0) not null,
    id_aeroport_plecare number(5,0) not null,
    id_aeroport_sosire number(5,0) not null,
    id_companie_aeriana number(5,0) not null,
    id_data_plecare number(5,0) not null,
    id_data_sosire number(5,0) not null,
    constraint zbor_pk primary key(id_zbor),
    constraint avion_fk FOREIGN key(id_avion) REFERENCES dw.avion(id_avion),
    constraint pilot_fk FOREIGN key(id_pilot) REFERENCES dw.pilot(id_pilot),
    constraint copilot_fk FOREIGN key(id_copilot) REFERENCES dw.pilot(id_pilot),
    constraint aeroport_plecare_fk FOREIGN key(id_aeroport_plecare) REFERENCES dw.aeroport(id_aeroport),
    constraint aeroport_sosire_fk FOREIGN key(id_aeroport_sosire) REFERENCES dw.aeroport(id_aeroport),
    constraint companie_aeriana_zbor_fk FOREIGN key(id_companie_aeriana) REFERENCES dw.companie_aeriana(id_companie_aeriana),
    constraint data_plecare_fk FOREIGN key(id_data_plecare) REFERENCES dw.timp(id_timp),
    constraint data_sosire_fk FOREIGN key(id_data_sosire) REFERENCES dw.timp(id_timp)

);

create  table  dw.bilet(
     id_zbor number(5,0) not null,
     id_client number(5,0) not null,
     pret number(5,2) not null,
     id_data_achizitionarii number(5,0)not null,
     id_tip_bilet number(5,0),
     constraint bilet_pk  primary key(id_zbor, id_client),
     constraint zbor_fk foreign key(id_zbor) REFERENCES dw.zbor(id_zbor),
     constraint client_fk foreign key(id_client) REFERENCES dw.client(id_client),
     constraint tip_bilet_fk FOREIGN KEY(id_tip_bilet) REFERENCES dw.tip_bilet(id_tip_bilet),
     constraint data_achizitionarii_fk FOREIGN key(id_data_achizitionarii) REFERENCES dw.timp(id_timp)
); 


      
