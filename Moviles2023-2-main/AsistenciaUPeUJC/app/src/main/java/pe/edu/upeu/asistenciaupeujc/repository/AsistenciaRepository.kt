package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.data.local.dao.AsistenciaDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestAsistencia
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject

interface AsistenciaRepository {
    suspend fun deleteAsistencia(asistencia: Asistencia)
    fun reportarAsistencias(): LiveData<List<Asistencia>>

    fun buscarAsistenciaId(id: Long): LiveData<Asistencia>

    suspend fun insertarAsistencia(asistencia: Asistencia): Boolean

    suspend fun modificarRemoteAsistencia(asistencia: Asistencia): Boolean
}

class AsistenciaRepositoryImp @Inject constructor(
    private val restAsistencia: RestAsistencia,
    private val asistenciaDao: AsistenciaDao
) : AsistenciaRepository {
    override suspend fun deleteAsistencia(asistencia: Asistencia) {
        CoroutineScope(Dispatchers.IO).launch {
            restAsistencia.deleteAsistencia(TokenUtils.TOKEN_CONTENT, asistencia.id)
        }
        asistenciaDao.eliminarAsistencia(asistencia)
    }

    override fun reportarAsistencias(): LiveData<List<Asistencia>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                val data = restAsistencia.reportarAsistencia(TokenUtils.TOKEN_CONTENT).body()!!
                asistenciaDao.insertarAsistencias(data)
            }
        } catch (e: Exception) {
            Log.i("ERROR", "Error: ${e.message}")
        }
        return asistenciaDao.reportarAsistencia()
    }

    override fun buscarAsistenciaId(id: Long): LiveData<Asistencia> {
        return asistenciaDao.buscarAsistencia(id)
    }

    override suspend fun insertarAsistencia(asistencia: Asistencia): Boolean {
        return restAsistencia.insertarAsistencia(TokenUtils.TOKEN_CONTENT, asistencia).body() != null
    }

    override suspend fun modificarRemoteAsistencia(asistencia: Asistencia): Boolean {
        var dd: Boolean = false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restAsistencia.actualizarAsistencia(TokenUtils.TOKEN_CONTENT, asistencia.id, asistencia).body() != null
    }
}
