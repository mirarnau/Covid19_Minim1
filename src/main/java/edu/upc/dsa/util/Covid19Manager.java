package edu.upc.dsa.util;

import edu.upc.dsa.models.Persona;
import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacunacion;

import java.util.List;

public interface Covid19Manager {
    void vacunar (Vacunacion vacunacion);
    int listarVacunaciones ();
    Vacuna[] listadoMarcasVacunas(); /**Lista las marcas ordenadas descendentemente por el número de vacunas aplicadas.*/
    void addSeguimiento (Seguimiento seguimiento);
    List<Seguimiento> getListaSeguimientos (String idPersona);
    void addListaVacunas (Vacuna[] listVacunasDisponibles);

    List<Vacunacion> getListaVacunaciones ();
    Persona findPersonById(String id);
    int findVacunaById(String idVacuna);




}
