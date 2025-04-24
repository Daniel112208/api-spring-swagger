package com.systempaymentsut.proyecto_fullstack_backend_ut.entities;

import java.time.LocalDate;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.PagoStatus;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.TypePago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Builder // permitir constriur objetos de esta clase con el patron builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate fecha;
    private double cantidad;
    private TypePago type;
    private PagoStatus status;
    private String file;

    //Relacion que tendria en la base de datos
    //Podemos tener muchos pagos con un estudiante 

    @ManyToOne
    private Estudiante estudiante;
    
}
