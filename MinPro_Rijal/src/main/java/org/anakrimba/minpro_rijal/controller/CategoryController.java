package org.anakrimba.minpro_rijal.controller;

import org.anakrimba.minpro_rijal.exception.ResourceNotFoundException;
import org.anakrimba.minpro_rijal.model.Category;
import org.anakrimba.minpro_rijal.repository.CategoryRepo;
import org.anakrimba.minpro_rijal.repository.PasienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {


    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> category = new ArrayList<Category>();

        categoryRepo.findAll().forEach(category::add);

        if (category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Categoory with id = " + id));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category categoryRequest) {
        Category category = categoryRepo.save(categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateTag(@PathVariable("id") long id, @RequestBody Category categoryRequest) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tagid" + id + "not found"));

        category.setPerawatan(categoryRequest.getPerawatan());
        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id) {
        categoryRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/category/")
    public ResponseEntity<HttpStatus> deleteAllCategory(@PathVariable("id") long id) {
        categoryRepo.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
