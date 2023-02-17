package com.study.app.domain.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.app.domain.order.Orders;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders orders;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
