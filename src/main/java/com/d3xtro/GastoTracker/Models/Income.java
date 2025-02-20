package com.d3xtro.GastoTracker.Models;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Table(name = "incomes")
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "category", nullable = false)
    private String category;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
