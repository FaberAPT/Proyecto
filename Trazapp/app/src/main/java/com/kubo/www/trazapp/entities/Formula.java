package com.kubo.www.trazapp.entities;

public class Formula
{
    private String nombreFormula, fechaFormula, fechaModificacion;
    private int idFormula, tipoFormula, estadoFormula;

    public int getIdFormula() { return idFormula; }

    public String getNombreFormula() { return nombreFormula; }

    public String getFechaFormula() { return fechaFormula; }

    public String getFechaModificacion() { return fechaModificacion; }

    public int getTipoFormula() { return tipoFormula; }

    public int getEstadoFormula() { return estadoFormula; }

    public void setIdFormula(int idFormula) { this.idFormula = idFormula; }

    public void setNombreFormula(String nombreFormula) { this.nombreFormula = nombreFormula; }

    public void setFechaFormula(String fechaFormula) { this.fechaFormula = fechaFormula; }

    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public void setTipoFormula(int tipoFormula) { this.tipoFormula = tipoFormula; }

    public void setEstadoFormula(int estadoFormula) { this.estadoFormula = estadoFormula; }
}
