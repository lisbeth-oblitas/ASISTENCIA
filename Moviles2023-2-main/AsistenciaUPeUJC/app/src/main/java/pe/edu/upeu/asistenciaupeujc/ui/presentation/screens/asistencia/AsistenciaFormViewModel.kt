package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Asistencia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.repository.AsistenciaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaFormViewModel @Inject constructor(
    private val activRepo: AsistenciaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getAsistencia(idX: Long): LiveData<Asistencia> {
        return activRepo.buscarAsistenciaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addAsistencia(Asistencia: Asistencia){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", Asistencia.toString())
            activRepo.insertarAsistencia(Asistencia)
        }
    }
    fun editAsistencia(Asistencia: Asistencia){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteAsistencia(Asistencia)
        }
    }
}