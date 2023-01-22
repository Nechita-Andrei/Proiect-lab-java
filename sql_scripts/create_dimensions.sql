create dimension dw.timp_dim
level an_nivel is (timp.an)
level luna_nivel is (timp.luna)
level zi_nivel is (timp.zi)
level ora_nivel is (timp.ora)
hierarchy timp (
    ora_nivel child of
    zi_nivel child of
    luna_nivel child of
    an_nivel
);

create dimension dw.aeroport_dim
level aeroport is (aeroport.id_aeroport)
level tara is (aeroport.tara)
level judet is (aeroport.judet)
level oras is (aeroport.oras)
level strada is (aeroport.strada)
level cod_postal is (aeroport.cod_postal)
hierarchy aeroport
(   aeroport child of
    cod_postal child of
    strada child of
    oras child of
    judet child of
    tara
)
attribute aeroport determines(dw.aeroport.denumire, dw.aeroport.capacitate, dw.aeroport.an_constructie);



create dimension dw.avion_dim
level avion is (avion.id_avion)
level denumire is (avion.denumire)
level capacitate is (avion.capacitate)
level an_constructie is (avion.an_constructie)
level companie_aeriana is (avion.id_companie_aeriana)
attribute avion determines(dw.avion.denumire, dw.avion.capacitate, dw.avion.an_constructie, dw.avion.id_companie_aeriana);

create dimension dw.client_dim
level client is (client.id_client)
level prenume is (client.prenume)
level nume is (client.nume)
level cnp is (client.cnp)
level varsta is (client.varsta)
level email is (client.email)
level telefon is (client.telefon)
attribute client determines(dw.client.prenume, dw.client.nume, dw.client.cnp, dw.client.varsta, dw.client.email, dw.client.telefon);

create dimension dw.companie_aeriana_dim
level companie_aeriana is (companie_aeriana.id_companie_aeriana)
level denumire is (companie_aeriana.denumire)
level vechime is (companie_aeriana.vechime)
level email is (companie_aeriana.email)
level telefon is (companie_aeriana.telefon)
level website is (companie_aeriana.website)
attribute companie_aeriana determines( dw.companie_aeriana.denumire,dw.companie_aeriana.vechime,dw.companie_aeriana.email,dw.companie_aeriana.telefon,dw.companie_aeriana.website);

create dimension dw.pilot_dim
level pilot is (pilot.id_pilot)
level prenume is (pilot.prenume)
level nume is (pilot.nume)
level varsta is (pilot.varsta)
level experienta is (pilot.experienta)
level companie_aeriana is (pilot.id_companie_aeriana)
attribute pilot determines(dw.pilot.prenume, dw.pilot.nume, dw.pilot.varsta, dw.pilot.experienta,dw.pilot.id_companie_aeriana);

create dimension dw.tip_bilet_dim
level tip_bilet is (tip_bilet.id_tip_bilet)
level denumire is (tip_bilet.denumire)
level descriere is (tip_bilet.descriere)
attribute tip_bilet determines(dw.tip_bilet.denumire, dw.tip_bilet.descriere);
