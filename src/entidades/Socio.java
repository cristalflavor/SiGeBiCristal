package entidades;

import java.time.LocalDate;

public class Socio {
    private int idSocio;
    private String apellido;
    private String nombre;
    private String domicilio;
    private int dni;
    private String telefono;
    private String mail;
    private LocalDate fechaDeAlta;
    private LocalDate fechaDeBaja;
    private String fotoPerfilNombre;
    private boolean estado;
    
    
    public Socio(){}
        
    public Socio(int idSocio, String apellido, String nombre, String domicilio, int dni, String telefono, String mail, LocalDate fechaDeAlta, LocalDate fechaDeBaja, String fotoPerfilNombre, boolean estado) {
        this.idSocio = idSocio;
        this.apellido = apellido;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.telefono = telefono;
        this.mail = mail;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = fechaDeBaja;
        this.fotoPerfilNombre = fotoPerfilNombre;
        this.estado = estado;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public int getDni(){
        return this.dni;
    }
    public void setDni(int dni){
        this.dni = dni;
    }
    
    public String getFotoPerfilNombre(){
        return this.fotoPerfilNombre;
    }
    public void setFotoPerfilNombre(String fotoPerfilNombre){
        this.fotoPerfilNombre = fotoPerfilNombre;
    }

    public int getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public LocalDate getFechaDeBaja() {
        return fechaDeBaja;
    }

    public void setFechaDeBaja(LocalDate fechaDeBaja) {
        this.fechaDeBaja = fechaDeBaja;
    }
    
    
    public void activarLector(Socio l){
        
    }
    
    public void desactivarLector(Socio l){
        
    }
    
}
