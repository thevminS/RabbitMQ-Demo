package com.rabitmq.pcw.controllers;

import com.rabitmq.pcw.constants.Constants;
import com.rabitmq.pcw.dtos.Price;
import com.rabitmq.pcw.dtos.Quote;
import com.rabitmq.pcw.services.PcwService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BASE_URL)
public class PcwController {

    private final PcwService pcwService;

    @PostMapping(Constants.PRICE_URL)
    public ResponseEntity<?> getPrice(@RequestBody Quote quote){
        Price price = pcwService.sentToPrice(quote);
        if (price.getId().equals(null))
            return ResponseEntity.status(400).body(price);
        else
            return ResponseEntity.ok(price);
    }
}
