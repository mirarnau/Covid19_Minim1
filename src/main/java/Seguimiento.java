public class Seguimiento {
    private String fechaSeguimiento;
    private String descripcionSeguimiento;


    public void setFechaSeguimiento(String fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public void setDescripcionSeguimiento(String descripcionSeguimiento) {
        this.descripcionSeguimiento = descripcionSeguimiento;
    }



    public String getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public String getDescripcionSeguimiento() {
        return descripcionSeguimiento;
    }

    /** Constructor */
    public Seguimiento (String fechaSeguimiento, String descripcionSeguimiento){
        this.setFechaSeguimiento(fechaSeguimiento);
        this.setDescripcionSeguimiento(descripcionSeguimiento);
    }
}
