package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedNativeQuery;
import java.math.BigInteger;
import java.util.List;

public interface OrderRepo  extends CrudRepository<Order, BigInteger> {
    //@Query(value = "SELECT * FROM orders WHERE user_id = ?0", nativeQuery = true)
    Order findByCustomer(User customer);
}
