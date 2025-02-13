package org.example;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Polaznik")
public class Polaznik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolaznikID")
    private int id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;


    public Polaznik(String ime, String prezime) {
        this.ime=ime;
        this.prezime=prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}

