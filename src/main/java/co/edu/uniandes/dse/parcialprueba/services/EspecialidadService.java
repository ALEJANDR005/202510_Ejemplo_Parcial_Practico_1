package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalArgumentException {
        log.info("Creating a new especialidad");       
        
        if (especialidad.getDescripcion().length() < 10) {
            throw new IllegalArgumentException("La descripción de la especialidad debe tener al menos 10 caracteres");
        }
        return especialidadRepository.save(especialidad);
    }

}
