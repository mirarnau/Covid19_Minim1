package edu.upc.dsa.services;

import edu.upc.dsa.models.Persona;
import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacunacion;

import edu.upc.dsa.util.Covid19Manager;
import edu.upc.dsa.util.Covid19ManagerImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

@Api(value = "Covid19", description = "Endpoint to Covid19 vaccination service.")
@Path("/Covid19")

public class Covid19Service {
    private Covid19Manager manager;
    private Vacuna[] vacunasDisponibles;

    public Covid19Service(){
        this.manager = Covid19ManagerImpl.getInstance();
        if (true){
            /** Primero definimos las vacunas que queremos incluir */
            Vacuna v1 = new Vacuna("Pfizer");
            Vacuna v2 = new Vacuna("Moderna");
            Vacuna v3 = new Vacuna("Astra Zeneca");
            this.vacunasDisponibles = new Vacuna[] {v1, v2, v3};
            manager.addListaVacunas(vacunasDisponibles);
        }
    }
    @Path("basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    //----------------------------------------------
    @POST
    @ApiOperation(value = "Vacunar a una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "ERROR, Vacuna no existente.")

    })

    @Path("/Vacunaciones/Vacunar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vacunar (String idPersona, String idVacuna, String fecha) {
        if (manager.findVacunaById(idVacuna) == 1){
            return Response.status(500).build();
        }
        else{
            this.manager.vacunar(idPersona, idVacuna, fecha);
            return Response.status(201).build();
        }
    }
    //----------------------------------------------

    @GET
    @ApiOperation(value = "Get lista de marcas de vacunas ordenadas.", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacuna.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Lista de vacunas no encontrada (primero hay que añadir las marcas de vacunas.)")
    })
    @Path("/Vacunaciones/Marcas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoMarcasVacunas(Vacuna[] arrayVacunas){
        Vacuna[] listaVacunasOrdenadas = this.manager.listadoMarcasVacunas(this.vacunasDisponibles);
        GenericEntity<Vacuna[]> entity = new GenericEntity<Vacuna[]>(listaVacunasOrdenadas){};

        if (listaVacunasOrdenadas.length > 0){
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(404).entity(entity).build();
        }
    }

    //----------------------------------------------

    @GET
    @ApiOperation(value = "Get lista de vacunaciones ordenadas.", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacunacion.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "ERRROR, no se han realizado vacunaciones todavía.")
    })
    @Path("/Vacunaciones/ListaVacunaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVacunaciones(){
        int res = this.manager.listarVacunaciones();
        List<Vacunacion> listaVacunacionesOrdenada = this.manager.getListaVacunaciones();
        GenericEntity<List<Vacunacion>> entity = new GenericEntity<List<Vacunacion>>(listaVacunacionesOrdenada){};
        if (res == 0){

            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(404).entity(entity).build();
        }
    }

    //----------------------------------------------

    @POST
    @ApiOperation(value = "Añadir un seguimiento a una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "ERROR, La persona todavía no está vacunada.")

    })

    @Path("/Seguimientos/addSeguimiento/{idPersona}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSeguimiento (@PathParam("idPersona") Persona personaYaVacunada, String fechaSeguimiento, String DescripcionSeguimiento) {
        /** Comprovamos que la persona realmente ya esté vacunada */
        if (this.manager.findPersonById(personaYaVacunada.getIdPersona()) != null){
            this.manager.addSeguimiento(personaYaVacunada, fechaSeguimiento, DescripcionSeguimiento);
            return Response.status(201).build();
        }
        else{
            return Response.status(500).build();
        }
    }
    //----------------------------------------------
    @GET
    @ApiOperation(value = "Get lista de seguimientos de una persona.", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacunacion.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "ERRROR, no ae han realizado seguimientos sobre esa persona todavía")
    })
    @Path("/Seguimientos/getSeguimiento/{idPersona}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaSeguimientos(@PathParam("idPersona") String idPersona){
        /** Comprovamos si la persona tiene seguimientos. */
        List<Seguimiento> listaSeguimientos = this.manager.getListaSeguimientos(idPersona);
        GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(listaSeguimientos){};
        if (listaSeguimientos != null){
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(404).entity(entity).build();
        }
    }

}

















