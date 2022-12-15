package proiect.domain;

import java.io.Serializable;
import java.util.Objects;

public class BiletId implements Serializable {
    private int id_client;
    private int id_zbor;

    public BiletId() {
    }

    public BiletId(int id_client, int id_zbor) {
        this.id_client = id_client;
        this.id_zbor = id_zbor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiletId biletId = (BiletId) o;
        return id_client == biletId.id_client && id_zbor == biletId.id_zbor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_client, id_zbor);
    }
}
