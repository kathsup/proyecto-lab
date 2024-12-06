package fantasma;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ghostGame {

    private static Jugador jugador1;
    private static Jugador jugador2;
    private static Jugador[] jugadores = new Jugador[100];
    private static int playerCount = 0;
    public static String[][] tableroVisible = new String[6][6];
    public static String[][] tableroInvisible = new String[6][6];
    public static int fantasmasb;
    public static int fantasmasB;
    public static int fantasmasm;
    public static int fantasmasM;
    public static String dificultad ="normal"; 
    public static String modoDeJuego = "aleatorio"; 
    public static boolean jugador1Retiro = false;
    public static boolean jugador2Retiro = false;
    public static boolean juegoterminado = false;



    
    
    /*public static void menuInicio() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("------Menu de Inicio------");
            System.out.println("1. Login");
            System.out.println("2. Crear Player");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    crearPlayer();
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion!=3);
    }*/

    
    
    
    
    
    public static String login() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese su username: ");
        String username = entrada.nextLine();
        System.out.print("Ingrese su password: ");
        String password = entrada.nextLine();

        
        boolean loginExitoso = false;
        for (int i = 0; i < playerCount; i++) {
            if (jugadores[i] != null &&jugadores[i].getUsername().equals(username) && jugadores[i].getPassword().equals(password)) {
                System.out.println("Login exitoso. Cargando menu principal...");
                jugador1 = jugadores[i];
                loginExitoso = true;
                menuPrincipal();
                return jugador1.getUsername();

            }
        }

        System.out.println("Error: Username o password incorrectos.");
        return null;
        
    }

    
    
    
    
    
    
    public static void crearPlayer() {
        Scanner sc = new Scanner (System.in); 
        if (playerCount >= 100) {
            System.out.println("Error: No se pueden crear mas jugadores.");
            return;
        }

        System.out.print("Ingrese un username: ");
        String username = sc.nextLine();

        
        for (int i = 0; i < playerCount; i++) {
            if (jugadores[i].getUsername().equals(username)) {
                System.out.println("Error: El username ya esta en uso.");
                return;
            }
        }

        System.out.print("Ingrese una contraseña (minimo 8 caracteres): ");
        String password = sc.nextLine();

        
        if (password.length() < 8) {
            System.out.println("Error: La contraseña debe tener al menos 8 caracteres.");
            return;
        }

        
        jugadores[playerCount] = new Jugador(username, password);
        jugador1= jugadores[playerCount]; 
        playerCount++;
        System.out.println("Player creado exitosamente. Cargando menu principal...");
        menuPrincipal();
    }

    
    
    
  public static String jugador2() {
    Scanner entrada = new Scanner(System.in);
    boolean usuario2Encontrado = false;
    
    while (!usuario2Encontrado) {
        System.out.print("Ingrese jugador2: ");
        String usser2 = entrada.nextLine();

        
        if (jugador1 != null && jugador1.getUsername().equals(usser2)) {
            System.out.println("No puede ingresar el mismo usuario que el jugador1. Intente de nuevo.");
            continue;  
        }

        
        for (int i = 0; i < playerCount; i++) {
            if (jugadores[i] != null && jugadores[i].getUsername().equals(usser2)) {
                System.out.println("Jugador encontrado");
                jugador2 = jugadores[i];
                usuario2Encontrado = true;
                break;  
            }
        }

        if (!usuario2Encontrado) {
            System.out.println("Usuario no encontrado. Crear el usuario.");
            crearPlayer();
            break;
        }
    }

    return jugador2.getUsername();
}
    
    
    public static void jugarGhost() { 
       
        iniciarTablero(); 
      
    
    if (modoDeJuego.equals("manual")) {
        mostrarTablero();  
        if (dificultad.equals("normal")) {
            fantasmasb = 4;
            fantasmasm = 4;
            fantasmasB = 4;
            fantasmasM = 4;
            colocarFantasmas1M(8);  
            colocarFantasmas2M(8);  
        } else if (dificultad.equals("expert")) {
            fantasmasb = 2;
            fantasmasm = 2;
            fantasmasB = 2;
            fantasmasM = 2;
            colocarFantasmas1M(4);  
            colocarFantasmas2M(4);  
        } else if (dificultad.equals("genius")) {
            fantasmasb = 1;
            fantasmasm = 1;
            fantasmasB = 1;
            fantasmasM = 1;
            colocarFantasmas1M(2);  
            colocarFantasmas2M(2);  
            colocarFantasmasTrampa1();  
            colocarFantasmasTrampa2();  
        }
    } 
    
    
    else if (modoDeJuego.equals("aleatorio")) {
        
        if (dificultad.equals("normal")) {
            fantasmasb = 4;
            fantasmasm = 4;
            fantasmasB = 4;
            fantasmasM = 4;
            colocarAleatorio1(8);  
            colocarAleatorio2(8);  
        } else if (dificultad.equals("expert")) {
            fantasmasb = 2;
            fantasmasm = 2;
            fantasmasB = 2;
            fantasmasM = 2;
            colocarAleatorio1(4);  
            colocarAleatorio2(4);
        } else if (dificultad.equals("genius")) {
            fantasmasb = 1;
            fantasmasm = 1;
            fantasmasB = 1;
            fantasmasM = 1;
            colocarAleatorio1(2);  
            colocarAleatorio2(2); 
            colocarFantasmasTrampa1(); 
            colocarFantasmasTrampa2();  
        }
    }
    
    mostrarTablero();
    juegoterminado=false;
    turno();
    }

    
    
    
    public static void iniciarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tableroVisible[i][j] = "-";
                tableroInvisible[i][j] = "-";

            }
        }

    }

    
    
    
    public static void mostrarTablero() {
        // numeros del tablero
         System.out.print("  "); // Espacio inicio para la primera fila de números
    for (int i = 0; i <= 5; i++) {
        System.out.print(i + " ");
    }
    System.out.println();

    // Imprimir el tablero con los números de fila
    for (int i = 0; i < 6; i++) {
        System.out.print(i  + " "); 

        // Imprimir las celdas del tablero
        for (int j = 0; j < 6; j++) {
            System.out.print(tableroVisible[i][j] + " ");
        }
        System.out.println(); 
    }
        
    
    }

    
    
    
    
    public static void colocarFantasmas1M(int cantidadFantasmas) {
        Scanner entrada = new Scanner(System.in);
        
        try{
        for (int i = 0; i < cantidadFantasmas; i++) {
            String tipoFantasma = (i % 2 == 0) ? "b" : "m";
            String tipo = (tipoFantasma.equals("b")) ? "bueno" : "malo"; 

            System.out.println(jugador1.getUsername() + " Ingresa la posicion para el fantasma " + (i + 1) +tipo +" (Fila/columna)");
            int fila = entrada.nextInt();
            int columna = entrada.nextInt();

            if ((fila == 0 && columna > 0 && columna < 5) || (fila == 1 && columna >= 0 && columna <= 5) && tableroInvisible[fila][columna].equals("-")&&tableroVisible[fila][columna].equals("-")) {
                tableroInvisible[fila][columna] = tipoFantasma;
                tableroVisible[fila][columna] = "f";
                
            } else {
                System.out.println("Posicion invalida. Intentelo de nuevo.");
                i--;
            }
        }
}
        catch (InputMismatchException e){
            System.out.println("Ingreso invalido");
        }
    }

    public static void colocarAleatorio1(int cantidadFantasmas) {
    Random random = new Random(); 
    int fantasmasb = cantidadFantasmas / 2; 
    int fantasmasm = cantidadFantasmas / 2;  
    
    while (fantasmasb > 0 || fantasmasm > 0) {
        int fila = random.nextInt(2); 
        int columna = random.nextInt(6);  

        
        if (tableroInvisible[fila][columna].equals("-") && 
            tableroVisible[fila][columna].equals("-") && 
            !(fila == 0 && (columna == 0 || columna == 5))) { 
            if (fantasmasb > 0) {
                tableroInvisible[fila][columna] = "b"; 
                tableroVisible[fila][columna] = "f";
                fantasmasb--;
            } else if (fantasmasm > 0) {
                tableroInvisible[fila][columna] = "m"; 
                tableroVisible[fila][columna] = "f";
                fantasmasm--;
            }
        }
    }
}

    
    public static void colocarAleatorio2(int cantidadFantasmas) {
    Random random = new Random(); 
    int fantasmasB= cantidadFantasmas / 2; 
    int fantasmasM = cantidadFantasmas / 2;  
    
    while (fantasmasB > 0 || fantasmasM > 0) {
        int fila = random.nextInt(2) + 4;
        int columna = random.nextInt(6);  

        
        if (tableroInvisible[fila][columna].equals("-") && 
            tableroVisible[fila][columna].equals("-") && 
            !(fila == 5 && (columna == 0 || columna == 5))) { 

            if (fantasmasB > 0) {
                tableroInvisible[fila][columna] = "B"; 
                tableroVisible[fila][columna] = "F";
                fantasmasB--;
            } else if (fantasmasM > 0) {
                tableroInvisible[fila][columna] = "M"; 
                tableroVisible[fila][columna] = "F";
                fantasmasM--;
            }
        }
    }
}



    
    public static void colocarFantasmas2M(int cantidadFantasmas) {
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < cantidadFantasmas; i++) {
            
            
            String tipoFantasma = (i % 2 == 0) ? "B" : "M"; 
            String tipo = (tipoFantasma.equals("B")) ? "bueno" : "malo";

            System.out.println(jugador2.getUsername() + " jugador 2 Ingrese posicion para fantasma " + (i + 1) +tipo +" (Fila/columna)");
            int fila = entrada.nextInt();
            int columna = entrada.nextInt();

            if ((fila == 5 && columna > 0 && columna < 5) || (fila == 4 && columna >= 0 && columna <= 5) && tableroInvisible[fila][columna].equals("-")&&tableroVisible[fila][columna].equals("-")) {
                tableroInvisible[fila][columna] = tipoFantasma;
                tableroVisible[fila][columna] = "F";
                
            } else {
                System.out.println("Posicion invalida. Intentelo de nuevo.");
                i--;
            }
            }
       
        
    }

    
    
    

    public static boolean ganar() {
        
        if (jugador1Retiro) {
        System.out.println(jugador2.getUsername() + " ha ganado porque " + jugador1.getUsername() + " se retiro.");
        jugador2.sumarPuntos(3); 
        juegoterminado = true;
        jugador2.agregarJuegoAlHistorial(jugador2.getUsername() + " gano porque " + jugador1.getUsername() + " se retiro.");
      
        
    } else if (jugador2Retiro) {
        System.out.println(jugador1.getUsername() + " ha ganado porque " + jugador2.getUsername() + " se retiro.");
        jugador1.sumarPuntos(3); 
        juegoterminado = true;
        jugador1.agregarJuegoAlHistorial(jugador1.getUsername() + " gano porque " + jugador2.getUsername() + " se retiro.");
        juegoterminado = true;
    }
        if (fantasmasb == 0) {
            System.out.println(jugador2.getUsername()+" ha ganado porque se comio todos los fantasmas buenos de "+jugador1.getUsername());
            jugador1.agregarJuegoAlHistorial(jugador2.getUsername()+" ha ganado porque se comio todos los fantasmas buenos de "+jugador1.getUsername());
            juegoterminado = true;
            jugador2.sumarPuntos(3);
        } else if (fantasmasM == 0) {
            System.out.println(jugador2.getUsername()+" gano porque "+jugador1.getUsername()+ " se comio todos sus fantasmas malos");
            jugador1.agregarJuegoAlHistorial((jugador2.getUsername()+" gano porque "+jugador1.getUsername()+ " se comio todos sus fantasmas malos"));
            juegoterminado = true;
            jugador2.sumarPuntos(3);
        } else if (tableroInvisible[0][0].equals("B") || tableroInvisible[0][5].equals("B")) {
            System.out.println(jugador2.getUsername()+" gano por sacar un fantasma bueno del castillo");
            jugador1.agregarJuegoAlHistorial(jugador2.getUsername()+" gano por sacar un fantasma bueno del castillo");
            jugador2.sumarPuntos(3);
            juegoterminado = true;
        }
        //
        if (fantasmasB == 0) {
            System.out.println(jugador1.getUsername() +"ha ganado porque se comio todos los fantasmas buenos de"+ jugador2.getUsername());
            jugador1.agregarJuegoAlHistorial(jugador1.getUsername() +"ha ganado porque se comio todos los fantasmas buenos de"+ jugador2.getUsername());
            jugador1.sumarPuntos(3);
            juegoterminado = true;
            
        } else if (fantasmasm == 0) {
            System.out.println(jugador1.getUsername()+" gano porque "+ jugador2.getUsername()+ " se comio todos sus fantasmas malos");
            jugador1.agregarJuegoAlHistorial(jugador1.getUsername()+" gano porque "+ jugador2.getUsername()+ " se comio todos sus fantasmas malos");
            
            jugador1.sumarPuntos(3);
            juegoterminado = true;
        } else if (tableroInvisible[5][0].equals("b") || tableroInvisible[5][5].equals("b")) {
            System.out.println(jugador1.getUsername() +"gano por sacar un fantasma bueno del castillo");
            jugador1.agregarJuegoAlHistorial(jugador1.getUsername() +"gano por sacar un fantasma bueno del castillo");
            jugador1.sumarPuntos(3);
            juegoterminado = true;
        }
        
         

        return juegoterminado;
    }

    
    
    
    
    
    public static void turno() {

        boolean turnojugador1 = true;
        System.out.println();
        System.out.println();
        while (!juegoterminado) {
            if (turnojugador1) {
                System.out.println("Turno de " + jugador1.getUsername());
                
                turnoJugador1();
                

            } else {
                System.out.println("Turno jugador " + jugador2.getUsername());
                turnoJugador2();
                
            }

            turnojugador1 = !turnojugador1;
        }
    }

    
    public static boolean retiro(String jugadorActual, String jugadorOponente) {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Desea salir del juego? (s/n): ");
    String retiro = entrada.nextLine().toLowerCase();

    
    if (retiro.equals("s")) {
        System.out.println(jugadorOponente + " ha ganado porque " + jugadorActual + " se ha retirado.");
        return true;  
    } else if (retiro.equals("n")) {
        System.out.println("El juego continuara.");
        return false;  
    } else {
        System.out.println("Opción invalida, el juego continuara.");
        return false;  
    }
}

    
    
    //desde awui estoy modificando
    public static void turnoJugador1() {
    Scanner entrada = new Scanner(System.in);
    int fila = 0, columna = 0, nuevaFila = 0, nuevaColumna = 0;

    boolean seleccionValida = false;
    while (!seleccionValida) {
        System.out.println("Ingrese coordenada de fantasma a mover: (fila/columna)");
        fila = entrada.nextInt();
        columna = entrada.nextInt();

        if (fila == -1 && columna == -1) {
            if (retiro(jugador1.getUsername(), jugador2.getUsername())) {
                jugador1Retiro = true;
                verificarCondicionesDeVictoria();
                return;
            } else {
                return; 
            }
        }

        if (tableroVisible[fila][columna].equals("f") && fila <= 5 && columna <= 5) {
            System.out.println("Fantasma seleccionado");
            seleccionValida = true;  
        } else {
            System.out.println("Error al seleccionar fantasma, vuelva a intentarlo.");
        }
    }

    boolean movimientoValido = false;
    while (!movimientoValido) {
        System.out.println("Ingrese la nueva posición a donde desea mover el fantasma (fila/columna):");
        nuevaFila = entrada.nextInt();
        nuevaColumna = entrada.nextInt();

        
        
        if (MovimientoValido(fila, columna, nuevaFila, nuevaColumna, 1)) { 
            // Solo capturar si el nuevo destino tiene un fantasma del oponente
            if (tableroVisible[nuevaFila][nuevaColumna]=="F") {
                capturarFantasma(nuevaFila, nuevaColumna, 1);
            }
            moverFantasma(fila, columna, nuevaFila, nuevaColumna, 1);

            movimientoValido = true; 

            

            if (verificarCondicionesDeVictoria()) {
                return;  
            }

            mostrarTablero();
        } else {
            System.out.println("Movimiento invalido. Intente de nuevo.");
        }
    }
}



    
    
    
    
    public static void turnoJugador2() {
    Scanner entrada = new Scanner(System.in);
    int fila = 0, columna = 0, nuevaFila = 0, nuevaColumna = 0;

    boolean seleccionValida = false;
    while (!seleccionValida) {
        System.out.println("Ingrese coordenada de fantasma a mover: (fila/columna)");
        fila = entrada.nextInt();
        columna = entrada.nextInt();

        if (fila == -1 && columna == -1) {
            if (retiro(jugador2.getUsername(), jugador1.getUsername())) {
                jugador2Retiro = true;
                verificarCondicionesDeVictoria();
                return;
            } else {
                return;
            }
        }

        if (tableroVisible[fila][columna].equals("F") && fila <= 5 && columna <= 5) {
            System.out.println("Fantasma seleccionado");
            seleccionValida = true;
        } else {
            System.out.println("Error al seleccionar fantasma, vuelva a intentarlo.");
        }
    }

    boolean movimientoValido = false;
    while (!movimientoValido) {
        System.out.println("Ingrese la nueva posición a donde desea mover el fantasma (fila/columna):");
        nuevaFila = entrada.nextInt();
        nuevaColumna = entrada.nextInt();

        
        
        if (MovimientoValido(fila, columna, nuevaFila, nuevaColumna, 2)) {
            // Solo capturar si el nuevo destino tiene un fantasma del oponente
            if (tableroVisible[nuevaFila][nuevaColumna]=="f") {
                capturarFantasma(nuevaFila, nuevaColumna, 2);
            }
            moverFantasma(fila, columna, nuevaFila, nuevaColumna, 2);

            movimientoValido = true;

            

            if (verificarCondicionesDeVictoria()) {
                return;
            }

            mostrarTablero();
        } else {
            System.out.println("Movimiento invalido. Intente de nuevo.");
        }
    }
}

    
    
    
    public static boolean MovimientoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, int jugador) {
        int diferenciaFila = Math.abs(filaDestino - filaOrigen);
        int diferenciaColumna = Math.abs(columnaDestino - columnaOrigen);

        if ((diferenciaFila == 1 && diferenciaColumna == 0) || (diferenciaFila == 0 && diferenciaColumna == 1)) {
            
            if (tableroVisible[filaDestino][columnaDestino].equals("-") || (jugador == 1 && tableroVisible[filaDestino][columnaDestino].equals("F"))) {
                return true;  
            } else if (jugador == 2 && tableroVisible[filaDestino][columnaDestino].equals("f")) {
                return true;  
            }
        }

        return false;  
    }

    
    
    
    
    public static void moverFantasma(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, int jugador) {
       
        tableroVisible[filaDestino][columnaDestino] = tableroVisible[filaOrigen][columnaOrigen];  
        tableroVisible[filaOrigen][columnaOrigen] = "-";  

        tableroInvisible[filaDestino][columnaDestino] = tableroInvisible[filaOrigen][columnaOrigen];  
        tableroInvisible[filaOrigen][columnaOrigen] = "-";  
    }

    
    
    
    public static void capturarFantasma(int fila, int columna, int jugador) {
    if (jugador == 1 && tableroVisible[fila][columna].equals("F")) { // Jugador 1 captura fantasmas del Jugador 2
        if (tableroInvisible[fila][columna].equals("B")) {
            System.out.println(jugador1.getUsername() + " capturo un fantasma bueno de "+jugador1.getUsername());
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
            fantasmasB--;  
        } else if (tableroInvisible[fila][columna].equals("M")) {
            System.out.println(jugador1.getUsername()+"capturo un fantasma malo de "+jugador2.getUsername());
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
            fantasmasM--;  
        }
        else if(tableroInvisible[fila][columna].equals("T")){
            System.out.println(jugador1.getUsername()+"capturaste un jugador trampa");
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
        }
    } else if (jugador == 2 && tableroVisible[fila][columna].equals("f")) { 
        if (tableroInvisible[fila][columna].equals("b")) {
            System.out.println(jugador2.getUsername() +"capturó un fantasma bueno de "+jugador2.getUsername());
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
            fantasmasb--;  
        } else if (tableroInvisible[fila][columna].equals("m")) {
            System.out.println(jugador2.getUsername() + "capturó un fantasma malo de " + jugador2.getUsername());
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
            fantasmasm--; 
        }
        else if(tableroInvisible[fila][columna].equals("T")){
            System.out.println(jugador2.getUsername()+"capturaste un jugador trampa");
            tableroVisible[fila][columna] = "-";
            tableroInvisible[fila][columna] = "-";
        }
    }

  
}
    
    
    public static boolean verificarCondicionesDeVictoria() {
        
        return ganar();
    }

    //termine hasta aqui 
    
    
    public static void dificultad(){
    
      Scanner entrada=new Scanner(System.in);
      int opcion;
        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Normal (8 fantasmas)");
        System.out.println("2. Expert (4 fantasmas)");
        System.out.println("3. Genius (fantasmas trampa)");
        opcion = entrada.nextInt();
    
        switch(opcion){
        
            case 1: 
                dificultad = "normal"; 
                System.out.println("Dificultad: normal");
              break; 
            case 2: 
                dificultad = "expert";
                System.out.println("Dificultad: expert");
              break; 
           case 3: 
                dificultad = "genius";
                System.out.println("Dificultad: genius");
              break; 
           default: 
               System.out.println("Opcion invalida. Dificultad normal por defecto");
               dificultad = "normal"; 
               break;
        }
    
    }
    
   
   public static void colocarFantasmasTrampa1() {
    Random random = new Random();  // Un solo generador aleatorio
    
    for (int i = 0; i < 4; i++) {
        int fila = random.nextInt(2);  // Filas 0 y 1
        int columna = random.nextInt(6);  // Columnas 0 a 5
        
        // Verificar que no sea una posición ocupada y no sea (0,0) ni (0,5)
        if (tableroInvisible[fila][columna].equals("-") && 
            !(fila == 0 && (columna == 0 || columna == 5))) {
            
            tableroInvisible[fila][columna] = "T";  // Colocar fantasma trampa
            tableroVisible[fila][columna] = "f";  // Colocar en el tablero visible
        } else {
            i--;  // Reintentar si la posición es inválida o prohibida
        }
    }
}

    
    public static void colocarFantasmasTrampa2() {
    Random random = new Random();  
    
    for (int i = 0; i < 4; i++) {
        int fila = random.nextInt(2) + 4; 
        int columna = random.nextInt(6);  
        
       
        if (tableroInvisible[fila][columna].equals("-") && 
            !(fila == 5 && (columna == 0 || columna == 5))) {
            
            tableroInvisible[fila][columna] = "T";  
            tableroVisible[fila][columna] = "F";  
        } else {
            i--;  
        }
    }
}

    public static void burbuja(Jugador[]jugadores){
    Jugador temp; 
    for (int i = 0; i < playerCount - 1; i++) {
        for (int j = 0; j < playerCount - i - 1; j++) {
            if (jugadores[j].getPuntos() < jugadores[j + 1].getPuntos()) {
                temp = jugadores[j];
                jugadores[j] = jugadores[j + 1];
                jugadores[j + 1] = temp;
            }
        }
    }
    
    }
    
    
    public static void ranking(){
    
        burbuja(jugadores);
        for (int i = 0; i < playerCount; i++) {
            System.out.println("jugador "+(i+1));
            System.out.println("Usuario: "+jugadores[i].getUsername());
            System.out.println("Contrasena: "+jugadores[i].getPassword()); 
            System.out.println("puntos: "+jugadores[i].getPuntos());
            System.out.println();
        }
    
    
    }
    

    public static void modoJuego(){
    Scanner entrada = new Scanner(System.in);
    String modo;
    
    do {

            System.out.println("-----MODO DE JUEGO-----");
            System.out.println("a. aleatorio");
            System.out.println("b. manual");
            System.out.println("c. Regresar al menu principal");
            modo = entrada.nextLine().toLowerCase();

            switch (modo) {
                
                case "a":
                    System.out.println("Modo: aleatorio");
                    modoDeJuego = "aleatorio";
                    break;
                case "b":
                    System.out.println("Modo: manual");
                    modoDeJuego="manual";
                    break;
                case "c":
                    menuPrincipal();
                    break; 
                default: System.out.println("Opcion invalida. Modo por default: aleatorio");
                modoDeJuego = "aleatorio"; 
                break; 
            }
        } while (!modo.equals("c"));
    
    
    }
    
    public static void ultimos10juegos(){
    
     if (jugador1 != null) {
        System.out.println("------ ultimos 10 Juegos ------");
        String[] historial = jugador1.getHistorialJuegos();

        int juegosMostrados = 0;  // Para contar cuántos juegos se han mostrado
        for (int i = jugador1.indiceHistorial; i < jugador1.indiceHistorial + 10; i++) {
            int index = i % 10;  // Usar el operador % para girar en el arreglo circular
            if (historial[index] != null) {  // Solo mostrar si hay un juego registrado en esa posición
                juegosMostrados++;
                System.out.println(juegosMostrados + ". " + historial[index]);
            }
        }
    } else {
        System.out.println("No hay ningun jugador logueado.");
    }
    
    
    }
    
    
    
    
    
    
    
    public static void reportes(){
    Scanner entrada = new Scanner (System.in); 
    String reporte;
    
    do{
        System.out.println("-----REPORTES-----");
        System.out.println("a. Descripcion De mis Ultimos 10 Juegos");
        System.out.println("b. Ranking de Jugadores");
        System.out.println("c. regresar a menu principal");
        reporte = entrada.nextLine().toLowerCase(); 
    
        switch(reporte){
            case "a": 
                System.out.println("Ultimas 10 partidas: ");
                ultimos10juegos();
                break; 
            case "b": 
                System.out.println("Ranking de jugadores");
                ranking();
                break;
            case "c":
                System.out.println("Regresando a menu principal...");
                menuPrincipal();
                break; 
            default: 
                System.out.println("Opcion invalida, intente de nuevo");
                break; 
        }
    
    }while(!reporte.equals("c"));
    }
    
    public static void mostrarMisDatos(){
    
        System.out.println("---MIS DATOS---");
        System.out.println("Usuario: "+jugador1.getUsername());
        System.out.println("Password: "+jugador1.getPassword());
        System.out.println("Puntos: "+jugador1.getPuntos());
    }
    
    public static void cambiarClave(){
    
        Scanner entrada = new Scanner(System.in);
        
        boolean cambio=false;
        while(!cambio){
        System.out.println("Ingrese la nueva password: ");
        String clave = entrada.nextLine();
        if (clave.length() < 8) {
            System.out.println("Error: La clave debe tener al menos 8 caracteres.Vuelva a intentarlo");
            cambio=false;
        }
        else{
        jugador1.setPassword(clave);
        jugador1.getPassword(); 
        cambio=true;
        System.out.println("Su clave se cambio exitosamente!");
        
        }
        }
    }
    
    public static void eliminarCuenta(){
        System.out.println("-----Eliminar cuenta-----");
          if (jugador1 != null) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Estas seguro de que deseas eliminar tu cuenta? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();

        if (confirmacion.equals("s")) {
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i] != null && jugadores[i].getUsername().equals(jugador1.getUsername())) {
                    jugadores[i] = null;  // Eliminar el jugador del arreglo
                    System.out.println("Tu cuenta ha sido eliminada exitosamente.");
                    return;
                }
            }
            jugador1 = null;
            //menuInicio();
            return; 
        } else {
            System.out.println("No se ha eliminado tu cuenta.");
            miperfil(); 
        }
    } else {
        System.out.println("No hay un jugador logueado para eliminar.");
        //menuInicio();
        return; 
    }
    }
    
    
    
    
     public static void miperfil(){
    Scanner entrada = new Scanner(System.in);
    String perfil;
    do{
        System.out.println("------Mi perfil------");
        System.out.println("a. Ver mis datos");
        System.out.println("b. Cambiar password");
        System.out.println("c. eliminar mi cuenta");
        System.out.println("d. Regresar al menu principal");
        perfil = entrada.nextLine().toLowerCase(); 
        
        switch(perfil){
         
            case "a": 
                mostrarMisDatos();
                break; 
            case "b": 
                System.out.println("-----Cambiar Password-----");
                cambiarClave();
                break; 
            case "c": 
                eliminarCuenta();
                break; 
            case "d":
                break; 
            default: 
                break; 
        
        
        }
    
    }while(!perfil.equals("d"));
    
    }
    
    public static void configuracion() {
        Scanner entrada = new Scanner(System.in);
        String configuracion;
        do {

            System.out.println("-----Configuracion-----");
            System.out.println("a. Dificultad");
            System.out.println("b. Modo de juego");
            System.out.println("c. Regresar al menu principal");
            configuracion = entrada.nextLine().toLowerCase();

            switch (configuracion) {

                case "a":
                    System.out.println("DIFICULTAD");
                    dificultad();
                    break;
                case "b":
                    System.out.println("Modo de juego");
                    modoJuego();
                    break;
                case "c":
                    menuPrincipal();
                    break; 
                default: System.out.println("Opción invalida vuelva a intentarlo");
                break; 
            }
        } while (!configuracion.equals("c"));

    }

    
   
    
    public static void menuPrincipal() {

        int opcion;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("------Menu Principal------");
            System.out.println("1. Jugar Ghost");
            System.out.println("2. Configuracion");
            System.out.println("3. Reportes");
            System.out.println("4. Mi Perfil");
            System.out.println("5. Salir ");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    jugador2();
                    jugarGhost();

                    break;
                case 2:
                    configuracion();
                    break;
                case 3:
                    reportes();
                    break;
                case 4:
                    miperfil(); 
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    
                    break;
                default:
                    System.out.println("Seleccion invalida. Vuelva a intentarlo.");
                    break;

            }
        } while (opcion != 5);

    }

}
