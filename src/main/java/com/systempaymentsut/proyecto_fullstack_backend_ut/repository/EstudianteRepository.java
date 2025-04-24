package com.systempaymentsut.proyecto_fullstack_backend_ut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante, String>{

    // Metodo personalizado para buscar ubn estudiante en especifico
    Estudiante findByCodigo(String codigo);
    

    // Lista estudiantes pertenecientes a un programa en especifico
    List<Estudiante> findByProgramaId(String programaId);
    
}
