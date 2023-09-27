import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistencia")

data class Asistencia(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: Long,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String,
    var actividadId: String,
)



data class AsistenciaReport(
    var id: Long,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: Long,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String,
    var actividadId: String,


    //var matriculaxs: List<Matriculaxs>,
    //var eventoxs: List<Eventoxs>,
)