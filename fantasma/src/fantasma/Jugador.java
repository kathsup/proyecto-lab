
package fantasma;
public class Jugador {
  
    Jugador player1; 
    //atributos 
    private String username; 
    private String password; 
    private int puntos; 
    private String[] historialJuegos = new String[10];
    public int indiceHistorial = 0;
    
    //constructor 

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
    }

    
    //setters y getters
    public String getUsername(){
    return username;
    }

    public String getPassword() {
        return password;
    }

    
    public int getPuntos() {
        return puntos;
    }

   public void sumarPuntos(int puntos) {
        this.puntos += puntos;  // Sumar los puntos al puntaje actual
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   // Método para agregar un juego al historial
    public void agregarJuegoAlHistorial(String descripcion) {
        historialJuegos[indiceHistorial] = descripcion;  // Guardar la descripción del juego en el arreglo
        indiceHistorial = (indiceHistorial + 1) % 10;  // Avanzar el índice y reiniciar si llega a 10
    }

    // Método para obtener el historial de juegos
    public String[] getHistorialJuegos() {
        return historialJuegos;
    }

    
    
}
