package com.study.app.presentation;


import com.study.app.domain.order.Orders;
import com.study.app.presentation.dto.OrderDto;
import com.study.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;

    /**
     * V1. 엔티티 직접 노출
     * - 엔티티가 변하면 API 스펙이 변한다.
     * - 트랜잭션 안에서 지연 로딩 필요
     * - 양방향 연관관계 문제
     *
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용 X)
     * - 트랜잭션 안에서 지연 로딩 필요
     *
     * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용 O)
     * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
     *
     * V4. JPA에서 DTO로 바로 조회, 컬렉션 N 조회 (1 + N Query)
     * - 페이징 가능
     *
     * V5. JPA에서 DTO로 바로 조회, 컬렉션 1 조회 최적화 버전 (1 + 1 Query)
     * - 페이징 가능
     *
     * V6. JPA에서 DTO로 바로 조회, 플랫 데이터(1Query) (1 Query)
     * - 페이징 불가능
     */

    @GetMapping("/api/v1/orders")
    public List<Orders> ordersV1() {
        List<Orders> all = orderRepository.findAll();
        for (Orders order : all) { // Lazy 강제 초기화
            order.getMember().getName();
            order.getDelivery().getAddress();
            order.getOrderItems().stream()
                    .forEach(o -> o.getItem().getName());
        }
        return all;
    }

    /**
     * V2.엔티티를 조회해서 DTO로 변환 (fetch join 사용 X)
     * - 단점: 지연로딩으로 쿼리 N번 호출 (N+1문제 발생)
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll().stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
    }

    /**
     * V3.엔티티를 조회해서 DTO로 변환 (fetch join 사용 O)
     * - fetch join으로 쿼리 1번 호출
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderRepository.findAllWithItem().stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
