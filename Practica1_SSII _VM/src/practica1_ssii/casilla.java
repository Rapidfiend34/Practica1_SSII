package practica1_ssii;

import javax.swing.*;
import java.awt.*;

public class casilla extends JPanel {
    private Image fondo;
    private boolean estancia;
    private boolean persona;
    private Image imagenSobrepuesta;

    // Clase utilizada para representar las casillas interactivas del edificio
    public casilla(String pathImagen, boolean instancia) {
        this.estancia = instancia;
        this.imagenSobrepuesta = new ImageIcon(
                "C:\\\\Users\\\\aaron\\\\OneDrive - Universitat de les Illes Balears\\\\Documents\\\\NetBeansProjects\\\\Practica1_SSII\\\\src\\\\practica1_ssii\\\\images\\\\Persona.png")
                .getImage();
        this.persona = instancia;
        this.fondo = new ImageIcon(pathImagen).getImage();
    }

    public void PisoLocated() {
        // Estancia actual del ascensor en este piso
        this.estancia = true;
    }

    public void PisoDislocated() {
        // Se ha marchado de este piso
        this.estancia = false;
    }

    public void PuertaAbierta() {
        // Actualizamos el sprite de la puerta a abierta
        this.fondo = new ImageIcon(
                "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\ascensor_abierto.jpg")
                .getImage();
        this.repaint();
    }

    public void PuertaCerrada() {
        // Actualizamos el sprite de la puerta a cerrada
        this.fondo = new ImageIcon(
                "C:\\\\Users\\\\aaron\\\\OneDrive - Universitat de les Illes Balears\\\\Documents\\\\NetBeansProjects\\\\Practica1_SSII\\\\src\\\\practica1_ssii\\\\images\\\\ascensor_cerrado.jpg")
                .getImage();
        this.repaint();
    }

    public boolean isPersona() {
        // Comprobacion para saber si está ocupada la casilla
        return persona;
    }

    public void AddPersona() {
        // Añadir persona a la casilla del pasillo
        this.persona = true;
    }

    public void DeletePersona() {
        // Eliminar persona del pasillo
        this.persona = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Dibujar la imagen de fondo en el panel
        if (fondo != null) {
            g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        // Aplicar una capa semitransparente para oscurecer la imagen
        if (this.estancia) {
            g2d.setColor(new Color(128, 128, 0, 100)); // Color amarillo semitransparente
        } else {
            g2d.setColor(new Color(255, 255, 255, 100)); // Color blanco estandar semistransparente
        }
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Dibujar un rectángulo en todo el panel
        if (this.persona) {
            // Dibujar persona en la casilla del pasillo
            g2d.drawImage(imagenSobrepuesta, this.getWidth() / 4, this.getHeight() / 3, getWidth() / 3 + getWidth() / 3,
                    getHeight() / 3 + getHeight() / 3, this);
        }
        g2d.dispose();
    }

    public void ActivacionEstancia() {
        // Estancia actual del ascensor en este piso
        estancia = true;
        this.revalidate();
        this.repaint();
    }

}
