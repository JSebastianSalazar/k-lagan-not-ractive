package com.klagan.tets.presentation.controllers;



import com.klagan.tets.presentation.dto.PriceDto;
import com.klagan.tets.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping()
    public ResponseEntity<PriceDto> getPrice(
            @RequestParam(name = "brandId") Long brandId,
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "applicationDate") LocalDateTime applicationDate) {
        PriceDto priceDto = priceService.getPrice(brandId, productId, applicationDate);

        if (priceDto != null) {
            return ResponseEntity.ok(priceDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
