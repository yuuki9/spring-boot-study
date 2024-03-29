package com.study.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.app.domain.delivery.Address;
import com.study.app.domain.order.Orders;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
}
