package com.klagan.tets.presentation.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
public class PriceDto {

    @Id
    private Long id;

    private Long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Long productId;

    private Integer priority;

    private BigDecimal price;
    
    private String currency;
}
