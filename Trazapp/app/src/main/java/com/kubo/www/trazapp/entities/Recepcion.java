package com.kubo.www.trazapp.entities;

public class Recepcion
{
    private String nombreEmpresa, matriculaCamion, conductorCamion, materialRecepcion, fechaRecepcion,
                   estadoRecepcion, inspeccionVisual, presentacion, loteProveedor, comentarios;
    private int idRecepcion, numeroSilo, tipoMaterial;
    private double 	cantidadRecepcion, densidad, pesoEspecifico;

    public int getIdRecepcion() { return idRecepcion; }

    public String getNombreEmpresa() { return nombreEmpresa; }

    public String getMatriculaCamion() { return matriculaCamion; }

    public String getConductorCamion() { return conductorCamion; }

    public double getCantidadRecepcion() { return cantidadRecepcion; }

    public String getMaterialRecepcion() { return materialRecepcion; }

    public int getTipoMaterial() { return tipoMaterial; }

    public int getNumeroSilo() { return numeroSilo; }

    public String getFechaRecepcion() { return fechaRecepcion; }

    public String getEstadoRecepcion() { return estadoRecepcion; }

    public String getInspeccionVisual() { return inspeccionVisual; }

    public String getPresentacion() { return presentacion; }

    public String getLoteProveedor() { return loteProveedor; }

    public double getDensidad() { return densidad; }

    public double getPesoEspecifico() { return pesoEspecifico; }

    public String getComentarios() { return comentarios; }

    public void setIdRecepcion(int idRecepcion) { this.idRecepcion = idRecepcion; }

    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public void setMatriculaCamion(String matriculaCamion) { this.matriculaCamion = matriculaCamion; }

    public void setConductorCamion(String conductorCamion) { this.conductorCamion = conductorCamion; }

    public void setCantidadRecepcion(double cantidadRecepcion) { this.cantidadRecepcion = cantidadRecepcion; }

    public void setMaterialRecepcion(String materialRecepcion) { this.materialRecepcion = materialRecepcion; }

    public void setTipoMaterial(int tipoMaterial) { this.tipoMaterial = tipoMaterial; }

    public void setNumeroSilo(int numeroSilo) { this.numeroSilo = numeroSilo; }

    public void setFechaRecepcion(String fechaRecepcion) { this.fechaRecepcion = fechaRecepcion; }

    public void setEstadoRecepcion(String estadoRecepcion) { this.estadoRecepcion = estadoRecepcion; }

    public void setInspeccionVisual(String inspeccionVisual) { this.inspeccionVisual = inspeccionVisual; }

    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public void setLoteProveedor(String loteProveedor) { this.loteProveedor = loteProveedor; }

    public void setDensidad(double densidad) { this.densidad = densidad; }

    public void setPesoEspecifico(double pesoEspecifico) { this.pesoEspecifico = pesoEspecifico; }

    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}
