package com.kubo.www.trazapp.entities;

public class Formula
{
    private String nombreFormula, fechaFormula, fechaModificacion, tipoFormula, estadoFormula;
    private int idFormula;

    public int getIdFormula() { return idFormula; }

    public String getNombreFormula() { return nombreFormula; }

    public String getFechaFormula() { return fechaFormula; }

    public String getFechaModificacion() { return fechaModificacion; }

    public String getTipoFormula() { return tipoFormula; }

    public String getEstadoFormula() { return estadoFormula; }

    public void setIdFormula(int idFormula) { this.idFormula = idFormula; }

    public void setNombreFormula(String nombreFormula) { this.nombreFormula = nombreFormula; }

    public void setFechaFormula(String fechaFormula) { this.fechaFormula = fechaFormula; }

    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public void setTipoFormula(String tipoFormula) { this.tipoFormula = tipoFormula; }

    public void setEstadoFormula(String estadoFormula) { this.estadoFormula = estadoFormula; }
}
