package com.jjmontenegrop.springbootwebscraping.services;

import com.jjmontenegrop.springbootwebscraping.dtos.ResponseDTO;

import java.util.Set;

public interface ScraperService {
    Set<ResponseDTO> getVehiclesByModel(String model);
}
