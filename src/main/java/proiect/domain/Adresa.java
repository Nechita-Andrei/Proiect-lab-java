package proiect.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ADRESA")
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ADRESA")
    @SequenceGenerator(name = "MY_GENERATOR_ADRESA", sequenceName = "ID_ADRESA")
    private Integer id_adresa;
    private String judet;
    private String localitate;
    private String strada;



    public Adresa(String judet, String localitate, String strada) {
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
    }

    public Adresa() {

    }
}
