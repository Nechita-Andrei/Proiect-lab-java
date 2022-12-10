package proiect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR_CLIENT")
    @SequenceGenerator(name = "MY_GENERATOR_CLIENT", sequenceName = "ID_CLIENT")
    private int id_client;


    private String nume;


    private String prenume;


    @Column
    private Date data_nasterii;

    @OneToOne
    @JoinColumn(name = "id_contact", referencedColumnName = "id_contact")
    private Contact contact;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JoinTable(name = "bilet", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "zbor_id"))
//    private Set<Zbor> zboruri;

    public Client() {

    }

    public Client(String nume, String prenume, Date data_nasterii, Contact contact) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nasterii = data_nasterii;
        this.contact = contact;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", data_nasterii=" + data_nasterii +
                ", contact=" + contact +
                '}';
    }

    public int getId_client() {
        return id_client;
    }


//    public void setZboruri(Set<Zbor> zboruri) {
//        this.zboruri = zboruri;
//    }
//
//    public void addZbor(Zbor zbor){
//        zboruri.add(zbor);
//    }

}
