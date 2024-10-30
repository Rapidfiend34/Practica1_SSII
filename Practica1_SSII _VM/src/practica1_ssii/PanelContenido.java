package practica1_ssii;

import javax.swing.*;
import java.awt.*;

public class PanelContenido extends JPanel {
    private Image fondo;
    private PanelConCasillas casillas;
    private PanelPersonas pI;
    private PanelPersonas pD;
    private Ocupacion panelSentido;
    private Ocupacion panelDestino;
    private int dimension;
    private int personas;
    private JLabel GenteInside;

    public PanelContenido(String pathImagen, int dim, int persona) {
        this.dimension = dim;
        this.personas = persona;

        this.fondo = new ImageIcon(pathImagen).getImage();
        setLayout(new GridBagLayout());
        this.Inicializar();

    }

    public void Inicializar() {
        // Crear restricciones para el GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.casillas = new PanelConCasillas(this.dimension, this.personas);
        this.add(casillas, gbc);
        pI = new PanelPersonas(this.dimension, this.personas, false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        this.add(pI, gbc);
        pD = new PanelPersonas(this.dimension, this.personas, false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        this.add(pD, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 20, 20, 0);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        GenteInside = new JLabel("Gente adentro: 0");
        GenteInside.setForeground(new Color(255, 255, 255));
        this.add(GenteInside, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        panelDestino = new Ocupacion(false);
        this.add(panelDestino, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        panelSentido = new Ocupacion(true);
        this.add(panelSentido, gbc);
    }

    public void CambioSentido(boolean sentido) {
        // Cambiamos sentido del panel
        if (sentido) {
            panelSentido.SentidoSubir();
        } else {
            panelSentido.SentidoBajar();
        }
    }

    public void ActualizarDestino(int num) {
        // Actualizamos posicion ascensor en panel
        panelDestino.ActualizarDestino(num);
    }

    public void ActualizarContadorPersonas(int num) {
        this.GenteInside.setText("Gente adentro " + Integer.toString(num));
        this.GenteInside.revalidate();
        this.GenteInside.repaint();
    }

    public void CambiarPos(int num, int y) {
        casillas.DesmarcarCasilla(y);
        casillas.MarcarCasilla(num);
        this.revalidate();
        this.repaint();
    }

    public void AñadirPersona(int a) {
        this.pI.AñadirPersona(a);
    }

    public void AñadirPersonaSalida(int a) {
        this.pD.AñadirPersona(a);
    }

    public void EliminarPersona(int a) {
        this.pI.EliminarPersona(a);
    }

    public void EliminarPersonaSalida(int a) {
        this.pD.EliminarPersona(a);
    }

    public void AbrirAscensor(int pos) {
        casillas.AbrirAscensor(pos);
    }

    public void CerrarAscensor(int pos) {
        casillas.CerrarAscensor(pos);
    }

    public void ChangeDimension(int dim) {
        // cambiar dimension pisos
        this.dimension = dim;
        this.removeAll();
        this.Inicializar();
        this.revalidate();
        this.repaint();
    }

    public void ChangePersonDim(int dim) {
        // Cambiar dimsension personas-piso
        this.personas = dim;
        this.removeAll();
        this.Inicializar();
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen en el panel
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

}