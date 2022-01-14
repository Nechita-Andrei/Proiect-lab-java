package proiect.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "cont")
public class Cont {

    public Cont(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String banca;

    public void setId(int id) {
        this.id = id;
    }

    public void setSumaDeBani(int sumaDeBani) {
        this.sumaDeBani = sumaDeBani;
    }

    public int getId() {
        return id;
    }

    public String getBanca() {
        return banca;
    }

    public int getSumaDeBani() {
        return sumaDeBani;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public Cont(String banca, int sumaDeBani, String numarCard) {
        this.banca = banca;
        this.sumaDeBani = sumaDeBani;
        this.numarCard = numarCard;
    }

    @Column
    @NonNull
    private Integer sumaDeBani;

    @Column(unique = true,length = 10)
    private String numarCard;
}
