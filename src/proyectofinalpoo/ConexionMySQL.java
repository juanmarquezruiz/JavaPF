package proyectofinalpoo;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMySQL {
    private static Connection con = null;
    private static Statement st = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://u3izbkwgfact1l0c:1DwgeE2VixHUgIuVAY8O@bt5vtvpi1cmv4s5i4co8-mysql.services.clever-cloud.com:3306/bt5vtvpi1cmv4s5i4co8";
    private static final String URL = "jdbc:mysql://54.39.75.7:3306/bt5vtvpi1cmv4s5i4co8";

    public static void abrirConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, "u3izbkwgfact1l0c", "1DwgeE2VixHUgIuVAY8O");
        st = con.createStatement();
    }

    public static void cerrarConexion() throws SQLException {
        con.close();
        st.close();
        con = null;
        st = null;
    }

    public static LinkedList<Subsidiado> listarSubsidiado() throws SQLException {
            ResultSet rs;  
            String consulta;
            LinkedList<Subsidiado> lst = new LinkedList();         
        try {
            abrirConexion();
            consulta = "SELECT * FROM subsidiado "
                     + "where Subsidio <> 'No subsidiado';";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                int no = rs.getInt("NO");
                int identificacion = rs.getInt("Identificacion");
                String Municipio = rs.getString("Municipio");
                String Departamento = rs.getString("Departamento");
                String Subsidio = rs.getString("Subsidio");
                String Nombre = rs.getString("Nombre"); 
                Subsidiado p = new Subsidiado(no, identificacion, Municipio, Departamento, Subsidio, Nombre);
                lst.add(p); 
            }
            rs.close();
            cerrarConexion();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lst;
    }
    
    public static LinkedList<Subsidiado> BuscarSubsidiado(long Identificacion) throws SQLException {
            ResultSet rs;  
            String consulta;
            LinkedList<Subsidiado> lst = new LinkedList();
        try {
            abrirConexion();
            consulta = "SELECT * FROM subsidiado "
                    + "where Identificacion = "+ Identificacion +";";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                int no = rs.getInt("NO");
                int identificacion = rs.getInt("Identificacion");
                String Municipio = rs.getString("Municipio");
                String Departamento = rs.getString("Departamento");
                String Subsidio = rs.getString("Subsidio");
                String Nombre = rs.getString("Nombre"); 
                Subsidiado p = new Subsidiado(no, identificacion, Municipio, Departamento, Subsidio, Nombre);
                lst.add(p); 
            }
            rs.close();
            cerrarConexion();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lst;
    }
    
    public static LinkedList<Subsidio> CatSubsidio() throws SQLException {
        ResultSet rs;        
        String consulta;
        LinkedList<Subsidio> lst = new LinkedList();
        consulta = "SELECT * FROM subsidio;";
        try {
            abrirConexion();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String SubsidioNombre = rs.getString("SubsidioNombre");
                Subsidio p = new Subsidio(Id, SubsidioNombre);
                lst.add(p); 
            }
            rs.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    
    public static String validarSubsidiado(long IdentificacionSubsidiado) {
        try {
            abrirConexion();
            ResultSet rs;
            String Query_Validacion = "Select identificacion from subsidiado where identificacion = " + IdentificacionSubsidiado + ";";
            rs = st.executeQuery(Query_Validacion);
            if (rs.next() == false) {
                return "no existe";
            } else {
                return "El numero de documento que intenta usar ya existe. Por favor digite otro";
            }
        } catch (SQLException e) {
            return "Error, ocurrio un problema en la BD";
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    
    public static LinkedList<Subsidiado> listarCiudadano() throws SQLException {
            ResultSet rs;  
            String consulta;
            LinkedList<Subsidiado> lst = new LinkedList();
            
        try {
            
            abrirConexion();
            consulta = "SELECT * FROM subsidiado ;";          
                    
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                int no = rs.getInt("NO");
                int identificacion = rs.getInt("Identificacion");
                String Municipio = rs.getString("Municipio");
                String Departamento = rs.getString("Departamento");
                String Subsidio = rs.getString("Subsidio");
                String Nombre = rs.getString("Nombre"); 
               
                Subsidiado p = new Subsidiado(no, identificacion, Municipio, Departamento, Subsidio, Nombre);
                lst.add(p); 
            }
            rs.close();
            cerrarConexion();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lst;
    }
    
 public static String insertarsubsidio(long Identificacion, String Municipio,
            String Departamento, String Subsidio, String Nombre) throws SQLException {
        try {
            abrirConexion();
            String insertsubsidio = "INSERT INTO subsidiado VALUES "
                    + "( null, "+ Identificacion + ",'" + Municipio + "', '" + Departamento + "','" + Subsidio + "','"+ Nombre  +"');";
            st.executeUpdate(insertsubsidio);
            cerrarConexion();
            return "Ok";
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return "Error, " + ex.getMessage();
        }
    } 
}
