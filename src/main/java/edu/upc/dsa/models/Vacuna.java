package edu.upc.dsa.models;

public class Vacuna implements Comparable<Vacuna> {
    private String idVacuna;
    private int numUsos = 0;

    public void setIdVacuna(String idVacuna) {
        this.idVacuna = idVacuna;
    }

    public void setNumUsos(int numUsos) {
        this.numUsos = numUsos;
    }

    public String getIdVacuna() {
        return idVacuna;
    }

    public int getNumUsos() {
        return numUsos;
    }

    //Constructor
    public Vacuna (String idVacuna){
        setIdVacuna(idVacuna);
    }
    public Vacuna(){};

    /**Comparador según número de usos de cada marca de vacuna. */
    public int compareTo (Vacuna v){
        return (int) (this.getNumUsos() - v.getNumUsos());
    }


}
