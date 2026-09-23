package com.example.progevaluacionventana.models;

import java.util.Date;

public class Cliente {
    private String nombre;
    private String apellido;
    private String tipoCliente;
    private String cuidad;
    private Date fechaNacimiento;
    private String TipoSolicitud;
    private String ServiciosInteres;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String tipoCliente, String cuidad, Date fechaNacimiento, String tipoSolicitud, String serviciosInteres) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCliente = tipoCliente;
        this.cuidad = cuidad;
        this.fechaNacimiento = fechaNacimiento;
        TipoSolicitud = tipoSolicitud;
        ServiciosInteres = serviciosInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSolicitud() {
        return TipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        TipoSolicitud = tipoSolicitud;
    }

    public String getServiciosInteres() {
        return ServiciosInteres;
    }

    public void setServiciosInteres(String serviciosInteres) {
        ServiciosInteres = serviciosInteres;
    }
}
