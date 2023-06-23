package org.anakrimba.minpro_rijal.controller;

import org.anakrimba.minpro_rijal.exception.ResourceNotFoundException;
import org.anakrimba.minpro_rijal.model.Category;
import org.anakrimba.minpro_rijal.model.Pasien;
import org.anakrimba.minpro_rijal.repository.CategoryRepo;
import org.anakrimba.minpro_rijal.repository.PasienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PasienController {

    @Autowired
    PasienRepo pasienRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/pasien")
    public ResponseEntity<List<Pasien>> getAllPasien(@RequestParam(required = false) String nama) {
        List<Pasien> pasiens = new ArrayList<>();

        if (nama == null) {
            pasienRepo.findAll().forEach(pasiens::add);
        } else {
            pasienRepo.findByNamaContaining(nama).forEach(pasiens::add);
        }

        if (pasiens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pasiens, HttpStatus.OK);

    }

    @PostMapping("/pasien/add")
    public ResponseEntity<Pasien> createPasien(@RequestBody Pasien pasienRequest) {
        Pasien pasien = pasienRepo
                .save(pasienRequest);
        Category category = categoryRepo.findById(pasienRequest.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Category Not Fount"));
        pasien.setCategory(category);
        return new ResponseEntity<>(pasien, HttpStatus.CREATED);
    }

    @GetMapping("/pasien/{id}")
    public ResponseEntity<Pasien> getPasienById(@PathVariable("id") long id) {
        Pasien pasien = pasienRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Pasien with id = " + id));

        return new ResponseEntity<>(pasien, HttpStatus.OK);
    }

    @PutMapping("/pasien/{id}/edit")
    public ResponseEntity<Pasien> updatePasien(@PathVariable("id") long id, @RequestBody Pasien pasien) {
        Pasien _pasien = pasienRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Pasien with id = " + id));

        _pasien.setNama(pasien.getNama());
        _pasien.setAlamat(pasien.getAlamat());
        _pasien.setUmur(pasien.getUmur());
        _pasien.setJenisKelamin(pasien.getJenisKelamin());

        return new ResponseEntity<>(pasienRepo.save(_pasien), HttpStatus.OK);
    }

    @DeleteMapping("/pasien/{id}")
    public ResponseEntity<HttpStatus> deletePasien(@PathVariable("id") long id){

        pasienRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/pasien")
    public ResponseEntity<HttpStatus> deleteAllPasien(){
        pasienRepo.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
