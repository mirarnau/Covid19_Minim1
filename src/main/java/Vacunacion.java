public class Vacunacion implements Comparable<Vacunacion>{
    private int idVacunacion;
    private Persona personaVacunada;
    private String idVacunaUsada;
    private String fechaVacunacion;

    public void setIdVacunacion(int idVacunacion) {
        this.idVacunacion = idVacunacion;
    }

    public void setPersonaVacunada(Persona personaVacunada) {
        this.personaVacunada = personaVacunada;
    }

    public void setIdVacunaUsada(String idVacunaUsada) {
        this.idVacunaUsada = idVacunaUsada;
    }

    public void setFechaVacunacion(String fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public int getIdVacunacion() {
        return idVacunacion;
    }

    public Persona getPersonaVacunada() {
        return personaVacunada;
    }

    public String getIdVacunaUsada() {
        return idVacunaUsada;
    }

    public String getFechaVacunacion() {
        return fechaVacunacion;
    }

    /**Constructor */
    public Vacunacion (int idVacunacion, Persona personaVacunada, String idVacunaUsada, String fechaVacunacion){
        setIdVacunacion(idVacunacion);
        setPersonaVacunada(personaVacunada);
        setIdVacunaUsada(idVacunaUsada);
        setFechaVacunacion(fechaVacunacion);
    }
    /**Comparador según orden de realización (mediante el ID de la vacunación). Ordena de menor a mayor. */
    public int compareTo (Vacunacion v){
        return (int) (this.getIdVacunacion() - v.getIdVacunacion());
    }
}
