package practica1_ssii;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

class Operaciones extends SwingWorker<Void, String> {
    private InterfazAscensor mainFrame;
    private Agente agent;

    public Operaciones(int dim, InterfazAscensor mainFrame) {
        this.agent = new Agente(dim, this);
        this.mainFrame = mainFrame;
    }

    public void ActualizarContadorPersonas(int num) {
        SwingUtilities.invokeLater(() -> mainFrame.ActualizarContadorPersonas(num));
    }

    public void ActualizarPosicion(int a, int y) {
        SwingUtilities.invokeLater(() -> mainFrame.CambiarPosAscensor(a, y));
    }

    public void AbrirAscensor(int a) {
        SwingUtilities.invokeLater(() -> mainFrame.AbrirAscensor(a));
    }

    public void CerrarAscensor(int a) {
        SwingUtilities.invokeLater(() -> this.mainFrame.CerrarAscensor(a));
    }

    public void AñadirPersona(int a) {
        SwingUtilities.invokeLater(() -> this.mainFrame.AñadirPersona(a));
    }

    public void AñadirPersonaSalida(int a) {
        SwingUtilities.invokeLater(() -> this.mainFrame.AñadirPersonaSalida(a));
    }

    public void EliminarPersona(int a) {
        SwingUtilities.invokeLater(() -> this.mainFrame.EliminarPersona(a));
    }

    public void EliminarPersonaSalida(int a) {
        // Delay para eliminar la salida de la persona
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> this.mainFrame.EliminarPersonaSalida(a));
    }

    public void CambioSentido(boolean sentido) {
        SwingUtilities.invokeLater(() -> this.mainFrame.CambioSentido(sentido));
    }

    public void ActualizarDestino(int num) {
        SwingUtilities.invokeLater(() -> this.mainFrame.ActualizarDestino(num));
    }

    @Override
    protected Void doInBackground() throws Exception {
        this.ActualizarPosicion(agent.Pisos - 1, agent.Pisos - 2);
        int initialDelay = 1000;
        Timer timerLlegadaPersonas = new Timer();
        Random random = new Random();
        class PersonaTimerTask extends TimerTask {
            int contador = 0; // Contador para controlar cuántas veces se genera la persona
            final int maxGeneraciones = 5; // Máximo de veces que se ejecutará la tarea

            @Override
            public void run() {
                if (contador < maxGeneraciones) {
                    int pisoAleatorio = random.nextInt(agent.Pisos); // Genera un piso aleatorio
                    System.out.println("He llegado en el piso " + pisoAleatorio);
                    SwingUtilities.invokeLater(() -> mainFrame.AñadirPersona(agent.Pisos - 1 - pisoAleatorio));
                    System.out.println("Contador: " + contador);
                    agent.llegadaPersona(pisoAleatorio);
                    contador++;

                    // Generar un nuevo tiempo aleatorio entre 5 y 10 segundos
                    int nextDelay = random.nextInt(5000, 10001);
                    System.out.println("Próxima generación en " + nextDelay / 1000 + " segundos.");

                    // Reprogramar la tarea con un nuevo retardo aleatorio
                    timerLlegadaPersonas.schedule(new PersonaTimerTask(), nextDelay);
                } else {
                    System.out.println("Se alcanzó el límite de solicitudes.");
                    timerLlegadaPersonas.cancel(); // Cancela el Timer
                }
            }
        }

        System.out.println("Primera generación en " + initialDelay / 1000 + " segundos.");
        timerLlegadaPersonas.schedule(new PersonaTimerTask(), initialDelay);
        initialDelay = random.nextInt(5000, 10001);
        // Bucle infinito del agente comprobando las acciones a realizar, puede
        // modiciarse como accion finito
        while (true) {
            agent.AbrirPuerta();

            agent.Esperar();

            agent.CerrarPuerta();

            if(agent.SubirPiso()){continue;}

            if(agent.BajarPiso()){continue;}
            if(agent.BajarPiso2I()){continue;}
            
            if(agent.SubirPiso2I()){continue;}
            

            
        }
    }

}
