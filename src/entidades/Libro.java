package entidades;

public class Libro {
    private long isbn;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;
    private String editorial;
    private boolean estado;
    private int cantEjemplares;
    
    public Libro(){}


    public Libro(long isbn, String titulo, String autor, int anio, String genero, String editorial, boolean estado, int cantEjemplares) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.editorial = editorial;
        this.estado = estado;
        this.cantEjemplares = cantEjemplares;
    }

 

    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    public int getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(int cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }
}
