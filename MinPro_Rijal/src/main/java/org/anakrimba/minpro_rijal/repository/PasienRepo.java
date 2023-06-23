package org.anakrimba.minpro_rijal.repository;

import org.anakrimba.minpro_rijal.model.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PasienRepo extends JpaRepository<Pasien ,Long> {

    List<Pasien> findByNamaContaining(String nama);
}
