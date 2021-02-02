package com.example.store.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItems {
    @EmbeddedId
    private OrderItemsPK items;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orders_id")
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderId=" + items.getOrderId() +
                "productId=" + items.getProductId() +
                ", quantity=" + quantity +
                '}';
    }
}
