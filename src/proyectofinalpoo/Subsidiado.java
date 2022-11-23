package proyectofinalpoo;
public class Subsidiado {
 private int no;
 private int identificacion; 
 private String Municipio; 
 private String Departamento; 
 private String Subsidio;
 private String Nombre;

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public Subsidiado() {
    }

    public Subsidiado(int no, int identificacion, String Municipio, String Departamento, String Subsidio, String Nombre) {
        this.no = no;
        this.identificacion = identificacion;
        this.Municipio = Municipio;
        this.Departamento = Departamento;
        this.Subsidio = Subsidio;
        this.Nombre = Nombre;
    }

    public String getSubsidio() {
        return Subsidio;
    }

    public void setSubsidio(String Subsidio) {
        this.Subsidio = Subsidio;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }
    
}
