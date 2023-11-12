package entidades;

import java.time.LocalDate;
import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Ejemplar ejemplar;
    private Socio lector;
    private boolean estado;
    
    public Prestamo(){};

    public Prestamo(int idPrestamo, LocalDate fechaInicio, LocalDate fechaFin, Ejemplar ejemplar, Socio lector, boolean estado) {
        this.idPrestamo = idPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ejemplar = ejemplar;
        this.lector = lector;
        this.estado = estado;
    }

    public Prestamo(LocalDate fechaInicio, LocalDate fechaFin, Ejemplar ejemplar, Socio lector, boolean estado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ejemplar = ejemplar;
        this.lector = lector;
        this.estado = estado;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }
    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Socio getLector() {
        return lector;
    }
    public void setLector(Socio lector) {
        this.lector = lector;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public void solicitarLibro(Ejemplar e, Socio l){
        
    }
    
    public void devolverLibro(Ejemplar e, Socio l){
        
    }
}
