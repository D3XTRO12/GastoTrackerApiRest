package com.d3xtro.GastoTracker.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long id;
    private String name;
    private Double amount;
    private String category;
    private String date;
    private boolean isCredit;
    private int installments;
    private Double installmentAmount;
    private boolean isPaid;
    private Long userId;
}
