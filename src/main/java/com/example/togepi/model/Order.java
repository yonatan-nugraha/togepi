package com.example.togepi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "status", length = 10, nullable = false)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @NotEmpty
    @JsonManagedReference
    private List<Item> items;

    @Transient
    private BigDecimal priceSubtotal;

    @Transient
    private BigDecimal taxSubtotal;

    @Transient
    private BigDecimal grandTotal;

    public Order() {
        this.priceSubtotal = this.taxSubtotal = this.grandTotal = BigDecimal.ZERO;
    }

    public Order(List<Item> items) {
        this.priceSubtotal = this.taxSubtotal = this.grandTotal = BigDecimal.ZERO;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", items=" + items +
                ", priceSubtotal=" + priceSubtotal +
                ", taxSubtotal=" + taxSubtotal +
                ", grandTotal=" + grandTotal +
                "}";
    }
}
