package proiect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Client.Rol rol;

    public enum Rol{
      ADMIN,
      CLIENT
    }
    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isDiscount() {
        return discount;
    }

    @Column
    private int varsta;

    public Client(String nume, String prenume, int varsta, String email,  String telefon, String parola,Cont cont, Rol rol) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.email = email;
        this.cont = cont;
        this.telefon = telefon;
        this.parola = parola;
        this.discount=false;
        this.rol=rol;
    }

    @Column(nullable = false,unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "contId",referencedColumnName = "id")
    private Cont cont;

    @Column(length = 10)
    private String telefon;

    @Column(nullable = false)
    private String parola;

    @Column
    private boolean discount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "bilet", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "zbor_id"))
    private Set<Zbor> zboruri;

    public Client() {

    }

    public Cont getCont() {
        return cont;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setCont(Cont cont) {
        this.cont = cont;
    }

    public String getEmail() {
        return email;
    }
    public Set<Zbor> getZboruri() {
        return zboruri;
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

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setZboruri(Set<Zbor> zboruri) {
        this.zboruri = zboruri;
    }

    public void addZbor(Zbor zbor){
        zboruri.add(zbor);
    }

}
