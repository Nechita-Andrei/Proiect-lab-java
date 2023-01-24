package proiect.domain;


import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @Column(name = "id_contact")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_GENERATOR")
    @SequenceGenerator(name = "MY_GENERATOR", sequenceName = "ID_CONTACT", allocationSize = 1)
    private int idContact;

    @Column(unique = true, nullable = false)
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
                "id_contact=" + idContact +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
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
