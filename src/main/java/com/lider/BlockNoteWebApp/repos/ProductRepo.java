package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.Message;

import com.lider.BlockNoteWebApp.domain.Product;
import com.lider.BlockNoteWebApp.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepo extends CrudRepository<Product, BigInteger> {
    List<Product> findByName(String name);

    List<Product> findAllByAuthor(User user);

//    @Query(value = "SELECT * FROM products WHERE ID = ?0", nativeQuery = true)
//    Product findById(BigInteger id);
}
