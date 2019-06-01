package com.example.togepi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {

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

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

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
}
