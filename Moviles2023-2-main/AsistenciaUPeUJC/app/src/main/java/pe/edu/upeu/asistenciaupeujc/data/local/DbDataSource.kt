package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.AsistenciaDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.modelo.Materialesx

@Database(entities = [Asistencia::class, Materialesx::class], version = 2)
abstract class DbDataSource:RoomDatabase() {
    abstract fun asistenciaDao():Asistencia

    abstract fun asistenciaDao(): AsistenciaDao
}