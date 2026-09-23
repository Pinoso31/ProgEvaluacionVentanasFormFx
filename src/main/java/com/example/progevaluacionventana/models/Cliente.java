package com.example.progevaluacionventana.models;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String tipoCliente;
    private String cuidad;
    private Date fechaNacimiento;
    private String TipoSolicitud;
    private String ServiciosInteres;
    private String rutaFoto;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String tipoCliente,
                   String cuidad, Date fechaNacimiento,
                   String tipoSolicitud, String serviciosInteres) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCliente = tipoCliente;
        this.cuidad = cuidad;
        this.fechaNacimiento = fechaNacimiento;
        this.TipoSolicitud = tipoSolicitud;
        this.ServiciosInteres = serviciosInteres;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getCuidad() { return cuidad; }
    public void setCuidad(String cuidad) { this.cuidad = cuidad; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSolicitud() { return TipoSolicitud; }
    public void setTipoSolicitud(String tipoSolicitud) {
        this.TipoSolicitud = tipoSolicitud;
    }

    public String getServiciosInteres() { return ServiciosInteres; }
    public void setServiciosInteres(String serviciosInteres) {
        this.ServiciosInteres = serviciosInteres;
    }

    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
}