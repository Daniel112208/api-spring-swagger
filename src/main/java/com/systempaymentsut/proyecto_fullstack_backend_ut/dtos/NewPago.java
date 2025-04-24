package com.systempaymentsut.proyecto_fullstack_backend_ut.dtos;

import java.time.LocalDate;

import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getters and Setters
@NoArgsConstructor
@AllArgsConstructor
public class NewPago {

    private double cantidad;
    private TypePago typePago;
    private LocalDate date;
    private String codigoestudiante;
    
}
