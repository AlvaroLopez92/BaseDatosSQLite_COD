
package sqlite.cod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    
    public static Connection conexion;
    ResultSet result;
    
    ArrayList<Parametros>equipo = new ArrayList<>();
    
    private String url;
    
    public Conexion(){
        url="/home/putodruida/NetBeansProjects/Contornos de desenvolvemento/SQLITE-COD/SQLITE-COD/basesCOD.db";
    }
    public Conexion(String url){
        this.url = url;
    }
    /**
     * Aquí se conecta con la base de datos
     */
    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException ex){
            System.out.println("DRIVERS NOT FOUND: "+ex.getMessage());
        }
        try{
            conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("Conexion a Base Realizada");
        }
        catch (SQLException e){
            System.out.println("Error");
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /**
     * Aquí se cargan los datos que se introducen en la base de datos
     */
    public void cargarArray(){
        equipo.add(new Parametros(
        Integer.parseInt(JOptionPane.showInputDialog("Inserta el ID del Equipo")),
        JOptionPane.showInputDialog("Inserta el Nombre del Equipo"),
        JOptionPane.showInputDialog("Inserta el Nombre del entrenador")));
    }
    
    /**
     * Esto introduce los datos en su tabla correspondiente
     */
    public void insertarEq(){
        
        try{
            PreparedStatement insert = conexion.prepareStatement("insert into equipos(id,nombre,entrenador) values(?,?,?)");
            for(int i=0;i<equipo.size();i++){
                insert.setInt(1,equipo.get(i).getId());
                insert.setString(2,equipo.get(i).getNombre());
                insert.setString(3,equipo.get(i).getEntrenador());                
                insert.execute();
                int count=insert.getUpdateCount();
                    System.out.println(count+" Fila Insertada");
            }
        }catch (SQLException ex){
            System.out.println("Error al insertar los datos en la tabla Equipos: "+ex.getMessage());
        }
    }
    
    /**
     * Esto visualiza la tabla que queramos y su contenido
     */
    public void visualizarEq(){
        
        try{
            PreparedStatement  ver = conexion.prepareStatement("select * from equipos");
            result = ver.executeQuery();
            while(result.next()){
                System.out.println("ID: "+result.getInt("ID"));
                System.out.println("Equipo: "+result.getString("Nombre"));
                System.out.println("Entrenador: "+result.getString("Entrenador"));
            }
        }catch (SQLException ex){
            System.out.println("Error al leer la Base de Datos: "+ex.getMessage());
        }
        try{
            Statement s = conexion.createStatement();
            ResultSet r = s.executeQuery("select count(*) as rowcount from equipos");
            r.next();
            int count = r.getInt("rowcount");
            r.close();
            System.out.println("Total de filas en la tabla: "+count);
        }catch(SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    /**
     * Aquí podemos modificar la fila que queramos de la tabla
     */
    public void modificarEq(Integer reg, String nom, String entr){
        
        try{
            PreparedStatement actualizar = conexion.prepareStatement("update equipos set nombre='"+nom+"',entrenador='"+entr+"'where id="+reg.toString());
            actualizar.execute();
            int count=actualizar.executeUpdate();
            System.out.println("Registro de Equipos Actualizado");
        }catch(SQLException ex){
            System.out.println("Error al actualizar Registros: "+ex.getMessage());
        }
    }
    
    /**
     * Aquí podemos borrar la fila que queramos de la tabla
     */
    public void borrarEquipos(Integer dato){
        
        try{
            Statement borrar = conexion.createStatement();
            borrar.execute("delete from equipos where id="+dato.toString());
            int count=borrar.getUpdateCount();
            System.out.println("Fila Borrada de la Tabla");
        }catch(SQLException ex){
            System.out.println("Error al borar la fila: "+ex.getMessage());
        }
    }
    
    /**
     * Con esto borramos todos los datos de la tabla
     */
    public void vaciarDatos(){
        try{
            PreparedStatement vaciar = conexion.prepareStatement("delete from equipos");
            vaciar.execute();
            System.out.println("Tabla Vaciada con Exito");
        }catch(SQLException ex){
            System.out.println("Error al vacias la tabla: "+ex.getMessage());
        }
    }
    
    /**
     Con esto nos desconectamos de la base de datos
     */
    public void desconnect(){
        try{
            conexion.close();
            System.out.println("Base de Datos Cerrada con Exito");
        }catch(SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
}
