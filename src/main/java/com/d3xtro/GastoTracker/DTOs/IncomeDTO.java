package com.d3xtro.GastoTracker.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {
    private Long id;
    private String name;
    private Double amount;
    private String category;
    private Long userId;
}
