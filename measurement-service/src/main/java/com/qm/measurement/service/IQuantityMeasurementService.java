package com.qm.measurement.service;

import com.qm.measurement.dto.ArithmeticInputDTO;
import com.qm.measurement.dto.ArithmeticResponseDTO;
import com.qm.measurement.dto.QuantityInputDTO;
import com.qm.measurement.dto.ResponseDTO;
import java.util.List;

public interface IQuantityMeasurementService {
    ResponseDTO convertQuantities(QuantityInputDTO dto, String userEmail);
    List<ResponseDTO> getHistoryForUser(String userEmail);
    ArithmeticResponseDTO performArithmetic(ArithmeticInputDTO dto, String userEmail);
}
