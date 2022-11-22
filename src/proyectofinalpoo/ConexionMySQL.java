package proyectofinalpoo;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMySQL {

    // La conexión a la BD
    private static Connection con = null;

    // Las diversas instrucciones que se envían a la BD
    private static Statement st = null;

    // El driver de MySQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // El url de la BD, se especifica el nombre del host y el de la base de datos
    // private static final String URL = "jdbc:mysql://localhost:3306/subsidios2022";
    private static final String URL = "jdbc:mysql://u3izbkwgfact1l0c:1DwgeE2VixHUgIuVAY8O@bt5vtvpi1cmv4s5i4co8-mysql.services.clever-cloud.com:3306/bt5vtvpi1cmv4s5i4co8";

    // Abrir la conexión con la BD
    public static void abrirConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // Se realiza la conexión
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, "u3izbkwgfact1l0c", "1DwgeE2VixHUgIuVAY8O");
        st = con.createStatement();
    }

    // Cerrar la conexiòn con la BD
    public static void cerrarConexion() throws SQLException {
        con.close();
        st.close();
        con = null;
        st = null;
    }

    public static LinkedList<Subsidiado> listarSubsidiado() throws SQLException {
        ResultSet rs;         // Para obtener los datos de la BD
        String consulta;
        LinkedList<Subsidiado> lst = new LinkedList();

        // Se arma la consulta
        consulta = "SELECT * FROM subsidiado;";

        // Se envia la consulta a la BD
        rs = st.executeQuery(consulta);

        // Ahora se obtiene la información de la BD
        while (rs.next()) {
            int no = rs.getInt("NO");
            int identificacion = rs.getInt("Identificacion");
            String Municipio = rs.getString("Municipio");
            String Departamento = rs.getString("Departamento");
            String Subsidio = rs.getString("Subsidio");

            Subsidiado p = new Subsidiado(no, identificacion, Municipio, Departamento, Subsidio);
            lst.add(p); // Se agrega el objeto a la lista de estudiantes
        }

        // Se cierra el ResultSet
        rs.close();
        cerrarConexion();

        return lst;
    }

    public static LinkedList<Subsidio> listarSubsidio() throws SQLException {
        ResultSet rs;         // Para obtener los datos de la BD
        String consulta;
        LinkedList<Subsidio> lst = new LinkedList();

        // Se arma la consulta
        consulta = "SELECT * FROM subsidio;";

        try {
            abrirConexion();
            // Se envia la consulta a la BD
            rs = st.executeQuery(consulta);

            // Ahora se obtiene la información de la BD
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String SubsidioNombre = rs.getString("SubsidioNombre");

                Subsidio p = new Subsidio(Id, SubsidioNombre);
                lst.add(p); // Se agrega el objeto a la lista de estudiantes
            }

            // Se cierra el ResultSet
            rs.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;
    }

    public static String validarSubsidiado(int IdentificacionSubsidiado) {

        try {

            abrirConexion();

            ResultSet rs;

            String Query_Validacion = "Select identificacion from ciudadano where identificacion = " + IdentificacionSubsidiado + ";";

            rs = st.executeQuery(Query_Validacion);

            if (rs.next() == false) {
                return "no existe";
            } else {
                return "Si existe";
            }
        } catch (SQLException e) {

            return "Error, ocurrio un problema en la BD";

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    public static LinkedList<Ciudadano> listarCiudadano() throws SQLException {
        ResultSet rs;
        String consulta;
        LinkedList<Ciudadano> lst = new LinkedList();

        consulta = "SELECT * FROM ciudadano;";

        // Se envia la consulta a la BD
        rs = st.executeQuery(consulta);
        while (rs.next()) {
            int identificacion = rs.getInt("Identificacion");
            String nombre = rs.getString("Nombre");
            Ciudadano c = new Ciudadano(identificacion, nombre);
            lst.add(c); // Se agrega el objeto a la lista de estudiantes
        }
        rs.close();
        return lst;
    }



    /* 
            StringBuilder salida = new StringBuilder();
        Iterator it = lst.iterator();
        while (it.hasNext()) {

       Ciudadano c = new Ciudadano();
            boolean res = it.hasNext();
            JOptionPane.showMessageDialog(null, "Identificacion: "+c.getIdentificacion()+"Nombre: "+ c.getNombre());
            salida.append(res);
        }
        System.out.println(salida);
    
    
    
     */
    public static void imprimirLista(LinkedList<Subsidiado> lst) {
        StringBuilder salida = new StringBuilder();
        Iterator it = lst.iterator();
        while (it.hasNext()) {
            Subsidiado p = (Subsidiado) it.next();
            String res = "no" + p.getNo()
                    + "Identificacion: " + p.getIdentificacion()
                    + "Municipio: " + p.getMunicipio()
                    + "Departamento: " + p.getDepartamento()
                    + "Subsidio: " + p.getSubsidio()
                    + "\n";
            salida.append(res);
        }
        System.out.println(salida);
    }

    public static String insertarpersona(int Identificacion, String Nombre) throws SQLException {
        try {
            abrirConexion();

            String insertpersona = "INSERT INTO SUBSIDIO VALUES (" + Identificacion + "','" + Nombre + "');";
            st.executeUpdate(insertpersona);

            cerrarConexion();
            return "Ok";

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return "Error, " + ex.getMessage();
        }

    }

    public static String insertarsubsidio(int Identificacion, String Municipio,
            String Departamento, String Subsidio) throws SQLException {

        try {
            abrirConexion();
            String insertsubsidio = "INSERT INTO SUBSIDIO VALUES "
                    + "(" + Identificacion + "','" + Municipio + "', '" + Departamento + "','" + Subsidio + "');";
            st.executeUpdate(insertsubsidio);

            cerrarConexion();
            return "Ok";

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return "Error, " + ex.getMessage();
        }
    }
    
    //esto no debe estar en esta clase 
       public static void buscarSubsidio(LinkedList<Subsidiado> identificacion) throws SQLException {
        // Se arma la consulta
        //LinkedList<Subsidiado> identificacion = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la identificacion del subsidiado que va  buscar"));
        //String consulta = "Select identificacion from subsidiados;";
        //System.out.println(consulta);

        // Se envia la consulta a la BD
        //st.executeUpdate(consulta);
    }
}
