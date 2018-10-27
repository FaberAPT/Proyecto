package com.kubo.www.trazapp.entities;

public class Componente
{
    private int idFormulaComponente, idFormula, ordenComponente, tipoComponente, estadoCompoenente;
    private String nombreComponente, dosificacionComponente;
    private float porcentajeComponente;

    public int getIdFormulaComponente() { return idFormulaComponente; }

    public int getIdFormula() { return idFormula; }

    public String getNombreComponente() { return nombreComponente; }

    public float getPorcentajeComponente() { return porcentajeComponente; }

    public int getOrdenComponente() { return ordenComponente; }

    public String getDosificacionComponente() { return dosificacionComponente; }

    public int getTipoComponente() { return tipoComponente; }

    public int getEstadoCompoenente() { return estadoCompoenente; }

    public void setIdFormulaComponente(int idFormulaComponente) { this.idFormulaComponente = idFormulaComponente; }

    public void setIdFormula(int idFormula) { this.idFormula = idFormula; }

    public void setNombreComponente(String nombreComponente) { this.nombreComponente = nombreComponente; }

    public void setPorcentajeComponente(float porcentajeComponente) { this.porcentajeComponente = porcentajeComponente; }

    public void setOrdenComponente(int ordenComponente) { this.ordenComponente = ordenComponente; }

    public void setDosificacionComponente(String dosificacionComponente) { this.dosificacionComponente = dosificacionComponente; }

    public void setTipoComponente(int tipoComponente) { this.tipoComponente = tipoComponente; }

    public void setEstadoCompoenente(int estadoCompoenente) { this.estadoCompoenente = estadoCompoenente; }
}
