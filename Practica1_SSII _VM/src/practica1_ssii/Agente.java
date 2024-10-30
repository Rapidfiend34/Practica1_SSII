package practica1_ssii;

import java.util.Random;

public class Agente {
    final int Pisos;
    Operaciones op;
    private int personasT;
    // Percepciones
    int[] b;
    int[] c;
    int a;
    boolean d; // true=abierto false=cerrado
    boolean sigma;
    boolean state; // true=subida false=bajada

    public Agente(int dim, Operaciones ope) {
        this.personasT = 0;
        this.op = ope;
        this.Pisos = dim;
        this.b = new int[this.Pisos];
        this.c = new int[this.Pisos];
        this.a = 0;
        this.d = false;
        this.sigma = true;
        this.state = true;
    }

    public void AbrirPuerta() {
        if ((!this.d) && ((this.b[a] != 0) || (this.c[a] != 0))) { // Proposiciones de obertura de puerta
            this.op.AbrirAscensor(this.Pisos - a - 1);
            this.d = true; // Puerta abierta
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void CerrarPuerta() {
        if ((this.d) && (this.sigma)) { // Proposición de cierre
            this.d = false; // Puerta cerrada
            this.op.CerrarAscensor(this.Pisos - a - 1);
            System.out.println("Cerramos puertas");
        }
    }

    public void generarEntrada() {
        // Generamos una petición aleatoria
        Random ran = new Random();
        int pos = ran.nextInt(this.Pisos);
        this.b[pos]++;
        System.out.println("Me dirijo a " + pos);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Esperar() {
        if (this.d) { // Proposición de espera de cierre
            try {
                this.personasT = this.personasT - this.b[a] + this.c[a];
                this.op.ActualizarContadorPersonas(personasT);
                while (this.b[a] != 0) { // Mientras haya gente que quiera salir del ascensor en el piso actual vamos
                    // sacando a personas
                    this.op.AñadirPersonaSalida(this.Pisos - a - 1);
                    this.b[a]--;
                    // Generamos un hilo para eliminar a la persona de la salida después de un
                    // cierto tiempo
                    Thread cleanerThread = new Thread(() -> this.op.EliminarPersonaSalida(this.Pisos - a - 1));
                    cleanerThread.start();
                }
                System.out.println("Hemos salido del ascensor");
                while (this.c[a] != 0) { // Mientras haya gente que quiera entrar quitamos persona de espera y generamos
                    // petición
                    this.op.EliminarPersona(this.Pisos - a - 1);
                    System.out.println("He entrado al ascensor en el piso" + a);
                    generarEntrada();
                    this.c[a]--;
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.sigma = true; // Delay(sigma)
        }
    }

    public boolean SubirPiso() {
        // Proposiciones de subida de piso
        if ((this.a < this.Pisos - 1) && (this.b[a] == 0) && (this.c[a] == 0) && (!this.d)
                && ((Sig(this.b) != -1) || (Sig(this.c) != -1)) && (this.state)) {
            // Condicion para establecer el destino del ascensor en el panel correspondiente
            if ((Sig(this.b) != -1) && (Sig(this.c) != -1) && (Sig(this.b) <= Sig(this.c))) {
                this.op.ActualizarDestino(Sig(this.b));
            } else if ((Sig(this.b) != -1) && (Sig(this.c) != -1) && (Sig(this.b) >= Sig(this.c))) {
                this.op.ActualizarDestino(Sig(this.c));
            } else if ((Sig(this.b) != -1)) {
                this.op.ActualizarDestino(Sig(this.b));
            } else if ((Sig(this.c) != -1)) {
                this.op.ActualizarDestino(Sig(this.c));
            }
            int y = a;
            a++;
            System.out.println("Subimos");
            this.op.CambioSentido(true); // Cambiamos el sentido siempre que se realice la accion
            this.op.ActualizarPosicion(this.Pisos - a - 1, this.Pisos - y - 1);
            this.state = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    public boolean SubirPiso2I() {
        // Proposiciones de subida de piso
        if ((this.a < this.Pisos - 1) && (this.b[a] == 0) && (this.c[a] == 0) && (!this.d)
                && ((Sig(this.b) != -1) || (Sig(this.c) != -1)) && (!this.state)) {
            // Condicion para establecer el destino del ascensor en el panel correspondiente
            if ((Sig(this.b) != -1) && (Sig(this.c) != -1) && (Sig(this.b) <= Sig(this.c))) {
                this.op.ActualizarDestino(Sig(this.b));
            } else if ((Sig(this.b) != -1) && (Sig(this.c) != -1) && (Sig(this.b) >= Sig(this.c))) {
                this.op.ActualizarDestino(Sig(this.c));
            } else if ((Sig(this.b) != -1)) {
                this.op.ActualizarDestino(Sig(this.b));
            } else if ((Sig(this.c) != -1)) {
                this.op.ActualizarDestino(Sig(this.c));
            }
            int y = a;
            a++;
            System.out.println("Subimos");
            this.state = true;
            this.op.CambioSentido(true); // Cambiamos el sentido siempre que se realice la accion
            this.op.ActualizarPosicion(this.Pisos - a - 1, this.Pisos - y - 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    public boolean BajarPiso() {
        // Proposiciones bajada
        if ((this.a > 0) && (!this.d) && (this.b[a] == 0) && (this.c[a] == 0)
                && ((Prec(this.b) != -1) || (Prec(this.c) != -1)) && (!this.state)) {
            // Condicion para establecer el destino del ascensor en el panel correspondiente
            if ((Prec(this.b) != -1) && (Prec(this.c) != -1) && (Prec(this.b) <= Prec(this.c))) {
                this.op.ActualizarDestino(Prec(this.b));
            } else if ((Prec(this.b) != -1) && (Prec(this.c) != -1) && (Prec(this.b) >= Prec(this.c))) {
                this.op.ActualizarDestino(Prec(this.c));
            } else if ((Prec(this.b) != -1)) {
                this.op.ActualizarDestino(Prec(this.b));
            } else if ((Prec(this.c) != -1)) {
                this.op.ActualizarDestino(Prec(this.c));
            }
            System.out.println("Bajamos");
            int y = a;
            a--;

            this.op.CambioSentido(false); // Cambiamos el sentido siempre que se realice la accion
            this.op.ActualizarPosicion(this.Pisos - a - 1, this.Pisos - y - 1);
            this.state = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    public boolean BajarPiso2I() {
        // Proposiciones bajada
        if ((this.a > 0) && (!this.d) && (this.b[a] == 0) && (this.c[a] == 0)
                && ((Prec(this.b) != -1) || (Prec(this.c) != -1)) && (this.state)) {
            // Condicion para establecer el destino del ascensor en el panel correspondiente
            if ((Prec(this.b) != -1) && (Prec(this.c) != -1) && (Prec(this.b) <= Prec(this.c))) {
                this.op.ActualizarDestino(Prec(this.b));
            } else if ((Prec(this.b) != -1) && (Prec(this.c) != -1) && (Prec(this.b) >= Prec(this.c))) {
                this.op.ActualizarDestino(Prec(this.c));
            } else if ((Prec(this.b) != -1)) {
                this.op.ActualizarDestino(Prec(this.b));
            } else if ((Prec(this.c) != -1)) {
                this.op.ActualizarDestino(Prec(this.c));
            }
            System.out.println("Bajamos");
            int y = a;
            a--;

            this.op.CambioSentido(false); // Cambiamos el sentido siempre que se realice la accion
            this.op.ActualizarPosicion(this.Pisos - a - 1, this.Pisos - y - 1);
            this.state = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;

    }

    private int Prec(int[] array) {
        // Recorrido del predecesor
        for (int i = this.a - 1; i >= 0; i--) {
            if ((array[i] != 0)) {
                return i;
            }
        }
        return -1;
    }

    private int Sig(int[] array) {
        // Recorrido para el siguiente
        for (int i = this.a + 1; i < this.Pisos; i++) {
            if ((array[i] != 0)) {
                return i;
            }
        }
        return -1;
    }

    public void llegadaPersona(int nm) {
        // Aumentamos las peticiones de entrada en el piso
        this.c[nm]++;
    }
}
