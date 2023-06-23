package org.anakrimba.minpro_rijal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "pasien")
public class Pasien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat")
    private String  alamat;
    @Column(name = "umur")
    private String umur;

    @Column(name = "jenisKelamin")
    private String jenisKelamin;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Pasien() {
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public Pasien(String nama, String alamat, String umur, String jenisKelamin) {
        this.nama = nama;
        this.alamat = alamat;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;

    }

    @Override
    public String toString() {
        return "Pasien{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", umur='" + umur + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                '}';
    }
}