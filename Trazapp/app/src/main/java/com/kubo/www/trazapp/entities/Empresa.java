package com.kubo.www.trazapp.entities;

public class Empresa
{
    private String cifEmpresa, nombreEmpresa, direccionEmpresa, codigoPostal, provinciaEmpresa;
    private int silosPrincipales, silosCorrectores, silosLiquidos, silosAlmacen, silosExpedicion;

    public String getCifEmpresa() { return cifEmpresa; }

    public String getNombreEmpresa() { return nombreEmpresa; }

    public String getDireccionEmpresa() { return direccionEmpresa; }

    public String getCodigoPostal() { return codigoPostal; }

    public String getProvinciaEmpresa() { return provinciaEmpresa; }

    public int getSilosLiquidos() { return silosLiquidos; }

    public int getSilosAlmacen() { return silosAlmacen; }

    public int getSilosExpedicion() { return silosExpedicion; }

    public int getSilosCorrectores() { return silosCorrectores; }

    public int getSilosPrincipales() { return silosPrincipales; }

    public void setCifEmpresa(String cifEmpresa) { this.cifEmpresa = cifEmpresa; }

    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public void setDireccionEmpresa(String direccionEmpresa) { this.direccionEmpresa = direccionEmpresa; }

    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public void setProvinciaEmpresa(String provinciaEmpresa) { this.provinciaEmpresa = provinciaEmpresa; }

    public void setSilosPrincipales(int silosPrincipales) { this.silosPrincipales = silosPrincipales; }

    public void setSilosCorrectores(int silosCorrectores) { this.silosCorrectores = silosCorrectores; }

    public void setSilosLiquidos(int silosLiquidos) { this.silosLiquidos = silosLiquidos; }

    public void setSilosAlmacen(int silosAlmacen) { this.silosAlmacen = silosAlmacen; }

    public void setSilosExpedicion(int silosExpedicion) { this.silosExpedicion = silosExpedicion; }
}
