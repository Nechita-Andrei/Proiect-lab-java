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



    public void setSumaDeBani(Integer sumaDeBani) {
        this.sumaDeBani = sumaDeBani;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    @Column
    private String banca;

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getBanca() {
        return banca;
    }

    public Integer getSumaDeBani() {

        return sumaDeBani;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public Cont(String banca, Integer sumaDeBani, String numarCard) {
        this.banca = banca;
        this.sumaDeBani = sumaDeBani;
        this.numarCard = numarCard;
    }

    @Column
    private Integer sumaDeBani;

    @Column(unique = true,length = 10)
    private String numarCard;
}
