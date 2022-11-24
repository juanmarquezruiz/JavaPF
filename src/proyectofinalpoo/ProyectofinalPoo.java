package proyectofinalpoo;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ProyectofinalPoo {

    static int select = -1;

    public static void main(String[] args) {
        while (select != 5) {
            try {
                String Lectura = JOptionPane.showInputDialog(null, """
                                                                   Seleccione un opci\u00f3n:
                                                                   1. Registrar Subsidio
                                                                   2. Consultar Ciudadanos
                                                                   3. Consultar Subsidiados
                                                                   4. Buscar Subsidiados
                                                                   5. Salir""");
                select = Integer.parseInt(Lectura);
                switch (select) {
                    case 1 -> JOptionPane.showMessageDialog(null, RegistraSubsidio());
                    case 2 -> JOptionPane.showMessageDialog(null, ConsultarCiudadanos());
                    case 3 -> JOptionPane.showMessageDialog(null, ConsultarSubsidiados());
                    case 4 -> JOptionPane.showMessageDialog(null, BuscarSubsidiado());
                    case 5 -> JOptionPane.showMessageDialog(null, "Gracias por usar el Software");
                    default -> JOptionPane.showMessageDialog(null, "Opcion no reconocida");
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Uoop! Error!, si intenta salir use opcion 5.");
            }
        }
    }
 
    public static String RegistraSubsidio() {
        try {
            LinkedList<Subsidio> CatSubsidios = CargarListaSubsidios();
            String[] ListaSubsidios = ArrayString(CatSubsidios);
            String NombreSubsidiado;
            long IdentificacionSubsidiado;
            String Municipio;
            String Departamento;
            String Subsidio;
            NombreSubsidiado = JOptionPane.showInputDialog(null,"Introduzca Nombre Completo");
            IdentificacionSubsidiado = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca su identificacion"));
            Municipio = JOptionPane.showInputDialog(null, "Introduzca Ciudad o Municipio");
            Departamento = JOptionPane.showInputDialog(null, "Introduzca Departamento");
            Subsidio = (String)JOptionPane.showInputDialog(null, "Seleccione Subsidio", "Subsidio", JOptionPane.QUESTION_MESSAGE, null, ListaSubsidios, ListaSubsidios[0]);
            
            String Result = ConexionMySQL.validarSubsidiado(IdentificacionSubsidiado);
            if (!"no existe".equals(Result)) {
                return Result;
            }

            Result = ConexionMySQL.insertarsubsidio(IdentificacionSubsidiado, Municipio, Departamento, Subsidio, NombreSubsidiado);
            
            if (!"Ok".equals(Result)) {
                return Result;
            }
            return "Subsidio del ciudadano registrado";
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            
            return "Opps! intente nuevamente. A ocurrido el siguiente error, " + e.getMessage();
        }
    }
    
       public static String[] ArrayString(LinkedList<Subsidio> list) {
        String[] OutPutArray = new String[list.size()];
        int i = 0;
        for (Subsidio sub : list){
           OutPutArray[i] = sub.getSubsidioNombre();
           i++;
       }
        return OutPutArray;
    }
    
    public static String ConsultarCiudadanos() {
        try {
            LinkedList<Subsidiado> ResultadoCiudadanos = ConexionMySQL.listarCiudadano();
            
            return GenerarLista(ResultadoCiudadanos);
           
        } catch (SQLException e) {
            return "Excepción:" + e.getMessage();
        }
    }
    
    public static String ConsultarSubsidiados() {
        try {
            
            LinkedList<Subsidiado> ResultadoSubsidiados = ConexionMySQL.listarSubsidiado();
                    
            return GenerarLista(ResultadoSubsidiados);
            
        } catch (SQLException e) {
            return "Excepción:" + e.getMessage();
        }
    }
    
    public static String BuscarSubsidiado() {
        try {
            long IdentificacionSubsidiado = Integer.parseInt(JOptionPane.showInputDialog("Introduzca su identificacion"));
            LinkedList<Subsidiado> ResultadoSubsidiados = ConexionMySQL.BuscarSubsidiado(IdentificacionSubsidiado);
            return GenerarLista(ResultadoSubsidiados);
        } catch (SQLException e) {
            System.out.println("Excepción:" + e);
        }
        return "";
    }
    
    public static LinkedList<Subsidio> CargarListaSubsidios() {
        try {
            return ConexionMySQL.CatSubsidio();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
  
    public static String GenerarLista(LinkedList<Subsidiado> lst) {
       
        Iterator it = lst.iterator();
        String Check = "\u2713";
        String noCheck = "X";
        String mark = "";
        String Subsidio = "";
         String ToString = "                               "+ Check + " = Tiene Subsidio, " + noCheck + " = Sin Subsidio, " +"\n" +"\n";
        while (it.hasNext()) {
            Subsidiado ObjSubsidiado = (Subsidiado) it.next();
            Subsidio = ObjSubsidiado.getSubsidio().trim();
            ToString += ("No subsidiado".equals(Subsidio) ? noCheck : Check) + " -- "
                    + ObjSubsidiado.getNo() + " NOM: " + ObjSubsidiado.getNombre() + " DOC: " + ObjSubsidiado.getIdentificacion()
                    + " DEP: " + ObjSubsidiado.getDepartamento() + " MUN: " + ObjSubsidiado.getMunicipio()
                    + " SUBSIDIO: " + ObjSubsidiado.getSubsidio() + "\n";
        }
        return ToString;
    }
}
