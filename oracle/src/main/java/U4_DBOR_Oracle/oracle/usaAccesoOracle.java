package U4_DBOR_Oracle.oracle;

public class usaAccesoOracle {
    public static void main(String[] args) {
        accesoOracle a = new accesoOracle();
 a.abrirConexion();
        
        a.mostrarContactos(); // Esto ya estaba en tu código
        
        // Supongamos que quieres crear la tabla misAlumnos
        //a.crearTablaMisAlumnos();
        
        // Insertar un estudiante en la tabla MISALUMNOS
        // Asegúrate de ajustar los parámetros según los campos de tu tabla ESTUDIANTE
        //a.insertarEstudiante("a1a", "David", "622723300");
        
        // Buscar el teléfono de un alumno por nombre
        //a.buscarTelefonoPorNombre("DAVID");
        
        //borrar alomno por nombre
        //a.borrarAlumnoPorNombre("DAVID");
        
        
        // Mostrar todos los alumnos
        //a.mostrarTodosLosAlumnos();
        
        // Mostrar la información de la tabla ADMITIDOS
        a.mostrarInfoAdmitidos();
        
        a.cerrarConexion();
    }
}