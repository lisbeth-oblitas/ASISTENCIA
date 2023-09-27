package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.Asistencia

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBoxTwo
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils

@Composable
fun AsistenciaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AsistenciaFormViewModel= hiltViewModel()
) {
    val AsistenciaD:Asistencia
    if (text!="0"){
        AsistenciaD = Gson().fromJson(text, Asistencia::class.java)
    }else{
        AsistenciaD= Asistencia("","", "","","","","","","","",)
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(AsistenciaD.id!!,
        darkMode,
        navController,
        AsistenciaD,
        viewModel
    )

}

private operator fun Any.contains(id: String): Boolean {

}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               Asistencia:Asistencia,
               viewModel: AsistenciaFormViewModel){

    Log.i("VERRR", "d: "+Asistencia?.id!!)
    val person=Asistencia(,"","", "","","","","","","","", "", "", "", "")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latituda=lo.latitude.toString()
                person.longitud=lo.longitude.toString()
            }
        }
    }
    scope.launch{
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        Log.e("LATLON", "Lat: ${person.latituda} Lon: ${person.longitud}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text =Asistencia?.id!!,"Nomb. Asistencia:", MyFormKeys.NAME )
                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Evento:", Asistencia?.evento!!, listE)

                var listEv = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                ComboBoxTwo(easyForm = easyForm, "Calificacion:", Asistencia?.calificacion!!, listEv)


                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = Asistencia?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = Asistencia?.hora!!, MyFormKeys.TIME, "HH:mm:ss")
                TimePickerCustom(easyForm = easyForm, label = "Tipo", texts = Asistencia?.tipo!!, MyFormKeys.TIME_TOLER,"HH:mm:ss")
                NameTextField(easyForms = easyForm, text = Asistencia?.evento!!, "Materiales:", MyFormKeys.MATERIALES )
                //DropdownMenuCustom(easyForm = easyForm, label = "Validar Inscripcion:", Asistencia.validInsc, list =listEv, MyFormKeys.VALIDINSCRIP )
                //DropdownMenuCustom(easyForm = easyForm, label = "Validar Asis. SubAsistencia:", Asistencia.asisSubact, list =listEv, MyFormKeys.ASISSUBACT )
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Entrada y Salida:", Asistencia.entsal, list =listEv, MyFormKeys.ENTSAL )
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Offline:", Asistencia.offlinex, list =listEv, MyFormKeys.OFFLINE )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.id=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.evento=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.fecha=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.hora=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.latituda=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        person.longitud=(lista.get(6) as EasyFormsResult.StringResult).value
                        person.tipo= splitCadena((lista.get(7) as EasyFormsResult.GenericStateResult<String>).value)
                        person.calificacion=(lista.get(6) as EasyFormsResult.StringResult).value
                        person.entsal= splitCadena((lista.get(9) as EasyFormsResult.GenericStateResult<String>).value)
                        person.offlinex= splitCadena((lista.get(10) as EasyFormsResult.GenericStateResult<String>).value)


                        if (id==0.toLong()){
                            Log.i("AGREGAR", "M:"+ person.id)
                            Log.i("AGREGAR", "VI:"+ person.evento)
                            Log.i("AGREGAR", "SA:"+ person.calificacion)
                            Log.i("AGREGAR", "ES:"+ person.entsal)
                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            viewModel.addAsistencia(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editAsistencia(person)
                        }
                        navController.navigate(Destinations.AsistenciaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.AsistenciaUI.route)
                    }
                }
            }
        }
    }
}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}