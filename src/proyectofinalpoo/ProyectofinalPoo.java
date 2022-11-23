package proyectofinalpoo;

import java.sql.SQLException;
import java.util.Iterator;
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

        try {
            ConexionMySQL.abrirConexion();
            //System.out.println("Se conectó a la base de datos");
            LinkedList<Ciudadano> res = ConexionMySQL.listarCiudadano();
            

            ConexionMySQL.cerrarConexion();
            imprimirCiudadano(res);
            
            //System.out.println("Se desconectó de la base de datos");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Excepción:" + e);
        }
        return "";
    }

       public static String ConsultarSubsidiados() {    
        try {
           
            //System.out.println("Se conectó a la base de datos");
            LinkedList<Subsidiado> ResultadoSubsidiados = ConexionMySQL.listarSubsidiado();
            
            return listarSubsidiado(ResultadoSubsidiados);
           
        } catch (SQLException e) {
           
            return "Excepción:" + e.getMessage();
        
        }
     
    } 
    
 
    public static String BuscarSubsidiados() {    
 try {
            ConexionMySQL.abrirConexion();
            //System.out.println("Se conectó a la base de datos");
            LinkedList<Subsidiado> res = ConexionMySQL.listarSubsidiado();
            ConexionMySQL.buscarSubsidio(res);
            
            ConexionMySQL.cerrarConexion();
            //System.out.println("Se desconectó de la base de datos");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Excepción:" + e);
        }
        return "";
    }
    
    
    

    public static void CargarListaSubsidios() {

        try {
            ListaSubsidio = ConexionMySQL.listarSubsidio();
        } catch (SQLException ex) {
            Logger.getLogger(ProyectofinalPoo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void imprimirCiudadano(LinkedList<Ciudadano> lst) {
        StringBuilder salida = new StringBuilder();
        Iterator it = lst.iterator();
        while (it.hasNext()) {
            
            Ciudadano ObjCiudadano = (Ciudadano) it.next();
            String res = "Identificacion: " + ObjCiudadano.getIdentificacion()
                    + "Nombre: " + ObjCiudadano.getNombre()
                    + "\n";
            salida.append(res);
        }
        System.out.println(salida);
    }
    
    
    
     public static String listarSubsidiado(LinkedList<Subsidiado> lst) {
        String ToString =  "";
         
        Iterator it = lst.iterator();
        
        while (it.hasNext()) {
            
            Subsidiado ObjSubsidiado = (Subsidiado) it.next();
            ToString += ObjSubsidiado.getNo() + " Nombre: " + ObjSubsidiado.getNombre() + " Identificacion: " + ObjSubsidiado.getIdentificacion() 
                    + " Departamento: " + ObjSubsidiado.getDepartamento() + " Municipio: " + ObjSubsidiado.getMunicipio() 
                    + " Subsidio:" + ObjSubsidiado.getSubsidio() + "\n";
            
        }
       return ToString;
    }
}
