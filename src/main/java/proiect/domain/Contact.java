package proiect.domain;


import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR")
    @SequenceGenerator(name = "MY_GENERATOR", sequenceName = "ID_CONTACT")
    private int id_contact;

    @Column
    private String email;
    @Column
    private String telefon;
    @Column
    private String website;

    public Contact(){

    }


    public Contact(String email, String telefon, String website) {
        this.email = email;
        this.telefon = telefon;
        this.website = website;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id_contact=" + id_contact +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public int getId_contact() {
        return id_contact;
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
