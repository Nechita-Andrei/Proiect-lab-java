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

    @Column(nullable = false)
    private float pret;

    @Column(nullable = false)
    private Date data_achizitionare;

    @ManyToOne
    @JoinColumn(name = "id_tip_bilet",referencedColumnName = "id_tip_bilet")
    private TipBilet tip_bilet;

    public Bilet() {

    }

    public Bilet(int id_client, int id_zbor, float pret, Date data_achizitionare, TipBilet tip_bilet) {
        this.id_client = id_client;
        this.id_zbor = id_zbor;
        this.pret = pret;
        this.data_achizitionare = data_achizitionare;
        this.tip_bilet = tip_bilet;
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

    public TipBilet getTip_bilet() {
        return tip_bilet;
    }

    public void setTip_bilet(TipBilet tipBilet) {
        this.tip_bilet = tipBilet;
    }
}
