package com.pruebas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import com.pruebas.model.Inscripcion;
import com.pruebas.model.services.InscripcionService;
import com.pruebas.repository.InscripcionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {
    private static final String ALUMNO = "Jeremy Calderon Chavez";
    private static final String CODIGO = "I202500542";

    @Mock
    private InscripcionRepository repository;

    @InjectMocks
    private InscripcionService service;

    @BeforeAll
    static void identificarme() {
        System.out.println("Alumno: " + ALUMNO + " Código: " + CODIGO);
    }

    private Inscripcion crearInscripcion(Long id, String codigoEstudiante, Long cursoId) {
        Inscripcion i = new Inscripcion();
        i.setId(id);
        i.setCodigoEstudiante(codigoEstudiante);
        i.setCursoId(cursoId);
        return i;
    }
}
