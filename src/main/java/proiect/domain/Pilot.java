package proiect.domain;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "PILOT")
public class Pilot {

    @Id
    @Column(name = "id_pilot")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_PILOT")
    @SequenceGenerator(name = "MY_GENERATOR_PILOT", sequenceName = "ID_PILOT", allocationSize = 1)
    private int idPilot;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(nullable = false)
    private Date data_nasterii;

    @Column
    private Date data_angajarii;

    @ManyToOne
    @JoinColumn(name = "id_companie_aeriana")
    private CompanieAeriana companieAerianaPilot;


    public Pilot() {

    }

    public Pilot(String nume, String prenume, Date data_nasterii, Date data_angajarii, CompanieAeriana companieAerianaPilot) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nasterii = data_nasterii;
        this.data_angajarii = data_angajarii;
        this.companieAerianaPilot = companieAerianaPilot;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getData_nasterii() {
        return data_nasterii;
    }

    public void setData_nasterii(Date data_nasterii) {
        this.data_nasterii = data_nasterii;
    }

    public Date getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(Date data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public CompanieAeriana getCompanieAerianaPilot() {
        return companieAerianaPilot;
    }

    public void setCompanieAerianaPilot(CompanieAeriana companieAerianaPilot) {
        this.companieAerianaPilot = companieAerianaPilot;
    }

    public int getIdPilot() {
        return idPilot;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id_pilot=" + idPilot +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", data_nasterii=" + data_nasterii +
                ", data_angajarii=" + data_angajarii +
                ", companieAerianaPilot=" + companieAerianaPilot +
                '}';
    }
}
