package com.elevate.app.takeaway.repository;

import com.elevate.app.takeaway.dto.order.Order;
import com.elevate.app.takeaway.dto.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<List<UserAddress>> findByUserId(long userId);

//    @Query(value = "SELECT user_id FROM address WHERE city=?1", nativeQuery = true)
//    Optional<List<Order>> getOrdersByUserId(String City);
}
