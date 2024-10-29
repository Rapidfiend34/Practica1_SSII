package practica1_ssii;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ocupacion extends JPanel {
    private Image imagenEscalada;
    private boolean tipo; // True=panel de sentido, False=panel de destino
    private String num;
    private boolean orientacion; // True=subida, False=bajada

    public Ocupacion(boolean or) {
        this.orientacion = true;
        this.tipo = or;
        this.num = "0";
        this.setBackground(Color.DARK_GRAY); // Solo para hacer visible el panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(50, 50));

        ImageIcon imagenIcono = new ImageIcon(
                "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\bombilla.png"); // Cambia
                                                                                                                                                                          // por
                                                                                                                                                                          // la
                                                                                                                                                                          // ruta
                                                                                                                                                                          // de
                                                                                                                                                                          // tu
                                                                                                                                                                          // imagen
        imagenEscalada = imagenIcono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo
        if (imagenEscalada != null) {
            g.drawImage(imagenEscalada, 0, 0, getWidth(), getHeight(), this);
        }
        if (!tipo) {
            // Panel que indica el destino del ascensor
            g.setFont(new Font("Arial", Font.BOLD, 44)); // Definir la fuente
            g.setColor(Color.RED); // Cambiar el color del texto a rojo
            g.drawString(num, 14, 40);
        } else {
            // Panel que indica el sentido con el que se desplaza el ascensor
            if (this.orientacion) {
                g.drawImage(new ImageIcon(
                        "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\flecha_arriba.png")
                        .getImage(), 0, 5, getWidth(), getHeight() - 10, this);
            } else {
                g.drawImage(new ImageIcon(
                        "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\flecha_abajo.png")
                        .getImage(), 0, 0, getWidth(), getHeight(), this);

            }
        }
    }

    public void SentidoBajar() {
        // Cambio de sentido hacia abajo
        this.orientacion = false;
        this.repaint();
    }

    public void SentidoSubir() {
        // Cambio de sentido hacia arriba
        this.orientacion = true;
        this.repaint();
    }

    public void ActualizarDestino(int num) {
        // Actualizamos el valor del panel destino
        this.num = Integer.toString(num);
    }
}
