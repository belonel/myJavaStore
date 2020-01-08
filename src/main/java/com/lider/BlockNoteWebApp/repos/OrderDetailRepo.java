package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.Order;
import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface OrderDetailRepo extends CrudRepository<OrderDetail, BigInteger> {
    //List<OrderDetail> findByOrder(Order order);

//    @Query(value = "SELECT * FROM order_details WHERE ORDER_ID = ?0", nativeQuery = true)
    List<OrderDetail> findAllByOrder(Order order);

//    @Query(value = "SELECT * FROM order_details WHERE PRODUCT_ID = ?0", nativeQuery = true)
    OrderDetail findByProductId(BigInteger id);

    //OrderDetail findByProduct(Product product);
}