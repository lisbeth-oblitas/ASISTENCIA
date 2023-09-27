package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia

@Dao
interface AsistenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistencia(asistencia: Asistencia)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistencias(asistencia: List<Asistencia>)

    @Update
    suspend fun modificarAsistencia(asistencia: Asistencia)

    @Delete
    suspend fun eliminarAsistencia(actividad: Asistencia)

    @Query("select * from asistencia")
    fun reportatAsistencia():LiveData<List<Asistencia>>

    @Query("select * from asistencia where id=:idx")
    fun buscarAsistencia(idx: Long):LiveData<Asistencia>

}