package proyectofinalpoo;


public class Subsidio {
    private int Id;
    private String subsidioNombre;

    public int getId() {
        return Id;
    }

    public String getSubsidioNombre() {
        return subsidioNombre;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setSubsidioNombre(String SubsidioNombre) {
        this.subsidioNombre = SubsidioNombre;
    }

    public Subsidio(int Id, String SubsidioNombre) {
        this.Id = Id;
        this.subsidioNombre = SubsidioNombre;
    }

    

}
