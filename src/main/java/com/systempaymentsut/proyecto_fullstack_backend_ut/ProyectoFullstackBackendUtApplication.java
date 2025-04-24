package com.systempaymentsut.proyecto_fullstack_backend_ut;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Estudiante;
import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.PagoStatus;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.TypePago;
import com.systempaymentsut.proyecto_fullstack_backend_ut.repository.EstudianteRepository;
import com.systempaymentsut.proyecto_fullstack_backend_ut.repository.PagoRepository;

@SpringBootApplication
public class ProyectoFullstackBackendUtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFullstackBackendUtApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository) {
		return args -> {
			estudianteRepository.save(Estudiante.builder()

					.id(UUID.randomUUID().toString())
					.nombre("Melissa")
					.apellido("Gordillo")
					.codigo("1234")
					.programaId("ISI123")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Juan")
					.apellido("Pérez")
					.codigo("1235")
					.programaId("ISI123")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Laura")
					.apellido("Ramírez")
					.codigo("1236")
					.programaId("ISI123")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Carlos")
					.apellido("Martínez")
					.codigo("1237")
					.programaId("ISI123")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Andrea")
					.apellido("López")
					.codigo("1238")
					.programaId("ISI123")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("David")
					.apellido("Torres")
					.codigo("1239")
					.programaId("ISI123")
					.build());

			// Obtener tipos de pagos diferentes para cada estudiante
			TypePago tiposdepago[] = TypePago.values();

			Random random = new Random();

			estudianteRepository.findAll().forEach(estudiante -> {

				for (int i = 0; i < 10; i++) {
					int index = random.nextInt(tiposdepago.length);

					// Construir un objeto Pago con valores aleatorios
					Pago pago = Pago.builder()
							.cantidad(1000 + (int) (Math.random() * 20000))
							.type(tiposdepago[index])
							.status(PagoStatus.CREADO)
							.fecha(LocalDate.now())
							.estudiante(estudiante)
							.build();

					pagoRepository.save(pago);

				}

			});

		};
	}

}
