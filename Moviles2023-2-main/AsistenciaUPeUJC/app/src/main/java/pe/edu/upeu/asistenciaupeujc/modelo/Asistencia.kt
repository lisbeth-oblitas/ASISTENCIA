package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "asistencia")

data class Asistencia(
    @PrimaryKey(autoGenerate = true)

    var id: String,
    var evento: String,
    var fecha: String,
    var hora : String,
    var latituda: String,
    var longitud: String,
    var tipo: String,
    var calificacion: String,
    var entsal: String,
    var offlinex: String,







)