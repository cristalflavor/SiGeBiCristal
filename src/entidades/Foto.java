package entidades;

import java.io.File;
import java.io.FileInputStream;

public class Foto {
    
    private File file;
    private FileInputStream fis;
    private String fotoPerfilNombre;
    private String idSocio;
    
    public Foto(File file, FileInputStream fis, String fotoPerfilNombre, String idSocio){
        this.file = file;
        this.fis = fis;
        this.fotoPerfilNombre = fotoPerfilNombre;
        this.idSocio = idSocio;
    }
    
    public Foto(File file, FileInputStream fis, String idSocio){
        this.file = file;
        this.fis = fis;
        this.idSocio = idSocio;
    }
    
    public Foto(){}
    
    
    public String getFotoPerfilNombre(){
        return this.fotoPerfilNombre;
    }
    public void setFotoPerfilNombre(String fotoPerfilNombre){
        this.fotoPerfilNombre = fotoPerfilNombre;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public FileInputStream getFis() {
        return fis;
    }
    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public String getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }
}
