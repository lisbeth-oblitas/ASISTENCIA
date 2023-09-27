/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;

import pe.edu.upeu.asistencia.models.Asistencia;

/**
 *
 * @author DELL
 */
public interface AsistenciaService {
     Asistencia save(Asistencia activiad);

    List<Asistencia> findAll();

    Map<String, Boolean> delete(Long id);

    Asistencia getAsistenciaById(Long id);

    Asistencia update(Asistencia asistencia, Long id);   
    
}