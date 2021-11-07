package edu.upc.dsa.util;

import edu.upc.dsa.models.Persona;
import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacunacion;

import java.util.*;

public class Covid19ManagerImpl implements Covid19Manager {
    private static Covid19ManagerImpl instance;
    private HashMap<String, Persona> personasHashmap = new HashMap<String, Persona>(); /**Diccionario de personas.*/
    public List<Vacunacion> listaVacunaciones = new ArrayList<Vacunacion>();
    public Vacuna[] arrayVacunas;


    /**Singleton */
    private Covid19ManagerImpl() {
    }
    public static Covid19Manager getInstance(){
        if (instance==null) {
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }

    public void vacunar(String idPersona, String idVacuna, String fecha) {
        /** Añadimos a la nueva persona que se ha vacunado.*/
        Persona persona = new Persona(idPersona);
        this.personasHashmap.put(idPersona, persona);

        /**Incrementamos el contador de usos de esa marca de vacuna.*/
        for (Vacuna v : arrayVacunas) {
            if (v.getIdVacuna() == idVacuna) {
                v.setNumUsos(v.getNumUsos() + 1);
            }
        }

        /**Realizamos la vacunación, añadiéndola a la lista de vacunaciones.*/
        if (listaVacunaciones == null) {
            Vacunacion vacunacion = new Vacunacion(0, persona, idVacuna, fecha);
            listaVacunaciones.add(vacunacion);
        }
        else{
            Vacunacion vacunacion = new Vacunacion(listaVacunaciones.size(), persona, idVacuna, fecha);
            listaVacunaciones.add(vacunacion);
        }
    }
    /** Returns 1 if empty vaccination list, and 0 if OK  */
    public int listarVacunaciones (){
        if (this.listaVacunaciones.size() > 0) {
            List<Vacunacion> listaVacunacionesOrdenada = new ArrayList<Vacunacion>();
            /**Vamos añadiendo a la nueva lista las vacunaciones agrupadas por marca de vacuna, ordenadamente.*/
            for (Vacuna vacuna : arrayVacunas) {
                for (Vacunacion vacunacion : this.listaVacunaciones) {
                    /**Primero añadimos en otra lista las vacunaciones con esa vacuna especifica.*/
                    List<Vacunacion> listaVacunacionesOrdenadasFecha = new ArrayList<Vacunacion>();
                    if (vacunacion.getIdVacunaUsada() == vacuna.getIdVacuna()) {
                        listaVacunacionesOrdenadasFecha.add(vacunacion);
                    }
                    /**Después la ordenamos según fueron realizadas.*/
                    Collections.sort(listaVacunacionesOrdenadasFecha);
                    /**Y finalmente añadimos este tramo a la lista de vacunaciones modificada que sustituirá a la original.*/
                    for (Vacunacion v : listaVacunacionesOrdenadasFecha) {
                        listaVacunacionesOrdenada.add(v);
                    }
                }
            }
            /**Finalmente, esta lista reemplaza a la original, para que ésta esté ordenada como se pide..*/
            this.listaVacunaciones = listaVacunacionesOrdenada;
            return 0;
        }
        else{
            return 1;
        }
    }

    public Vacuna[] listadoMarcasVacunas (Vacuna[] arrayVacunas){
        Vacuna[] marcasOrdenadas = this.arrayVacunas;
        Arrays.sort(marcasOrdenadas);
        Collections.reverse(Arrays.asList(marcasOrdenadas));
        return  marcasOrdenadas;
    }

    public void addSeguimiento (Persona personaYaVacunada, String fechaSeguimiento, String DescripcionSeguimiento){
        try {
            this.personasHashmap.get(personaYaVacunada).addSeguimiento(fechaSeguimiento, DescripcionSeguimiento);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Seguimiento> getListaSeguimientos (String idPersona){
        return this.personasHashmap.get(idPersona).getListaSeguimientos();
    }

    public void addListaVacunas (Vacuna[] listaVacunasDisponibles){
        this.arrayVacunas = listaVacunasDisponibles;
    }

    public Persona findPersonById (String id){
        return personasHashmap.get(id);
    }

    public List<Vacunacion> getListaVacunaciones(){
        return this.listaVacunaciones;
    }

    /** Returns 0 if found, and 1 if not found */
    public int findVacunaById (String idVacuna){
        int i = 0;
        int found = 0;
        while ((i < this.arrayVacunas.length)&&(found == 0)){
            if (this.arrayVacunas[i].getIdVacuna() == idVacuna){
                found = 1;
            }
            i++;
        }
        if (found == 1){
            return 0;
        }
        else{
            return 1;
        }
    }
}
