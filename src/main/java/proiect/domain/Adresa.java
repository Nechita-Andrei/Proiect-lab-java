package proiect.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
