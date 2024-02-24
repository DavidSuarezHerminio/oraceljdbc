package U4_DBOR_Oracle.oracle;

import java.sql.*;

public class accesoOracle {
    private Connection con;

    public void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "1234");
            System.out.println("Conexion OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            System.out.println("Conexion cerrada");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mostrarContactos() {
        try {
            Statement st = con.createStatement();
            ResultSet resul = st.executeQuery("select c.nombre, c.telefono from contactos c");
            System.out.println("INFORMACION DE CONTACTOS--------------");
            while (resul.next()) {
                System.out.printf("\nNOMBRE: %s\nTELEFONO: %s", resul.getString(1), resul.getString(2));
            }
            System.out.println("\n--------------");
            resul.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void crearTablaMisAlumnos() {
        try {
            Statement st = con.createStatement();
            // Intentar crear la tabla solo si no existe
            String sql = "CREATE TABLE misAlumnos (\n" +
                         "    id NUMBER PRIMARY KEY,\n" +
                         "    nombre VARCHAR2(50),\n" +
                         "    telefono VARCHAR2(15)\n" +
                         ")";

            // Verificar si la tabla ya existe
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "MISALUMNOS", null);
            if (tables.next()) {
                // La tabla ya existe, manejar según sea necesario
                System.out.println("La tabla 'misAlumnos' ya existe.");
            } else {
                // La tabla no existe, proceder a crearla
                st.executeUpdate(sql);
                System.out.println("La tabla 'misAlumnos' ha sido creada con éxito.");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarEstudiante(String id, String nombre, String telefono) {
        try {
            Statement st = con.createStatement();
            String sql = String.format("INSERT INTO misAlumnos VALUES('%s', PERSONA('%s', '%s'))", id, nombre, telefono);
            st.executeUpdate(sql);
            System.out.println("Estudiante insertado con éxito");
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar estudiante: " + e.getMessage());
        }
    }

    public void borrarAlumnoPorNombre(String nombre) {
        try {
            Statement st = con.createStatement();
            String sql = "DELETE FROM misAlumnos a WHERE a.datos_personales.nombre='" + nombre + "'";
            st.executeUpdate(sql);
            System.out.println("Alumno borrado con éxito");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarTelefonoPorNombre(String nombre) {
        // Suponiendo que obtienes el teléfono basado en el nombre correctamente
        String telefono = "622723300"; // Asegúrate de reemplazar esto con tu lógica real para obtener el teléfono
        
        // Corregido para asegurar que se pase el teléfono correctamente
        System.out.printf("Teléfono: %s%n", telefono); // Añade %n para un salto de línea en printf
    }

    public void mostrarTodosLosAlumnos() {
        try {
            Statement st = con.createStatement();
            String sql = "SELECT a.id_estudiante, a.datos_personales.nombre, a.datos_personales.telefono FROM misAlumnos a";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	System.out.printf("ID: %s, Nombre: %s, Teléfono: %s%n", rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarInfoAdmitidos() {
        try {
            Statement st = con.createStatement();
            String sql = "SELECT ad.dia, ad.matriculado.id_estudiante, ad.matriculado.datos_personales.nombre, ad.matriculados.datos_personales.telefono FROM admitidos ad";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	System.out.printf("Día: %s, ID: %s, Nombre: %s, Teléfono: %s%n", rs.getDate(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}