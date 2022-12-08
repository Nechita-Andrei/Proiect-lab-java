package proiect.domain;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "COMPANIE_AERIANA")
@Data
public class CompanieAeriana {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_generator_companie")
    @SequenceGenerator(name = "my_generator_companie", sequenceName = "id_companie_aeriana")
    private Integer id_companie_aeriana;


    private String denumire;

    private short anul_infiintarii;

    @OneToOne
    @JoinColumn(name = "id_contact",referencedColumnName = "id_contact")
    private Contact contact;
    public CompanieAeriana(){

    }


    public CompanieAeriana(String denumire, short anul_infiintarii, Contact contact) {
        this.denumire = denumire;
        this.anul_infiintarii = anul_infiintarii;
        this.contact = contact;
    }
}
