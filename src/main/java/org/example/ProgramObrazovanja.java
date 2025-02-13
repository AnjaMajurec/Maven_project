package org.example;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ProgramObrazovanja")
public class ProgramObrazovanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramObrazovanjaID")
    private int id;

    @Column(name = "Naziv")
    private String naziv;

    @Column(name = "CSVET")
    private int csvet;


    public ProgramObrazovanja() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCsvet() {
        return csvet;
    }

    public void setCsvet(int csvet) {
        this.csvet = csvet;
    }
}
