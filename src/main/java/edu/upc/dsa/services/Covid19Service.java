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
import io.swagger.models.auth.In;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/Covid19", description = "Endpoint to Covid19 vaccination service.")
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

            Persona persona1 = new Persona("Arnau");
            Persona persona2 = new Persona("Bianca");
            Persona persona3 = new Persona("Montse");
            Persona persona4 = new Persona("Jordi");
            Persona persona5 = new Persona("Josep");

            Vacunacion vacunacion0 = new Vacunacion(0, persona1, "Moderna", "15/07/2021");
            Vacunacion vacunacion1 = new Vacunacion(1, persona2, "Moderna", "15/07/2021");
            Vacunacion vacunacion2 = new Vacunacion(2, persona3, "Astra Zeneca", "15/07/2021");
            Vacunacion vacunacion3 = new Vacunacion(3, persona4, "Moderna", "15/07/2021");
            Vacunacion vacunacion4 = new Vacunacion(4, persona5, "Pfizer", "15/07/2021");

            manager.vacunar(vacunacion0);
            manager.vacunar(vacunacion1);
            manager.vacunar(vacunacion2);
            manager.vacunar(vacunacion3);
            manager.vacunar(vacunacion4);

            Seguimiento seguimiento1 = new Seguimiento("Arnau", "5/10/2021", "Evolución OK");
            Seguimiento seguimiento2 = new Seguimiento("Arnau", "10/10/2021", "Evolución PERF");

            manager.addSeguimiento(seguimiento1);
            manager.addSeguimiento(seguimiento2);
        }
    }

    //----------------------------------------------

    @GET
    @ApiOperation(value = "Prova 2.", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message= "No seguimientos - no funcional en aquesta prova.)")
    })
    @Path("test2/{idPersona}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testResponse(@PathParam("idPersona") String idPersona) {
        Integer length = this.manager.getListaSeguimientos(idPersona).size();
        return Response.status(201).entity(length).build();

    }

    //----------------------------------------------

    @Path("test/{idPersona}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int test(@PathParam("idPersona") String idPersona) {
        return manager.getListaSeguimientos(idPersona).size();
    }

    //----------------------------------------------

    @Path("basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "OK!";
    }

    //----------------------------------------------

    @POST
    @ApiOperation(value = "Vacunar a una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 400, message = "ERROR, Vacuna no existente.")

    })

    @Path("/vacunaciones/vacunar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vacunar (Vacunacion vacunacion) {
        System.out.println(vacunacion.getIdVacunaUsada());
        if (manager.findVacunaById(vacunacion.getIdVacunaUsada()) == 1){
            return Response.status(400).build();
        }
        else{
            this.manager.vacunar(vacunacion);
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
    @Path("/vacunaciones/marcas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoMarcasVacunas(){
        Vacuna[] listaVacunasOrdenadas = this.manager.listadoMarcasVacunas();
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
    @Path("/vacunaciones/vacunaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVacunaciones(){
        int res = this.manager.listarVacunaciones();
        List<Vacunacion> listaVacunacionesOrdenada = this.manager.getListaVacunaciones();
        GenericEntity<List<Vacunacion>> entity = new GenericEntity<List<Vacunacion>>(listaVacunacionesOrdenada){};
        if (listaVacunacionesOrdenada.size() > 0){

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

    @Path("/Seguimientos/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSeguimiento (Seguimiento seguimiento) {
        /** Comprovamos que la persona realmente ya esté vacunada */
        if (this.manager.findPersonById(seguimiento.getIdPersona()) != null){
            this.manager.addSeguimiento(seguimiento);
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
            @ApiResponse(code = 201, message = "Successful", response = Seguimiento.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "ERRROR, no se han realizado seguimientos sobre esa persona todavía")
    })
    @Path("/Seguimientos/get/{id_persona}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaSeguimientos(@PathParam("id_persona") String idPersona){
        /** Comprovamos si la persona tiene seguimientos. */
        System.out.println(idPersona);
        List<Seguimiento> listaSeguimientos = this.manager.getListaSeguimientos(idPersona);
        GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(listaSeguimientos){};
        if (listaSeguimientos.size() > 0){
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(404).entity(entity).build();
        }
    }

}

















