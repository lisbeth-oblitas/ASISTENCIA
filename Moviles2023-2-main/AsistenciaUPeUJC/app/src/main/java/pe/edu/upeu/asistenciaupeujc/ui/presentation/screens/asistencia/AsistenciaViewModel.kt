package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Asistencia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.repository.AsistenciaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaViewModel @Inject constructor(
    private val activRepo: AsistenciaRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<Asistencia>> by lazy {
        activRepo.reportarAsistencias()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addAsistencia() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteActividad(toDelete: Asistencia) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            activRepo.deleteAsistencia(toDelete);
        }
    }

}