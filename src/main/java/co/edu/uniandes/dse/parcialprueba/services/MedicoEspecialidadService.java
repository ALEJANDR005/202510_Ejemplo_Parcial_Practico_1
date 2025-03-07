package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional; 

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public MedicoEntity addEspecialidadToMedico(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        log.info("Asociar especialidad a médico");

        Optional<MedicoEntity> medico = medicoRepository.findById(medicoId);
        Optional<EspecialidadEntity> especialidad = especialidadRepository.findById(especialidadId);

        if (medico.isEmpty() || especialidad.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el médico o la especialidad");
        }
        
        medico.get().getEspecialidades().add(especialidad.get()); 
        return medicoRepository.save(medico.get());
    }
}
