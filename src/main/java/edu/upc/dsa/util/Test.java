package edu.upc.dsa.util;

import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacunacion;
import edu.upc.dsa.models.Persona;

import java.sql.SQLOutput;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /** Primero definimos las vacunas que queremos incluir */
        Vacuna v1 = new Vacuna("Pfizer");
        Vacuna v2 = new Vacuna("Moderna");
        Vacuna v3 = new Vacuna("Astra Zeneca");
        Vacuna[] vacunasDisponibles = new Vacuna[] {v1, v2, v3};
        Covid19ManagerImpl.getInstance().addListaVacunas(vacunasDisponibles);

        /** Aquí ya ejecutamos las operaciones.*/
        Covid19Manager manager = Covid19ManagerImpl.getInstance();

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
        Seguimiento seguimiento2 = new Seguimiento("Arnau", "10/10/2021", "Evolución OK");

        manager.addSeguimiento(seguimiento1);
        manager.addSeguimiento(seguimiento2);

        Vacuna [] vacunasOrdenadas = Covid19ManagerImpl.getInstance().listadoMarcasVacunas();
        for (Vacuna v : vacunasOrdenadas){
            System.out.println(v.getIdVacuna());
        }

        Covid19ManagerImpl.getInstance().listarVacunaciones();
        List<Vacunacion> listaVacunaciones = Covid19ManagerImpl.getInstance().getListaVacunaciones();
        for (Vacunacion v : listaVacunaciones){
            System.out.println("ID Vacunación: " + v.getIdVacunacion());
            System.out.println("Persona vacunada: " + v.getPersonaVacunada().getIdPersona());
            System.out.println("Vacuna usada: " + v.getIdVacunaUsada());
            System.out.println("Fecha: " + v.getFechaVacunacion());
            System.out.println("------------------");
        }

        for (Seguimiento s : persona1.getListaSeguimientos()){
            System.out.println("SEGUIMIENTO:");
            System.out.println("Persona:" + s.getIdPersona());
            System.out.println("Fecha: " + s.getFechaSeguimiento());
            System.out.println("Descripción: " + s.getDescripcionSeguimiento());
        }


    }
}
