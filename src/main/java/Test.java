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
        Covid19ManagerImpl.getInstance().vacunar("Arnau", "Pfizer", "15/07/2021");
        Covid19ManagerImpl.getInstance().vacunar("Bianca", "Moderna", "18/07/2021");
        Covid19ManagerImpl.getInstance().vacunar("Montse", "Pfizer", "30/07/2021");
        Covid19ManagerImpl.getInstance().vacunar("Jordi", "Moderna", "3/08/2021");
        Covid19ManagerImpl.getInstance().vacunar("Josep", "Astra Zeneca", "10/08/2021");

        Vacuna [] vacunasOrdenadas = Covid19ManagerImpl.getInstance().listadoMarcasVacunas(vacunasDisponibles);
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



    }
}
