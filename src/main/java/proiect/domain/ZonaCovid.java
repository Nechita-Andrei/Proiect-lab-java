package proiect.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ZONA_COVID")
public class ZonaCovid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_ZONA_COVID")
    @SequenceGenerator(name = "MY_GENERATOR_ZONA_COVID", sequenceName = "ID_ZONA_COVID")
    private Integer id_zona_covid;

    @Column
    private String descriere;

    @Column(unique = true, nullable = false)
    private String zona;



    public ZonaCovid(String descriere, String zona) {
        this.descriere = descriere;
        this.zona = zona;
    }

    public ZonaCovid() {

    }

}
