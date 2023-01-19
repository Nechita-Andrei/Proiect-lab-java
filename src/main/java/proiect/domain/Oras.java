package proiect.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORAS")
public class Oras {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ORAS")
    @SequenceGenerator(name = "MY_GENERATOR_ORAS", sequenceName = "ID_ORAS")
    private Integer id_oras;
    @ManyToOne
    @JoinColumn(name = "id_judet")
    private Integer id_judet;
    @ManyToOne
    @JoinColumn(name = "id_zona_covid")
    private Integer id_zona_covid;
    private String denumire;
    private String abreviere;


    public Oras(Integer id_judet, Integer id_zona_covid, String denumire, String abreviere) {
        this.id_judet = id_judet;
        this.id_zona_covid = id_zona_covid;
        this.denumire = denumire;
        this.abreviere=abreviere;
    }

    public Oras() {

    }

}
