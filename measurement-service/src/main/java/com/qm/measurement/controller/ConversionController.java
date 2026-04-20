package com.qm.measurement.controller;

import com.qm.measurement.dto.ArithmeticInputDTO;
import com.qm.measurement.dto.ArithmeticResponseDTO;
import com.qm.measurement.dto.QuantityInputDTO;
import com.qm.measurement.dto.ResponseDTO;
import com.qm.measurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class ConversionController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/convert")
    public ResponseDTO convert(@RequestBody QuantityInputDTO dto, Authentication auth) {
        return service.convertQuantities(dto, auth.getName());
    }

    @PostMapping("/arithmetic")
    public ArithmeticResponseDTO arithmetic(@RequestBody ArithmeticInputDTO dto, Authentication auth) {
        return service.performArithmetic(dto, auth.getName());
    }

    @GetMapping("/history")
    public List<ResponseDTO> getHistory(Authentication auth) {
        return service.getHistoryForUser(auth.getName());
    }
}
