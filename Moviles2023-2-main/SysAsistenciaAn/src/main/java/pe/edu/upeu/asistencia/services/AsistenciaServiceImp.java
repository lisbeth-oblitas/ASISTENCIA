/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Asistencia; 
import pe.edu.upeu.asistencia.repositories.AsistenciaRepository; 
import pe.edu.upeu.asistencia.services.AsistenciaService;


@RequiredArgsConstructor
@Service
@Transactional
public class AsistenciaServiceImp implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepo;

    @Override
    public Asistencia save(Asistencia asistencia) { // Cambio de Actividad a Asistencia
        try {
            return asistenciaRepo.save(asistencia);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Asistencia> findAll() { // Cambio de Actividad a Asistencia
        try {
            return asistenciaRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Asistencia asistencia = asistenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not exist with id: " + id)); // Cambio de Actividad a Asistencia

        asistenciaRepo.delete(asistencia);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Asistencia getAsistenciaById(Long id) {
        Asistencia asistencia = asistenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not found with id: " + id));
        return asistencia;
    }

    @Override
    public Asistencia update(Asistencia asistencia, Long id) { // Cambio de Actividad a Asistencia
        Asistencia existingAsistencia = asistenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not found with id: " + id));
        
        // Actualizar los campos de asistencia con los valores proporcionados en la solicitud.
        existingAsistencia.setFecha(asistencia.getFecha());
        existingAsistencia.setHoraReg(asistencia.getHoraReg());
        existingAsistencia.setCalificacion(asistencia.getCalificacion());

        // Guardar la asistencia actualizada.
        return asistenciaRepo.save(existingAsistencia);
    }
}
