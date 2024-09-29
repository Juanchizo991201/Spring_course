package com.jjmontenegrop.springbootwebscraping.controllers;

import com.jjmontenegrop.springbootwebscraping.dtos.ResponseDTO;
import com.jjmontenegrop.springbootwebscraping.services.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/scraper")
@RequiredArgsConstructor
public class ScraperController {

    private final ScraperService scraperService;

    @RequestMapping("/getVehiclesByModel")
    public Set<ResponseDTO> getVehiclesByModel(@RequestParam String model) {
        return scraperService.getVehiclesByModel(model);
    }
}
