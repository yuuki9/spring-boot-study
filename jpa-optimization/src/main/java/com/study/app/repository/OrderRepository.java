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

    public List<Orders> findAllWithMemberDelivery() {
        return entityManager.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d", Orders.class)
                .getResultList();
    }

    public List<Orders> findAllWithItem(){
        return entityManager.createQuery("select distinct o from Orders o " +
                "join fetch o.member m " +
                "join fetch o.delivery d " +
                "join fetch o.orderItems oi " +
                "join fetch oi.item i", Orders.class)
                .getResultList();
    }
}
