
package sqlite.cod;


public class Parametros {
    
   private String nombre,entrenador;
   int id;

   Parametros(){
    }
   

    public Parametros(int id, String nombre, String entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.entrenador = entrenador;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parametros{" + "nombre=" + nombre + ", entrenador=" + entrenador + ", id=" + id + '}';
    }
   
    
   
}