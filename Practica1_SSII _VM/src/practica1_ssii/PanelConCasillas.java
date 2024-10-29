package practica1_ssii;

import javax.swing.*;
import java.awt.*;

public class PanelConCasillas extends JPanel {
    private int numCasillas;
    private int numCola;
    private casilla[] cajas;

    public PanelConCasillas(int num, int personas) {
        this.cajas = new casilla[num];
        this.numCasillas = num;
        this.numCola = personas;
        // Establecer el layout para que las casillas estén en una columna
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Añadir espacio alrededor de las casillas para centrarlas
        setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));
        agregarCajasDinamicas();
    }

    private void agregarCajasDinamicas() {
        // Calcular el tamaño proporcional de cada caja basado en el número de cajas
        int alturaCaja = 300 / (this.numCasillas + numCola); // Suponiendo que la altura total del panel es 300

        for (int i = 0; i < this.numCasillas; i++) {
            casilla caja = new casilla(
                    "C:\\\\Users\\\\aaron\\\\OneDrive - Universitat de les Illes Balears\\\\Documents\\\\NetBeansProjects\\\\Practica1_SSII\\\\src\\\\practica1_ssii\\\\images\\\\ascensor_cerrado.jpg",
                    false);
            caja.setPreferredSize(new Dimension(alturaCaja, alturaCaja)); // Tamaño proporcional
            caja.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para visualizar la caja
            caja.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Añadir la caja al panel
            add(caja);
            cajas[i] = caja;

            if (i < this.numCasillas - 1) {
                add(Box.createVerticalStrut(2)); // Espaciador entre las cajas
            }
        }
        cajas[0].setBackground(new Color(175, 175, 30));
        cajas[0].repaint();
    }

    public void AbrirAscensor(int pos) {
        cajas[pos].PuertaAbierta();
        cajas[pos].revalidate();
        cajas[pos].repaint();
    }

    public void CerrarAscensor(int pos) {
        cajas[pos].PuertaCerrada();
        cajas[pos].revalidate();
        cajas[pos].repaint();
    }

    public void IndicarCasilla() {
        for (int i = 0; i < 5; i++) {
            cajas[i].ActivacionEstancia();
            cajas[i].revalidate();
            cajas[i].repaint();

            this.revalidate();
            this.repaint();
        }
    }

    public void MarcarCasilla(int num) {
        cajas[num].PisoLocated();
        cajas[num].revalidate();
        cajas[num].repaint();
    }

    public void DesmarcarCasilla(int num) {
        cajas[num].PisoDislocated();
        cajas[num].revalidate();
        cajas[num].repaint();
    }
}