package com.d3xtro.GastoTracker.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "expenses")
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "isCredit", nullable = false)
    private boolean isCredit;

    @Column(name = "installments", nullable = false)
    private int installments;

    @Column(name = "installmentAmount", nullable = false)
    private Double installmentAmount;

    @Column(name = "isPaid", nullable = false)
    private boolean isPaid;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
