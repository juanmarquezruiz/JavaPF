package proyectofinalpoo;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProyectofinalPoo {


    static int select = -1;
    static LinkedList<Subsidio> ListaSubsidio = new LinkedList();

 
    
    public static void main(String[] args) {
        
        CargarListaSubsidios();
        
        while (select != 5) {

            try {
                String Lectura = JOptionPane.showInputDialog(
                        null,
                         "Seleccione un opción:"
                        + "\n1. Registrar Subsidio"
                        + "\n2. Consultar Ciudadanos"
                        + "\n3. Consultar Subsidiados"
                        + "\n4. Buscar Subsidiados"
                        + "\n5. Salir"
                );

                select = Integer.parseInt(Lectura);

                switch (select) {

                    case 1:

                        JOptionPane.showMessageDialog(null, RegistraSubsidio());
                        break;

                    case 2:

                        JOptionPane.showMessageDialog(null, ConsultarCiudadanos());
                        break;

                    case 3:

                        JOptionPane.showMessageDialog(null, ConsultarSubsidiados());
                        break;

                    case 4:

                        JOptionPane.showMessageDialog(null, BuscarSubsidiados());
                        break;

                    case 5:

                        JOptionPane.showMessageDialog(null, "Gracias por usar el Software");
                        break;

                    default:

                        JOptionPane.showMessageDialog(null, "Opcion no reconocida");
                        break;

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Uoop! Error!, si intenta salir use opcion 5.");

            }
        }
    }

    public static String RegistraSubsidio() {
        try {
            String NombreSubsidiado;
            int IdentificacionSubsidiado;
            String Municipio;
            String Departamento;
            String Subsidio;
            NombreSubsidiado = JOptionPane.showInputDialog("Introduzca Nombre Completo");
            IdentificacionSubsidiado = Integer.parseInt(JOptionPane.showInputDialog("Introduzca su identificacion"));
            Municipio = JOptionPane.showInputDialog("Introduzca Municipio");
            Departamento = JOptionPane.showInputDialog("Introduzca Departamento");
            Subsidio = JOptionPane.showInputDialog("Introduzca Subsidio");
            
            String Result = ConexionMySQL.validarSubsidiado(IdentificacionSubsidiado);
            
            if (!"no existe".equals(Result)) {
                return Result;
            }
            
            Result = ConexionMySQL.insertarpersona(IdentificacionSubsidiado, NombreSubsidiado);
            if (!"Ok".equals(Result)) {
                return Result;
            }
            
            Result = ConexionMySQL.insertarsubsidio(IdentificacionSubsidiado, Municipio, Departamento, Subsidio);
            if (!"Ok".equals(Result)) {
                return Result;
            }
            
            return "Subsidio del ciudadano registrado, y termino aca";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String ConsultarCiudadanos() {
        return "Subsidio Registrado";
    }

    public static String ConsultarSubsidiados() {
        return "Subsidio Registrado";
    }

    public static String BuscarSubsidiados() {
        return "Subsidio Registrado";
    }
    
    public static void CargarListaSubsidios(){
            
        try {
            ListaSubsidio = ConexionMySQL.listarSubsidio();
        } catch (SQLException ex) {
            Logger.getLogger(ProyectofinalPoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
