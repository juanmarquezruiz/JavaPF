package proyectofinalpoo;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProyectofinalPoo {

    static int select = -1;

    public static void main(String[] args) {
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
            return "Subsidio del ciudadano registrado, y termino aca";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String ConsultarCiudadanos() {
 
 try {
            ConexionMySQL.abrirConexion();
            //System.out.println("Se conectó a la base de datos");
            LinkedList<Ciudadano> res = ConexionMySQL.listarCiudadano();
            ConexionMySQL.imprimirCiudadano(res);
            
            ConexionMySQL.cerrarConexion();
            //System.out.println("Se desconectó de la base de datos");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Excepción:" + e);
        }
        return "";
    } 

    public static String ConsultarSubsidiados() {
        return "Subsidio Registrado";
    }

    public static String BuscarSubsidiados() {
        return "Subsidio Registrado";
    }
}
