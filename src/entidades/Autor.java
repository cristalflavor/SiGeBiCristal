package entidades;

public class Autor {
    private int idAutor;
    private String apellido;
    private String nombre;
    private String generoFav;
    
    public Autor(){}

    public Autor(int idAutor, String apellido, String nombre, String generoFav) {
        this.idAutor = idAutor;
        this.apellido = apellido;
        this.nombre = nombre;
        this.generoFav = generoFav;
    }
    

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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

    public String getGeneroFav() {
        return generoFav;
    }
    public void setGeneroFav(String generoFav) {
        this.generoFav = generoFav;
    }
    
    
}
