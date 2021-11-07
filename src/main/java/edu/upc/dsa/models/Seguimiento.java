package edu.upc.dsa.models;

public class Seguimiento {
    private String idPersona;
    private String fechaSeguimiento;
    private String descripcionSeguimiento;


    public void setFechaSeguimiento(String fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public void setDescripcionSeguimiento(String descripcionSeguimiento) {
        this.descripcionSeguimiento = descripcionSeguimiento;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public String getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public String getDescripcionSeguimiento() {
        return descripcionSeguimiento;
    }

    /** Constructor */
    public Seguimiento (String idPersona, String fechaSeguimiento, String descripcionSeguimiento){
        this.idPersona = idPersona;
        this.setFechaSeguimiento(fechaSeguimiento);
        this.setDescripcionSeguimiento(descripcionSeguimiento);
    }
}
