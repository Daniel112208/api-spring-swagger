package com.systempaymentsut.proyecto_fullstack_backend_ut.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Entity
@Builder // permitir constriur objetos de esta clase con el patron builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    // Modificadores de accero; public: publico, private: privado, protected: protegido
    // Metodos accesores - get (Obtener) - set (Establecer)
    
    @Id
    private String id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String codigo;

    private String programaId;

    private String foto;

    

}
