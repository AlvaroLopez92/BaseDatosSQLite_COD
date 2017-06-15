
package sqlite.cod;

import javax.swing.JOptionPane;

public class BaseDatosSQLite_COD {

    public static void main(String[] args) {
        
        Conexion con =new Conexion();
        
        int opciones;
        do{
            opciones=Integer.parseInt(JOptionPane.showInputDialog("1) Conectar a la Base de Datos \n2) Insertar nuevo Equipo \n3) Visualizar datos \n4) Borrar Fila \n5) Vaciar Datos \n6)Actualizar Datos \n7) Desconectar de la Base de Datos \n0) Salir"));
            
            switch(opciones){
                case 1:
                    con.connect();
                    break;
                case 2:
                    con.cargarArray();
                    con.insertarEq();
                    break;
                case 3:
                    con.visualizarEq();
                    break;
                case 4:
                    con.borrarEquipos(Integer.parseInt(JOptionPane.showInputDialog("Inserta el ID del Equipo:")));
                    break;
                case 5:
                    con.vaciarDatos();
                    break;
                case 6:
                    con.modificarEq(Integer.parseInt(JOptionPane.showInputDialog("Inserta el el Nombre del Equipo:")),
                                                      JOptionPane.showInputDialog("Inserta el nuevo Nombre para actualizar:"),
                                                      JOptionPane.showInputDialog("Inserte el nuevo Entrenador para actualizar:"));
                    break;
                case 7:
                    con.desconnect();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null,"Acepta para Salir");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Error");
            }
        }while(opciones!=0);
    }
}