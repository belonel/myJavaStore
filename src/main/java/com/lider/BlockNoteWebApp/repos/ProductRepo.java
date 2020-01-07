package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.Message;

import com.lider.BlockNoteWebApp.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findByName(String name);
}
