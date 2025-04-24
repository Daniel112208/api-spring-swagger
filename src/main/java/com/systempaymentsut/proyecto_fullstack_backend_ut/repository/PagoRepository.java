package com.systempaymentsut.proyecto_fullstack_backend_ut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.PagoStatus;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.TypePago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long>{

    // Lista de pagos asociados a un estudiante
    List<Pago> findByEstudianteCodigo(String codigo);

    // Lista para buscar los pagos por su estado
    List<Pago> findByStatus(PagoStatus status);

    // Lista de pagos segun el tipo seleccionado
    List<Pago> findByType(TypePago type);
    
}
