package com.klagan.tets.service;


import com.klagan.tets.model.Price;
import com.klagan.tets.persistence.PriceRepository;
import com.klagan.tets.presentation.dto.PriceDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceDto getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        validateParameters(brandId, productId, applicationDate);

        List<Price> prices = priceRepository
                .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);

        if (prices.isEmpty()) {
            return null;
        }

        Price highestPriorityPrice = findHighestPriorityPrice(prices);
        return entityToDto(highestPriorityPrice);
    }

    private Price findHighestPriorityPrice(List<Price> prices) {
        Price highestPriority = null;

        for (Price price : prices) {
            if (highestPriority == null || price.getPriority() > highestPriority.getPriority()) {
                highestPriority = price;
            }
        }

        return highestPriority;
    }

    private void validateParameters(Long brandId, Long productId, LocalDateTime applicationDate) {
        if (brandId == null || productId == null || applicationDate == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos.");
        }
    }

    private PriceDto entityToDto(Price price) {
        return PriceDto.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .build();
    }
}
