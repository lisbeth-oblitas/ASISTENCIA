package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Asistencia
import pe.edu.upeu.asistenciaupeujc.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAsistencia {
    @GET("/asis/asistencia/list")
    suspend fun reportarAsistencia(@Header("Authorization") token:String):Response<List<Asistencia>>

    @GET("/asis/asistencia/buscar/{id}")
    suspend fun getAsistenciaId(@Header("Authorization") token:String, @Query("id") id:Long):Response<Asistencia>

    @DELETE("/asis/asistencia/eliminar/{id}")
    suspend fun deleteAsistencia(@Header("Authorization") token:String, @Path("id") id:Long):Response<MsgGeneric>

    @PUT("/asis/asistencia/editar/{id}")
    suspend fun actualizarAsistencia(@Header("Authorization") token:String, @Path("id") id:Long, @Body asistencia: Asistencia): Response<Asistencia>

    @POST("/asis/asistencia/crear")
    suspend fun insertarAsistencia(@Header("Authorization") token:String,@Body asistencia: Asistencia): Response<Asistencia>
}