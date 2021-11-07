package edu.upc.dsa.util;

import edu.upc.dsa.models.Persona;
import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacunacion;

import java.util.List;

public interface Covid19Manager {
    void vacunar (String idPersona, String idVacuna, String fecha);
    int listarVacunaciones ();
    Vacuna[] listadoMarcasVacunas(Vacuna[] arrayVacunas); /**Lista las marcas ordenadas descendentemente por el número de vacunas aplicadas.*/
    void addSeguimiento (Persona personaYaVacunada, String fechaSeguimiento, String DescripcionSeguimiento);
    List<Seguimiento> getListaSeguimientos (String idPersona);
    void addListaVacunas (Vacuna[] listVacunasDisponibles);

    List<Vacunacion> getListaVacunaciones ();
    Persona findPersonById(String id);
    int findVacunaById(String idVacuna);




}
