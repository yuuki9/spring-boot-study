package com.study.app.repository;

import com.study.app.domain.order.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager entityManager;

    public void save(Orders order){
        entityManager.persist(order);
    }

    public List<Orders> findAll(){
        return entityManager.createQuery("select o from Orders o", Orders.class).getResultList();
    }


}
