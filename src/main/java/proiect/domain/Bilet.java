package proiect.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BILET")
@IdClass(BiletId.class)
public class Bilet {

    @Id
    private int id_client;

    @Id
    private int id_zbor;

    @Column
    private float pret;

    @Column
    private Date data_achizitionare;

    @ManyToOne
    @JoinColumn(name = "id_tip_bilet",referencedColumnName = "id_tip_bilet")
    private TipBilet tipBilet;

    public Bilet() {

    }

    public Bilet(int id_client, int id_zbor, float pret, Date data_achizitionare, TipBilet tipBilet) {
        this.id_client = id_client;
        this.id_zbor = id_zbor;
        this.pret = pret;
        this.data_achizitionare = data_achizitionare;
        this.tipBilet = tipBilet;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_zbor() {
        return id_zbor;
    }

    public void setId_zbor(int id_zbor) {
        this.id_zbor = id_zbor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public Date getData_achizitionare() {
        return data_achizitionare;
    }

    public void setData_achizitionare(Date data_achizitionare) {
        this.data_achizitionare = data_achizitionare;
    }

    public TipBilet getTipBilet() {
        return tipBilet;
    }

    public void setTipBilet(TipBilet tipBilet) {
        this.tipBilet = tipBilet;
    }
}
