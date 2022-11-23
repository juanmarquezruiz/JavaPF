package proyectofinalpoo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Ciudadano {
  private int identificacion;
  private String Nombre;

    public Ciudadano() {
    }

    public Ciudadano(int identificacion, String Nombre) {
        this.identificacion = identificacion;
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    

    
}
