package com.systempaymentsut.proyecto_fullstack_backend_ut.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Estudiante;
import com.systempaymentsut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.PagoStatus;
import com.systempaymentsut.proyecto_fullstack_backend_ut.enums.TypePago;
import com.systempaymentsut.proyecto_fullstack_backend_ut.repository.EstudianteRepository;
import com.systempaymentsut.proyecto_fullstack_backend_ut.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional // para asegurar que los metodos de la calse se ejecuten debtro de una
               // transaccion
public class PagoService {

    // inyeccion de dependencias de PagoRepository para interactuar con la base de
    // datos de pagos
    @Autowired
    private PagoRepository pagoRepository;

    // inyeccion de dependencias de EstudianteRepository para obtener informacion de
    // los estudiantes desde la BD
    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * metodo para guardar el pago en la BD y almacenar un archivo pdf en el
     * servidor
     * 
     * @param file             archivo pdf que se subira al servidor
     * @param cantiad          para saber cual es elmonto del pago realizado
     * @param tipo             para saber el tipo de pago
     * @param date             fecha de cuando de realizo el pago
     * @param codigoEstudiante codigo del estudiamte que realiza el pago
     * @return objeto Pago guardado en la BD
     * @throws IOException excepcion lanzada si ocurre un error al manejar el file
     *                     PDF
     */

    public Pago savePago(MultipartFile file, double cantiad, TypePago type, LocalDate date, String codigoEstudiante)
            throws IOException {

        /**
         * Construir la ruta donde se uardara el archivo dtro del sistema
         * System.getProperty("user.home"): obtiene la ruta del directorio personal del
         * usuario del actual SO
         * Paths.get : construir una ruta dentro del directorio personal en la carpeta
         * "enset-data/pagos"
         * 
         */

        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");

        // Verificar si la carpeta ya existe si no la debe crear
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // generamos un nombre unico para el archivo usando UUID (identificador unico
        // universal)
        String fileName = UUID.randomUUID().toString();

        // Construimos la ruta completa del archivo agregando la extension .pdf
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");

        // Guardamos el archivo recibido en la ubicacion especificada dentro del sistema
        // de archivos
        Files.copy(file.getInputStream(), filePath);

        // Buscamos el estudiante que realiza el pago con su codigo
        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);

        // Creamos un nuevo objeto Pago utilizando el patron de diseño Builder

        Pago pago = Pago.builder()
                .type(type)
                .status(PagoStatus.CREADO) // ESTADO INICIAL DEL PAGO
                .fecha(date)
                .estudiante(estudiante)
                .cantidad(cantiad)
                .file(filePath.toUri().toString()) // ruta del archivo pdf almacenado
                .build(); // construccion final del objeto Pago

        return pagoRepository.save(pago);
    }

    public byte[] getArhivoPorId(Long pagoId) throws IOException {

        // busca un objeto Pago en la BD por su ID
        Pago pago = pagoRepository.findById(pagoId).get();

        /**
         * pago.getFile: obtiene la URI del archivo guardado como una cadena de texto
         * URI.create: convierte la cadena de texto en un objeto URI
         * pathOf: convierte la URI en un path para poder acceder al archivo
         * Files.readAllBytes: lee el contenido del archivo y lo va a devolver en un
         * Array vector de bytes
         * esto permite obtener el contenido del archivo para su posterior uso por
         * ejemplo descargarlo
         */

        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus(PagoStatus status, Long id) {

        // busca un objeto Pago en la BD por su ID
        Pago pago = pagoRepository.findById(id).get();

        // Actualiza el estado del pago
        pago.setStatus(status);

        // Guarda el objeto Pago actualizado en la BD y lo devuelve
        return pagoRepository.save(pago);

    }

}
