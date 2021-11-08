package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String idPersona;
    private List<Seguimiento> listaSeguimientos = new ArrayList<Seguimiento>();

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public List<Seguimiento> getListaSeguimientos() {
        return listaSeguimientos;
    }

    /**Creamos constructor */
    public Persona (String idPersona){
        setIdPersona(idPersona);
    }
    public Persona(){};

    public void addSeguimiento (Seguimiento seguimiento){
        this.listaSeguimientos.add(seguimiento);
    }
}
