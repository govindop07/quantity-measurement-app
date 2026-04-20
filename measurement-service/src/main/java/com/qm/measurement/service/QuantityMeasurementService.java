package com.qm.measurement.service;

import com.qm.measurement.core.IMeasurable;
import com.qm.measurement.core.Quantity;
import com.qm.measurement.dto.ArithmeticInputDTO;
import com.qm.measurement.dto.ArithmeticResponseDTO;
import com.qm.measurement.dto.QuantityInputDTO;
import com.qm.measurement.dto.ResponseDTO;
import com.qm.measurement.model.QuantityMeasurementEntity;
import com.qm.measurement.repository.IQuantityMeasurementRepository;
import com.qm.measurement.units.LengthUnit;
import com.qm.measurement.units.TemperatureUnit;
import com.qm.measurement.units.VolumeUnit;
import com.qm.measurement.units.WeightUnit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseDTO convertQuantities(QuantityInputDTO dto, String userEmail) {
        String type = dto.getType() == null ? "LENGTH" : dto.getType().toUpperCase();
        double fromValue = dto.getThisQuantityDTO().getValue();
        String fromUnit = dto.getThisQuantityDTO().getUnit();
        String toUnit = dto.getThatQuantityDTO().getUnit();
        double result;

        switch (type) {
            case "TEMPERATURE": {
                Quantity<TemperatureUnit> q = new Quantity<>(fromValue, TemperatureUnit.fromString(fromUnit));
                result = q.convertTo(TemperatureUnit.fromString(toUnit)).getValue();
                break;
            }
            case "VOLUME": {
                Quantity<VolumeUnit> q = new Quantity<>(fromValue, VolumeUnit.fromString(fromUnit));
                result = q.convertTo(VolumeUnit.fromString(toUnit)).getValue();
                break;
            }
            case "WEIGHT": {
                Quantity<WeightUnit> q = new Quantity<>(fromValue, WeightUnit.fromString(fromUnit));
                result = q.convertTo(WeightUnit.fromString(toUnit)).getValue();
                break;
            }
            default: {
                Quantity<LengthUnit> q = new Quantity<>(fromValue, LengthUnit.fromString(fromUnit));
                result = q.convertTo(LengthUnit.fromString(toUnit)).getValue();
                break;
            }
        }

        result = Math.round(result * 10000.0) / 10000.0;

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("CONVERT");
        entity.setOperand1(fromValue + " " + fromUnit);
        entity.setOperand2(toUnit);
        entity.setResult(String.valueOf(result));
        entity.setUserEmail(userEmail);
        repository.save(entity);

        ResponseDTO response = new ResponseDTO();
        response.setResultValue(result);
        response.setUnit(toUnit);
        return response;
    }

    @Override
    public ArithmeticResponseDTO performArithmetic(ArithmeticInputDTO dto, String userEmail) {
        String type       = dto.getType() == null ? "LENGTH" : dto.getType().toUpperCase();
        String operation  = dto.getOperation() == null ? "ADD" : dto.getOperation().toUpperCase();
        double val1       = dto.getQuantity1().getValue();
        String unit1Str   = dto.getQuantity1().getUnit();
        double val2       = dto.getQuantity2().getValue();
        String unit2Str   = dto.getQuantity2().getUnit();
        String resUnitStr = dto.getResultUnit() != null ? dto.getResultUnit() : unit1Str;

        double result;

        switch (type) {
            case "TEMPERATURE": {
                TemperatureUnit u1 = TemperatureUnit.fromString(unit1Str);
                TemperatureUnit u2 = TemperatureUnit.fromString(unit2Str);
                TemperatureUnit uR = TemperatureUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            case "VOLUME": {
                VolumeUnit u1 = VolumeUnit.fromString(unit1Str);
                VolumeUnit u2 = VolumeUnit.fromString(unit2Str);
                VolumeUnit uR = VolumeUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            case "WEIGHT": {
                WeightUnit u1 = WeightUnit.fromString(unit1Str);
                WeightUnit u2 = WeightUnit.fromString(unit2Str);
                WeightUnit uR = WeightUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
            default: {
                LengthUnit u1 = LengthUnit.fromString(unit1Str);
                LengthUnit u2 = LengthUnit.fromString(unit2Str);
                LengthUnit uR = LengthUnit.fromString(resUnitStr);
                result = computeArithmetic(new Quantity<>(val1, u1), new Quantity<>(val2, u2), operation, uR);
                break;
            }
        }

        result = Math.round(result * 10000.0) / 10000.0;

        String sym = operatorSymbol(operation);
        boolean isDivide = operation.equals("DIVIDE");
        String expression = val1 + " " + unit1Str + " " + sym + " " + val2 + " " + unit2Str
                + " = " + result + " " + (isDivide ? "(ratio)" : resUnitStr);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation(operation);
        entity.setOperand1(val1 + " " + unit1Str);
        entity.setOperand2(val2 + " " + unit2Str);
        entity.setResult(result + (isDivide ? "" : " " + resUnitStr));
        entity.setUserEmail(userEmail);
        repository.save(entity);

        ArithmeticResponseDTO response = new ArithmeticResponseDTO();
        response.setResultValue(result);
        response.setResultUnit(isDivide ? "ratio" : resUnitStr);
        response.setExpression(expression);
        response.setOperation(operation);
        return response;
    }

    private <T extends IMeasurable> double computeArithmetic(
            Quantity<T> q1, Quantity<T> q2, String op, T resultUnit) {
        double base1 = q1.toBase();
        double base2 = q2.toBase();
        switch (op) {
            case "ADD":      return resultUnit.fromBaseUnit(base1 + base2);
            case "SUBTRACT": return resultUnit.fromBaseUnit(base1 - base2);
            case "MULTIPLY": return resultUnit.fromBaseUnit(base1 * base2);
            case "DIVIDE":
                if (base2 == 0) throw new ArithmeticException("Division by zero");
                return base1 / base2;
            default: throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }

    private String operatorSymbol(String op) {
        switch (op) {
            case "ADD":      return "+";
            case "SUBTRACT": return "-";
            case "MULTIPLY": return "x";
            case "DIVIDE":   return "/";
            default:         return op;
        }
    }

    @Override
    public List<ResponseDTO> getHistoryForUser(String userEmail) {
        return repository.findByUserEmail(userEmail)
                .stream()
                .map(e -> {
                    ResponseDTO dto = new ResponseDTO();
                    dto.setResultString(e.getOperation() + " | " + e.getOperand1() + " , " + e.getOperand2() + " → " + e.getResult());
                    dto.setResultValue(parseDouble(e.getResult()));
                    dto.setUnit(e.getOperand2());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private double parseDouble(String val) {
        try { return Double.parseDouble(val.trim().split(" ")[0]); }
        catch (Exception e) { return 0.0; }
    }
}
