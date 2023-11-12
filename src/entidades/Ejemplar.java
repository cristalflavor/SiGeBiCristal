package entidades;

public class Ejemplar {
    private int codigo;//Me parece
    private Libro libro;//que no va
    private String estado;
    
    private int idEjemplar;
    private long isbn;
    
    public Ejemplar(){}

    public Ejemplar(int codigo, Libro libro, String estado) {
        this.codigo = codigo;
        this.libro = libro;
        this.estado = estado;
    }
    

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    /* agregado */
    public long getIsbn(){
        return this.isbn;
    }
    public void setIsbn(long isbn){
        this.isbn = isbn;
    }
    
    public int getIdEjemplar(){
        return this.idEjemplar;
    }
    public void setIdEjemplar(int idEjemplar){
        this.idEjemplar = idEjemplar;
    }
    
    
    
}
