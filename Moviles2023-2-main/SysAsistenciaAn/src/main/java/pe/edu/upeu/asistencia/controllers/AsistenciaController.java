/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upeu.asistencia.models.Asistencia;
import pe.edu.upeu.asistencia.services.AsistenciaService;


/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService; // Cambio de AsistenciaController a AsistenciaService

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Asistencia>> listAsistencia() { // Cambio de listActividad a listAsistencia
        List<Asistencia> asistenciaList = asistenciaService.findAll(); // Cambio de AsistenciaService a asistenciaService
        return ResponseEntity.ok(asistenciaList);
    }

    @PostMapping("/crear")
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia data = asistenciaService.save(asistencia);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Asistencia asistencia = asistenciaService.getAsistenciaById(id);
        return ResponseEntity.ok(asistencia);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsistencia(@PathVariable Long id) {
        Asistencia asistencia = asistenciaService.getAsistenciaById(id);
        return ResponseEntity.ok(asistenciaService.delete(asistencia.getId()));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        Asistencia updatedAsistencia = asistenciaService.update(asistenciaDetails, id);
        return ResponseEntity.ok(updatedAsistencia);
    }
}
