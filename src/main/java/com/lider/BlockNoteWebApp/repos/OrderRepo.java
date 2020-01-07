package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.Message;
import com.lider.BlockNoteWebApp.domain.Order;
import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo  extends CrudRepository<Order, Integer> {
//    List<OrderDetail> findById(Long id);
}
